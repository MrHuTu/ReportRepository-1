package com.zdjc.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.zdjc.report.util.SpringContextUtil;
@EnableSwagger2
@ServletComponentScan
@SpringBootApplication
@PropertySource("application-report.yml")
//@EnableScheduling  
public class ReportApplication {
	
	public static void main(String[] args) {
		ApplicationContext reportApplication  = SpringApplication.run(ReportApplication.class, args);
		
		SpringContextUtil.setApplicationContext(reportApplication);  
	}
	
}
