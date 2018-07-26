package com.zdjc.report.mapper.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.ReportPic;

public interface ReportPicMapper {
	
	public boolean insertPic(ReportPic reportPic);
	
    public List<ReportPic> selectPicById(@Param("projectId")String projectId,@Param("reportTyp")String reportTyp);
    
    public List<ReportPic> selectPic();
    
    
}
