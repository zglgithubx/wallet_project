package com.example.wallet.config;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;
import java.sql.SQLException;
/**
 * @ClassName DataSourceConfig
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 10:10
 * 控制数据源初始化在检查数据库完成之后
 */
@Configuration
public class DataSourceConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

	/**
	 * spring监控，druid的拦截器
	 *
	 * @return
	 */
	@Bean
	public DruidStatInterceptor druidStatInterceptor() {
		return new DruidStatInterceptor();
	}

	@Bean
	@Scope("prototype")
	public JdkRegexpMethodPointcut druidStatPointcut() {
		JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
		pointcut.setPatterns("com.xx.xxxx.*.service.*.service.*", "com.xxxx.xxxx.*.service.*.mapper.*");
		return pointcut;
	}

	/**
	 * aop配置
	 *
	 * @param druidStatInterceptor
	 * @param druidStatPointcut
	 * @return
	 */
	@Bean
	public DefaultPointcutAdvisor druidStatAdvisor(DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setAdvice(druidStatInterceptor);
		advisor.setPointcut(druidStatPointcut);
		return advisor;
	}

	private DruidDataSource dataSource;

	@Bean
	@DependsOn("DBInitConfig")
	public DataSource init(
			@Value("${spring.datasource.driver-class-name}")
					String driverClassName,
			@Value("${spring.datasource.username}")
					String username,
			@Value("${spring.datasource.password}")
					String password,
			@Value("${spring.datasource.url}")
					String url)
	{
		if (dataSource != null) {
			return dataSource;
		}
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driverClassName);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setUrl(url);
		return druidDataSource;
	}
}
