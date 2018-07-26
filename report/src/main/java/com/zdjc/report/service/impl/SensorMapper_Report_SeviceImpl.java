package com.zdjc.report.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.SensorMapper_Report;
import com.zdjc.report.model.Sensor;
import com.zdjc.report.service.SensorMapper_Report_Sevice;
@Service
public class SensorMapper_Report_SeviceImpl implements SensorMapper_Report_Sevice {
	
	@Autowired
	SensorMapper_Report sensorMapper_Report;

	@Override
	public int insertSensor(Sensor sensor) {
		
		return sensorMapper_Report.insertSensor(sensor);
	}

	@Override
	public Sensor selectByPrimaryKey(Integer sensorId) {
		
		return sensorMapper_Report.selectByPrimaryKey(sensorId);
	}

}
