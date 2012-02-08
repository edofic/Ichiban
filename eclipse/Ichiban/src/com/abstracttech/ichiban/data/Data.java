package com.abstracttech.ichiban.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.abstracttech.ichiban.R;

import android.content.res.Resources;
import android.util.Log;

/**
 * @author andraz
 *
 */
public class Data {
	private static ArrayList<String> data=null;
	private static int index=0;
	
	private static Timer timer;
	private static boolean isAutoupdating=false;

	private static double x,y,z;
	private static int rpm, turnRatio;


	public static void loadCSV(Resources res) throws IOException {
		InputStream inputStream = res.openRawResource(R.raw.data);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		data=new ArrayList<String>();
		
		String line;
		while ((line = reader.readLine()) != null) {
			data.add(line);
		}
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
	public static void update()
	{
		try {
			String line=null;

			//if there is local data, load it, else use bluetooth
			if(data!=null)
				line=getNextLine();
			else
				; //todo: data from bluetooth

			String[] values=line.split(",");	
			x=Double.parseDouble(values[0]);
			y=Double.parseDouble(values[1]);
			z=Double.parseDouble(values[2]); 
			rpm=Integer.parseInt(values[3]);
			turnRatio=Integer.parseInt(values[4]);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ICHIBAN", "something wrong with data");
		}
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
		timer.cancel();
		isAutoupdating=false;
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

	public static int getRpm() {
		return rpm;
	}

	public static int getTurnRatio() {
		return turnRatio;
	}
	
	public static boolean isAutoupdating(){
		return isAutoupdating;
	}
}
