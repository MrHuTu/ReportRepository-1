package com.zdjc.report.config;

import javax.servlet.MultipartConfigElement;



import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
public class ConfigFileSize {
	
	 /**  
     * 文件上传配置  
     * @return  
     */  
    @Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();  
        //文件最大  
        factory.setMaxFileSize("100MB"); //KB,MB  
        /// 设置总上传数据总大小  
        factory.setMaxRequestSize("20MB");  
        return factory.createMultipartConfig();  
    }

}
