package com.zdjc.report.mapper.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.AuthorityReport;
import com.zdjc.report.model.fictitious.MyPageMode;
import com.zdjc.report.model.fictitious.PageMode;

public interface AuthorityReportMapper {
	
	 public void insertMasage(AuthorityReport authorityReport);
	 
	 public  List<AuthorityReport> selectFileName(@Param("projectId") String projectId,@Param("reportType") String reportType,@Param("reportName") String reportName);
	 
	 public List<AuthorityReport> gitFilename(@Param("id")String id);
	 /**
	  * 分页查询
	  * @param projectId 项目id
	  * @param currIndex 当前页码
	  * @param pageSize  一夜数据量大小
	  * @return
	  */
	 public  List<PageMode> gitAllMassageByProjectId(@Param("projectId") String projectId,
			 @Param("currIndex")String currIndex ,@Param("pageSize")String pageSize,@Param("reportType")String reportType) ;
	 
	 public  MyPageMode<AuthorityReport> getReportAllMassage(@Param("projectId") String projectId,
			 @Param("currIndex")String currIndex ,@Param("pageSize")String pageSize,@Param("reportType")String reportType) ;


}
