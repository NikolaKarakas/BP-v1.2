import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import View.GUI;
import controler.DataBaseHandler;
import controler.Response;
import databaseScripts.Scripts;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.*;
public class Main  {
	
	
	
	static String username = "petergaspar";
	static String reponame = "imagesearch";
	static ArrayList<String> merged_sha_list = new ArrayList<String>();;



	
	public static void main(String[] args)  {
		
		//DataBaseHandler dataBaseHandler = new DataBaseHandler();
		Application.launch(GUI.class,args);
		/*System.out.println("11111111");
		

		URL url= new URL(username, reponame); 
		// TODO Auto-generated method stub

		//CONNECT TO THE DATABASE
		DataBaseHandler dataBaseHandler = new DataBaseHandler();
		JSONResponse jsonResponse ;
		
		
		
		
		/*
		Scripts scripts = new Scripts(dataBaseHandler.getConnection());
		scripts.empty_database();
		scripts.reset_pkeys();
		System.exit(1);*/
		
			// GET COMMIT RESPONSE	
		/*Response response =new Response();
		int page = 1;
		int commit_no=1;
		
		while(response.getResponse().length()>12) {
			
			//CONVERT IT TO JSON
			 
			 try {
				 response = new Response(url.commit_list(""),page++);

			} catch (Exception e) {
				
				//e.printStackTrace();
				if(e instanceof NotFoundException)
					System.out.print("NAAS");
				// TODO: handle exception
			}
			 System.exit(1);
			 jsonResponse = new JSONResponse(response.getResponse());
			 System.out.println(response.getResponse().substring(0,20));
			
			
			
			
			
	
			
			for (int i = 0; i<jsonResponse.getJsonArraySize(); i++)
			{
			
				
				System.out.println(commit_no++);
				
				//Napravi toliko i Commita
				
				if(merged_sha_list.contains(jsonResponse.getIterator(i).getString("sha"))) {
					//System.out.println("DUPLI");
				}
				else {
				Commit commit = new Commit(jsonResponse.getIterator(i),dataBaseHandler,  url,merged_sha_list);

				DifferenceResponse difference = new DifferenceResponse(response.get_commit_diff(url.commit_list("/"+
																		commit.getSha())),dataBaseHandler);
	
	
				//System.out.println("\n\n\n");
				}
	
			}
			
	}
		*/
		
				


		
	}
	
/*	public ArrayList<String> get_merged_shaList() {
		return merged_sha_list;
		
	}*/


}
