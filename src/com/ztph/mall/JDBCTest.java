package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {
		temPlate();
	}

	static void temPlate() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ztph?useSSL=true", "root",
					"Linghua457");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from teacher;");
			while (rs.next()) {
				System.out.print("id = " + rs.getObject(1) + ":"
						+ rs.getObject(2) + ":" + rs.getObject(3) + ":"
						+ rs.getObject(4) + ":" + rs.getObject(5) + ":"
						+ rs.getObject(6));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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

	static void test() {
		try {
			// 1、注册驱动
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			// 2、建立连接,连接时，数据库名称应该为schame对应名称，同时需要加上允许采用ssl方式，即useSSL=true;
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ztph?useSSL=true", "root",
					"Linghua457");
			// 3、创建语句
			Statement stmt = conn.createStatement();
			// 4、执行语句
			ResultSet rs = stmt.executeQuery("select * from teacher;");
			// 5、遍历查询
			while (rs.next()) {
				System.out.print(rs.getObject(1) + ":" + rs.getObject(2) + ":"
						+ rs.getObject(3) + ":" + rs.getObject(4) + ":"
						+ rs.getObject(5) + ":" + rs.getObject(6));
			}
			// 6、释放资源
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
