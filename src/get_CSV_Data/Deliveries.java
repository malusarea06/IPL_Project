package get_CSV_Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Deliveries {
	
	public static final int MATCH_ID = 0;
	public static final int INNING = 1;
	public static final int BATTING_TEAM = 2;
	public static final int BOWLING_TEAM = 3;
	public static final int OVER = 4;
	public static final int BALL = 5;
	public static final int BATSMAN = 6;
	public static final int BOWLER = 7;
	public static final int WIDE_RUNS = 8;
	public static final int BYE_RUNS = 9;
	public static final int LEGBYE_RUNS = 10;
	public static final int NOBALL_RUNS = 11;
	public static final int PENALTY_RUNS = 12;
	public static final int BATSMAN_RUNS = 13;
	public static final int EXTRA_RUNS = 14;
	public static final int TOTAL_RUNS = 13;
	
	
	
	public static List<ArrayList<String>> hash_map= new ArrayList<ArrayList<String>>();
	String file1 = "//Users//aniketmalusare//eclipse-workspace//IPL_Project//deliveries.csv";
	
	
	/*This method reads all data from deliveries.csv file*/
	public void read_Data() throws Exception {
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
			hash_map.add(data_list);
			
		}		
	}
	
	
	/*Returns the single entire row */
	public ArrayList<String> get_row(int col_name,String value)
	{
		ArrayList<String> row = new ArrayList<String>();
		int count = 0;
		for(int i = 1 ; i < hash_map.size() ; i++)
		{
			ArrayList<String> data = hash_map.get(i);
			if(data.get(col_name).equals(value))
			{
				//System.out.println(row);
				count = i;
			}
		}
		return hash_map.get(count);
	}
	
	/*This method returns multiple rows*/
	public List<ArrayList<String>> get_nrows(int col_name, String value)
	{
		List<ArrayList<String>> str = new ArrayList<ArrayList<String>>();
		//ArrayList<String> row = new ArrayList<String>();
		for (int i = 0 ; i < hash_map.size() ; i++)
		{
			ArrayList<String> data = hash_map.get(i);
			if(data.get(col_name).equals(value))
			{
				str.add(hash_map.get(i));
			}
		}
		return str;
	}
	
	/*This method returns single row*/
	public ArrayList<String> get_drow(int col_name,String value)
	{
		ArrayList<String> row = new ArrayList<String>();
		int count = 0;
		for(int i = 1 ; i < hash_map.size() ; i++)
		{
			ArrayList<String> data = hash_map.get(i);
			if(data.get(col_name).equals(value))
			{
				//System.out.println(row);
				count = i;
			}
		}
		return hash_map.get(count);
	}
	
	public ArrayList<String> get_d(int column_name)
	{
		ArrayList<String> values = new ArrayList<String>();

		for(int i = 1 ; i < hash_map.size() ; i++)
		{
			
			ArrayList<String> list1 = hash_map.get(i);
			values.add(list1.get(column_name));
			//System.out.println(list1.get(column_name));

			
		}
		//System.out.println("Size : "+values.length);
		return values;
		
	}
}
