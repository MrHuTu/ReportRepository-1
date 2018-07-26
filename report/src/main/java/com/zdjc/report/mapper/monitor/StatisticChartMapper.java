package com.zdjc.report.mapper.monitor;

import java.util.List;

import com.zdjc.report.model.StatisticChart;

public interface StatisticChartMapper {


	List<StatisticChart> selectByPojoId(int poJoId);


}