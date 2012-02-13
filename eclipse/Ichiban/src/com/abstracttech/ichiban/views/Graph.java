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
	
	private Method graphDataMethod;  	// dataSream method
	private Stack data;					// Stack of data to be drawn
	private long time = 0;				// local timer

	/**
	 * 
	 * @param data = Method in Data. This determinate which data is used 
	 */
	public Graph(Context context, AttributeSet attrs, Method dataSource) {
		super(context, attrs);
		this.graphDataMethod = dataSource; // set the method in Data. This sets data to be shown
		data = new Stack();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param dataSource = Method in Data. This determinate which data is used 
	 */
	public void ChangeDataSorce(Method dataSource)
	{
		graphDataMethod = dataSource;		// determination new dataSorce
		data.removeAllElements();			// restart data
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		if(isInEditMode()) //dummy data for editor
		{}		
		else
		{
			time += SystemClock.currentThreadTimeMillis();	// updating local timer
			
			if (time > IchibanActivity._UPDATE_INTERVAL)	//data updated every update interval
			{
				time = 0;
				data.push(graphDataMethod);												//updating data from predefined source
				if (data.size() > 20000 / (float)IchibanActivity._UPDATE_INTERVAL)		//to store only 20 seconds of data
					data.pop();
			}
							
			float[] points = new float[data.size() * 4];	//array of points of graph
			for (int i = 0; i < data.size() - 1; i++)		//Set points to draw lines
			{
				points[i * 4] = this.getLeft() + this.getWidth() * i / (float)data.size();													//x0
				points[i * 4 + 1] = (float) (this.getTop() + this.getHeight() * (1 - Double.parseDouble(data.elementAt(i).toString())));	//y0
				points[i * 4 + 2] = this.getLeft() + this.getWidth() * (i + 1) / (float)data.size();										//x1
				points[i * 4 + 3] = (float) (this.getTop() + this.getHeight() * (1 - Double.parseDouble(data.elementAt(i+1).toString())));	//y1
			}				
			
			Paint p = new Paint();
			p.setColor(Color.RED);							//setting paint (Color, stroke...) and drawing the lines
			p.setStrokeWidth(4);
			canvas.drawLines(points, p);
		}
		
		super.onDraw(canvas);
	}
}