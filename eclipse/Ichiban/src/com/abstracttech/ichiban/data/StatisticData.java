package com.abstracttech.ichiban.data;

import java.util.LinkedList;
import java.util.List;

public abstract class StatisticData {
	protected float cMin; //current minimum
	protected float cMax; //current minimum
	protected double total; //for averaging
	protected int n;
	//protected List<Integer> data; //use this for history

	public StatisticData()
	{
		cMin=Integer.MAX_VALUE;
		cMax=Integer.MIN_VALUE;
		total=0;
		n=0;
		//data = new LinkedList<Integer>();
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

	public abstract void update();
}
