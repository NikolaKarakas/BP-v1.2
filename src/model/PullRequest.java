package model;

import java.sql.Connection;
import java.util.ArrayList;

import org.json.JSONObject;

import controler.DataBaseHandler;



public class PullRequest {


	private String diff_url;
	private String user_name;
	private String merge_commit_sha;

	private int git_id;
	private int number;
	DataBaseHandler dataBaseHandler;
	


	
	
	
	
	public PullRequest(JSONObject iterator,DataBaseHandler dataBaseHandler) {

		diff_url=iterator.getString("diff_url");
		number=iterator.getInt("number");

		user_name= iterator.optJSONObject("user").getString("login");
		git_id= iterator.optJSONObject("user").getInt("id");
		
		this.dataBaseHandler=dataBaseHandler;
		dataBaseHandler.check_contributor(user_name, git_id);
		dataBaseHandler.write_new_pullrequest(number);
	
	}
	
	public PullRequest(JSONObject iterator) {

		diff_url=iterator.getString("diff_url");
		number=iterator.getInt("number");
		merge_commit_sha=iterator.getString("merge_commit_sha");

		user_name= iterator.optJSONObject("user").getString("login");
		git_id= iterator.optJSONObject("user").getInt("id");

	
	}
	
	
	public String getUser_name() {
		return user_name;
	}

	public String getDiff_url() {
		return diff_url;
	}

	public int getGit_id() {
		return git_id;
	}

	public String getMerge_commit_sha() {
		return merge_commit_sha;
	}

	public void setMerge_commit_sha(String merge_commit_sha) {
		this.merge_commit_sha = merge_commit_sha;
	}
	
}
