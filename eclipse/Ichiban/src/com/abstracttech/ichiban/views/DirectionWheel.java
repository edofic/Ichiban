package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

public class DirectionWheel extends ImageView implements UpdatableView{
	public DirectionWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setImageResource(R.drawable.volan);
		
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		canvas.save();
	     canvas.rotate(Data.getTurnRatioPercentage() * 50,this.getWidth()/2f,this.getHeight()/2f);
	     super.onDraw(canvas);
	     canvas.restore();
	}

	@Override
	public void update() {
		this.postInvalidate();		
	}
}