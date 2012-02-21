package com.abstracttech.ichiban.views.time;

import com.abstracttech.ichiban.activities.IchibanActivity;
import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;

public class Running extends TextView {
	private long value;

	public Running(Context context, AttributeSet attrs) {
		super(context, attrs);
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("never started");
		else if(IchibanActivity.isRunning())
		{
			long d = Data.getRunningTime(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(""+DateFormat.format("mm:ss", d)); //DateFormat.format("hh:mmm:ss", d*));
			}
		}	
		super.onDraw(canvas);
	}

}
