package com.skyon.spider.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库工具类
 * @author 辉
 *
 */
public class DBUtil {
	
	public static final String URL = "jdbc:mysql://192.168.2.182:3306/test";//数据库地址
	
	public static final String USER_NAME = "jh";//用户名
	
	public static final String PWD = "jh123";//密码
	
	//连接数据库
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @param url
	 * @param name
	 * @param pwd
	 * @return
	 */
	public static Connection getDBConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PWD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
