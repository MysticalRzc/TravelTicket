package Trip_Manage;

import java.sql.*;

public class Visited_SQL{
	private static final String DRIVERNAME="com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/information?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root", PASSWD = "123456";
	private static Connection con = null;
	static{
		loadDriver();
	}
	
	private static void loadDriver(){				//打开数据库
		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()throws SQLException{
		if(con == null)
			con = DriverManager.getConnection(URL,"root","123456");
		return con;
	}
	
	public static void close(){							//关闭数据库
		try {
			if(con != null && !con.isClosed())
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet Reading(String reading){		//读取数据库
		try {
			Connection con = Visited_SQL.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(reading);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writing(String writing){		//修改数据库
		try {
			Connection con = Visited_SQL.getConnection();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(writing);
			System.out.println("数据库写入成功");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}