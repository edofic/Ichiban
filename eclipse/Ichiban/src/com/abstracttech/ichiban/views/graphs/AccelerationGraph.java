package com.abstracttech.ichiban.views.graphs;

import android.content.Context;
import android.util.AttributeSet;
import com.abstracttech.ichiban.data.Data;
import com.abstracttech.ichiban.views.Graph;

public class AccelerationGraph extends Graph 
{
	public AccelerationGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		//values are between -0.5 and 0.5 but they are offset by 0.5 upwards
		bottom=0;
		top=1;
	}

	protected float GetData()
	{
		return Data.accData.getAcc()/2+0.5f;
	}
}