package com.abstracttech.ichiban.views.path;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class Now extends TextView {

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
			long d = Data.pathData.getPath(); //data from datasource
			if(d!=value)
			{
				value=d;
				this.setText(String.format("%s", d));
			}
		}
		
		super.onDraw(canvas);
	}
}
