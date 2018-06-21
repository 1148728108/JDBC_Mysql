package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
	static Connection conn = null;
	static Properties properties = null;
	
	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("database.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			Class.forName(properties.getProperty("mysql_driver"));
			conn = DriverManager.getConnection(properties.getProperty("mysqldb_url"),
					properties.getProperty("mysql_user"),properties.getProperty("mysql_password"));
			System.out.println("数据库连接成功！！！");
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("找不到数据库驱动程序！！！");
			return null;
		} catch (SQLException e) {
			System.out.println("数据库连接出错！！！");
			return null;
		}
	}
	
	public static void Close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}















