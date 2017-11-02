package com.ztph.mall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest {
	
	public static void main(String[] args){
		try {
			//1、注册驱动
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			//2、建立连接,连接时，数据库名称应该为schame对应名称，同时需要加上允许采用ssl方式，即useSSL=true;
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ztph?useSSL=true","root","Linghua457");
			//3、创建语句
			Statement stmt = conn.createStatement();
			//4、执行语句
			ResultSet rs =stmt.executeQuery("select * from teacher;");
			//5、遍历查询
			while(rs.next()){
				System.out.print(rs.getObject(1)+":"+rs.getObject(2)+":"+rs.getObject(3)+":"+rs.getObject(4)+":"+rs.getObject(5)+":"+rs.getObject(6));
			}
			//6、释放资源
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
