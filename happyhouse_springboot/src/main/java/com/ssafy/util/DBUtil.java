package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String ID = "ssafy";
	private static final String PASSWORD = "ssafy";
	
	static { // 클래스가 로드될때 한번
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnect() throws SQLException {
		return DriverManager.getConnection(URL,ID,PASSWORD);
	}
	
	public static void close(AutoCloseable... closeables) { //... 같은타입만
		try {
			for(AutoCloseable ac:closeables) {
				if(ac!=null)
					ac.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
