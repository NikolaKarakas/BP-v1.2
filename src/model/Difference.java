package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controler.DataBaseHandler;

public class Difference {

	private String difference;
	private DataBaseHandler dataBaseHandler;
	
	public Difference (String difference, DataBaseHandler dataBaseHandler) {
		this.difference = difference;
		this.dataBaseHandler = dataBaseHandler;
		
		process_new_difference();
		
		
	}
	
	
	
	



	private void  process_new_difference() {
		
		String[] lines = difference.split("\n");
		System.out.println(difference);
		
		int counter_min, counter_plus,list_plus_counter,list_minus_counter; // COUNTERS OF MINUSES AND PLUSES
		
		int row_db =1; // ROW IN DATABASE
		
		ArrayList<Integer> length_minus= new ArrayList<>();
		ArrayList<Integer> length_plus= new ArrayList<>();
		
		for(int i = 1; i < lines.length;i++) {
			
			if(lines[i].charAt(0)=='+') {								
		    		System.out.println("Add Operation");
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
				while(lines[i].charAt(0)=='-') {
					length_minus.add(lines[i].length()-1);
		 			counter_min++;
		 			i++;	
		 		}
				//COUNT ALL PLUSES
				while(lines[i].charAt(0)=='+' ) {
					length_plus.add(lines[i].length()-1);
		 			counter_plus++;
		 			i++;
		 		}
				i--;
				
		 		System.out.println("Broj Minusa: "+ counter_min + ", broj pluseva: "+ counter_plus);
		 		int j;
				if(counter_min >= counter_plus) {
					for( j = row_db;j< row_db + counter_plus;j++) {
						dataBaseHandler.add_new_change(j, ".",length_plus.get(list_plus_counter++));
						System.out.println("Modify Operation");
					}
					row_db=j;
					for( j= row_db; j< row_db + (counter_min-counter_plus); j++ ) {
						dataBaseHandler.add_new_change(j, "-",length_minus.get(list_minus_counter++));
						System.out.println("Delete Operation");
					}
					row_db=j-1;
					
				}
				else {
						
					for( j = row_db;j< row_db + counter_min;j++) {
						dataBaseHandler.add_new_change(j, ".",length_plus.get(list_plus_counter++));
						System.out.println("Modify Operation");
					}
					row_db=j;
					for( j = row_db;j< row_db + (counter_plus-counter_min);j++) {
						dataBaseHandler.add_new_change(j, "+",length_plus.get(list_plus_counter++));
						System.out.println("ADD Operation");
					}
					row_db=j-1;
					
					
				}
								
			}
			else {
				System.out.println("PRAZAN RED");

				
			}
	 		System.out.println("ZAVRSAVAM I = "+ i +", ROW = " + row_db);

			row_db++;
		
				
		}
				
	}

	
	
	
	
	
	
	
	
}

