package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.os.SystemClock;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class AaccelerationTextView extends TextView {

	private double preRpm = 0;
	private long preTime;
	
	public AaccelerationTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("Aceleration: 3.567");
		else
		{
			this.setText("Aceleration: " + Double.toString((Data.getRpm() - preRpm) / (double)(SystemClock.currentThreadTimeMillis() - preTime) )); //acceleration data
			this.preRpm = Data.getRpm();
			this.preTime = SystemClock.currentThreadTimeMillis();
		}
		
		super.onDraw(canvas);
	}

}
