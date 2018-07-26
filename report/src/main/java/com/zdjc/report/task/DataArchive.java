package com.zdjc.report.task;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.zdjc.report.model.Sensor;
import com.zdjc.report.model.Who;
import com.zdjc.report.service.MigrationData_Report_Service;
import com.zdjc.report.service.ProjectService;
import com.zdjc.report.service.SensorMapper_Monitor_Sevice;
import com.zdjc.report.service.SensorMapper_Report_Sevice;
import com.zdjc.report.service.WhoService;
import com.zdjc.report.util.GitYmlParaUtils;

/**
 * 
 * @author huchao 2018年7月5日10:03:14
 * 数据归档 
 */
@Configuration
public class DataArchive {
	
	//private static final Logger logger = LoggerFactory.getLogger(DataArchive.class);
	
	@Autowired
	MigrationData_Report_Service migrationDataService;
	
	
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;
	
	@Autowired
	WhoService  whoService;
	
	@Autowired
	SensorMapper_Monitor_Sevice sensor_Monitor_Sevice;
	
	@Autowired
	SensorMapper_Report_Sevice sensorMapper_Report_Sevice;
	
	
	
	@Autowired 
	ProjectService projectMapper_Report_Service;
	/**
	 * 
	 * 定时归档 report.report表数据
	 * 
	 * @throws Exception  cron="0 0 12 * * ?" 每天中午12点触发 
	 */
	@Scheduled( fixedRate = 1000*60*60)
	public void archiveReportData() throws Exception {
		
		List<Who> a = whoService.getWho();
		
		System.out.println(a);

		Map<String,String>  map= gitYmlParaUtils.getSetTime(0);
		
		migrationDataService.insertData(map.get("start"),map.get("end"));
		
		Map<String,String>  map1= gitYmlParaUtils.getSetTime(1);
		
		migrationDataService.insertData(map1.get("start"),map1.get("end"));
		
		Map<String,String>  map2= gitYmlParaUtils.getSetTime(2);
		
		migrationDataService.insertData(map2.get("start"),map2.get("end"));
	}	
	
	/**
	 * 
	 * 定时归档 report.report表数据
	 * 
	 * @throws Exception  cron="0 0 12 * * ?" 每天中午12点触发 
	 */
	@Scheduled( fixedRate = 1000*60*60)
	public void archiveReport() throws Exception {
		
		//链接数据库Monitor，查询数据
		List<Sensor> sensors =  sensor_Monitor_Sevice.selectSensor();
		
		Iterator<Sensor> iter = sensors.iterator();
		
		while(iter.hasNext()){
			
			Sensor sensor = iter.next();
			
			//在report库下查询sensor表校重
			if(sensorMapper_Report_Sevice.selectByPrimaryKey(sensor.getSensorId())==null){
				
			 sensorMapper_Report_Sevice.insertSensor(sensor);
												
			};						
		}	
	}	
	

	
}
