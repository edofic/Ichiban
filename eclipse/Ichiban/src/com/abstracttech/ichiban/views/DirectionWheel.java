package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

public class DirectionWheel extends ImageView {
	
	private Matrix matrix;									//rotation matrix
	
	public DirectionWheel(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.matrix = new Matrix();
		this.setImageResource(R.drawable.rpm_bg);	
		this.setScaleType(ScaleType.MATRIX);		//Uses matrix to change image 
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		matrix.reset();		//reseting matrix
		
		if(isInEditMode()) //dummy data for editor
			matrix.setRotate((float) (120 * 0.5f - 60), (this.getLeft() + this.getRight()) / 2  , (this.getTop()+ this.getBottom()) / 2);
		else
			matrix.setRotate((float) (120 * Data.getTurnRatioPercentage() - 60), (this.getLeft() + this.getRight()) / 2  , (this.getTop()+ this.getBottom()) / 2);
		
		this.setImageMatrix(matrix);	//set new matrix
		
		super.onDraw(canvas);
	}
}