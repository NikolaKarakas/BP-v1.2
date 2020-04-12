package controler;

import java.security.PKCS12Attribute;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.validation.metadata.ReturnValueDescriptor;

import org.omg.CORBA.PRIVATE_MEMBER;

public class DataBaseHandler {
	
	private  Connection connection;
	
	private int file_pkey;
	private int pull_pkey;
	private int user_pkey;
	private int commit_pkey;

	
	
	public DataBaseHandler() {
		
		String url = "jdbc:postgresql://localhost:5432/bprojekt1";
        String user = "postgres";
        String password = "nikola123";
		
        
        try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(DataBaseHandler.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
              
	}

	public void check_contributor(String user_name,int git_id) {
	
           PreparedStatement pst;
           

	    try {
			pst = connection.prepareStatement("SELECT id FROM developers where  user_name = ?");
			 pst.setString(1, user_name);
		        ResultSet rs = pst.executeQuery();
		        	
		        if(rs.next()==false)
		        {
		        	//DOES NOT EXIST ADD NEW USER
	        		System.out.println("USER DOES NOT EXIST");
	        		 write_new_user(user_name, git_id);
		        }
		        else
		        	do { 
		        		user_pkey = rs.getInt(1);
		        		System.out.println("USER ID EXISTS WITH ID: " +  user_pkey);
		          	 	}
		        	while (rs.next());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	   
	}
	
	public void write_new_user(String user_name, int git_id) {
		
		PreparedStatement pst;
		System.out.println("...ADDING NEW USER...");

	    try {
			pst = connection.prepareStatement("INSERT INTO developers(user_name,git_id) VALUES (?,?)");
			pst.setString(1, user_name);
			pst.setInt(2, git_id);
			
			pst.executeUpdate();

			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	    
	     check_contributor(user_name, git_id);
	    
	   	   
	}
	
	
	public void write_new_file(String file_name) {
		
		PreparedStatement pst;	
		
		

	    try {
			pst = connection.prepareStatement("INSERT INTO files(name,creator,owner) VALUES (?,?,?) RETURNING id;");
			pst.setString(1,file_name);
			pst.setLong(2, user_pkey);
			pst.setLong(3, user_pkey);
			ResultSet keySet = pst.executeQuery();
			System.out.println("ADDING NEW FILE: "+ file_name);
			
			if(keySet.next()) {
				file_pkey = keySet.getInt(1);
				System.out.println("ID NOVOG PR FAJLA : " + keySet.getInt(1));
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				 try {
						pst = connection.prepareStatement("SELECT id FROM files where  name = ?");
						 pst.setString(1, file_name);
					        ResultSet rs = pst.executeQuery();
					        	
					        
					        if(rs.next()==true)
					        	do { 
					        			file_pkey = rs.getInt(1);
										System.out.println("FILE " + file_name + " " +  file_pkey+ " ALREADY EXISTS");

					          	 	}
					        	while (rs.next());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
	  
			
	}
	
	
	public  void write_new_pullrequest(int number) {
		
		PreparedStatement pst;
		System.out.println("...ADDING NEW PULL REQUEST...");

	    try {
			pst = connection.prepareStatement("INSERT INTO pullrequests(number,developer,merged) VALUES (?,?,?) RETURNING ID");
			pst.setInt(1, number);
			pst.setInt(2, user_pkey);
			pst.setBoolean(3,false);

			ResultSet keySet = pst.executeQuery();
			
			if(keySet.next()) {
				pull_pkey = keySet.getInt(1);
				System.out.println("ID NOVOG PR JE : " + keySet.getInt(1));
			}
		
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
public  void write_new_commit(String sha,String date_commited) {
		
		PreparedStatement pst;
		System.out.println("...ADDING NEW COMMIT...");

	    try {
			pst = connection.prepareStatement("INSERT INTO commits(sha,date_commited,developer) VALUES (?,?,?) RETURNING ID");
			pst.setString(1, sha);
			pst.setDate(2, Date.valueOf(date_commited));
			pst.setInt(3, user_pkey);

			ResultSet keySet = pst.executeQuery();
			
			if(keySet.next()) {
				commit_pkey = keySet.getInt(1);
				System.out.println("ID NOVOG COMMITA JE : " + keySet.getInt(1));
			}
		
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Connection getConnection() {
		return connection;
	}
	
	public void add_new_change(int line_num,String operation, int length) {

		
		PreparedStatement pst;

	    try {
			pst = connection.prepareStatement("INSERT INTO changes(commit,file,line,length,operation) VALUES (?,?,?,?,?);");
			pst.setInt(1, commit_pkey);
			pst.setInt(2, file_pkey);
			pst.setInt(3,line_num);
			pst.setInt(4,length);
			pst.setString(5,operation);
			pst.executeUpdate();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	


	
}