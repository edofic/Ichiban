package com.abstracttech.ichiban.data;

public class PathData extends StatisticData {
	private float path;
	private float lastSpeed;
	private float lastUpdateTime;

	@Override
	public void update() {
		//calculate acceleration
		path+=((Data.speedData.getSpeed()+lastSpeed)/2f)*(System.currentTimeMillis()-lastUpdateTime);

		float d = getAcc();

		if(d>cMax)
			cMax=d;
		if(d<cMin)
			cMin=d;

		//averaging
		n++;
		total+=d;
	}

	public float getAcc() {
		return path;
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
