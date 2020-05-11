package model;

public class URL {

	private String username;
	private String access_token="?access_token=";
	private String reponame;


	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getReponame() {
		return reponame;
	}



	public void setReponame(String reponame) {
		this.reponame = reponame;
	}



	
	public String getAccess_token() {
		return access_token;
	}



	public void setAccess_token(String access_token) {
		this.access_token = this.access_token + access_token;
	}



	private String commit_list_url;
	
	public URL() {

	}

public URL(String a,String b) {
	this.username = a;
	this.reponame=b;
}
	
	
	public String commit_list(String string) {
		return "https://api.github.com/repos/"+ username +"/"+ reponame+"/commits"+string+access_token;

	}

	
}
