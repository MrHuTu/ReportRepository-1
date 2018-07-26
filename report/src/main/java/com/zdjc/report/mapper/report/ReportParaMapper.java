package com.zdjc.report.mapper.report;

import java.util.List;

import com.zdjc.report.model.ReportPara;

/**
 * 查询报告配置参数
 * @author 胡超  2018年4月17日10:55:17
 *
 */
public interface ReportParaMapper {
	
	public List<ReportPara> selectReportPara();
	
}
