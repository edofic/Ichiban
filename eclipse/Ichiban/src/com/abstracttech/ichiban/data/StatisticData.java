package com.abstracttech.ichiban.data;

import java.util.LinkedList;
import java.util.Queue;

import com.abstracttech.ichiban.activities.IchibanActivity;

/**
 * template for all statistic data
 * source for graphs' datapoints
 */
public abstract class StatisticData {
	protected final int _MAX_DATA_POINTS = 2000 / IchibanActivity._UPDATE_INTERVAL;

	protected float cMin; //current minimum
	protected float cMax; //current minimum
	protected double total; //for averaging
	protected int n;
	public Queue<Float> data;
	public Object[] array;
	public float first,last;; //min and max from datapoints;
	//protected List<Integer> data; //use this for history

	public StatisticData()
	{
		clear();
	}

	public float get()
	{
		return 0;
	}

	public float getMin(){
		return cMin;
	}
	public float getMax(){
		return cMax;
	}
	public float getAvg(){
		return (n==0)?0:(float)(total/n); 	
		//total has precision of 16decimals
		//considering that rpm data only has 2 significant decimals
		//this gives 10^14 data points before error. 
		//this equals to approx. 100000 years of 35ms sampling.
	}

	protected void updateData(float nextPoint){
		if (data.size()>=_MAX_DATA_POINTS) {
			first = data.poll();
		}
		data.add(nextPoint);
		last=nextPoint;

		array=data.toArray();
	}

	public void clear()
	{
		cMin=Integer.MAX_VALUE;
		cMax=Integer.MIN_VALUE;
		total=0;
		n=0;
		data = new LinkedList<Float>();
	}
	public abstract void update();
}
