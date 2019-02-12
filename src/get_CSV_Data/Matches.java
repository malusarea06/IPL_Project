package get_CSV_Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*This class read all IPL data from .CSV file
 * String file11 = "//User//aniketmalusare//Desktop//Softwares//IPL_data//matches.csv";*/


public class Matches {
	public static final int MATCH_ID = 0;
	public static final int SEASON = 1;
	public static final int CITY = 2;
	public static final int DATE = 3;
	public static final int TEAM1 = 4;
	public static final int TEAM2 = 5;
	public static final int TOSS_WINNER = 6;
	public static final int TOSS_DECISION = 7;
	public static final int RESULT = 8;
	public static final int WINNER = 9;
	
	
	

	String file1 = "//Users//aniketmalusare//eclipse-workspace//IPL_Project//matches.csv";
	HashMap<String,String> in_data = new HashMap<String,String>();   // Holds all data
	public  static List<ArrayList<String>> hashmap= new ArrayList<ArrayList<String>>();
	
	
	
	
	
	/*This method reads all data from MATCHES.csv file*/
	public void readData() throws Exception {
		File file = new File(file1);
		BufferedReader br = new BufferedReader(new FileReader(file));
		List<String> list = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
	

		for(String line : list) {
			ArrayList<String> data_list = new ArrayList<String>();
			String[] data = line.split(",");
			for(int i = 0 ; i < data.length ; i++)
			{
				data_list.add(data[i]);
			}
			hashmap.add(data_list);
			
			
		}		
	}
	
	/*This method returns entire specified column*/
	public ArrayList<String> get_data(int column_name)
	{
		ArrayList<String> values = new ArrayList<String>();

		for(int i = 1 ; i < hashmap.size() ; i++)
		{
			
			ArrayList<String> list1 = hashmap.get(i);
			values.add(list1.get(column_name));
			//System.out.println(list1.get(column_name));

			
		}
		//System.out.println("Size : "+values.length);
		return values;
		
	}
	
	
	/*Returns the single entire row */
	public ArrayList<String> get_row(int col_name,String value)
	{
		ArrayList<String> row = new ArrayList<String>();
		int count = 0;
		for(int i = 1 ; i < hashmap.size() ; i++)
		{
			ArrayList<String> data = hashmap.get(i);
			if(data.get(col_name).equals(value))
			{
				//System.out.println(row);
				count = i;
			}
		}
		return hashmap.get(count);
	}
	
	/*This method returns multiple records*/
	public List<ArrayList<String>> get_nrows(int col_name, String value)
	{
		List<ArrayList<String>> str = new ArrayList<ArrayList<String>>();
		//ArrayList<String> row = new ArrayList<String>();
		for (int i = 0 ; i < hashmap.size() ; i++)
		{
			ArrayList<String> data = hashmap.get(i);
			if(data.get(col_name).equals(value))
			{
				str.add(hashmap.get(i));
			}
		}
		return str;
	}
	
	
		
		
		
		
		
		
		
		
		
	}

