package com.abstracttech.ichiban.data;

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
}
