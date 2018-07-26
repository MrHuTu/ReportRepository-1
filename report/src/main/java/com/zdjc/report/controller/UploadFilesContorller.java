package com.zdjc.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartRequest;

import com.zdjc.report.model.fictitious.Result;
import com.zdjc.report.service.AuthorityReportService;

/**
 * 
 * @author 胡超
 * 处理报告配置相关接口
 * 2018年5月16日16:25:21
 *
 */
@RestController()
@RequestMapping(value = "/uploadfiles")
@Api(value = "文件上传", tags = { "文件上传" })
public class UploadFilesContorller {
	
	/*@Autowired
	ConfigReportService configReportService;*/
	
	@Autowired
	AuthorityReportService authorityReportService;
	/**
	 * 配置日报
	 * @param file
	 * @param projectId
	 * @param reportMain
	 * @param reportWrite
	 * @param reportApproval
	 * @param reportRatify
	 * @param projectSurvey
	 * @param projectAchievement
	 * @param projectSuggest
	 * @param remark
	 * @return
	 * @throws IOException
	 */
	/*@PostMapping("/configDay")
	@ApiOperation(value = "配置日报--chao.hu", notes = "配置日报",  httpMethod = "POST")	
    public  Result<String>  cfgD( MultipartRequest file,String projectId,String reportMain,String reportWrite,String reportApproval,String reportRatify,String projectSurvey,String projectAchievement,String projectSuggest,String remark) throws IOException {
		
      return new Result<String>().success(configReportService.Cfg(file, projectId, reportMain, reportWrite, reportApproval, reportRatify, projectSurvey, projectAchievement, projectSuggest, remark));
    }*/
	
	@PostMapping("/authorityReport")
	@ApiOperation(value = "掃描件上傳,具有權威效應的報告文當，提供給客戶使用--chao.hu", notes = "配置日报",  httpMethod = "POST")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
    @ApiImplicitParam(name = "reportType", value = "报告类型:Day(日报),Week(周报),Month(月报),Quarter(季报),Year(年报)(区分大小写)", required = true, dataType = "String", paramType = "query"),
    @ApiImplicitParam(name = "timeOfReport", value = "报告隶属时间", required = true, dataType = "String", paramType = "query"),
	 @ApiImplicitParam(name = "personInCharge", value = "责任人", required = true, dataType = "String", paramType = "query")})
	public Result<String> authorityReport(MultipartRequest file,String projectId,String reportType,String timeOfReport,String personInCharge){
		
		return new Result<String>().setMsg(authorityReportService.Cfg(file, projectId,reportType,timeOfReport,personInCharge));
		
	}
	
	
	@GetMapping("/getFilename")
	@ApiOperation(value = "查询文件名", notes = "查询文件名",  httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "报表唯一Id", required = true, dataType = "String", paramType = "query")})
	public Result<String> getFilename(String id){
		
		return authorityReportService.getFilename(id);
		
	}
	
}
