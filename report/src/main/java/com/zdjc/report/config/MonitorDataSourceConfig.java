package com.zdjc.report.config;



import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * 从数据源配置
 */
@Configuration
@MapperScan(basePackages = "com.zdjc.report.mapper.monitor", sqlSessionTemplateRef = "monitorSqlSessionTemplate")
public class MonitorDataSourceConfig {
    @Bean(name = "monitorDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.monitor")
    public DataSource setDataSource() {    	
    	
    	DataSource dataSource =  DataSourceBuilder.create().build();
    	    	
        return dataSource;
    }

    @Bean(name = "monitorTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("monitorDataSource") DataSource dataSource) {
    	
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "monitorSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("monitorDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/monitor/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "monitorSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("monitorSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}