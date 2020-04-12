package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	private final String url = "jdbc:postgresql://localhost:5432/bprojekt1";
    private final String user = "postgres";
    private final String password = "nikola123";
    
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
        
        
    }
}
