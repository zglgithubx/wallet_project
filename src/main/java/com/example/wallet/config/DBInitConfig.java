package com.example.wallet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.*;

/**
 * @ClassName DBInitConfig
 * @Author ZhuGuangLiang <786945363@qq.com>
 * @Date 2023/02/25 10:07
 * 检查数据库执行类（检查是否存在指定的数据库–不存在则创建数据库-否则跳过）
 */

@Configuration
public class DBInitConfig {
	private static final Logger LOG = LoggerFactory.getLogger(DBInitConfig.class);

	private static final String SCHEMA_NAME = "schema_name";
	/**
	 * com.mysql.cj.jdbc.Driver
	 */
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	/**
	 * jdbc_url
	 */
	@Value("${spring.datasource.db-url}")
	private String url;
	/**
	 * 账号名称
	 */
	@Value("${spring.datasource.username}")
	private String username;
	/**
	 * 账号密码
	 */
	@Value("${spring.datasource.password}")
	private String password;
	/**
	 * 需要创建的数据名称
	 */
	@Value("${spring.datasource.db-name}")
	private String dbName;

	@PostConstruct
	public void init() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.error("JDBC URL解析错误", e);
		}

		try (Connection connection = DriverManager.getConnection(url, username, password);
			 Statement statement = connection.createStatement()) {
			String sal = "select schema_name from information_schema.schemata where schema_name = " + "'" + dbName + "'";
			//查询返回的结果集
			ResultSet resultSet = statement.executeQuery(sal);
			if (!resultSet.next()) {
				//查不到数据库，执行数据库初始化脚本
				LOG.warn("查不到数据库({})", dbName);
				String createDb = "CREATE DATABASE IF NOT EXISTS " + dbName
						+ " DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci ";
				connection.setAutoCommit(false);
				statement.execute(createDb);
				connection.commit();
				LOG.info("创建数据库（{}）成功", dbName);
			} else {
				String databaseName = resultSet.getString(SCHEMA_NAME);
				LOG.warn("已经存在数据库({})", databaseName);
			}
			if (resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			LOG.error("启动项目检查数据库是否创建报错", e);
		}

	}
}
