package com.abstracttech.ichiban.views.time;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

import com.abstracttech.ichiban.data.Data;

/**
 * text view for displaying gui update interval
 */
public class UpdateInterval extends TextView {
	private int rate;

	public UpdateInterval(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("100ms");
		else if (rate != Data.getRate())
			this.setText((rate = Data.getRate()) + "ms");

		super.onDraw(canvas);
	}

}
