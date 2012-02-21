package com.abstracttech.ichiban.data;

public class PathData extends StatisticData{
	private long path;
	private float lastSpeed;
	private long lastUpdateTime;

	public void update() {
		//calculate acceleration
		long nt=System.currentTimeMillis();
		path+=(Data.speedData.getSpeed()+lastSpeed)*(nt-lastUpdateTime)/(2*60*10); //2 for averaging, 60 for minutes to seconds, 10 for centimeters to milimeters
		lastUpdateTime=nt;
		updateData(path);
	}

	public float getPath(){
		return path/1000f;
	}
}
