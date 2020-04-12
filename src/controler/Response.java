package controler;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class Response {

	private Client client;
	private WebTarget target;
	private String response;
	
	public void setClient(Client client) {
		this.client = client;
	}

	public void setTarget(WebTarget target) {
		this.target = target;
	}



	public String getResponse() {
		return response;
	}

	public Response(String url) {
		
		client = ClientBuilder.newClient();
		target = client.target(url);
		response = target.request(MediaType.APPLICATION_JSON).get(String.class);
		if (response.charAt(0) == '[')
			response="{ \"obj\":" + response + "}";
		else {
			response="{ \"obj\":[" + response + "]}";
		}
	}
	
public Response() {
		
	
	}
	
	

public String get_commit_diff(String url) {
		
		client = ClientBuilder.newClient();
		target = client.target(url);
		
		MediaType V1 = new MediaType("application","vnd.github.VERSION.diff");

		return target.request(V1).get(String.class);
		
		
	
	}
	
public String get_commit_pullrequest(String url) {
	client = ClientBuilder.newClient();
	target = client.target(url);



	MediaType V1 = new MediaType("application","vnd.github.groot-preview+json");
	this.response= target.request(V1).get(String.class);
	if (target.request(V1).get(String.class).charAt(0) == '[')
		return "{ \"obj\":" + response + "}";
	else {
		return "{ \"obj\":[" + response + "]}";
	}

}

	
}
