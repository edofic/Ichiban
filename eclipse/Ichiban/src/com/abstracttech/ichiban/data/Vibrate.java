package com.abstracttech.ichiban.data;

import android.os.Vibrator;

public class Vibrate{
	
	private Vibrator vibrator;
	private float preX, preY, preZ;
	private final int milliseconds = 60;
	private final float pogoj = 0.4f;
	
	public Vibrate(Vibrator v)
	{
		vibrator = v;
	}
	
	/**
	 * 
	 * @param ms = milliseconds
	 */
	public void vibrate(long ms)
	{
		vibrator.vibrate(milliseconds);
	}

	public void update()
	{
		if(Math.abs(preX - Data.getX()) + Math.abs(preY - Data.getY()) + Math.abs(preZ - Data.getZ()) > pogoj)
			vibrator.vibrate(milliseconds);
		
		preX = Data.getX();
		preY = Data.getY();
		preZ = Data.getZ();
	}
}