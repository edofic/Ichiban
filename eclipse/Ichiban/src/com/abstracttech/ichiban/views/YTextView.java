package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class YTextView extends TextView {

	public YTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("Y: -0.146818");
		else
			this.setText("Y: " + Float.toString(Data.getY())); //data from datasource
		
		super.onDraw(canvas);
	}

}
