package com.zdjc.report.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartRequest;


public interface ConfigReportService {
	
	public String Cfg( MultipartRequest file,String projectId,String reportMain,String reportWrite,String reportApproval,String reportRatify,String projectSurvey,String projectAchievement,String projectSuggest,String remark) throws IOException;

}
