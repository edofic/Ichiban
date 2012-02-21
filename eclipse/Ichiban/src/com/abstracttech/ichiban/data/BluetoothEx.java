package com.abstracttech.ichiban.data;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.activities.DeviceListActivity;
import com.abstracttech.ichiban.activities.IchibanActivity;
import com.abstracttech.ichiban.activities.Preferences;

public class BluetoothEx {
	public static final int _UPDATE_INTERVAL = IchibanActivity._UPDATE_INTERVAL;
	private static Timer timer=null; //for sending scheduled bt querries. class Data handles responses
	private static boolean bt_enabled=false;
	private Activity parrent;

	/** Called when the activity is first created. */
	public void onCreate(Activity starter) {
		parrent = starter;
		// Get local Bluetooth adapter
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


		/* we dont necceserily need bt

		// If the adapter is null, then Bluetooth is not supported
		if (mBluetoothAdapter == null) {
			Toast.makeText(parrent, "Bluetooth is not available", Toast.LENGTH_LONG).show();
			return;
		}
		 */
	}


	public void test(View v) {
		Toast.makeText(parrent, "use IchibanActivity's test() to quickly test something ;)", Toast.LENGTH_LONG).show();
	}

	/**
	 * starts autoupdating
	 * wheather is it over BT or local, depending on loaded data
	 * in real application it would also send start command to the car
	 * @param v view that called it; for use with buttons, not used
	 */
	public void start(View v) {
		if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
			Toast.makeText(parrent, R.string.not_connected, Toast.LENGTH_SHORT).show();
		}
		else
		{
			timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					sendMessage("?"); 
				}
			}, 0, _UPDATE_INTERVAL);
		}
	}

	/**
	 * stops autoupdating
	 * wheather is it over BT or local
	 * in real application it would also send stop command to the car
	 * @param v view that called it; for use with buttons, not used
	 */
	public void stop(View v) {
		if(timer!=null)
			timer.cancel();
		timer=null;
	}


	//BELOW THIS IS CODE FOR BLUETOOTH
	// Debugging
	private static final String TAG = "BluetoothChat";
	private static final boolean D = true;

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Key names received from the BluetoothChatService Handler
	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	// Intent request codes
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private static final int REQUEST_ENABLE_BT = 2;

	// Name of the connected device
	private String mConnectedDeviceName = null;
	// Local Bluetooth adapter
	private BluetoothAdapter mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothChatService mChatService = null;

	public void onStart() {

		//if have used bt before
		if (bt_enabled) {
			// If BT is not on, request that it be enabled.
			// setupChat() will then be called during onActivityResult
			if (!mBluetoothAdapter.isEnabled()) {
				Intent enableIntent = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				parrent.startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
				// Otherwise, setup the chat session
			} else {
				if (mChatService == null)
					setupChat();
			}
		}
	}

	private void setupChat() {
		// Initialize the BluetoothChatService to perform bluetooth connections
		mChatService = new BluetoothChatService(parrent, mHandler);
	}


	public synchronized void onResume() {
		if(D) Log.e(TAG, "+ ON RESUME +");

		// Performing this check in onResume() covers the case in which BT was
		// not enabled during onStart(), so we were paused to enable it...
		// onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
		if (mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't started already
			if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// Start the Bluetooth chat services
				mChatService.start();
			}
		}
	}

	private void sendMessage(String message) {
		// Check that we're actually connected before trying anything
		if (mChatService.getState() != BluetoothChatService.STATE_CONNECTED) {
			Toast.makeText(parrent, R.string.not_connected, Toast.LENGTH_SHORT).show();
			return;
		}

		// Check that there's actually something to send
		if (message.length() > 0) {
			// Get the message bytes and tell the BluetoothChatService to write
			byte[] send = message.getBytes();
			mChatService.write(send);
		}
	}

	// The Handler that gets information back from the BluetoothChatService
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case MESSAGE_STATE_CHANGE:
				if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
				switch (msg.arg1) {
				case BluetoothChatService.STATE_CONNECTED:
					Toast.makeText(parrent, R.string.connect, Toast.LENGTH_SHORT);
					break;
				case BluetoothChatService.STATE_CONNECTING:
					Toast.makeText(parrent, "connecting", Toast.LENGTH_SHORT);
					break;
				case BluetoothChatService.STATE_LISTEN:
				case BluetoothChatService.STATE_NONE:
					Toast.makeText(parrent, R.string.not_connected, Toast.LENGTH_SHORT);
					break;
				}
				break;
			case MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				//do nothing
				break;
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				String readMessage = new String(readBuf, 0, msg.arg1);
				Data.btUpdate(readMessage);
				break;
			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				Toast.makeText(parrent, "Connected to "
						+ mConnectedDeviceName, Toast.LENGTH_SHORT).show();
				break;
			case MESSAGE_TOAST:
				Toast.makeText(parrent, msg.getData().getString(TOAST),
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(D) Log.d(TAG, "onActivityResult " + resultCode);
		switch (requestCode) {
		case REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address
				String address = data.getExtras()
						.getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
				// Get the BLuetoothDevice object
				BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
				// Attempt to connect to the device
				mChatService.connect(device);
			}
			break;
		case REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Initialize the BluetoothChatService to perform bluetooth connections
				mChatService = new BluetoothChatService(parrent, mHandler);

			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				Toast.makeText(parrent, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();

			}
		}
	}

	public void onDestroy() {
		// Stop the Bluetooth chat services
		if (mChatService != null) mChatService.stop();
		if(D) Log.e(TAG, "--- ON DESTROY ---");
	}

	public void handleBTmenu(MenuItem item)
	{
		switch (item.getItemId()) {
		case R.id.scan:
			if (mBluetoothAdapter == null) {
				Toast.makeText(parrent, "Bluetooth is not available", Toast.LENGTH_LONG).show();
				return;
			}
			bt_enabled=true;
			onStart();
			// Launch the DeviceListActivity to see devices and do scan
			Intent serverIntent = new Intent(parrent, DeviceListActivity.class);
			parrent.startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
			Data.destroyData();
		case R.id.loadCSV:
			//load data from local csv file
			try {
				Data.loadCSV(parrent.getResources());
				bt_enabled=false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		case R.id.loadDenseCSV:
			//load data from local csv file
			try {
				Data.loadDenseCSV(parrent.getResources());
				bt_enabled=false;
			} catch (IOException e) {
				e.printStackTrace();
			}		
		case R.id.preferences:
			//run preferences
			Intent serverIntent1 = new Intent(parrent, Preferences.class);
			parrent.startActivity(serverIntent1);
		}
	}
}
