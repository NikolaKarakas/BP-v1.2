package model;
import controler.*;

import java.sql.Date;
import java.text.Format;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Commit {
	
	private String sha;
	private String author;
	private String date;
	private int git_id;


	private PullRequest pullRequest;
	private ArrayList<PullRequest> pullRequests;
	DataBaseHandler dataBaseHandler;

	private Response response = new Response();
	
	
	
	public Commit(JSONObject iterator,DataBaseHandler dataBaseHandler, URL url,ArrayList<String> parrents_List) {

		pullRequests = new ArrayList<PullRequest>();
		
		setSha(iterator.getString("sha"));

		add_parrents(iterator,parrents_List);

		
		setDate(iterator.optJSONObject("commit").optJSONObject("author").getString("date"));
		format_date();
		
		if(iterator.optJSONObject("author") == null){
			//sSystem.out.println("NEMA AUTORA---" + iterator.optJSONObject("commit").optJSONObject("author").getString("name"));
			this.author=iterator.optJSONObject("commit").optJSONObject("author").getString("name");
			setGit_id(-1);
		}
		else {
			this.author=iterator.optJSONObject("author").getString("login");
			setGit_id(iterator.optJSONObject("author").getInt("id"));
		}
		this.response=response;
		check_for_pull_request(iterator,url);
		this.dataBaseHandler=dataBaseHandler;
		dataBaseHandler.check_contributor(author, git_id);
		dataBaseHandler.write_new_commit(sha,date);

		
		
		
	
	}
	
	
	public void add_parrents(JSONObject jsonObject,ArrayList<String> parrents_List) {
		 JSONArray jsonArray = jsonObject.getJSONArray("parents");
		 
		 for(int i = jsonArray.length()-2 ; i >=0 ;i--)
		 {
			 JSONObject iterator = jsonArray.getJSONObject(i);
			 parrents_List.add(iterator.getString("sha"));

		 }
		 		
	}
	
	public void format_date() {
		
		String[] splitString= this.date.split("[a-zA-Z]");
		setDate(splitString[0]);
	}
	
	public void check_for_pull_request(JSONObject object,URL url) {

		String pullrequestString = this.response.get_commit_pullrequest(url.commit_list("/"+sha+"/pulls"));

		JSONResponse jsonResponse = new JSONResponse(pullrequestString);
		if(jsonResponse.getJsonArraySize()>0) {
			
			System.out.println("FROM PULLREQ");

		for (int i =  0 ; i < jsonResponse.getJsonArraySize(); i++)
		{
			this.pullRequest = new PullRequest(jsonResponse.getIterator(i));
			pullRequests.add(pullRequest);

		}
		
		
		//System.out.println("AUTHOR IZ PULL REQUESTA");
		this.author=pullRequest.getUser_name();
		System.out.println("Author:" + author);
		setGit_id(pullRequest.getGit_id());
		}
		else {
			System.out.println("Author:" + author);
			//System.out.println("PR NE POSTOJI");
		}
	}

	
	

	public String getSha() {
		return sha;
	}


	public void setSha(String sha) {
		this.sha = sha;
	}


	public String getAuthor() {
		return author;
	}




	public PullRequest getPullRequest() {
		return pullRequest;
	}


	public void setPullRequest(PullRequest pullRequest) {
		this.pullRequest = pullRequest;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public int getGit_id() {
		return git_id;
	}

	public void setGit_id(int git_id) {
		this.git_id = git_id;
	}
}
