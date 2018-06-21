import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
/**
 * Blob 大文件（二进制文件）插入与取出
 * @author madong
 *
 */
public class BlobDemo {

	public static void main(String[] args) {
		String jdbc_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/jdbc_test";
		String user = "root";
		String psw = "123456";
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet rs = null;
		OutputStream output = null;
		try {
			Class.forName(jdbc_driver);
			con = DriverManager.getConnection(db_url, user, psw);
//			pre = con.prepareStatement("insert into usertest(name,psw,myImg) values (?,'111',?)");
//			pre.setString(1, "Cool");
//			pre.setBlob(2, new FileInputStream("d:/2.jpg"));
//			pre.execute();
//			pre.setString(1, "八至");
//			pre.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("121212".getBytes()))));
//			pre.executeUpdate();
			pre = con.prepareStatement("select * from usertest where id = ?");
			pre.setInt(1, 20003);
			rs = pre.executeQuery();
			output = new FileOutputStream("d:/3.jpg");
			while(rs.next()) {
				Blob blob = rs.getBlob("myImg");
				InputStream input = blob.getBinaryStream();
				int i = -1;
				while((i = input.read()) != -1) {
					output.write(i);
				}
			}
			System.out.println("插入成功！！！");
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
