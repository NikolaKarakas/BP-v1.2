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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import controler.DataBaseHandler;
import controler.Response;
import databaseScripts.Scripts;
import model.*;
public class Main {
	
	
	
	static String username = "nikolakarakas95";
	static String reponame = "test2";
	static ArrayList<String> merged_sha_list = new ArrayList<String>();;
	



	
	public static void main(String[] args) {

		URL url= new URL(username, reponame);
		// TODO Auto-generated method stub

		//CONNECT TO THE DATABASE
		DataBaseHandler dataBaseHandler = new DataBaseHandler();
		JSONResponse jsonResponse ;
		
		// GET COMMIT RESPONSE				
		Response response = new Response(url.commit_list(""));
		
		//CONVERT IT TO JSON
		 jsonResponse = new JSONResponse(response.getResponse());
		
		System.out.println("SIZE " + jsonResponse.getJsonArraySize());
		
		/*Scripts scripts = new Scripts(dataBaseHandler.getConnection());
		scripts.empty_database();
		scripts.reset_pkeys();
		System.exit(1);*/
		

		
		for (int i = 0; i<jsonResponse.getJsonArraySize(); i++)
		{
			
			
			
			//Napravi toliko i Commita
			
			if(merged_sha_list.contains(jsonResponse.getIterator(i).getString("sha"))) {
				System.out.println("DUPLI");
			}
			else {
			Commit commit = new Commit(jsonResponse.getIterator(i),dataBaseHandler,  url,merged_sha_list);
			System.out.println(commit.getSha());
			DifferenceResponse difference = new DifferenceResponse(response.get_commit_diff(url.commit_list("/"+
																	commit.getSha())),dataBaseHandler);


			System.out.println("\n\n\n");
			}

		}
		
		
		
		
		
		
		System.exit(1);
		
		
		/*
		System.exit(1);
		//GET RESPONSE
		 response = new Response(pullRequestListUrl);
		
		//CONVERT IT TO JSON
		 jsonResponse = new JSONResponse(response.getResponse());
		
		//MAKE EVERY RESPOND TO PULL REQUEST OBJECT
		ArrayList<PullRequest> pullRequests = new ArrayList<PullRequest>();
		for (int i =  0 ; i < jsonResponse.getJsonArraySize(); i++)
		{
			//Napravi toliko i Pull Requestova
			PullRequest pullRequest = new PullRequest(jsonResponse.getIterator(i),dataBaseHandler);
			
		
			// get user from pull
						
		
			//GET DIFFS FROM PULL REQUESTS
			response = new Response(pullRequest.getDiff_url());
		
			
			//add to list
			pullRequests.add(pullRequest);
		}*/
		

		
	}
	
	public ArrayList<String> get_merged_shaList() {
		return merged_sha_list;
		
	}

}
