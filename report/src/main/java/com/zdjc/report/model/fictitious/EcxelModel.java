package com.zdjc.report.model.fictitious;

public class EcxelModel {
	
	//当前检测时间
	private String currentTimes;
	
	//累计变化量
	private String totalLaserChange;
	
	//单次变化量
	private String currentLaserChange;
	
	//变化速率
	private String  speedChange;

	public String getCurrentTimes() {
		return currentTimes;
	}

	public void setCurrentTimes(String currentTimes) {
		this.currentTimes = currentTimes;
	}

	
	public String getTotalLaserChange() {
		return totalLaserChange;
	}

	public void setTotalLaserChange(String totalLaserChange) {
		this.totalLaserChange = totalLaserChange;
	}

	public String getCurrentLaserChange() {
		return currentLaserChange;
	}

	public void setCurrentLaserChange(String currentLaserChange) {
		this.currentLaserChange = currentLaserChange;
	}

	public String getSpeedChange() {
		return speedChange;
	}

	public void setSpeedChange(String speedChange) {
		this.speedChange = speedChange;
	}

	@Override
	public String toString() {
		return "EcxelModel [currentTimes=" + currentTimes
				+ ", totalLaserChange=" + totalLaserChange
				+ ", currentLaserChange=" + currentLaserChange
				+ ", speedChange=" + speedChange + "]";
	}
	
	

}
