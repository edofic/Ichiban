package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.graphics.BitmapFactory;

public class Gmeter extends ImageView {
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
		super.onDraw(canvas);
		
		canvas.drawBitmap(dot, 
				(float)(getWidth()*(1+Data.getX())-dotWidth)/2f, 
				(float)(getHeight()*(1+Data.getY())-dotHeight)/2f, null);
		
		this.invalidate();
	}

}
