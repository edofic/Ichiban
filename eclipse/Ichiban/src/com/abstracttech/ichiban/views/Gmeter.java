package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

/**
 * formula-1 style g-meter
 */
public class Gmeter extends ImageView {

	private Bitmap dot;
	private float dotHeight, dotWidth;
	private float lastX,lastY;

	public Gmeter(Context context, AttributeSet attrs) {
		super(context, attrs);

		if(isInEditMode())
			return;

		dot = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.g_dot);
		dotWidth = dot.getWidth();
		dotHeight = dot.getHeight();

		lastX=newX();
		lastY=newY();

		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas){

		super.onDraw(canvas);

		if(isInEditMode())
			return;

		if(lastX != Data.getX() || lastY != Data.getY())
		{
			lastX = newX();
			lastY = newY();

			//interpolate data
			canvas.drawBitmap(dot , lastX , lastY , null);
		}
		//redraw next frame
		//this.invalidate();
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
