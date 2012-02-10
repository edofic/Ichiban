package com.abstracttech.ichiban.views.textviews;

import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

public class DataSourceTextView extends TextView {

	public DataSourceTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setText("Editor data");
		else
			this.setText((Data.hasLocalData()?"local":"bluetooth")+" data"); //where is data coming from?
		
		super.onDraw(canvas);
	}

}
