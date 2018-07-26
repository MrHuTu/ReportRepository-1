package com.zdjc.report.model.fictitious;

import java.util.List;

/**
 * 封装了 ecxel数据模型的实体类
 * @author Administrator
 *
 */
public class EcxelData {
	
	//项目名称
	private String projectName;
	//测试单位
	private String testUnit;	
	//初次测试时间
	private String firstTime;
	//监测指标
	private String itemName;
	//传感器编号
	private String sensorNumber;
	//测点名称
	private String monitorPoint;
	//终端编号
	private String smuNumber;
	//采集器通道
	private String smuChannel;
	
	//监测数据
	List<EcxelModel> datas;
	
	public EcxelData() {
		this.testUnit="湖南中大建设工程检测技术有限公司";
		
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTestUnit() {
		return testUnit;
	}

	public void setTestUnit(String testUnit) {
		this.testUnit = testUnit;
	}

	public String getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(String firstTime) {
		this.firstTime = firstTime;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSensorNumber() {
		return sensorNumber;
	}

	public void setSensorNumber(String sensorNumber) {
		this.sensorNumber = sensorNumber;
	}

	public String getMonitorPoint() {
		return monitorPoint;
	}

	public void setMonitorPoint(String monitorPoint) {
		this.monitorPoint = monitorPoint;
	}

	public String getSmuNumber() {
		return smuNumber;
	}

	public void setSmuNumber(String smuNumber) {
		this.smuNumber = smuNumber;
	}

	public String getSmuChannel() {
		return smuChannel;
	}

	public void setSmuChannel(String smuChannel) {
		this.smuChannel = smuChannel;
	}

	public List<EcxelModel> getDatas() {
		return datas;
	}

	public void setDatas(List<EcxelModel> datas) {
		this.datas = datas;
	}

	@Override
	public String toString() {
		return "EcxelData [projectName=" + projectName + ", testUnit="
				+ testUnit + ", firstTime=" + firstTime + ", itemName="
				+ itemName + ", sensorNumber=" + sensorNumber
				+ ", monitorPoint=" + monitorPoint + ", smuNumber=" + smuNumber
				+ ", smuChannel=" + smuChannel + ", datas=" + datas + "]";
	}
	
	
}
