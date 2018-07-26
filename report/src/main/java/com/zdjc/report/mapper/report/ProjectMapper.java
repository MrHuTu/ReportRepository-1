package com.zdjc.report.mapper.report;

import com.zdjc.report.model.Project;



public interface ProjectMapper {
	

	
	
	/**
	 * 查询
	 * @param projectId
	 * @return
	 */
	public Project selectByPrimaryKey(Integer projectId);
}