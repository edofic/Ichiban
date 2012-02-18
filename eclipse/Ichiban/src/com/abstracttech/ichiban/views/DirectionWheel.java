package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.activities.IchibanActivity;
import com.abstracttech.ichiban.data.Data;

public class DirectionWheel extends ImageView {
	
	private Matrix matrix;									//rotation matrix
	private float preData = 0;
	private float rotation = 0;
	
	
	public DirectionWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.matrix = new Matrix();
		this.setImageResource(R.drawable.volan);	
		//this.setScaleType(ScaleType.MATRIX);		//Uses matrix to change image 
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		matrix.reset();		//reseting matrix
		
		if(isInEditMode()) //dummy data for editor
			matrix.setRotate((float) (120 * 0.5f - 80), this.getWidth() / 2  , this.getHeight() / 2);
		else
		{				
			rotation = ((160 * Data.getTurnRatioPercentage() - 80) - rotation) / 2;//(preRot -  120 * Data.getTurnRatioPercentage() - 60) / IchibanActivity._UPDATE_INTERVAL;
			matrix.setRotate(rotation, this.getWidth() / 2  , this.getHeight() / 2);
		}
		
		this.setImageMatrix(matrix);	//set new matrix
		
		super.onDraw(canvas);
	}
}