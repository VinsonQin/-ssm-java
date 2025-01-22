package com.qdu.config;

import javax.sql.DataSource;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.qdu.service"})
@EnableTransactionManagement
@MapperScan(basePackages = {"com.qdu.mapper"})
@PropertySource({"classpath:config/jdbc.properties"})
public class SpringConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DruidDataSource dataSource() {
		
		//属性文件中属性的值都是字符串，所以如果需要转换类型，需要手动转换
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));

		dataSource.setInitialSize(Integer.parseInt(env.getProperty("initialSize")));
		dataSource.setMaxActive(Integer.parseInt(env.getProperty("maxActive")));
		dataSource.setMaxWait(Integer.parseInt(env.getProperty("maxWait")));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("minIdle")));
		dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("minEvictableIdleTimeMillis")));

		dataSource.setValidationQuery(env.getProperty("validationQuery"));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("minEvictableIdleTimeMillis")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(env.getProperty("testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.parseBoolean(env.getProperty("testOnReturn")));

		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory factoryBean(DataSource ds) throws Exception {
		SqlSessionFactoryBean factoryBean=new SqlSessionFactoryBean();
		factoryBean.setDataSource(ds);
		factoryBean.setTypeAliasesPackage("com.qdu.entity,com.qdu.handler");
		// 添加 PageHelper 插件
		PageInterceptor pageInterceptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("helperDialect", "mysql"); // 配置数据库方言
		properties.setProperty("reasonable", "true");     // 开启分页合理化
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "count=countSql");
		pageInterceptor.setProperties(properties);

		// 设置拦截器
		factoryBean.setPlugins(pageInterceptor);
		return factoryBean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
