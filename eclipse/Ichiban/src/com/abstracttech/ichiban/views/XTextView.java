package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class XTextView extends TextView {

	public XTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("X: 0.000000");
		else
			this.setText("X: " + Float.toString(Data.getX())); //data from datasource
		
		super.onDraw(canvas);
	}

}