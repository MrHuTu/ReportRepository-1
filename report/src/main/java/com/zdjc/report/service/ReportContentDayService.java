package com.zdjc.report.service;

import java.util.List;

import com.zdjc.report.model.ReportContentDay;

public interface ReportContentDayService {
	
	public List<ReportContentDay>  selectDayConfigById(String id);
	
	public void insertDayConfigById(ReportContentDay reportContentDay);
	
	public void updateDayConfigById(ReportContentDay reportContentDay);

}
