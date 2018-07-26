package com.zdjc.report.service;

import java.util.List;

import com.zdjc.report.model.StatisticChart;

public interface StatisticChartService {

	List<StatisticChart> selectByPojoId(int poJoId);

}
