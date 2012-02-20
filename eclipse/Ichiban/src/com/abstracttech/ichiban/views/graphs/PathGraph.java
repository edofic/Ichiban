package com.abstracttech.ichiban.views.graphs;

import android.content.Context;
import android.util.AttributeSet;

import com.abstracttech.ichiban.data.Data;

public class PathGraph extends Graph 
{
	public PathGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected float GetData()
	{
		return top = Data.pathData.getPath();
	}
	
	@Override
	protected void onPoll(float d)
	{	
		bottom=d;
	}
}