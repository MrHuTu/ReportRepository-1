package com.zdjc.report.service;

import java.util.HashMap;
import java.util.List;

import com.zdjc.report.model.ReportData;

/**
 * 连接Monitor数据库，查询数据,为数据迁移提供数据源头
 * @author huchao
 *MigrationData_Report_Service
 */
public interface MigrationData_Monitor_Service {
	
	public HashMap<String, List<ReportData>> selectRepotrData(String beginTime,String endTime);
	
	
	
}
