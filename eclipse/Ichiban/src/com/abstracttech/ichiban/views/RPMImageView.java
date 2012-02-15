package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

public class RPMImageView extends ImageView {
	
	private Bitmap pointer;									//pointer image
	private Matrix matrix;									//rotation matrix
	
	public RPMImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setImageResource(R.drawable.rpm_bg);													//Set default image
		this.pointer = BitmapFactory.decodeResource(getResources(), R.drawable.rpm_dial);				//Pointer image
		this.matrix = new Matrix();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		if(isInEditMode()) //dummy data for editor
		{
			matrix.setRotate((float) (0.2f * 219.f -140),pointer.getWidth() / 2 , pointer.getHeight() - 10);		
			matrix.postTranslate(this.getWidth() / 2 - pointer.getWidth() / 2 , this.getHeight() / 2 - pointer.getHeight() + 10);		// Rotates pointer
        }
		else
		{
			matrix.reset();																												//reseting matrix			
																				
			matrix.setRotate((float) (Data.getRpmPercentage() * 219.f -140),pointer.getWidth() / 2 , pointer.getHeight() - 10);			//0 -> up, + -> clockwise, - -> anticlockwise
			matrix.postTranslate(this.getWidth() / 2 - pointer.getWidth() / 2 , this.getHeight() / 2 - pointer.getHeight() + 10);
		}
		canvas.drawBitmap(pointer, matrix, null);
	}
}