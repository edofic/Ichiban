package com.abstracttech.ichiban.data;

public class SpeedData extends StatisticData {
	float d;
	
	public SpeedData(){
		super();
		cMax=Integer.MIN_VALUE;
		cMin=Integer.MAX_VALUE;
	}
	
	@Override
	public void update() {
		d = Data.getRpm()*Data._SPEED_TO_RPM_RATIO;

		if(d>cMax)
		{
			cMax=d;
		}
		if(d<cMin && d>0)
		{
			cMin=d;
		}

		//averaging
		n++;
		total+=d;
		
		updateData(d);
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
