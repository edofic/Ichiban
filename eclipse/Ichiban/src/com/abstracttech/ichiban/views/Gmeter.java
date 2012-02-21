package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.activities.IchibanActivity;
import com.abstracttech.ichiban.data.Data;

/**
 * formula-1 style g-meter
 */
public class Gmeter extends ImageView {
	private Bitmap dot;
	private float dotHeight, dotWidth;
	private float lastX,lastY;
	private long lastUpdate;

	private final float inter = IchibanActivity._UPDATE_INTERVAL;

	public Gmeter(Context context, AttributeSet attrs) {
		super(context, attrs);

		if(isInEditMode())
			return;

		dot = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.g_dot);
		dotWidth = dot.getWidth();
		dotHeight = dot.getHeight();

		lastX=newX();
		lastY=newY();

	}

	@Override
	protected void onDraw(Canvas canvas){
		
		super.onDraw(canvas);
		
		if(isInEditMode())
			return;

		float cx = newX();
		float cy = newY();
		long nt=System.currentTimeMillis(); //current time

		//interpolate data
		canvas.drawBitmap(dot, 
				lastX + (cx - lastX)*(float)(nt-lastUpdate)/inter, 
				lastY + (cy - lastY)*(float)(nt-lastUpdate)/inter, null);

		//update data on select time interval
		if((nt-lastUpdate)>=inter)
		{
			lastX=cx;
			lastY=cy;
			lastUpdate=nt;
		}

		//redraw next frame
		this.invalidate();
	}

	private float newX()
	{
		return (float)(getWidth()*(1+Data.getX())-dotWidth)/2f; //current x
	}

	private float newY()
	{
		return (float)(getHeight()*(1+Data.getY())-dotHeight)/2f; //current x
	}

}
