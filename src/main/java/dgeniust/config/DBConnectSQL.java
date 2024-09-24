package dgeniust.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dgeniust.dao.impl.IUserDAO;
import dgeniust.dao.impl.UserDAOImpl;
import dgeniust.models.UserModel;

public class DBConnectSQL {
	private static String USERNAME = "root";
	private static String PASSWORD = "dgeniust1401";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/ltweb";
	
		
	public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
	public static void main(String[] args) {
		
		 try { new DBConnectSQL();
		 System.out.println(DBConnectSQL.getDatabaseConnection()); } catch (Exception
		 e) { e.printStackTrace(); }
		 
		
	}

}
