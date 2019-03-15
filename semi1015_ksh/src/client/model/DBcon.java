package client.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
	static Connection con;
	
	public DBcon() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pw="123456";
		
		con=DriverManager.getConnection(url,user,pw);
	}

	public static Connection getConnection() throws Exception {
		if (con==null) {
			new DBcon();
		}
		return con;
	}
	
}
