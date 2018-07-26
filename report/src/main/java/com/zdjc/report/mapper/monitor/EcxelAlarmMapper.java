package com.zdjc.report.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.fictitious.EcxelAlarm;

public interface EcxelAlarmMapper {
	
	public List<EcxelAlarm> selectAlarms(@Param("sensorNumber")String sensorNumber,@Param("smuNumber")String smuNumber,
			@Param("smuChannel")String smuChannel,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

}
