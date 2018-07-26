package com.zdjc.report.mapper.monitor;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.ReportData;
/**
 * 查询报告数据,使用monitor数据库驱动,将对应MigrationDataMapper.xml。放到monitor文件夹下,多数据源会根据包的不同自动选中连接池
 */

public interface MigrationDataMapper_Monitor {
	
	
	public List<ReportData> selectRepotrData( @Param("tableName") String tableName, @Param("beginTime") String beginTime, @Param("endTime")String endTime,@Param("pojoId")String pojoId);
	
}
