package com.abstracttech.ichiban.views.graphs;

import android.content.Context;
import android.util.AttributeSet;
import com.abstracttech.ichiban.data.Data;

public class AccelerationGraph extends Graph 
{
	public AccelerationGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		bottom=-80;
		top=80;
	}

	protected float GetData()
	{
		return Data.accData.getAcc();
	}

	@Override
	protected boolean isThereData() {
		return (Data.accData.data!=null && Data.accData.data.size()>0);
	}

	@Override
	protected Object[] getArray() {
		return Data.accData.array;
	}
}