package com.abstracttech.ichiban.views.graphs;

import android.content.Context;

import android.util.AttributeSet;

import com.abstracttech.ichiban.data.Data;

public class SpeedGraph extends Graph 
{
	public SpeedGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		bottom=-20;
		top=160;
	}

	@Override
	protected float GetData()
	{
		return Data.speedData.getSpeed()/Data._MAX_SPEED;
	}

	@Override
	protected boolean isThereData() {
		return (Data.speedData.data!=null && Data.accData.data.size()>0);
	}

	@Override
	protected Object[] getArray() {
		return Data.speedData.array;
	}
}