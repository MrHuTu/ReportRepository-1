package com.zdjc.report.service;

import java.util.List;

import org.springframework.web.multipart.MultipartRequest;

import com.zdjc.report.model.AuthorityReport;
import com.zdjc.report.model.fictitious.MyPageMode;
import com.zdjc.report.model.fictitious.PageMode;
import com.zdjc.report.model.fictitious.Result;

public interface AuthorityReportService {
	
	 public void insertMasage(AuthorityReport authorityReport);
	 
	 public List<AuthorityReport> selectFileName( String projectId,String reportType,String reportName);
	 
	 public  List<AuthorityReport>  gitFilename(String id);
	 
	 public  Result<String>  getFilename(String id);
	 
	 public String Cfg(MultipartRequest file,String projectId,String reportType,String timeOfReport,String personInCharge);
	 
	/* public String downFile(String projectId,String reportType,String reportName,HttpServletResponse response);*/
	 
	 public Object downFile(String id);
	 
	 
	 public  List<PageMode> gitAllMassageByProjectId(String projectId ,String currIndex ,String pageSize,String reportType);
	 
	 public  MyPageMode<AuthorityReport> getReportAllMassage(String projectId ,String currIndex ,String pageSize,String reportType);

}
