package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.ReportParaMapper;
import com.zdjc.report.model.ReportPara;
import com.zdjc.report.service.ReportParaService;
@Service
public class ReportParaServiceImpl implements ReportParaService {
	@Autowired
	ReportParaMapper reportParaMapper;
	@Override
	public List<ReportPara> selectReportPara() {
		
		return reportParaMapper.selectReportPara();
	}

}
