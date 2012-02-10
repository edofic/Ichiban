package com.abstracttech.ichiban.views.textviews;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class WTVTextView extends TextView {

	public WTVTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("turn ratio: 7.135");
		else
			this.setText("turn ratio: " + Double.toString(Data.getTurnRatio())); //data from datasource
		
		super.onDraw(canvas);
	}

}
