package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {

	public static void main(String[] args) {
//		temPlate();
		readData("'or 1 or'");
	}

	private static void readData(String name) {
		Connection conn = null;
		//PreparedStatement Ԥ����sql,���Է�ֹ����ע��
//		PreparedStatement preStmt = null;
		Statement preStmt = null;
		ResultSet rSet = null;
		try {
			conn = JDBCUtil.getInstance().getConnection();
			
			/**
			 * ��ʾ��Ԥ����ʽ
			 */
			String sql = "select * from teacher where name='"+name+"';";
			System.out.println("sql = "+sql);
			preStmt = conn.createStatement();
			rSet = preStmt.executeQuery(sql);
			
			/**
			 * ��ʾԤ������䷽ʽ
			 */
//			String sql = "select id,name,age,sex,marrital_status,master from teacher where name = ?";
//			preStmt = conn.prepareStatement(sql);
//			preStmt.setString(1,name);
//			rSet = preStmt.executeQuery();

			while (rSet.next()) {
				System.out.println("id = "+rSet.getString("id")+"  name = "+rSet.getString("name")+"  age = "+rSet.getInt("age")+"  sex = "+rSet.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// 6���ͷ���Դ:ΪʲôҪ�ر���Դ����Ϊ�ײ���ͨ��socket�������ӵģ����ر��ͷŵĻ��������ĺܶ���Դ
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
			// 1��ע������
			// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			// System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			// 2����������,����ʱ�����ݿ�����Ӧ��Ϊschame��Ӧ���ƣ�ͬʱ��Ҫ�����������ssl��ʽ����useSSL=true;
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ztph?useSSL=true", "root",
					"Linghua457");
			// 3���������
			Statement stmt = conn.createStatement();
			// 4��ִ�����
			ResultSet rs = stmt.executeQuery("select * from teacher;");
			// 5��������ѯ
			while (rs.next()) {
				System.out.print(rs.getObject(1) + ":" + rs.getObject(2) + ":"
						+ rs.getObject(3) + ":" + rs.getObject(4) + ":"
						+ rs.getObject(5) + ":" + rs.getObject(6));
			}
			// 6���ͷ���Դ
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
