package com.zdjc.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zdjc.report.model.AuthorityReport;
import com.zdjc.report.model.fictitious.MyPageMode;
import com.zdjc.report.model.fictitious.Result;
import com.zdjc.report.service.AuthorityReportService;
import com.zdjc.report.service.ReportContentDayService;

@RestController()
@RequestMapping(value = "/report")
@Api(value = "得到报告配置信息,", tags = { "得到报告配置信息" })
/**
*　　┏┓　　　┏┓+ +
*　┏┛┻━━━┛┻┓ + +
*　┃　　　　　　　┃ 　
*　┃　　　━　　　┃ ++ + + +
* ████━████ ┃+

*　┃　　　　　　　┃ +
*　┃　　　┻　　　┃
*　┃　　　　　　　┃ + +
*　┗━┓　　　┏━┛
*　　　┃　　　┃　　　　　　　　　　　
*　　　┃　　　┃ + + + +
*　　　┃　　　┃
*　　　┃　　　┃ + 神兽保佑
*　　　┃　　　┃ 代码无bug　　
*　　　┃　　　┃　　+　　　　　　　　　
*　　　┃　 　　┗━━━┓ + +
*　　　┃ 　　　　　　　┣┓
*　　　┃ 　　　　　　　┏┛
*　　　┗┓┓┏━┳┓┏┛ + + + +
*　　　　┃┫┫　┃┫┫
*　　　　┗┻┛　┗┻┛+ + + +
*/
public class ReportMassageContorller {
	
	@Autowired	
	ReportContentDayService reportContentDayService;
	
	@Autowired	
	AuthorityReportService authorityReportService;
	/**
	 * 查询日报信息
	 * @return
	 *//*
	@ApiIgnore
	@GetMapping("/getDayMsg")
	@ApiOperation(value = "日报信息--chao.hu", notes = "日报信息",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query")})
	public Result<List<ReportContentDay>> gitCfgD(String projectId){
		
		return  new Result<List<ReportContentDay>>().success("查询成功", reportContentDayService.selectDayConfigById(projectId));
		
	}*/
	/*@GetMapping("/getFilename")
	@ApiOperation(value = "查询当前报告类型下的全部有效报告文件--chao.hu", notes = "日报信息",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "reportType", value = "Day日报，Week周报", required = true, dataType = "String", paramType = "query")})
	public Result<List<String>> gitFilename(String projectId, String reportType){
		
		
		return authorityReportService.gitFilename(projectId, reportType);
		
	}*/
	@GetMapping("/getAllFileMassage")
	@ApiOperation(value = "查询当前报告类型下的全部有效报告文件--chao.hu", notes = "日报信息",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "currIndex", value = "当前页码", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "pageSize", value = "每页数据量", required = true, dataType = "String", paramType = "query"),
	@ApiImplicitParam(name = "reportType", value = "报告类型:Day(日报),Week(周报),Month(月报),Quarter(季报),Year(年报)(区分大小写)", required = true, dataType = "String", paramType = "query")})
	
	public Result<MyPageMode<AuthorityReport>> getAllFileMassage(String projectId,String currIndex ,String pageSize, String reportType){
		
		
		return new Result<MyPageMode<AuthorityReport>>().success("查询成功", authorityReportService.getReportAllMassage(projectId,currIndex,pageSize,reportType));
		
	}
}
