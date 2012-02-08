package com.abstracttech.ichiban.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.abstracttech.ichiban.R;

import android.content.res.Resources;

/**
 * @author andraz
 *
 */
/**
 * @author andraz
 *
 */
/**
 * @author andraz
 *
 */
public class Data {
	private static ArrayList<String> data=null;
	private static int index=0;
	
	private static double x,y,z;
	private static int rpm, turnRatio;

	
	public static void loadCSV(Resources res) throws IOException {
	     InputStream inputStream = res.openRawResource(R.raw.data);
	     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    
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
	 * this is for testing ui only
	 * @param i new x value
	 */
	public static void update(double i)
	{
		x=i;	
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
}
