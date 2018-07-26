package com.zdjc.report.service;


/**
 * 连接Report数据库，查询数据,为数据迁移提供数据源头
 * @author huchao
 *MigrationData_Report_Service
 */
public interface MigrationData_Report_Service {
	
	public  void insertData(String beginTime,String endTime);
}
