package com.zdjc.report.mapper.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zdjc.report.model.fictitious.ReportWeekData_Head;
import com.zdjc.report.model.fictitious.SideTableData;
/**
 * 查询水平位移，和沉降的数据，用来填充word文档报告
 * @author 胡超  2018年4月18日19:06:37
 *
 */
public interface SideTableDataMapper {
	
	public List<SideTableData> selectSideTableData(@Param("pojoId")int pojoId,@Param("time")String time);
	
	
	public List<SideTableData> selectSideTableDataOfWeek(@Param("begin")String pojoId,@Param("end")String time,@Param("pointB")String pointB, @Param("pointE")String pointE);
	
	
	public  List<ReportWeekData_Head> selectReportOfWeek(@Param("projectId")String projectId, @Param("begin")String begin,@Param("end")String end,@Param("pointB")String pointB,@Param("pointE") String pointE);
	
	
	
}
