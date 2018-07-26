package com.zdjc.report.util;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zdjc.report.model.ReportConfig;
import com.zdjc.report.model.ReportPara;
import com.zdjc.report.model.fictitious.ProjectPara;
/**
 * 验证是否配置参数
 * @author 胡超
 *
 */
public class ReportConfigOpUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportConfigOpUtils.class);
		
	public static List<ReportConfig> reportConfigs;
	
	public static List<ReportPara> reportParas;
		
	//全部项目的在线监测参数
	public static List<ProjectPara> projectPara;
	
	
	/**
	 * 验证项目报表开关是否开启
	 * @param projectId
	 * @return true 开启;false 关闭 不可用
	 */
	public static boolean verifyreportConfig(String projectId) {

		Iterator<ReportConfig> paras = reportConfigs.iterator();
		while (paras.hasNext()) {
			ReportConfig reportConfig = paras.next();
			if(reportConfig.getProject_id()==Integer.parseInt(projectId)){
				if(reportConfig.getReportConfig_switch()==1){
					return true;
				}else{
					return false;
				}
			}
			

		}
		return false;
	}
	

	/**
	 * 验证 监测参数是否能支持生成报告
	 * 
	 * @param para
	 * @return
	 */
	public static boolean verifyReportPara(String para) {

		Iterator<ReportPara> paras = reportParas.iterator();
		
		while (paras.hasNext()) {
			
			ReportPara reportPara = paras.next();
			
			if (para.equalsIgnoreCase(reportPara.getParameter())) {
				
				return true;
			}

		}
		return false;
	}
	
	/**
	 * 获的当前项目的数据填充的bean id,这个bean由Spring管理
	 * @param pojoId
	 * @return
	 */
	public static String  gitClassPath(String pojoId,String reportTyp){
		
	Iterator<ReportConfig>  ite = reportConfigs.iterator();
	
	String path = null;
	
	while(ite.hasNext()){
		
		ReportConfig reportConfig = ite.next();
		
		if(pojoId.equals(String.valueOf(reportConfig.getProject_id())) &&  reportConfig.getReport_typ().contains(reportTyp)){
			
			path = reportConfig.getClass_path();
			
			
		}
	}
		return path;
		
	}
	
	/**
	 * 获取当期项目监测参数的种类
	 */
	public static int getObjrctPataCount(String pojoId){
		
		int count = 0;
		
		Iterator<ProjectPara> projectParas = projectPara.iterator();
		
		while(projectParas.hasNext()){
			
			ProjectPara projectPara = projectParas.next();
			
			if(String.valueOf(projectPara.getProject_id()).equals(pojoId)){

				count++;
				
			}
		}
		return count;
		
	}
	/**
	 * 取得当前模板类
	 */
	
	public  static String getModelPath(String pojoId,String reportTyp){
						
		Iterator<ReportConfig>  ite = reportConfigs.iterator();
		
		String path = null;
		
		while(ite.hasNext()){
			
			ReportConfig reportConfig = ite.next();
			
			if(String.valueOf(reportConfig.getProject_id()).equals(pojoId) && reportConfig.getReport_typ().contains(reportTyp) ){
												
				path  = reportConfig.getWord_path();
				
				logger.info(pojoId+"path:"+path);
												
			}
		}
		if(path==null){
			
			logger.error("请在表report_config中配置"+pojoId+"相关信息");
			
		}
		return path;				
		
	}

}
