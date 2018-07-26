package com.zdjc.report.mapper.report;

import java.util.List;

import com.zdjc.report.model.ReportContentDay;
/**
 * 日报配置
 */

public interface ReportContentDayMapper {
		
	public List<ReportContentDay>  selectDayConfigById(String projectId);
	
	public void insertDayConfigById(ReportContentDay reportContentDay);
	
	public void updateDayConfigById(ReportContentDay reportContentDay);
	
}
