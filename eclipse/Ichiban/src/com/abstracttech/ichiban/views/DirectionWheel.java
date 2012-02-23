package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

/**
 * steering wheel that rotates with respect to turn ratio
 */
public class DirectionWheel extends ImageView {
	public DirectionWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setImageResource(R.drawable.volan);
		
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		//draw rotated image set in xml
		canvas.save();
	     canvas.rotate(-Data.getTurnRatioPercentage() * 30,this.getWidth()/2f,this.getHeight()/2f);
	     super.onDraw(canvas);
	     canvas.restore();

	}
}