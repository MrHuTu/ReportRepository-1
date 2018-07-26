package com.zdjc.report.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.monitor.EcxelModelMapper;
import com.zdjc.report.model.StatisticChart;
import com.zdjc.report.model.fictitious.EcxelData;
import com.zdjc.report.service.EcxelModelService;
import com.zdjc.report.service.StatisticChartService;

@Service
public class EcxelModelServiceImpl implements EcxelModelService {
	@Autowired
	EcxelModelMapper ecxelModelMapper;
	@Autowired
	StatisticChartService statisticChartService;
	/**
	 *@param projectId
	 *@param beginTime
	 *@param endTime
	 */
	@Override
	public List<EcxelData> selectEcxelModelByProjectId(String projectId,String beginTime,String endTime) {		
		
		DateTime endTime_ = new DateTime(endTime);	 
		
		String 	trueEndTime = endTime_.plusDays(1).toString("YYYY-MM-dd");
	
		String tabName =null;
		
		List<EcxelData> allEcxelModel= new ArrayList<EcxelData>();
		
		List<StatisticChart> statisticChart =  statisticChartService.selectByPojoId(Integer.parseInt(projectId));
		
		if(statisticChart==null) return null;
		//遍历查询全部对应表中的数据
		for(int i=0;i<statisticChart.size();i++){
			
			tabName = statisticChart.get(i).getTableName();
			
			List<EcxelData> ecxelModel=  ecxelModelMapper.selectEcxelModelByProjectId(projectId,tabName,beginTime,trueEndTime);
			
			allEcxelModel.addAll(ecxelModel);
			
		}			
				
		return  allEcxelModel;
	}

}
