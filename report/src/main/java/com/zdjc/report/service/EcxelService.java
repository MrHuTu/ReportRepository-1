package com.zdjc.report.service;

import java.util.List;

import com.zdjc.report.model.fictitious.EcxelAlarm;


/**
 * 
 * @author 胡超
 * ecxel生成下载服务类
 */
public interface EcxelService {
	
	public Object downEcxel(String projectId,String beginTime,String endTime);
	
	public List<EcxelAlarm> selectAlarms(String sensorNumber,String smuNumber,String smuChannel,String beginTime,String endTime);
	
	public EcxelAlarm selectLinkMan(String projectId);

}
