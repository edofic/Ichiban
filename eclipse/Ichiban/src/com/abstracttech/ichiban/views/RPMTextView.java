package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class RPMTextView extends TextView {

	public RPMTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("rpm: 53.567");
		else
			this.setText("rpm: " + Double.toString(Data.getRpm())); //data from datasource
		
		super.onDraw(canvas);
	}

}
