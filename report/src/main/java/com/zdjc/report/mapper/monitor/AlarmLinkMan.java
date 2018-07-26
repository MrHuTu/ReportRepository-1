package com.zdjc.report.mapper.monitor;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.fictitious.EcxelAlarm;

public interface AlarmLinkMan {

	public EcxelAlarm selectLinkMan(@Param("projectId")String projectId);
}
