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
		datasource=Data.speedData;
	}
}