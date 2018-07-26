package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.monitor.SensorMapper_Monitor;
import com.zdjc.report.model.Sensor;
import com.zdjc.report.service.SensorMapper_Monitor_Sevice;
@Service
public class Sensor_Monitor_SeviceImpl implements SensorMapper_Monitor_Sevice {
	
	//查询Sensor
	@Autowired
	SensorMapper_Monitor SensorMapper;

	@Override
	public List<Sensor> selectSensor() {
		
		return SensorMapper.selectSensor();
	}

	
}
