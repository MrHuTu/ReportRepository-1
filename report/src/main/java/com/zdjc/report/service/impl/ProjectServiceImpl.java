package com.zdjc.report.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.ProjectMapper;
import com.zdjc.report.model.Project;
import com.zdjc.report.service.ProjectService;


@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	ProjectMapper  projectMapper_Report;

	

	@Override
	public Project selectByPrimaryKey(Integer projectId) {
		// TODO Auto-generated method stub
		return projectMapper_Report.selectByPrimaryKey(projectId);
	}

}
