package com.abstracttech.ichiban.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.abstracttech.ichiban.R;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

/**
 * @author andraz
 *
 */
public class Data {
	private static ArrayList<String> data=null;
	private static int index=0;
	private static int rate;

	private static Timer timer;
	private static boolean isAutoupdating=false;
	private static List<View> clients = new ArrayList<View>();

	private static double x,y,z, rpm, turnRatio;
	private static double locX, locY, locZ, locRpm, locTurn;

	private static String btLine=null;


	public static void loadCSV(Resources res) throws IOException {
		InputStream inputStream = res.openRawResource(R.raw.data);
		rate=300;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		data=new ArrayList<String>();

		String line;
		while ((line = reader.readLine()) != null) {
			data.add(line);
		}
	}
	
	public static void loadDenseCSV(Resources res) throws IOException {
		InputStream inputStream = res.openRawResource(R.raw.data35);
		rate=35;
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		data=new ArrayList<String>();

		String line;
		while ((line = reader.readLine()) != null) {
			data.add(line);
		}
	}
	
	public static void destroyData()
	{
		data=null;
	}
	
	public static boolean hasLocalData()
	{
		return data!=null;
	}

	private static String getNextLine()
	{
		int size=data.size();
		if(size==0)
			return null;
		int retIndex=index;
		if((index+1)<size)
			index++;
		else
			index=0;
		return data.get(retIndex);
	}

	/**
	 * load next line of data
	 */
	private static void updateInternal()
	{
		try {
			String line=null;

			//if there is local data, load it, else use bluetooth
			if(data!=null)
				line=getNextLine();
			else
				line=btLine;

			String[] values=line.split(",");	
			locX=Double.parseDouble(values[0]);
			locY=Double.parseDouble(values[1]);
			locZ=Double.parseDouble(values[2]); 
			locRpm=Double.parseDouble(values[3]);
			locTurn=Double.parseDouble(values[4]);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ICHIBAN", "something wrong with data");
		}
	}
	
	public static void update()
	{
		x=locX;
		y=locY;
		z=locZ;
		rpm=locRpm;
		turnRatio=locTurn;
		
		//notify clients
		for(View v : clients)
			v.postInvalidate();
	}

	public static void btUpdate(String line)
	{
		btLine=line;
		updateInternal();
		update();
	}

	/**
	 * automaticaly update data in specified period
	 * @param interval in miliseconds
	 */
	public static void startAutoupdate(int period)
	{
		if(isAutoupdating)
			return;

		timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				updateInternal(); 
			}
		}, 0, rate);

		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				update(); 
			}
		}, 0, period);
		isAutoupdating=true;
	}

	/** stops automatic self-updates
	 * 
	 */
	public static void stopAutoupdate()
	{
		if(timer!=null)
			timer.cancel();
		isAutoupdating=false;
	}
	
	public static void subscribe(View v){
		clients.add(v);
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}

	public static double getZ() {
		return z;
	}

	public static double getRpm() {
		return rpm;
	}

	public static double getTurnRatio() {
		return turnRatio;
	}
	
	public static double getXPercentage() {
		return x;
	}

	public static double getYPercentage() {
		return y;
	}

	public static double getZPercentage() {
		return z;
	}

	public static double getRpmPercentage() {
		return rpm / 100.f;
	}

	public static double getTurnRatioPercentage() {
		return turnRatio / 10.f;
	}

	public static boolean isAutoupdating(){
		return isAutoupdating;
	}
}
