package model;

public class URL {

	private String username;
	private String reponame;
	private String access_token="?access_token=dc74d0d65b14d5e5795ec09b6c622671893b0ffe";
	
	private String commit_list_url;
	
	
	
	public URL(String username,String reponame) {
		this.username= username;
		this.reponame= reponame;
	}

	
	
	public String commit_list(String string) {
		return "https://api.github.com/repos/"+ username +"/"+ reponame+"/commits"+string+access_token;

	}

	
}
