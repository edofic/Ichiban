package com.abstracttech.ichiban.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.res.Resources;

public class Data {
	private static ArrayList<String> data=new ArrayList<String>();
	private static int index=0;

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
}
