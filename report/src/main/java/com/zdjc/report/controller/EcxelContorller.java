package com.zdjc.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zdjc.report.service.EcxelService;

@RestController()
@RequestMapping(value = "/download")
@Api(value = "接口测试", tags = { "接口测试" })
public class EcxelContorller {


	
	@Autowired
	EcxelService ecxelService;
	
	
	
	@GetMapping("/downloadEcxel")
	@ApiOperation(value = "报告下载(有效报告) --chao.hu", notes = "生成当前项目报告,日报，周报等统一接口(客户)",  httpMethod = "GET")
	@ApiImplicitParams({ @ApiImplicitParam(name = "projectId", value = "项目Id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "beginTime", value = "起始时间", required = true, dataType = "String", paramType = "query"),
			 @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String", paramType = "query")})
	public Object gitPdfTest(String projectId,String beginTime,String endTime,HttpServletResponse response){
		
		return ecxelService.downEcxel(projectId, beginTime, endTime);
		
	}
}
