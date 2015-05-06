package lt.web.service.dao;

import java.sql.Connection;
import java.sql.SQLException;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;

import java.sql.DriverManager;
//import java.sql.DatabaseMetaData;

public class SqliteConnector {
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\database2");
		if(conn != null)
			return conn;
		return null;
	}
}
