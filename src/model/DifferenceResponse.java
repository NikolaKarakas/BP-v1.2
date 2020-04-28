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
		//System.out.println(response);
		String[] files = this.response.split("diff --git");
		files  = Arrays.copyOfRange(files, 1, files.length);
		StringBuilder stringBuilder = new StringBuilder();
		

		for(String file : files) {
			set_file_name(file);
			dataBaseHandler.write_new_file(file_name);
			//System.out.println(file);
			String lines[]=file.split("\n");
			//System.out.println("---------------------------------------------------------------");
			
			if(is_new_file(file)==0 && lines.length>3)
				try {
					lines =Arrays.copyOfRange(lines, 4, lines.length);
					
				} catch (IllegalArgumentException e) {
					System.out.println(file);
					System.exit(1);
				}
				
			else if(is_new_file(file)==0 && lines.length>4)			
				lines =Arrays.copyOfRange(lines, 5, lines.length);

			//for(String linString: lines)
				//System.out.println(lines[0]);
			Difference difference = new Difference(lines,dataBaseHandler);


		}

			
	}
	
	private int is_new_file(String file) {
		// CHECK IF FILE IS NEW OR MODIFICATED
		
		if(file.contains("--- /dev/null")) //novi fajl
			{
			
				return 1;
			}
			
		else if(file.contains("+++ /dev/null")) // delete 
			{
				return -1;
			}
		else 									//edit
			{
				return 0;
			}
	}
	
	

	
	private void set_file_name(String file) {
		String[] divide1 = file.split(" ");
		this.file_name = divide1[1].substring(2, divide1[1].length());
	}
		

		
	




	

}
