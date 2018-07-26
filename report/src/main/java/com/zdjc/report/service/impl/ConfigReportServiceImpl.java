package com.zdjc.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zdjc.report.model.ReportContentDay;
import com.zdjc.report.service.ConfigReportService;
import com.zdjc.report.service.ReportContentDayService;
import com.zdjc.report.util.GitYmlParaUtils;
/**
 * 配置报告服务类 D日报,W周报,文件上傳
 * @author Administrator
 *
 */
@Service
public class ConfigReportServiceImpl implements ConfigReportService {
	
	//处理日报的方法
	@Autowired
	ReportContentDayService reportContentDayService;
	
	@Autowired
	GitYmlParaUtils  gitYmlParaUtils;

	/*@Override
	public String Cfg(ReportContentDay reportContentDay) {
		
		String msg = "配置成功";
		
		String projectId = reportContentDay.getProjectId();
		
		if(type.equals("D")){
						
			if(reportContentDayService.selectDayConfigById(projectId).size()>0){//验证该项目是否已经配置
				
				reportContentDayService.insertDayConfigById(reportContentDay);
								
				
			}else{
				
				reportContentDayService.updateDayConfigById(reportContentDay);
				
			}
			
		}else if(type.equals("W")){
			
			
			
		}else{
			
			msg="配置失败";
		}
		
		
		return msg;
		
		
	
		
				
	}*/

	@Override
	public String Cfg(MultipartRequest file, String projectId,
			String reportMain, String reportWrite, String reportApproval,
			String reportRatify, String projectSurvey,
			String projectAchievement, String projectSuggest, String remark) throws IOException {
		
		String msg = "配置成功";
		
		Map<String, MultipartFile> multipartFiles = file.getFileMap();
		
		Iterator<String> keySet = 	multipartFiles.keySet().iterator();
		
		ReportContentDay reportContentDay = new ReportContentDay();
		
		//文件上传路径
		String path  = gitYmlParaUtils.accordingOsGetParm("upload");
		
		String picType =  gitYmlParaUtils.getPictype();
						
		while(keySet.hasNext()){
			
			String key = 	keySet.next();
			
			MultipartFile multipartFile = multipartFiles.get(key);
			
			
			if(multipartFile.isEmpty()) continue;
			
			String trueName = multipartFile.getOriginalFilename();
			
			String cType = trueName.substring(trueName.lastIndexOf(".")+1, trueName.length());
			
			System.out.println(trueName);
			
			if(picType.indexOf(cType)<0){
				
				msg="配置失败，请检查文件格式"+trueName+"。目前只支持："+picType;
				
				return msg;
			}
			
			String fileName = getUUID32()+trueName;
			
			if(key.equals("totalLaserChangePic")) reportContentDay.setTotalLaserChangePic(fileName);
			
			if(key.equals("currentLaserChangePic")) reportContentDay.setCurrentLaserChangePic(fileName);
			
			if(key.equals("speedChangePic")) reportContentDay.setSpeedChangePic(fileName);
			
			if(key.equals("monitorPoitPic")) reportContentDay.setMonitorPoitPic(fileName);
			
			byte[] bytes = multipartFile.getBytes();
			
			FileOutputStream out = new FileOutputStream(path+fileName);
			
			out.write(bytes);
			
			out.close();
			
			
		}
		
		reportContentDay.setProjectId(projectId);
		
		reportContentDay.setReportMain(reportMain);
		
		reportContentDay.setReportWrite(reportWrite);
		
		reportContentDay.setReportApproval(reportApproval);
		
		reportContentDay.setReportRatify(reportRatify);
		
		reportContentDay.setProjectSurvey(projectSurvey);
		
		reportContentDay.setProjectAchievement(projectAchievement);
		
		reportContentDay.setProjectSuggest(projectSuggest);
		
		reportContentDay.setRemark(remark);
		
		if(reportContentDayService.selectDayConfigById(projectId).size()<=0){//验证该项目是否已经配置
			
			reportContentDayService.insertDayConfigById(reportContentDay);
							
			
		}else{
			
			reportContentDayService.updateDayConfigById(reportContentDay);
			
		}
				
		return msg;
	}
	
	public static String getUUID32(){
		
	    String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
	    
	    return uuid+"@";
	
	}

}
