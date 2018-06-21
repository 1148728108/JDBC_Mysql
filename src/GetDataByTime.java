import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.RunnableScheduledFuture;

import javafx.scene.input.DataFormat;

public class GetDataByTime {
	private static long gettime(String str) {
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return data.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc_test";
		String user = "root";
		String psw = "123456";
		Connection con = null;
		PreparedStatement stat = null;
		try {
			Class.forName(jdbc_driver);
			con = DriverManager.getConnection(db_url, user, psw);
			stat = con.prepareStatement("select * from t_user where regTime > ? and regTime < ? order by regTime");
			Timestamp start = new Timestamp(gettime("2018-4-17 2:12:1"));
			Timestamp end = new Timestamp(gettime("2018-4-17 2:32:3"));
			stat.setTimestamp(1, start);
			stat.setTimestamp(2, end);
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1) + "---" + rs.getString(2) + "---" + rs.getString(3) + "---" + rs.getTimestamp(4));
			}
			System.out.println("end");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(stat != null)
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(con != null)
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

	}
}
