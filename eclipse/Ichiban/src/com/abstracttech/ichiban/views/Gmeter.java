package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.BitmapFactory;

public class Gmeter extends View {
	private Bitmap dot;
	private float dotHeight, dotWidth;

	public Gmeter(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		dot = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.g_dot);
		dotWidth = dot.getWidth();
		dotHeight = dot.getHeight();
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		
		
		float dataX =(float) Data.getX();
	
		float x = (getWidth()-dotWidth)/2f;
		float y = (getHeight()-dotHeight)/2f;
		
		x+= dataX * getWidth() /2f;
		
		canvas.drawBitmap(dot, x, y, null);
		
		super.onDraw(canvas);
	}

}
