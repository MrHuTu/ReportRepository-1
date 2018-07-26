package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.ReportContentDayMapper;
import com.zdjc.report.model.ReportContentDay;
import com.zdjc.report.service.ReportContentDayService;
/**
 *处理日报表的方法
 * @author 胡超
 * 
 *
 */
@Service("ReportContentDayServiceImpl")
public class ReportContentDayServiceImpl implements ReportContentDayService {
	
	@Autowired
	ReportContentDayMapper reportContentDayMapper;

	@Override
	public List<ReportContentDay> selectDayConfigById(String projectId) {
	
		return reportContentDayMapper.selectDayConfigById(projectId);
	}
	
	/**
	 * 插入
	 */
	@Override
	public void insertDayConfigById(ReportContentDay reportContentDay) {
	
		reportContentDayMapper.insertDayConfigById(reportContentDay);
	}
	
	/**
	 * 更新报告配置信息
	 */
	@Override
	public void updateDayConfigById(ReportContentDay reportContentDay) {
		
		reportContentDayMapper.updateDayConfigById(reportContentDay);
		
	}
}
