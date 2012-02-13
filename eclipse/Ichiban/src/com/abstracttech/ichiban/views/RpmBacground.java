package com.abstracttech.ichiban.views;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RpmBacground extends ImageView {

	public RpmBacground(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setImageResource(R.drawable.app_bg);//default values
		this.setAlpha(0);// set to invisible, because start RPM is 0
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
			this.setAlpha(0.5f);
		else
			this.setAlpha((int)(Data.getRpmPercentage() * 255));//set Alpha by RPM data
		
		super.onDraw(canvas);
	}

}
