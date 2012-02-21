package com.abstracttech.ichiban.views.speed;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.abstracttech.ichiban.data.Data;

/**
 * text view for displaying current speed on central screen
 */
public class NowMain extends TextView {

	private float value;

	public NowMain(Context context, AttributeSet attrs) {
		super(context, attrs);
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("0");
		else
		{
			float d = Data.speedData.get(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(String.format("%.0f", d));
			}
		}
		
		super.onDraw(canvas);
	}
}
