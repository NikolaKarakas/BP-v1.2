package model;

import javafx.beans.property.SimpleStringProperty;

public class PythonTable {
	
	private SimpleStringProperty developer1 = new SimpleStringProperty();
	private SimpleStringProperty developer2 = new SimpleStringProperty();
	private SimpleStringProperty file =  new SimpleStringProperty();
	private SimpleStringProperty numOfCommits = new SimpleStringProperty();
	private SimpleStringProperty count = new SimpleStringProperty();

	
	
	/*public PythonTable(String developer1, String developer2, String file, String numOfCommits) {
		this.developer1 = new SimpleStringProperty(developer1) ;
		this.developer2 = new SimpleStringProperty(developer2);
		this.file = new SimpleStringProperty(file);
		this.numOfCommits = new SimpleStringProperty(numOfCommits);
		
	}
	*/

	public String getDeveloper1() {
		return developer1.get();
	}
	public void setDeveloper1(SimpleStringProperty developer1) {
		this.developer1 = developer1;
	}
	public String getDeveloper2() {
		return developer2.get();
	}
	public void setDeveloper2(SimpleStringProperty developer2) {
		this.developer2 = developer2;
	}
	public String getFile() {
		return file.get();
	}
	public void setFile(SimpleStringProperty file) {
		this.file = file;
	}
	public String getNumOfCommits() {
		return numOfCommits.get();
	}
	public void setNumOfCommits(SimpleStringProperty numOfCommits) {
		this.numOfCommits = numOfCommits;
	}
	public String getCount() {
		return count.get();
	}
	public void setCount(SimpleStringProperty count) {
		this.count = count;
	}
	

	
	
	

}
