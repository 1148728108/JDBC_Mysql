package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseUse {
	static DataBaseConnection dbc = null;
	static Connection conn = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
	
	public DataBaseUse() {
		dbc = new DataBaseConnection();
		conn = dbc.getConnection();
	}
	
	public static void setData(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbc.Close();
		}
	}
	
	public static ResultSet getResultSet() {
		try {
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
