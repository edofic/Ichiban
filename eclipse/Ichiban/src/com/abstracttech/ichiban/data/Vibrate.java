package com.abstracttech.ichiban.data;

import android.os.Vibrator;

/**
 * vibration wrapper
 */
public class Vibrate{

	private Vibrator vibrator;
	private float preX, preY, preZ;
	private final int milliseconds = 60;
	private final float pogoj = 0.4f;
	private boolean toVibrate = true;

	public Vibrate(Vibrator v)
	{
		vibrator = v;
	}

	/**
	 * start vibrator
	 * @param ms time in milliseconds
	 */
	public void vibrate(long ms)
	{
		if(toVibrate)
			vibrator.vibrate(milliseconds);
	}

	/**
	 * update function
	 * handles big changes and vibrates automaticaly
	 */
	public void update()
	{
		if(toVibrate)
		{
			if(Math.abs(preX - Data.getX()) + Math.abs(preY - Data.getY()) + Math.abs(preZ - Data.getZ()) > pogoj)
				vibrator.vibrate(milliseconds);

			preX = Data.getX();
			preY = Data.getY();
			preZ = Data.getZ();
		}
	}

	/**
	 * set this to false to disable vibration or to true to enable them
	 * @param vibrate
	 */
	public void setVibrate(boolean vibrate)
	{
		toVibrate = vibrate;
	}
}