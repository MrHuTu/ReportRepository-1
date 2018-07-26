package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.monitor.StatisticChartMapper;
import com.zdjc.report.model.StatisticChart;
import com.zdjc.report.service.StatisticChartService;

@Service
public class StatisticChartServiceImpl implements StatisticChartService {

	@Autowired
	StatisticChartMapper statisticChartMapper;

	@Override
	public List<StatisticChart> selectByPojoId(int poJoId) {

		return statisticChartMapper.selectByPojoId(poJoId);

	}


}
