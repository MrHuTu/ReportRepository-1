package com.zdjc.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zdjc.report.service.AuthorityReportService;
import com.zdjc.report.service.WordUtil2007Service;

/**
 * 
 * Title: 报表控制器
 *
 * Description:处理报表业务
 *
 * @author huchao
 * @Date 2018年3月21日 上午9:45:43
 */
@RestController()
@RequestMapping(value = "/download")
@Api(value = "下载模块", tags = { "下载操作接口" })
public class WordReportsContorller {

	
	
	
	@Autowired	
	private WordUtil2007Service wordUtil2007Service;
	
	@Autowired	
	AuthorityReportService authorityReportService;
	
	
	/**
	 * 
	 * @param pojoId
	 * @param time
	 * @return
	 * @throws IOException
	 * 要增加一个参数，来表示报告文档的模式,为了暂时不影响前台的调用，这里将模式写死，接口调用方式不变
	 * D -日报
	 * W -周报
	 * M -月报
	 * Q -季报
	 * Y -年报
	 */
	
	/*@GetMapping("/downloadReport")
	@ApiOperation(value = "报告下载(非有效报告) --chao.hu", notes = "生成当前项目报告,日报，周报等统一接口(管理使用)",  httpMethod = "GET")	
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
						@ApiImplicitParam(name = "time", value = "要生成日报的日期(yyyy-MM-dd)", required = true, dataType = "String", paramType = "query"),
						@ApiImplicitParam(name = "reportType", value = "Day日报，Week周报,Month月报", required = true, dataType = "String", paramType = "query")})
	private Object generateWord(@RequestParam("projectId") String projectId,@RequestParam("time") String time,@RequestParam("reportType") String reportType) throws IOException {
					
			return DownloadUtils.downloadSolve(wordUtil2007Service.generateWord(projectId,time,reportType), false);
				

	}
	*/
	
	@GetMapping("/downloadReportAuthority")
	@ApiOperation(value = "报告下载(有效报告) --chao.hu", notes = "生成当前项目报告,日报，周报等统一接口(客户)",  httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "一份报告的唯一标示", required = true, dataType = "String", paramType = "query")})
	public Object gitPdfTest(String id){
		
		return authorityReportService.downFile(id);
		
	}
	@GetMapping("/GetFileName")
	@ApiOperation(value = "报告下载(有效报告) --chao.hu", notes = "生成当前项目报告,日报，周报等统一接口(客户)",  httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "一份报告的唯一标示", required = true, dataType = "String", paramType = "query")})
	public Object getFileName(String id){
		
		return authorityReportService.getFilename(id);
		
	}
	
}
