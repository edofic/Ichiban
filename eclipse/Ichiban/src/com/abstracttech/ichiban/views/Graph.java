package com.abstracttech.ichiban.views;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import com.abstracttech.ichiban.activities.IchibanActivity;

public class Graph extends View {
	
	private Method graphDataMethod;  	// dataSream method
	private Queue data;					// Stack of data to be drawn
	private long time = 0;				// local timer

	/**
	 * 
	 * @param data = Method in Data. This determinate which data is used 
	 */
	public Graph(Context context, AttributeSet attrs) {
		super(context, attrs);
		data = new LinkedList();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param dataSource = Method in Data. This determinate which data is used 
	 */
	public void ChangeDataSorce(Method dataSource)
	{
		graphDataMethod = dataSource;		// determination new dataSorce
		data.clear();			// restart data
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		if(isInEditMode()) //dummy data for editor
		{}		
		else if(!
				data.isEmpty())
		{
			time += SystemClock.currentThreadTimeMillis();	// updating local timer
			
			//if (time > IchibanActivity._UPDATE_INTERVAL)	//data updated every update interval
			{
				time = 0;
				data.add(graphDataMethod);												//updating data from predefined source
				if (data.size() > 3000 / (float)IchibanActivity._UPDATE_INTERVAL)		//to store only 20 seconds of data
					data.poll();
			}
							
			float[] points = new float[data.size() * 4];	//array of points of graph
			for (int i = 0; i < data.size() - 1; i++)		//Set points to draw lines
			{
				points[i * 4] =/* this.getLeft() */+ this.getWidth() * i / (float)data.size();													//x0
				points[i * 4 + 1] = (float) (/*this.getTop()*/ + this.getHeight() * (1 - Double.parseDouble(data.toArray()[i].toString())));	//y0
				points[i * 4 + 2] = /*this.getLeft() */+ this.getWidth() * (i + 1) / (float)data.size();										//x1
				points[i * 4 + 3] = (float) (/*this.getTop() */+ this.getHeight() * (1 - Double.parseDouble(data.toArray()[i+1].toString())));	//y1
			}				
			
			Paint p = new Paint();
			p.setColor(Color.RED);							//setting paint (Color, stroke...) and drawing the lines
			p.setStrokeWidth(4);
			canvas.drawLines(points, p);
			}	
	}
}