package com.abstracttech.ichiban.views.time;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;

public class Started extends TextView {
	private long value;

	public Started(Context context, AttributeSet attrs) {
		super(context, attrs);
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("never started");
		else
		{
			long d = Data.getStarted(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(DateFormat.format("hh:mmm:ss", d));
			}
		}	
		super.onDraw(canvas);
	}

}
