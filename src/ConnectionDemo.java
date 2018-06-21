import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
/**
 * 测试Statement进行SQL语句的插入执行
 * @author madong
 *
 */
public class ConnectionDemo {

	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc_test";
		String user = "root";
		String psw = "123456";
		Connection con = null;
		Statement stat = null;
		try {
			Class.forName(jdbc_driver);
			con = DriverManager.getConnection(db_url, user, psw);
			System.out.println(con);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			stat = con.createStatement();
			String sql = "insert into T_user(username,pwd,regTime) VALUES ('王五',56565,"+ time +")";
			stat.execute(sql);
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
