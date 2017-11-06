package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JDBCTest {

	public static void main(String[] args) {
//		temPlate();
//		readData("'or 1 or'");
//		readData("徐青松");//参数为 'or 1 or'含有sql关键字or或and时存在依赖注入问题
		insertDate("向日葵","女","已婚",25,"音乐");
	}
	
	private static void insertDate(String name,String sex,String marrital_status,int age,String master){
		Connection conn = null;
		PreparedStatement preStmt = null;
		
		try {
			conn = JDBCUtil.getInstance().getConnection();
			String sql = "insert into teacher(name,sex,marrital_status,age,master) values(?,?,?,?,?)";
			preStmt = conn.prepareStatement(sql);
			preStmt.setString(1, name);
			preStmt.setString(2, sex);
			preStmt.setString(3, marrital_status);
			preStmt.setInt(4, age);
			preStmt.setString(5, master);
			preStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.free(null, preStmt, conn);
		}
  	}

	private static void readData(String name) {
		Connection conn = null;
		//PreparedStatement sql预处理语句
//		PreparedStatement preStmt = null;
		Statement preStmt = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getInstance().getConnection();
			
			/**
			 * 非预处理语句测试
			 */
			String sql = "select * from teacher where name='"+name+"';";
			System.out.println("sql = "+sql);
			preStmt = conn.createStatement();
			rs = preStmt.executeQuery(sql);
			
			/**
			 * 预处理语句测试
			 */
//			String sql = "select id,name,age,sex,marrital_status,master from teacher where name = ?";
//			preStmt = conn.prepareStatement(sql);
//			preStmt.setString(1,name);
//			rSet = preStmt.executeQuery();

			while (rs.next()) {
				System.out.println("id = "+rs.getString("id")+"  name = "+rs.getString("name")+"  age = "+rs.getInt("age")+"  sex = "+rs.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtil.free(rs, preStmt, conn);
		}
		
	
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
			// 6、释放资源
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
			// 2、获取数据库连接，连接时有时会报错ssl认证失败，需要加参数useSSL=true
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ztph?useSSL=true", "root",
					"Linghua457");
			// 3、建立语句
			Statement stmt = conn.createStatement();
			// 4、执行sql语句
			ResultSet rs = stmt.executeQuery("select * from teacher;");
			// 5、处理sql语句结果
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
