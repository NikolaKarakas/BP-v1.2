package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.postgresql.shaded.com.ongres.stringprep.StringPrep;

import controler.DataBaseHandler;

public class Difference {

	private String[] lines;
	private DataBaseHandler dataBaseHandler;
	
	public Difference (String[] lines, DataBaseHandler dataBaseHandler) {
		this.lines = lines;
		this.dataBaseHandler = dataBaseHandler;
		
		process_new_difference();
		
		
	}
	
	
	private int add_rows(String line) {
		String number;
		String[] splits= line.split("\\+");
		number=splits[1];
		
		if(number.contains(","))
			splits=number.split(",");
		else
			splits=number.split(" ");
		
		
		number=splits[0];
		
		
		/*try {
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			System.out.println(line);
			System.exit(1);
		}*/
		return Integer.parseInt(number);
		
	}
	



	private void  process_new_difference() {
		

		
		int counter_min, counter_plus,list_plus_counter,list_minus_counter; // COUNTERS OF MINUSES AND PLUSES
		
		int row_db =1; // ROW IN DATABASE
		
		ArrayList<Integer> length_minus= new ArrayList<>();
		ArrayList<Integer> length_plus= new ArrayList<>();
		for(int i = 0; i < lines.length;i++) {
			if(lines[i].charAt(0)=='@' && lines[i].charAt(0)=='@') {
				//System.out.println(lines[i]);
				
				//System.out.println(add_rows(lines[i]));
				row_db=add_rows(lines[i]) -1;
			}

			
			if(lines[i].charAt(0)=='+') {								
		    		//System.out.println("Add Operation");
		    		dataBaseHandler.add_new_change(row_db, "+",lines[i].length()-1);
		    }
			else if((lines[i].charAt(0)=='-')){
				
				list_minus_counter=0;
				list_plus_counter=0;
				counter_min=0;
		 		counter_plus=0;
		 		length_minus.clear();
		 		length_plus.clear();
		 		
		 		
				//COUNT ALL MINUSES
				while( i<lines.length && lines[i].charAt(0)=='-' ) {
					length_minus.add(lines[i].length()-1);
		 			counter_min++;
		 			i++;	
		 		}
				//COUNT ALL PLUSES
				while( i<lines.length && lines[i].charAt(0)=='+') {
					length_plus.add(lines[i].length()-1);
		 			counter_plus++;
		 			i++;
		 		}
				i--;
				
		 		//System.out.println("Broj Minusa: "+ counter_min + ", broj pluseva: "+ counter_plus);
		 		int j;
				if(counter_min >= counter_plus) {
					for( j = row_db;j< row_db + counter_plus;j++) {
						dataBaseHandler.add_new_change(j, ".",length_plus.get(list_plus_counter++));
						//System.out.println("Modify Operation");
					}
					row_db=j;
					for( j= row_db; j< row_db + (counter_min-counter_plus); j++ ) {
						dataBaseHandler.add_new_change(j, "-",length_minus.get(list_minus_counter++));
						//System.out.println("Delete Operation");
					}
					row_db=j-1;
					
				}
				else {
						
					for( j = row_db;j< row_db + counter_min;j++) {
						dataBaseHandler.add_new_change(j, ".",length_plus.get(list_plus_counter++));
						//System.out.println("Modify Operation");
					}
					row_db=j;
					for( j = row_db;j< row_db + (counter_plus-counter_min);j++) {
						dataBaseHandler.add_new_change(j, "+",length_plus.get(list_plus_counter++));
						//System.out.println("ADD Operation");
					}
					row_db=j-1;
					
					
				}
								
			}
			else {
				//System.out.println("PRAZAN RED");

				
			}
	 		//System.out.println("ZAVRSAVAM I = "+ i +", ROW = " + row_db);

			row_db++;
		
				
		}
				
	}

	
	
	
	
	
	
	
	
}