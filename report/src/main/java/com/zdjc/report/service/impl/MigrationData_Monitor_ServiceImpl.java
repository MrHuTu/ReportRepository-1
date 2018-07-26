package com.zdjc.report.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.monitor.MigrationDataMapper_Monitor;
import com.zdjc.report.model.ReportConfig;
import com.zdjc.report.model.ReportData;
import com.zdjc.report.model.StatisticChart;
import com.zdjc.report.service.MigrationData_Monitor_Service;
import com.zdjc.report.service.StatisticChartService;
import com.zdjc.report.util.ReportConfigOpUtils;

/**
 * 归档report_config 配置了项目的数据
 * @author Administrator
 *
 */
@Service
public class MigrationData_Monitor_ServiceImpl implements MigrationData_Monitor_Service {
	private static final Logger logger = LoggerFactory.getLogger(MigrationData_Monitor_ServiceImpl.class);
	@Autowired
	private MigrationDataMapper_Monitor migrationDataMapper_Monitor;
	
	@Autowired 
	private StatisticChartService statisticChartService;
	
	/**
	 * 查询报告数据  ，数据源来自monitor数据库
	 */
	@Override
	public synchronized HashMap<String, List<ReportData>> selectRepotrData(String beginTime,String endTime) {
		
		int count = 0;
	
		HashMap<String,List<ReportData>> map  = new HashMap<String, List<ReportData>>();
		
		//这个对像是在服务器启动的时候生成的一个report_config表对象      
		List<ReportConfig> reportConfig = ReportConfigOpUtils.reportConfigs;
		
	
		if( reportConfig==null) return null;
		
			for(ReportConfig c: reportConfig){
				
				//report_confi配置的项目
				int pojoId  = c.getProject_id();
				
				System.out.println("<<"+"当前处理线程:"+Thread.currentThread().getName()+",当前处理项目ID:"+pojoId+">>");
				
				//查询pojoId下的原始数据表名
				List<StatisticChart> tables = statisticChartService.selectByPojoId(pojoId);											
				
				if(tables==null) return null;
				
				//if(obj==null){
					
					//当前归档数据的集合
					List<ReportData> datas = null;
				
					//查询当前pojoId的数据
					for(StatisticChart V: tables){
							
						//链接monitor库进行查询数据
						 datas =  migrationDataMapper_Monitor.selectRepotrData( V.getTableName(),beginTime,endTime,String.valueOf(V.getProjectId()));		
						 
						 
						 if(datas.size()==0) continue;
							 
						
						
						
						 map.put(V.getTableName()+count, datas);
						 
						 count++;
						 
						logger.info(pojoId+"项目下的"+V.getTableName()+"表,查询"+beginTime+"——"+endTime+"的数据");
					}
					
				
				
			}
			
		
		
		
		
		return map;
		
	}

}
