import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
/**
 * Clob 大文件（文本对象）插入与取出
 * @author madong
 *
 */
public class ClobDemo {

	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc_test";
		String user = "root";
		String psw = "123456";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		try {
			Class.forName(jdbc_driver);
			con = DriverManager.getConnection(db_url, user, psw);
			System.out.println(con);
//			pre = con.prepareStatement("insert into usertest(name,psw,myInfo) values (?,'111',?)");
//			pre.setString(1, "马东");
//			pre.setClob(2, new FileReader(new File("D:/1.txt")));
//			pre.setString(1, "八至");
//			pre.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("121212".getBytes()))));
//			pre.executeUpdate();
			pre = con.prepareStatement("select * from usertest where id = ?");
			pre.setInt(1, 20001);
			rs = pre.executeQuery();
			while(rs.next()) {
				Clob clob = rs.getClob("myInfo");
				Reader reader = clob.getCharacterStream();
				int i = -1;
				while((i = reader.read()) != -1) {
					System.out.print((char)i);
				}
			}
			System.out.println("查询成功！！！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
