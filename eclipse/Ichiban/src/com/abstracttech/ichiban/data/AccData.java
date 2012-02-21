package com.abstracttech.ichiban.data;

public class AccData extends StatisticData {
	public static final float _MAX_ACC=100;
	private float acc;
	private float lastSpeed;
	private long lastUpdateTime;

	@Override
	public void update() {
		//calculate acceleration
		long nt=System.currentTimeMillis();
		float speed=Data.speedData.get();
		acc=(speed -lastSpeed)/((float)(nt-lastUpdateTime)/60f);
		lastUpdateTime=nt;
		lastSpeed=speed;
		float d = get();

		if(d>cMax)
			cMax=d;
		if(d<cMin)
			cMin=d;

		//averaging
		n++;
		total+=d;
		updateData(d);
	}

	@Override
	public float get() {
		return acc;
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
}
