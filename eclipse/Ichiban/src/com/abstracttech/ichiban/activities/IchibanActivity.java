package com.abstracttech.ichiban.activities;

import java.util.ArrayList;
import java.util.List;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.BluetoothEx;
import com.abstracttech.ichiban.data.Data;
import com.abstracttech.ichiban.data.MainPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class IchibanActivity extends Activity {
	private BluetoothEx bt=new BluetoothEx();
	private static List<View> clients = new ArrayList<View>();

	public static final int _UPDATE_INTERVAL = 100;
	private static boolean running=false;

	public static boolean isRunning(){
		return running;
	}

	public static void subscribe(View v){
		clients.add(v);
	}

	private void notifyClients()
	{
		for(View v : clients)
			v.postInvalidate();
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pager);

		//load pages
		MainPagerAdapter adapter = new MainPagerAdapter();
		ViewPager myPager = (ViewPager) findViewById(R.id.mypager);
		myPager.setAdapter(adapter);
		myPager.setCurrentItem(1);

		bt.onCreate(this);

		running=false;
	}

	public void powerButtonClick(View v){

		try
		{
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
			notifyClients();
		}
		catch(Exception e)
		{
			Toast.makeText(this, R.string.not_connected, Toast.LENGTH_LONG).show();
			Log.e(getPackageName(), "power button clicked and something gone wrong", e);
		}
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
