import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 测试PreparedStatement，相较于Statement具有具有高效并且防止SQL注入
 * @author madong
 *
 */
public class ConnectionDemo2 {

	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc_test";
		String user = "root";
		String psw = "123456";
		Connection con = null;
		PreparedStatement pre = null;
		try {
			Class.forName(jdbc_driver);
			con = DriverManager.getConnection(db_url, user, psw);
			System.out.println(con);
			
			String sql = "insert into T_user(username,pwd,regTime) VALUES (?,?,?)";
			pre = con.prepareStatement(sql);
			pre.setString(1, "Marry");
			pre.setString(2, "55555");
			pre.setDate(3, new Date(System.currentTimeMillis()));
			pre.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pre != null)
				pre.close();
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
