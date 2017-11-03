package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public static void free(ResultSet rs,Statement stmt,Connection conn){
		// 6、释放资源:为什么要关闭资源，因为底层是通过socket进行连接的，不关闭释放的话，会消耗很多资源
		try {
			if (rs != null)
				rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
