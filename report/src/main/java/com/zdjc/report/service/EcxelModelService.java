package com.zdjc.report.service;

import java.util.List;

import com.zdjc.report.model.fictitious.EcxelData;
/**
 * 报告数据来源服务类
 * @author 胡超
 *
 */
public interface EcxelModelService {
	
	public List<EcxelData> selectEcxelModelByProjectId(String projectId,String beginTime,String endTime);
}
