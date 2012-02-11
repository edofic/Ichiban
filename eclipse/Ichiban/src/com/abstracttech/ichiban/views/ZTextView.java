package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class ZTextView extends TextView {

	public ZTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("Z: 0.35337");
		else
			this.setText("Z: " + Double.toString(Data.getZ())); //data from datasource
		
		super.onDraw(canvas);
	}

}
