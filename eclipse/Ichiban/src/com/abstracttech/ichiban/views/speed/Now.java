package com.abstracttech.ichiban.views.speed;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.abstracttech.ichiban.data.Data;

/**
 * text view for displaying current speed on left screen
 */
public class Now extends TextView {

	private float value;

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
			float d = Data.speedData.get(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(String.format("%.2f", d));
			}
		}
		
		super.onDraw(canvas);
	}
}
