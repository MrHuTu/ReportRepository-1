package com.zdjc.report.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.MigrationDataMapper_Report;
import com.zdjc.report.model.ReportData;
import com.zdjc.report.service.MigrationData_Monitor_Service;
import com.zdjc.report.service.MigrationData_Report_Service;
import com.zdjc.report.service.ReportDataService;

/**
 * 归档report_config 配置了项目的数据
 * @author Administrator
 *
 */
@Service
public class MigrationData_Report_ServiceImpl implements MigrationData_Report_Service {
	private static final Logger logger = LoggerFactory.getLogger(MigrationData_Report_ServiceImpl.class);
	//做插入操作，将数据插入report数据库
	@Autowired
	private MigrationDataMapper_Report migrationDataMapper_Report;
	
	//做查询操作，数据来源于Monitor的原始数据表,为归档提供数据源
	@Autowired
	private MigrationData_Monitor_Service migrationData_Monitor_Service;

	//在向report.Report_Data归档数据时，先查询是否某条数据已经插入
	@Autowired 
	private ReportDataService reportDataService;
	
	
	 // 数据迁移,向report数据库进行数据迁移
	@Override
	public synchronized  void insertData(String beginTime,String endTime) {
	
		
		boolean insert = true;
		
		//做查询操作，数据来源于Monitor的原始数据表,为归档提供数据源
		Map<String,List<ReportData>>  ReportDatas   = migrationData_Monitor_Service.selectRepotrData(beginTime, endTime);			
		
		if(ReportDatas==null ||  ReportDatas.isEmpty()) return;
		
						
		Iterator<String> ite = ReportDatas.keySet().iterator();
			
			while(ite.hasNext()){
				
				String  mapKey= ite.next();		
				
				if(ReportDatas.get(mapKey).size()>0){
					
					List<ReportData> lists = ReportDatas.get(mapKey);
					
					Iterator<ReportData>  ite1= lists.iterator();
					
					while(ite1.hasNext()){
						
						ReportData reportData = ite1.next();
											
						String sensor_number = reportData.getSensor_number();
						
						String smu_channel = reportData.getSmu_channel();
						
						String smu_number =  reportData.getSmu_number();						
						
						String current_times = new DateTime(reportData.getCurrent_times()).toString("YYYY-MM-dd HH:mm:ss");
						
						//在向report.Report_Data归档数据时，先查询是否某条数据已经插入
						if(!reportDataService.selectReportData(sensor_number, smu_channel, smu_number, current_times)){//数据不存在该数据库进入if
							
							insert = true;//允许归档数据
							
						}else{
							insert = false;//数据库已经存在该数据，无需归档
						
						};
					}	
					if(insert){
						//做插入操作，将数据插入report数据库
						migrationDataMapper_Report.insertData(ReportDatas.get(mapKey));	
						logger.info("==============================================归档beginTime:"+beginTime+"到endTime:"+endTime+"=====================================================");
					}
				}												
			}
					
		
		
	}
	
}
