package com.zdjc.report.mapper.monitor;

import java.util.List;

import com.zdjc.report.model.Sensor;


public interface SensorMapper_Monitor{
	

	/**
	 * 查询全部传感器数据,提供归档数据源
	 * 
	 * @param record
	 * @return
	 */
	public List<Sensor> selectSensor();

	

}