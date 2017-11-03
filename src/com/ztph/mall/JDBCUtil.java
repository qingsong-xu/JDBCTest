package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private static final String URL_STRING = "jdbc:mysql://localhost:3306/ztph?useSSL=true";
	private static final String NAME_STRING = "root";
	private static final String PW_STRING = "Linghua457";
	private static Connection conn = null;

	private static JDBCUtil jdbcUtil = null;

	private JDBCUtil() {
		// TODO Auto-generated constructor stub
	}

	public static JDBCUtil getInstance() {
		if (jdbcUtil == null) {
			synchronized (JDBCUtil.class) {
				if (jdbcUtil == null) {
					jdbcUtil = new JDBCUtil();
				}
			}
		}
		return jdbcUtil;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL_STRING, NAME_STRING,
					PW_STRING);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
