package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.data.Data;

import android.os.SystemClock;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class AccNowTextView extends TextView {
	private float value;
	
	public AccNowTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("53.567");
		else
		{
			float d = Data.accData.getAcc(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(String.format("%.2f", d));
			}
		}
		
		super.onDraw(canvas);
	}

}