/*
 * 
 * 	private void modify_file_difference() {
		

		String[] lines = difference.split("\n");
		System.out.println(difference);
		
		
		int row_db =1;
		
		
		ArrayList<Boolean> lengt_minus= new ArrayList<>();
		ArrayList<Boolean> lengt_plus= new ArrayList<>();
		
		int counter_min;
 		int counter_plus;
 		
 		
		for(int i = 1; i < lines.length;i++) {
			
			System.out.println("Red: " + row_db + " I: "+i);
			
			 if(lines[i].charAt(0)=='+') {											// FIRTS CHAR +
			    	if(check_length(lines[i])==true) {								//IF IT IS VALID, ADD IT
			    		System.out.println("Clean Add");
			    		dataBaseHandler.execute_addition_update(row_db);
			    		dataBaseHandler.write_new_line(row_db);
			    		
			    		
			    	}
			    	else {
			    		System.out.println("Add Update");
						dataBaseHandler.execute_addition_update(row_db);
						
					}
					
				}

			 
			 
			 if (lines[i].charAt(0) == '-') { 						//IF FIRST SIGN IS -
				 	
				 	if(lines[i + 1].charAt(0) == '+' && check_length(lines[i])==true && check_length(lines[i+1])==true) {	//-AAA +BBB
				 		dataBaseHandler.update_line(row_db);
				 		i++;
				 		System.out.println("Clean Modyf");
				 	}
				 	else if(lines[i + 1].charAt(0) == '+' && check_length(lines[i])==true && check_length(lines[i+1])==false) {	// -AAA +BB
				 		dataBaseHandler.delete_line(row_db);
				 		i++;
				 		System.out.println("Modyf Delete");
				 	}
				 	else if(lines[i + 1].charAt(0) == '+' && check_length(lines[i])==false && check_length(lines[i+1])==true) { //-AA +BBB
				 		dataBaseHandler.write_new_line(row_db);
				 		i++;
				 		System.out.println("ModyfAdd");
				 	}
				 	else if(lines[i+1].charAt(0) == '-') {															// -AA -AA -AA +BB
				 		counter_min=0;
				 		counter_plus=0;
				 		System.out.println("Multiple Minus");
				 		while(lines[i].charAt(0)=='-') {
				 			if(check_length(lines[i])==true)
				 				lengt_minus.add(true);
				 			else 
				 				lengt_minus.add(false);
				 			counter_min++;
				 			i++;
				 			
				 			
				 		}
				 		
				 		while(lines[i].charAt(0)=='+' ) {
				 			if(check_length(lines[i])==true)
				 				lengt_plus.add(true);
				 			else 
				 				lengt_plus.add(false);
				 			counter_plus++;
				 			i++;
				 		}
				 		i--;
				 
				 		
				 		System.out.println("Broj Minusa: "+ counter_min + ", broj pluseva: "+ counter_plus);
				 		int list_counter = 0;
				 		if(counter_plus<counter_min) {
				 			System.out.println("+<min");
				 			
				 			for(int j = row_db; j < row_db+counter_plus;j++) {
				 				System.out.println("Prvi For, Row start " +j);
				 				if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == true) 		//-AAA +BBB
				 					dataBaseHandler.update_line(j);
				 				else if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == false)	//-AAA + BB
				 					dataBaseHandler.delete_line(j);
				 				else if(lengt_minus.get(list_counter)==false && lengt_plus.get(list_counter) == false) { //-AA + BB
				 					
				 				}
				 					//dataBaseHandler.delete_line(j);
				 				else																					//-AA  +BBB
				 					dataBaseHandler.write_new_line(j);
				 				list_counter++;
				 			}
				 			
				 			row_db+=counter_plus;												//MOVE DATABASE LINE
				 			for(int j = row_db; j < row_db+(counter_min-counter_plus);j++) {
				 				System.out.println("Drugi For Row Delete "+ j);

					 			dataBaseHandler.delete_line(j);		
				 			}
				 			for(int j = row_db; j < row_db+(counter_min-counter_plus);j++) {
				 				System.out.println("Drugi For Row Update "+ row_db);
					 			dataBaseHandler.execute_deletion_update(row_db);
				 			}
				 			
				 		}
				 		else if(counter_plus ==counter_min) {
				 			
				 			for(int r = 0 ; r < 3;r++)
				 			{
				 				System.out.print(lengt_minus.get(r)+ " | "+ lengt_plus.get(r));
				 			}
				 			System.out.println("+==min");
				 			
				 			for(int j = row_db; j < row_db+counter_plus;j++) {
				 				System.out.println("Prvi For, Row start " +j);
				 				if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == true) 		//-AAA +BBB
				 					{dataBaseHandler.update_line(j);
				 					System.out.println("A");
				 					}
				 				else if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == false)	//-AAA + BB
				 					{dataBaseHandler.delete_line(j);
				 					System.out.println("B");
				 					}
				 				else if(lengt_minus.get(list_counter)==false && lengt_plus.get(list_counter) == false)	//-AA + BB
			 					{
			 					System.out.println("C");
			 					}
				 				else																					//-AA  +BBB
				 					{dataBaseHandler.write_new_line(j);
				 				
				 					System.out.println("D");
				 					}
				 				list_counter++;
				 			}
				 			
				 			row_db+=counter_plus;												//MOVE DATABASE LINE
				 		
				 			
				 		}
				 		else {
				 			for(int j = row_db; j < row_db+counter_min;j++) {
				 				if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == true) 		//-AAA +BBB
				 					dataBaseHandler.update_line(j);
				 				else if(lengt_minus.get(list_counter)==true && lengt_plus.get(list_counter) == false)	//-AAA + BB
				 					dataBaseHandler.delete_line(j);
				 				else																					//-AA  +BB
				 					dataBaseHandler.write_new_line(j);
				 				list_counter++;
				 			}
				 			row_db+=counter_min;
				 			for(int j = row_db; j < row_db+(counter_plus-counter_min);j++) {
				 				if(lengt_plus.get(list_counter) == true) {								//+AAA
						    		dataBaseHandler.write_new_line(j);
						    		dataBaseHandler.execute_addition_update(j);
						    		
						    	}
						    	else {															//+AA
									dataBaseHandler.execute_addition_update(j);
									
								}	
				 				list_counter++;
				 			}		
				 			
				 		}
				 			row_db--;
					 		System.out.println("IZLAZIM I = "+ i +", ROW = " + row_db);
					 		
					 		
				 	
				 	}
				 	else if(check_length(lines[i])==true) { // -AAA
				 		dataBaseHandler.delete_line(i);
				 		dataBaseHandler.execute_deletion_update(row_db);
				 		row_db--;
				 	}
				 	else {
				 			dataBaseHandler.execute_deletion_update(row_db);
				 			row_db--;
				 	}					
			 }
			 row_db++;
			 
			
	}
				
	}
 * 
 * 
 * if(counter_plus<=counter_min) {
					 			
					 			for(int j = row_db; j < row_db+counter_plus;j++) {
						 			dataBaseHandler.update_line(j);
						 		}
					 			row_db+=counter_plus;
					 			for(int j = row_db; j < row_db+(counter_min-counter_plus);j++) {
						 			dataBaseHandler.delete_line(j);				 			
					 			}		
					 			row_db+=(counter_min-counter_plus);
					 		}
					 		else {
					 			for(int j = row_db; j < row_db+counter_min;j++) {
						 			dataBaseHandler.update_line(j);
						 		}
					 			row_db+=counter_plus;
								
							}*/
