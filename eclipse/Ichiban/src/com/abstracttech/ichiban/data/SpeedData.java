package com.abstracttech.ichiban.data;

public class SpeedData extends StatisticData {
	float d;
	@Override
	public void update() {
		d = Data.getRpm()*4f;

		if(d>cMax)
			cMax=d;
		if(d<cMin)
			cMin=d;

		//averaging
		n++;
		total+=d;
	}

	@Override
	public float getMin(){
		if(cMin==Integer.MAX_VALUE)
			return 0;
		else
			return cMin;
	}

	@Override
	public float getMax(){
		if(cMax==Integer.MIN_VALUE)
			return 0;
		else
			return cMax;
	}
	
	public float getSpeed(){
		return d;
	}
}