package com.zdjc.report.service;

import java.util.List;

import com.zdjc.report.model.Sensor;

/**
 * 给Sensor归档提供数据源
 * @author huchao 
 * 2018年7月5日11:08:20
 *
 */
public interface SensorMapper_Monitor_Sevice {
	
	public List<Sensor> selectSensor();

}
