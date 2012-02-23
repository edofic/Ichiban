package com.abstracttech.ichiban.data;

import com.abstracttech.ichiban.views.graphs.GraphType;

import android.content.SharedPreferences;

/**
 * this holds global preferences
 */
public class Preferences {

	private static SharedPreferences pref;

	/**
	 * Initialize this from an activity class
	 * @param Pref set this as a parameter PreferenceManager.getDefaultSharedPreferences(this)
	 */
	public static void Initalize(SharedPreferences Pref)
	{
		pref = Pref;
	}

	/**
	 * 
	 * @return vibrations settings. true if enabled false if disabled
	 */
	public static boolean getVibrate()
	{
		return pref.getBoolean("vibration", true);
	}

	/**
	 * 
	 * @return sound settings. true if enabled false if disabled
	 */
	public static boolean getSound()
	{
		return pref.getBoolean("sound", true);
	}

	/**
	 * 
	 * @return restart settings. true if data reset after button start is pressed, false if data is paused
	 */
	public static boolean getRestartData()
	{
		return !pref.getString("reset", "notRestart").equals("notRestart");
	}	

	/**
	 * 
	 * @return Graph type for the first graph
	 */
	public static GraphType getGraph1Type()
	{
		String type = pref.getString("graph1", "ACCELERATION");

		if(type.equalsIgnoreCase("SPEED"))
			return GraphType.SPEED;
		if(type.equalsIgnoreCase("PATH"))
			return GraphType.PATH;
		if(type.equalsIgnoreCase("GFORCE"))
			return GraphType.TOTAL_ACC;
		return GraphType.ACCELERATION;
	}

	/**
	 * 
	 * @return Graph type for the second graph
	 */
	public static GraphType getGraph2Type()
	{
		String type = pref.getString("graph2", "SPEED");
		if(type.equalsIgnoreCase("ACCELERATION"))
			return GraphType.ACCELERATION;
		if(type.equalsIgnoreCase("PATH"))
			return GraphType.PATH;
		if(type.equalsIgnoreCase("GFORCE"))
			return GraphType.TOTAL_ACC;
		return GraphType.SPEED;
	}

	/**
	 * 
	 * @return Graph type for the third graph
	 */
	public static GraphType getGraph3Type()
	{
		String type = pref.getString("graph3", "GFORCE");
		if(type.equalsIgnoreCase("ACCELERATION"))
			return GraphType.ACCELERATION;
		if(type.equalsIgnoreCase("SPEED"))
			return GraphType.SPEED;
		if(type.equalsIgnoreCase("PATH"))
			return GraphType.PATH;
		return GraphType.TOTAL_ACC;
	}
}
