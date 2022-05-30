package bstorm.akimts.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;



public class OtherConnectionFactory {
	
	private static Map<AccessibleDatabase, DataSource> datasources = initMap();
	
	static Connection getConnection() throws SQLException {
		return getConnection(AccessibleDatabase.DBSLIDE);
	}
	
	static Connection getConnection(AccessibleDatabase accessDB) throws SQLException {
		// return DriverManager.getConnection(URL, USER, PSWD);
		DataSource ds = datasources.get(accessDB);
		if( ds == null )
			throw new RuntimeException("La database sélectionnée n'existe pas");
		
		return ds.getConnection();	
	}
	
	private static Map<AccessibleDatabase, DataSource> initMap(){
		Map<AccessibleDatabase, DataSource> map = new HashMap<>();
		
		map.put(AccessibleDatabase.DBSLIDE, getDataSourceFromAccessible(AccessibleDatabase.DBSLIDE));
		map.put(AccessibleDatabase.DBSLIDE_BIS, getDataSourceFromAccessible(AccessibleDatabase.DBSLIDE_BIS));
		
		return map;
	}
	
	public static enum AccessibleDatabase {
		DBSLIDE(
				"localhost",
				3306,
				"dbslide",
				"root",
				""
		),
		DBSLIDE_BIS(
				"localhost",
				3306,
				"dbslide_bis",
				"root",
				""
		);
		
		private final String serverName;
		private final int serverPort;
		private final String databaseName;
		private final String username;
		private final String password;
		
		private AccessibleDatabase(String serverName, int serverPort, String databaseName, String username,
				String password) {
			this.serverName = serverName;
			this.serverPort = serverPort;
			this.databaseName = databaseName;
			this.username = username;
			this.password = password;
		}
		
		public String getServerName() {
			return serverName;
		}
		public int getServerPort() {
			return serverPort;
		}
		public String getDatabaseName() {
			return databaseName;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		
	}
	
	private static DataSource getDataSourceFromAccessible(AccessibleDatabase accessDB) {
		MysqlDataSource ds = new MysqlDataSource(); 
		
		ds.setServerName( accessDB.getServerName() );
		ds.setPort( accessDB.getServerPort() );
		ds.setDatabaseName( accessDB.getDatabaseName() );
		ds.setUser( accessDB.getUsername() );
		ds.setPassword( accessDB.getPassword() );
		
		return ds;
	}
	
}
