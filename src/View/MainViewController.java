package View;

import java.net.URL;
import java.util.ResourceBundle;

import controler.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainViewController  {

	
	DataBaseHandler dataBaseHandler =  new DataBaseHandler(); 
	

		@FXML
	    private Label num_contLabel;

	    @FXML
	    private Label num_filesLabel;

	    @FXML
	    private Label num_codedfilesLabel;

	    @FXML
	    private Label num_commitsLabel;

	    @FXML
	    private Label num_fileSingleDevLabel;

	    @FXML
	    private Label num_file_multipleDev;

	    @FXML
	    private Label max_devSingleFileLabel;

	    @FXML
	    private Label dev_maxFilesWriteLabel;

	    @FXML
	    private Label dev_maxFilesWritePercLabel;

	    @FXML
	    private Label dev_maxLinesWritePLabel;

	    @FXML
	    private Label dev_maxFilesLinesPercLabel;
	    
	    @FXML
	    private Label num_filesDevPairLabel;
	    
	  @FXML
	    void initialize() {
	        assert num_contLabel != null : "fx:id=\"num_contLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_filesLabel != null : "fx:id=\"num_filesLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_codedfilesLabel != null : "fx:id=\"num_codedfilesLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_commitsLabel != null : "fx:id=\"num_commitsLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_fileSingleDevLabel != null : "fx:id=\"num_fileSingleDevelopLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_file_multipleDev != null : "fx:id=\"num_file_multipleDev\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_filesDevPairLabel != null : "fx:id=\"num_filesDevPairLabel\" was not injected: check your FXML file 'MainView.fxml'.";

	        System.out.println("NOVI KONTROLESS");
	        
	        set_labels();
	        
	    }
	  
	  public void set_labels() {
		  num_contLabel.setText(Integer.toString(dataBaseHandler.number_of_contributors()));
	      num_filesLabel.setText(Integer.toString(dataBaseHandler.number_of_files()));
	      num_codedfilesLabel.setText(Integer.toString(dataBaseHandler.number_of_coded_files()));
	      num_commitsLabel.setText(Integer.toString(dataBaseHandler.number_of_commits()));
	      num_fileSingleDevLabel.setText(Integer.toString(dataBaseHandler.files_single_dev()));
	      num_filesDevPairLabel.setText(Integer.toString(dataBaseHandler.files_developed_inpair()));
	      num_file_multipleDev.setText(Integer.toString(dataBaseHandler.files_multiple_dev()));
	      max_devSingleFileLabel.setText(Integer.toString(dataBaseHandler.max_dev_onFile()));





		  
	  }


	

}
