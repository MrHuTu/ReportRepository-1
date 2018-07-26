package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.ReportConfigMapper;
import com.zdjc.report.model.ReportConfig;
import com.zdjc.report.service.ReportConfigService;
@Service
public class ReportConfigServiceImpl implements ReportConfigService {
	@Autowired
	ReportConfigMapper reportConfigMapper;
	@Override
	public List<ReportConfig> selectReportConfig() {
		
		return reportConfigMapper.selectReportConfig();
		
	}

}
