package com.abstracttech.ichiban.views.graphs;

import android.content.Context;

import android.util.AttributeSet;

import com.abstracttech.ichiban.data.Data;

public class SpeedGraph extends Graph 
{
	public SpeedGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		bottom=0;
		top=1;
	}

	@Override
	protected float GetData()
	{
		return Data.speedData.getSpeed()/Data._MAX_SPEED;
	}
}