package com.zdjc.report.mapper.report;

import com.zdjc.report.model.Sensor;


public interface SensorMapper_Report {
	

	/**
	 * 数据归档
	 * 
	 * @param record
	 * @return
	 */
	public int insertSensor(Sensor sensor);
	
	
	/**
	 * 按主建查询
	 * @param sensorId
	 * @return
	 */
	public Sensor selectByPrimaryKey(Integer sensorId);

	

}