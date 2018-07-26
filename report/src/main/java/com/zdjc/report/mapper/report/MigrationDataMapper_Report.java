package com.zdjc.report.mapper.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.ReportData;
/**
 * 数据迁移,使用report数据库驱动,将对应MigrationDataMapper_Report.xml。放到report文件夹下,多数据源会根据包的不同自动选中连接池
 */
public interface MigrationDataMapper_Report {

	

	public void insertData( @Param("reportDatas") List<ReportData> reportDatas) ;
}
