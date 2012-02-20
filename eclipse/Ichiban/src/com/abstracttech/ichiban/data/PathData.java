package com.abstracttech.ichiban.data;

public class PathData {
	private long path;
	private float lastSpeed;
	private long lastUpdateTime;

	public void update() {
		//calculate acceleration
		long nt=System.currentTimeMillis();
		path+=(Data.speedData.getSpeed()+lastSpeed)*(nt-lastUpdateTime)/120000f; //2 for average, and there are 60000 miliseconds in a minute
		lastUpdateTime=nt;
	}

	public long getPath() {
		return path;
	}
}
