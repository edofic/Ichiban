package com.abstracttech.ichiban.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.res.Resources;

public class Data {
	private static ArrayList<String> data=new ArrayList<String>();
	private static int index=0;
	private static Timer timer;
	private static boolean isAutoupdating;
	private static String current=null;

	public static void loadCSV(Resources res) throws IOException {
	     InputStream inputStream = res.openRawResource(R.raw.data);
	     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    
	    String line;
	    while ((line = reader.readLine()) != null) {
	      	data.add(line);
	    }
	}

	public static String getNextLine()
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
	
	public static String getCurrentLine()
	{
		return current;
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
				current=getNextLine();
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
}
