package com.zdjc.report.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjc.report.mapper.report.SideTableDataMapper;
import com.zdjc.report.model.fictitious.ReportWeekData_Head;
import com.zdjc.report.model.fictitious.SideTableData;
import com.zdjc.report.service.SideTableDataService;
import com.zdjc.report.util.GitYmlParaUtils;
import com.zdjc.report.util.ReportDateUtils;

/**
 * 查询水平位移，和沉降的数据，用来填充word文档报告
 * 
 * @author 胡超 2018年4月18日19:15:07 2018年6月5日17:21:12补充（将该实现指定为全部报表数据查询的实现类）
 */
@Service
public class SideTableDataServiceImpl implements SideTableDataService {
	@Autowired
	SideTableDataMapper sideTableDataMapper;
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;

	@Override
	public List<SideTableData> selectSideTableData(int pojoId, String time) {

		return sideTableDataMapper.selectSideTableData(pojoId, time);

	}

	/**
	 * 取收敛，沉降周报数据
	 */
	@Override
	public List<SideTableData> selectSideTableDataOfWeek() {

		Map<String, String> sAnde = ReportDateUtils.getTimeInterval(new Date());

		String[] times = gitYmlParaUtils.getSetweek().split("\\;");

		return sideTableDataMapper.selectSideTableDataOfWeek(
				sAnde.get("begin"), sAnde.get("end"), times[0], times[1]);
	}

	@Override
	public List<ReportWeekData_Head> selectReportOfWeek(String projectId,String time) {
		
		DateTime dateTime = new DateTime(time);
				
		Map<String, String> sAnde = ReportDateUtils.getTimeInterval(dateTime.toDate());

		String[] times = gitYmlParaUtils.getSetweek().split("\\;");

		return sideTableDataMapper.selectReportOfWeek(projectId,
				sAnde.get("begin"), sAnde.get("end"), times[0], times[1]);
	}
	/**
	 * 取月报的数据,目前只适用于收敛，和沉降2个参数
	 */
	@Override
	public List<ReportWeekData_Head> selectReportOfMonth(String projectId,String time) {
		
		//DateTime dateTime = new DateTime(time);
				
		Map<String, Date> timeMap = ReportDateUtils.getTimeQuantumOfMonth(new Date());
		
		//该月第一天
		Date stime = timeMap.get("stime");
		
		DateTime stime_dateTime = new DateTime(stime);
		
		String begin = stime_dateTime.toString("yyyy-MM-dd");
		//该月最后一天
		Date etime = timeMap.get("etime");
		
		DateTime etime_dateTime = new DateTime(etime);
		
		String end = etime_dateTime.plusDays(1).toString("yyyy-MM-dd");

		String[] times = gitYmlParaUtils.getSetweek().split("\\;");
		
		System.out.println("=============begin="+begin+";end="+end);

		return sideTableDataMapper.selectReportOfWeek(projectId,
				begin,end, times[0], times[1]);
	}
}
