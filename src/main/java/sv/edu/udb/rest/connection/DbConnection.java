package sv.edu.udb.rest.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DbConnection {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/cursosUDB";
	
	
	// Credentials
	static final String USER_NAME 		= "root";
	static final String USER_PASSWORD 	= "L8MAksghK!teK7Pbd34jT.BhggUM2";
	
	/**
	 * TODO 
	 * Change this to protected!!!!
	 */
	protected Connection conn 		= null;
	protected Statement st 			= null;
	protected PreparedStatement pst	= null;
	protected ResultSet rs			= null;
	
	public DbConnection() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public void connect() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);
	}
	
	public void close() throws SQLException {
		if(Objects.nonNull(conn)) conn.close();
		conn = null;
	}
}
