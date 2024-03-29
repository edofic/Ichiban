package com.abstracttech.ichiban.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.abstracttech.ichiban.R;
import com.abstracttech.ichiban.data.Data;

/**
 *  vertical g-meter for displaying z-axis g-force
 */
public class Gbar extends ImageView {

	private Bitmap gSquare;
	private float data;
	private Paint p;
	
	public Gbar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		this.setImageResource(R.drawable.gbar_ozadje);
		gSquare = BitmapFactory.decodeResource(getResources(), R.drawable.gbar_kvadratek);
		p = new Paint();
		
		Data.subscribe(this);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		if(isInEditMode()) //dummy data for editor
		{}
		else if(data != Data.getZPercentage())
		{
			p.setAlpha(255);
			data = Data.getZPercentage();
			 
			for (int i = 0; i < Math.abs(data * 6); i++)
			{
				if (i >= Math.abs(data * 6) - 1)
					p.setAlpha((int)((Math.abs(data) *6 -i)*255));
				if(data > 0)
					canvas.drawBitmap(gSquare, this.getWidth() / 2 - gSquare.getWidth() / 2, (gSquare.getHeight() + 5) * 5  -  i * (gSquare.getHeight() + 5) + 20, p);
				else
					canvas.drawBitmap(gSquare, this.getWidth() / 2 - gSquare.getWidth() / 2, this.getHeight() - (gSquare.getHeight() + 5) * 6  +  i * (gSquare.getHeight() + 5) - 15, p);
			}
		}
		
		//this.invalidate(); //let's do that again
	}
}