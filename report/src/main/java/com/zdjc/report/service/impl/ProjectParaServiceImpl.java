package com.zdjc.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.ProjectParaMapper;
import com.zdjc.report.model.fictitious.ProjectPara;
import com.zdjc.report.service.ProjectParaService;
@Service
public class ProjectParaServiceImpl implements ProjectParaService {
	@Autowired
	ProjectParaMapper projectParaMapper;
	@Override
	public List<ProjectPara> selectProjectPara() {
		
		return projectParaMapper.selectProjectPara();
		
	}

}
