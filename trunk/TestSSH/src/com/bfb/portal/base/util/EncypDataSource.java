package com.bfb.portal.base.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 继承spring的DriverManagerDataSource类，重新实现密码获取方式，配置文件中为加密后的密码
 * @author Administrator
 *
 */
public class EncypDataSource extends DriverManagerDataSource {
	private String password;
	private String driverClassName;
	private String url;
	private String username;
	
	public void setPassword(String password) {
		try {
			System.out.println("src pwd======"+password);
			this.password = SecurityUtil.decrypt(password);
			
			System.out.println("decrypt pwd======"+this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Return the JDBC password to use for connecting through the Driver.
	 */
	public String getPassword() {
		return this.password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
