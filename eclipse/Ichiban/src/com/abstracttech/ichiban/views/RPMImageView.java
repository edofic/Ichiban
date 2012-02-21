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

/**
 * image view for displaying current speed with dashboard-style speedometer
 */
public class RPMImageView extends ImageView {

	private Bitmap pointer;									//pointer image, dial
	private Bitmap rpm_top;
	private Matrix matrix;									//rotation matrix

	public RPMImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

		this.setImageResource(R.drawable.rpm_bg);													//Set default image
		this.pointer = BitmapFactory.decodeResource(getResources(), R.drawable.rpm_dial);				//Pointer image
		this.rpm_top = BitmapFactory.decodeResource(getResources(), R.drawable.rpm_top);
		this.matrix = new Matrix();

		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		matrix.reset();

		if(isInEditMode()) //dummy data for editor
		{
			matrix.setRotate(0.2f * 220.f -110, pointer.getWidth() / 2 , pointer.getHeight() - 10);		
			matrix.postTranslate(this.getWidth() / 2 - pointer.getWidth() / 2 , this.getHeight() / 2 - pointer.getHeight() + 10);		// Rotates pointer
		}
		else
		{
			matrix.setRotate((float) (Data.getRpmPercentage() * 220.f -110),pointer.getWidth() / 2 , pointer.getHeight() - 10);			//0 -> up, + -> clockwise, - -> anticlockwise
			matrix.postTranslate(this.getWidth() / 2 - pointer.getWidth() / 2 , this.getHeight() / 2 - pointer.getHeight() + 10);
		}

		if(!isInEditMode()){
			canvas.drawBitmap(pointer, matrix, null);
			canvas.drawBitmap(rpm_top, this.getWidth()/2 - rpm_top.getWidth() / 2, this.getHeight()/2 - rpm_top.getHeight() / 2, null);
		}
	}
}