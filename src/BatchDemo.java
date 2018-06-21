import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Statement中的Batch一次可加入上万条数据
 * @author madong
 *
 */
public class BatchDemo {

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
			con.setAutoCommit(false);
			stat = con.createStatement();
			for(int i = 0;i < 20000; i++) {
			stat.addBatch("insert into usertest(name,psw,time) VALUES ('傻子" + i + "号',56565,NOW())");
			}
			stat.executeBatch();
			con.commit();
			System.out.println("插入成功！！！");
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
