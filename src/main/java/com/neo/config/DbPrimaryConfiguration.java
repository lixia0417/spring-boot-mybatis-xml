package com.neo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import com.neo.route.RoutingDataSource;
import com.neo.utils.Constants;

@Configuration
//@ImportResource(locations={"classpath:application-bean.xml"})
public class DbPrimaryConfiguration {


	@Bean("dynamicDataSource")
	DataSource dynamicDataSource(@Autowired @Qualifier("masterDataSource") DataSource masterDataSource,
			@Autowired @Qualifier("slaveDataSource") DataSource slaveDataSource) {
		System.err.println("create routing datasource...");
		Map<Object, Object> map = new HashMap<>();
		map.put(Constants.MasterDataSource, masterDataSource);
		map.put(Constants.SlaveDataSource, slaveDataSource);
		RoutingDataSource routing = new RoutingDataSource();
		routing.setTargetDataSources(map);
		routing.setDefaultTargetDataSource(masterDataSource);
		return routing;
	}
	
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired @Qualifier("dynamicDataSource") DataSource dynamicDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource 作为数据源则不能实现切换
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }

}
