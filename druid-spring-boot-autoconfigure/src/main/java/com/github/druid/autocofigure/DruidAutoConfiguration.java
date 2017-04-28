package com.github.druid.autocofigure;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.StringUtils;

/**
 * 
 * <p>Title: DruidAutoConfiguration</p>
 * <p>Description: druid数据源自动配置类</p>
 * @author Harry Chang
 * @Email cxiaolng@qq.com
 * @Company CassTime Technologies Co.Ltd.
 * @date 2017-04-28 22:04:15
 */
@Configuration
@ConditionalOnClass(DruidDataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.alibaba.druid.pool.DruidDataSource", matchIfMissing = true)
@EnableConfigurationProperties(DruidProperties.class)
public class DruidAutoConfiguration {

	@Bean
	public DruidDataSource dataSource(DataSourceProperties dataSourceProperties, DruidProperties druidProperties) {
		DruidDataSource druidDataSource = (DruidDataSource) dataSourceProperties.initializeDataSourceBuilder()
				.type(DruidDataSource.class).build();
		druidDataSource.configFromPropety(druidProperties.toProperties());
		druidDataSource.setInitialSize(druidProperties.getInitialSize());
		druidDataSource.setMinIdle(druidProperties.getMinIdle());
		druidDataSource.setMaxActive(druidProperties.getMaxActive());
		druidDataSource.setMaxWait(druidProperties.getMaxWait());
		druidDataSource.setConnectProperties(druidProperties.getConnectionProperties());
		return druidDataSource;
	}

	/**
	 * 注册一个StatViewServlet
	 * 
	 * @param druidProperties
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidStatViewServlet(DruidProperties druidProperties) {
		String path = druidProperties.getPath();
		String urlMapping = (path.endsWith("/") ? path + "*" : path);
		ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(), urlMapping);
		if(!StringUtils.isEmpty(druidProperties.getWebLoginUsername())){
			srb.addInitParameter("loginUsername", druidProperties.getWebLoginUsername());
		}
		if(!StringUtils.isEmpty(druidProperties.getWebLoginPassword())){
			srb.addInitParameter("loginPassword", druidProperties.getWebLoginPassword());
		}
		if(!StringUtils.isEmpty(druidProperties.getWebAllow())){
			srb.addInitParameter("allow", druidProperties.getWebAllow());
		}
		if(!StringUtils.isEmpty(druidProperties.getWebDeny())){
			srb.addInitParameter("deny", druidProperties.getWebDeny());
		}
		return srb;
	}

	@Bean
	public FilterRegistrationBean druidWebStatFilter(DruidProperties druidProperties) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		// 经常需要排除一些不必要的url，比如*.js,/jslib/*等等.
		filterRegistrationBean.addInitParameter("exclusions", druidProperties.getWebStatFilterExclusions());
		return filterRegistrationBean;
	}

}
