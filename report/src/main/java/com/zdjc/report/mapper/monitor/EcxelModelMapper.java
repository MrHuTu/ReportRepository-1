package com.zdjc.report.mapper.monitor;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.fictitious.EcxelData;

public interface EcxelModelMapper {
	
	public List<EcxelData> selectEcxelModelByProjectId(@Param("projectId")String projectId,@Param("tabName") String tabName,@Param("beginTime")String beginTime,@Param("endTime")String endTime);

}
