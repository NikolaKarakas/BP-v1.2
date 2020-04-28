package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application{
	


	@Override
	public void start(Stage primarystage) throws Exception {
		
		
		// TODO Auto-generated method stub
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("bootstrap.css").toExternalForm());
			primarystage.setScene(scene);
			primarystage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
	}

	

	

}	
