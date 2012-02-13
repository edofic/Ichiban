package com.abstracttech.ichiban.activities;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.BluetoothChatService;
import com.abstracttech.ichiban.data.BluetoothEx;
import com.abstracttech.ichiban.data.Data;
import com.abstracttech.ichiban.views.PowerButton;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class IchibanActivity extends Activity {
	private BluetoothEx bt=new BluetoothEx();

	public static final int _UPDATE_INTERVAL = 300;
	private boolean running=false;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setListeners();

		bt.onCreate(this);
		
		running=false;
	}

	private void setListeners(){
		((PowerButton)findViewById(R.id.power_button)).setExternalOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(running==false)
				{
					startCar(v);
					running=true;
				}
				else
				{
					stopCar(v);
					running=false;
				}
			}});
	}

	public void test(View v) {
		Toast.makeText(this, "use IchibanActivity's test() to quickly test something ;)", Toast.LENGTH_LONG).show();
	}

	/**
	 * starts autoupdating
	 * wheather is it over BT or local, depending on loaded data
	 * in real application it would also send start command to the car
	 * @param v view that called it; for use with buttons, not used
	 */
	public void startCar(View v) {
		if(Data.hasLocalData())
			Data.startAutoupdate(_UPDATE_INTERVAL);
		else
			bt.start(v);
	}

	/**
	 * stops autoupdating
	 * wheather is it over BT or local
	 * in real application it would also send stop command to the car
	 * @param v view that called it; for use with buttons, not used
	 */
	public void stopCar(View v) {
		bt.stop(v);
		Data.stopAutoupdate();
	}


	@Override
	protected void onStart() {
		super.onStart();

		bt.onStart();
	}

	@Override
	public synchronized void onResume() {
		super.onResume();
		bt.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
		stopCar(null);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		bt.onDestroy();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		bt.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		bt.handleBTmenu(item);
		return false;
	}
}