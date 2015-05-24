package lt.web.service.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqliteConnector {
	
	private static DataSource dataSource;
	
	static {
		Context initCtx;
		try{
			initCtx = new InitialContext();
			dataSource = (DataSource) initCtx.lookup("java:comp/env/jdbc/database");
		} catch (NamingException e){
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		if(dataSource != null){
			return dataSource.getConnection();
		}
		return null;
	}
	
}
