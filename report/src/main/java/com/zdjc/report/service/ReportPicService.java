package com.zdjc.report.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zdjc.report.model.ReportPic;
import com.zdjc.report.model.fictitious.Result;

public interface ReportPicService{


	public Result<String> insertPic(MultipartFile[] file,String projectId,String reportTyp);
	
	public Result<List<String>> selectPicById(String id,String reportTyp);
	
	public List<ReportPic> selectPic();
}
