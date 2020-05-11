package controler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import org.objectweb.asm.util.CheckAnnotationAdapter;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.python.antlr.base.slice._attributes_descriptor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.PythonTable;

public class MainViewController  {

	
	DataBaseHandler dataBaseHandler =  new DataBaseHandler(); 
	ArrayList<PythonTable> pythonTableList=new ArrayList<PythonTable>();

	
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
	    private Label dev_minFilesWritePLabel;

	    @FXML
	    private Label dev_minFilesPercLabel;
	    
	    @FXML
	    private Label num_filesDevPairLabel;
	    
	    @FXML
	    private Label label_chart_perc = new Label();
	    
	    @FXML
	    private Label dev_maxLinesWritten;

	    @FXML
	    private Label dev_maxLinesWrittensPerc;

	    @FXML
	    private Label dev_minLinesWrittens;

	    @FXML
	    private Label dev_minLinesWrittenPerc;
	    
	  @FXML
	    void initialize() {
	        assert num_contLabel != null : "fx:id=\"num_contLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_filesLabel != null : "fx:id=\"num_filesLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_codedfilesLabel != null : "fx:id=\"num_codedfilesLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_commitsLabel != null : "fx:id=\"num_commitsLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_fileSingleDevLabel != null : "fx:id=\"num_fileSingleDevelopLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_file_multipleDev != null : "fx:id=\"num_file_multipleDev\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert num_filesDevPairLabel != null : "fx:id=\"num_filesDevPairLabel\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert chart_dev_line != null : "fx:id=\"chart_dev_line\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert label_chart_perc != null : "fx:id=\"label_chart_perc\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert dev_maxLinesWritten != null : "fx:id=\"label_chart_perc\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert dev_maxLinesWrittensPerc != null : "fx:id=\"label_chart_perc\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert dev_minLinesWrittens != null : "fx:id=\"label_chart_perc\" was not injected: check your FXML file 'MainView.fxml'.";
	        assert dev_minLinesWrittenPerc != null : "fx:id=\"label_chart_perc\" was not injected: check your FXML file 'MainView.fxml'.";

	        developer1_T1.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("developer1"));
	        numOfCommits_T1.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("numOfCommits"));
	        developer1_T2.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("developer1"));
	        developer2_T2.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("developer2"));
	        count_T2.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("count"));
	        Column_numOfContributors.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("developer1"));
	        Column_numOfFiles.setCellValueFactory(new PropertyValueFactory<PythonTable,String>("count"));

	        System.out.println("NOVI KONTROLESS");
		    set_tabels();
	        set_labels();
		    set_images();

	        
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
	      dev_maxFilesWriteLabel.setText(Integer.toString(dataBaseHandler.dev_withMax_files()));
	      dev_maxFilesWritePercLabel.setText(Double.toString(dataBaseHandler.dev_max_file()/Double.parseDouble(num_codedfilesLabel.getText())*100));
	      dev_minFilesWritePLabel.setText(Integer.toString(dataBaseHandler.dev_withMin_files()));
	      dev_minFilesPercLabel.setText(Double.toString(dataBaseHandler.dev_min_file()/Double.parseDouble(num_codedfilesLabel.getText())*100));

	      dev_maxLinesWritten.setText(pythonTableList.get(0).getDeveloper1());
	      dev_maxLinesWrittensPerc.setText(pythonTableList.get(0).getCount());
	      dev_minLinesWrittens.setText(pythonTableList.get(1).getDeveloper1());
	      dev_minLinesWrittenPerc.setText(pythonTableList.get(1).getCount());

	     
	      
	  }
	  
	  
	  	@FXML
	    private TableView<PythonTable> table_dev_commits;
	  	
	  	@FXML
	    private TableColumn<PythonTable, String> developer1_T1;
	  	@FXML
	    private TableColumn<PythonTable, String> developer2;
	  	@FXML
	    private TableColumn<PythonTable, String> file;
	  	@FXML
	    private TableColumn<PythonTable, String> numOfCommits_T1;
	  	@FXML
	    private TableColumn<PythonTable, String> num_of_files;

	    @FXML
	    private TableView<PythonTable> table_dev_file_dev;
	    @FXML
	    private TableColumn<PythonTable, String> developer1_T2;
	    @FXML
	    private TableColumn<PythonTable, String> developer2_T2;
	    @FXML
	    private TableColumn<PythonTable, String> count_T2;
	    
	    @FXML
	    private TableView<PythonTable> table_noDev_noFile;
	    @FXML
	    private TableColumn<PythonTable, String> Column_numOfContributors;
	    @FXML
	    private TableColumn<PythonTable, String> Column_numOfFiles;
	    
	    

	    
	    public void set_tabels() {
	  	  dataBaseHandler.get_all();

	    	
	    	ScriptParser scriptParser = new ScriptParser();
	    	ArrayList<String> args = new ArrayList<>();
	    	args.add("scripts/table_dev_file.py");	
	    	table_dev_commits.setItems(scriptParser.getData_file_dev_table(getOutput(args)));
	    	
	    	args.clear();
	    	args.add("scripts/developers_lines.py");	
	    	pythonTableList= scriptParser.get_max_min_lines(getOutput(args));
	    	
	    	args.clear();
	    	args.add("scripts/table_dev_file_dev.py");	
	    	table_dev_file_dev.setItems(scriptParser.getData_dev_file_dev_table(getOutput(args)));
	    	
	    	args.clear();
	    	args.add("scripts/chart_dev_line.py");	
	    	//table_dev_file_dev.setItems(scriptParser.getData_dev_file_dev_table(getOutput(args)));
	    	chart_dev_line.setData(scriptParser.get_data_chart_dev_line(getOutput(args)));
	    	
	    	args.clear();
	    	args.add("scripts/table_noDev_noFile.py");	
	    	//table_dev_file_dev.setItems(scriptParser.getData_dev_file_dev_table(getOutput(args)));
	    	table_noDev_noFile.setItems(scriptParser.getData_noDev_noFile(getOutput(args)));
	    	
	    	args.clear();
	    	args.add("scripts/table_noDev_noFile.py");	
	    	//table_dev_file_dev.setItems(scriptParser.getData_dev_file_dev_table(getOutput(args)));
	    	table_noDev_noFile.setItems(scriptParser.getData_noDev_noFile(getOutput(args)));
	    	
	    	
	    	args.clear();
	    	args.add("scripts/graph_architect_activity.py");	
	    	getOutput(args);
	    	
	    	args.clear();
	    	args.add("scripts/graph_dev_file_activity.py");	
	    	getOutput(args);
	    	
	    	args.clear();
	    	args.add("scripts/graph_dev_time_changes.py");	
	    	getOutput(args);
	    	
	    	
	    	args.clear();
	    	args.add("scripts/graph_colaboration_commits1.py");	
	    	getOutput(args);
	    	
	    	
	    

		     // label_chart_perc.setText("ASAS");

	        
	      //  table_dev_commits.setItems(getdata());

	    }
	    
	    public void change_label() {
	    
	    	for (final PieChart.Data data : chart_dev_line.getData()) {
	            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
	                e -> {
	                    double total = 0;
	                    for (PieChart.Data d : chart_dev_line.getData()) {
	                        total += d.getPieValue();
	                    }
	                    //label_chart_perc.setTranslateX(e.getSceneX());
	                    //label_chart_perc.setTranslateY(e.getSceneY());
	                    String text = String.format("%.1f%%", 100*data.getPieValue()/total) ;
	                    label_chart_perc.setText(text);
	                   
	                 }
	                );
	        }
	    	
	    
	    }
	    
	    
	    @FXML
	    private PieChart chart_dev_line = new PieChart();
	   
	    

	    
	    
	    
	    public  String getOutput(ArrayList<String> args) {
			
			String exec="python";
			for(int i = 0 ; i < args.size();i++)
				exec=exec+ " " + args.get(i);
			
			System.out.println(exec);
			String output = "";
			
			try {
				String s  = null;
				Process p = Runtime.getRuntime().exec(exec);
				BufferedReader in  = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while(( s= in.readLine()) != null) {
					//System.out.println(s);
					output += s + "\n";
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return output;
			
		}
	    
	    
	    @FXML
	    private ImageView graph_date_colab = new ImageView();
	    @FXML
	    private ImageView graph_dev_date_files = new ImageView();
	    @FXML
	    private ImageView graph_dev_date_files2 = new ImageView();
	    @FXML
	    private ImageView graph_dev_date_changes = new ImageView();
	    
	    
	    
	    public void set_images() {
	    	ClassLoader classLoader = getClass().getClassLoader();
	    	
	    	Image image1 = new Image("file:///C:/Users/TOSHIBA/eclipse-workspace/BP_v1.2/scripts/img/graph_collaboration.png");
	    	graph_date_colab.setImage(image1);
	    	
	    	Image image2 = new Image("file:///C:/Users/TOSHIBA/eclipse-workspace/BP_v1.2/scripts/img/graph_dev_time_files.png");
	    	graph_dev_date_files.setImage(image2);
	    	
	    	Image image3 = new Image("file:///C:/Users/TOSHIBA/eclipse-workspace/BP_v1.2/scripts/img/graph_dev_time_files2.png");
	    	graph_dev_date_files2.setImage(image3);
	    	Image image4 = new Image("file:///C:/Users/TOSHIBA/eclipse-workspace/BP_v1.2/scripts/img/graph_dev_time_changes.png");
	    	graph_dev_date_changes.setImage(image4);
	    }


	

}
