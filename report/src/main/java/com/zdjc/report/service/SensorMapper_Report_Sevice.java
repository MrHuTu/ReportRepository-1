package com.zdjc.report.service;

import com.zdjc.report.model.Sensor;
/**
 * Sensor数据归档处理类
 * @author huchao
 *
 */

public interface SensorMapper_Report_Sevice {


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
