package model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import controler.DataBaseHandler;

public class DifferenceResponse {
	
	private String response;
	private String file_name;



	private DataBaseHandler dataBaseHandler;
	private int file_mode;
	
	public DifferenceResponse(String response,DataBaseHandler dataBaseHandler) {
		super();
		this.response=response;
		this.dataBaseHandler = dataBaseHandler;
		parseFiles();
		
	}




	private void parseFiles()
	{
		// PARSE RESPONSE STRING INTO SEPARATE DIFFERENCES
		String[] files = this.response.split("diff --git");
		files  = Arrays.copyOfRange(files, 1, files.length);
		
		for(String file : files) {
			set_file_name(file);
			dataBaseHandler.write_new_file(file_name);
			file = get_diff(file);
			Difference difference = new Difference(file,dataBaseHandler);
		}
			
			
	}
	

	

	
	private void set_file_name(String file) {
		String[] divide1 = file.split(" ");
		this.file_name = divide1[1].substring(2, divide1[1].length());
	}
		
	
	private String get_diff (String file) {
		String[] diffs = file.split("@@");
		//System.out.println("PARSOVANJE"+ diffs[2]);
		return(diffs[2]);
	}
	
		
	




	

}
