package databaseScripts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controler.DataBaseHandler;

public class Scripts {

	Connection connection;
	public Scripts (Connection connection)
	{
		this.connection = connection;
		
	}
	
	public  void empty_database() {
		
		PreparedStatement pst;

	    try {
			pst = connection.prepareStatement("DELETE FROM changes changes");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("DELETE FROM files files");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("DELETE FROM pullrequests pullrequests");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("DELETE FROM commits commits");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("DELETE FROM developers developers");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		
	}

	public void reset_pkeys() {
		
		
		PreparedStatement pst;

	    try {
			pst = connection.prepareStatement("alter sequence developers_id_seq restart with 1;");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("alter sequence files_id_seq restart with 1;");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			pst = connection.prepareStatement("alter sequence pullrequests_id_seq restart with 1;");
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
