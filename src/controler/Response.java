package controler;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

public class Response {

	private Client client;
	private WebTarget target;
	private String response= "STARTSTARTSTARTSTART";
	
	public void setClient(Client client) {
		this.client = client;
	}

	public void setTarget(WebTarget target) {
		this.target = target;
	}



	public String getResponse() {
		return response;
	}
	



	public Response(String url,int page) {
		
		
		ClientConfig configuration = new ClientConfig();
		configuration.property(ClientProperties.CONNECT_TIMEOUT, 1000000);
		configuration.property(ClientProperties.READ_TIMEOUT, 10000000);
		
		client = ClientBuilder.newClient(configuration);
		
		target = client.target(url+"&per_page=100&page="+page);
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
