package com.abstracttech.ichiban.views.speed;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class Max extends TextView {
	private float value;

	public Max(Context context, AttributeSet attrs) {
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
			float d = Data.speedData.getMax(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(String.format("%.2f", d));
			}
		}
		
		super.onDraw(canvas);
	}

}
