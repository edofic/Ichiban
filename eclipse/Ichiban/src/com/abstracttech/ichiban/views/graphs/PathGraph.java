package com.abstracttech.ichiban.views.graphs;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.abstracttech.ichiban.data.Data;

public class PathGraph extends Graph 
{
	public PathGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		top=Data.pathData.last;
		bottom=Data.pathData.first;
		super.onDraw(canvas);
	}
}