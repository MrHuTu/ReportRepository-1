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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
/**
 * 主数据源配置配置
 * @author Administrator
 *
 */
@Configuration
@MapperScan(basePackages = "com.zdjc.report.mapper.report", sqlSessionTemplateRef = "reportSqlSessionTemplate")
public class ReportDataSourceConfig {
    @Bean(name = "reportDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.report")
    @Primary
    public DataSource setDataSource() {
    	
    	DataSource dataSource =  DataSourceBuilder.create().build();
    	
        return dataSource;
    }

    @Bean(name = "reportTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("reportDataSource") DataSource dataSource) {
    	
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "reportSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("reportDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/report/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "reportSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("reportSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}