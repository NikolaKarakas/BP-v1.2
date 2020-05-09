package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.ws.rs.NotFoundException;

import org.python.antlr.PythonParser.return_stmt_return;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Commit;
import model.DifferenceResponse;
import model.JSONResponse;
import model.URL;

public class MainController implements Initializable {
	
	
	
	DataBaseHandler dataBaseHandler = new DataBaseHandler();
	
	JSONResponse jsonResponse ;
	private URL url = new URL();
	Response response =new Response();
	private static ArrayList<String> merged_sha_list = new ArrayList<String>();;
	Alert alert = new Alert(AlertType.INFORMATION);
	
	MainViewController mainViewController = new MainViewController();


	
	int page = 1;
	int commit_no=1;
	
	
	
	
	@FXML
    private Button cont;
	
	@FXML
    private TextField user_nameLabel = new TextField();

    @FXML
    private TextField repo_nameLabel;

    @FXML
    private TextField access_tokenLabel;
	
	
	public void nesto(ActionEvent actionEvent) {
		
		if(actionEvent.getSource()==cont) {
			try {
				

				set_Url();
				
				if(!get_data()) {
					
				Parent secondView = FXMLLoader.load(getClass().getResource("MainView.fxml"));
				Scene scene = new Scene(secondView,600,600);
				Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
				window.setScene(scene);
				window.show();
				
				}
				else {
					alert.setTitle("Information Dialog");
					alert.setHeaderText(null);
					alert.setContentText("Repository Not Found!");

					alert.showAndWait();
					
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	
	public void set_Url() {
		System.out.println(user_nameLabel.getText().toString());
		
		url.setUsername(user_nameLabel.getText());
		url.setReponame(repo_nameLabel.getText());
		//url.setAccess_token(access_tokenLabel.getText());
	}

	public boolean get_data() {
		if(true)
		return false;
		else 
		while(response.getResponse().length()>12) {
			
			try {
				 response = new Response(url.commit_list(""),page++);

			} catch (Exception e) {

				//e.printStackTrace();
				if(e instanceof NotFoundException)
				{
					
					return false;
				}
				// TODO: handle exception
			}
			
			//CONVERT IT TO JSON
			 
			 jsonResponse = new JSONResponse(response.getResponse());
			
			
			
			
			/*
	
			
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
			*/
	}
		return true;
		
	}


	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	

}
