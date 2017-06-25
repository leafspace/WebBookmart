package cn.cslg.WebBookmart.database;

import java.sql.*;

public class MyDB {
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String userPassword = "admin";
	private static String dbName = "temp";
    
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	private static void getConnection() {
		try {
			String url = "jdbc:mysql://192.168.1.128:3306/" + dbName + "?user=" + userName + "&password=" + userPassword;
			Class.forName(driverName);                                         //加载数据库驱动程序类
			connection = DriverManager.getConnection(url);                     //获取数据库链接
		} catch (ClassNotFoundException e) {
			System.out.println("Error : No Mysql driver !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static PreparedStatement getPstmt(String sql) {                     //获取数据链接
		getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	public static void update() {                                              //实现数据更新操作
		try {
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet query() {                                          //实现数据库查询操作
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static void allClose() {                                            //关闭数据库操作
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
