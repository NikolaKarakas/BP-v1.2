package main;
import java.awt.List;
import java.awt.im.InputContext;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;

import View.GUI;
import controler.DataBaseHandler;
import controler.DataBaseScripts;
import controler.MainViewController;
import controler.Response;
import controler.ScriptParser;
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
		
		/*ScriptPython scriptPython = new ScriptPython();
        scriptPython.runScript();*/
		
		//MainViewController mainViewController = new MainViewController();
		//mainViewController.set_tabels();
			       
		//PythonScript pythonScript = new PythonScript(argStrings);
		DataBaseHandler dataBaseHandler = new DataBaseHandler();
		
		/*DataBaseScripts scripts = new DataBaseScripts(dataBaseHandler.getConnection());
		scripts.empty_database();
		scripts.reset_pkeys();
		System.exit(1);*/
		
		Application.launch(GUI.class,args);
		/*System.out.println("11111111");
		 * 
		 */
		

		//URL url= new URL(username, reponame); 
		// TODO Auto-generated method stub

		//CONNECT TO THE DATABASE
		//JSONResponse jsonResponse ;
		
		
		
		
		
		
		
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
