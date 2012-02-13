package com.abstracttech.ichiban.views;

import java.lang.reflect.Method;

import com.abstracttech.ichiban.activities.IchibanActivity;
import com.abstracttech.ichiban.data.Data;

import java.lang.Object;
import java.util.Stack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;;

public class Graph extends View {
	
	private Method graphDataMethod;
	private Stack data;
	private long time = 0;

	/**
	 * 
	 * @param data = Method in Data. This determinate which data is used 
	 */
	public Graph(Context context, AttributeSet attrs, Method data) {
		super(context, attrs);
		this.graphDataMethod = data; // set the method in Data. This sets data to be shown
		data = new Stack();
		// TODO Auto-generated constructor stub
	}
	
	public void ChangeDataSorce(Method dataSource)
	{
		graphDataMethod = dataSource;
		data.removeAllElements();		
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
		{}		
		else
		{
			time += SystemClock.currentThreadTimeMillis();
			
			if (time > IchibanActivity._UPDATE_INTERVAL)
			{
				time = 0;
				data.push(graphDataMethod);
				if (data.size() > 20000 / (float)IchibanActivity._UPDATE_INTERVAL)
					data.pop();
			}
							
			float[] points = new float[data.size() * 4];
			for (int i = 0; i < data.size() - 1; i++)
			{
				points[i * 4] = this.getLeft() + this.getWidth() * i / (float)data.size();
				points[i * 4 + 1] = (float) (this.getTop() + this.getHeight() * (1 - Double.parseDouble(data.elementAt(i).toString())));
				points[i * 4 + 2] = this.getLeft() + this.getWidth() * (i + 1) / (float)data.size();
				points[i * 4 + 3] = (float) (this.getTop() + this.getHeight() * (1 - Double.parseDouble(data.elementAt(i+1).toString())));
			}				
			
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawCircle(100,100,20, p);

			canvas.drawLines(points, p);
		}
		
		super.onDraw(canvas);
	}
}