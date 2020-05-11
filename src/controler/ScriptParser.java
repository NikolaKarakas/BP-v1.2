package controler;
import java.util.ArrayList;

//rubinovakatarina@gmail.com
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import jnr.ffi.Struct.int16_t;
import model.PythonTable;

public class ScriptParser {

	
	
	public ObservableList<PythonTable> getData_file_dev_table(String data){
    	

		ObservableList<PythonTable> pythonTableList=FXCollections.observableArrayList();

		String[] lines = data.split("\n");
		
		for(int i = 1 ; i < lines.length;i++) {
			String[] rowStrings = lines[i].split("\\s+");
			 PythonTable pythonTable = new PythonTable() ;

			//System.out.println(rowStrings[2]);
			pythonTable.setDeveloper1(new SimpleStringProperty(rowStrings[1]));
			pythonTable.setNumOfCommits(new SimpleStringProperty(rowStrings[2]));

			pythonTableList.add(pythonTable);	
		}
		
		return pythonTableList;
		
	}
		
	public ObservableList<PythonTable> getData_dev_file_dev_table(String data){
	    	
	
			ObservableList<PythonTable> pythonTableList=FXCollections.observableArrayList();
		
	
			String[] lines = data.split("\n");
			
			for(int i = 1 ; i < lines.length;i++) {
				String[] rowStrings = lines[i].split("\\s+");
				 PythonTable pythonTable = new PythonTable() ;
	
				//System.out.println(rowStrings[2]);
				pythonTable.setDeveloper1(new SimpleStringProperty(rowStrings[1]));
				pythonTable.setDeveloper2(new SimpleStringProperty(rowStrings[2]));
				pythonTable.setCount(new SimpleStringProperty(rowStrings[3]));
	
				pythonTableList.add(pythonTable);	
			}
			
			return pythonTableList;
			
		}
	
	
	public ObservableList<PieChart.Data> get_data_chart_dev_line(String data){
	    	
	
			ObservableList<PieChart.Data> pythonTableList=FXCollections.observableArrayList();
			//System.out.println(data);
			
			String[] lines = data.split("\n");
			
			for(int i = 1 ; i < lines.length;i++) {
				String[] rowStrings = lines[i].split("\\s+");
				 PythonTable pythonTable = new PythonTable() ;
	
				//System.out.println(rowStrings[2]);
					pythonTableList.add(new PieChart.Data(rowStrings[1], Double.parseDouble(rowStrings[2])));	
	
			}
			
			return pythonTableList;
			
		}
	
	public ObservableList<PythonTable> getData_noDev_noFile(String data){
    	
		
		ObservableList<PythonTable> pythonTableList=FXCollections.observableArrayList();
	
		
		String[] lines = data.split("\n");
		
		for(int i = 1 ; i < lines.length;i++) {
			String[] rowStrings = lines[i].split("\\s+");
			 PythonTable pythonTable = new PythonTable() ;

			//System.out.println(rowStrings[2]);
			pythonTable.setDeveloper1(new SimpleStringProperty(rowStrings[1]));
			pythonTable.setCount(new SimpleStringProperty(rowStrings[2]));

			pythonTableList.add(pythonTable);	
		}
		
		return pythonTableList;
		
	}
	
public  ArrayList<PythonTable> get_max_min_lines(String data){
    	

	
		String[] lines = data.split("\n");
		ArrayList<PythonTable> pythonTableList=new ArrayList<PythonTable>();

		for(int i = 1 ; i < lines.length;i++) {
			String[] rowStrings = lines[i].split("\\s+");
			 PythonTable pythonTable = new PythonTable() ;

			//System.out.println(rowStrings[2]);
			pythonTable.setDeveloper1(new SimpleStringProperty(rowStrings[1]));
			pythonTable.setFile(new SimpleStringProperty(rowStrings[2]));
			pythonTable.setCount(new SimpleStringProperty(rowStrings[3]));


			pythonTableList.add(pythonTable);	
		}
		
		return pythonTableList;
		
	}
	
	
		
}
