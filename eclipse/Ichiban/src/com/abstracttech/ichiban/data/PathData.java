package com.abstracttech.ichiban.data;

public class PathData {
	private long path;
	private float lastSpeed;
	private long lastUpdateTime;

	public void update() {
		//calculate acceleration
		long nt=System.currentTimeMillis();
		path+=(Data.speedData.getSpeed()+lastSpeed)*(nt-lastUpdateTime)/2000f;
		lastUpdateTime=nt;
	}

	public long getPath() {
		return path;
	}
}
