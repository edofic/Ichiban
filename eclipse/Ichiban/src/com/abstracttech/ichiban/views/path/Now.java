package com.abstracttech.ichiban.views.path;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.abstracttech.ichiban.data.Data;

/**
 * text view for displaying path
 */
public class Now extends TextView {

	//local buffer
	private double value;

	public Now(Context context, AttributeSet attrs) {
		super(context, attrs);
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("53.56");
		else
		{
			//on change of data set text
			double d = Data.pathData.get(); //data from datasource
			if(d!=value)
			{
				value=d;
				if(d<10000)
				this.setText(String.format("%.3f", d));
				else if(d<100000)
				this.setText(String.format("%.2f", d));
				else
				this.setText(String.format("%.1f", d));
				
			}
		}
		
		super.onDraw(canvas);
	}
}
