package mainCall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

import get_CSV_Data.Deliveries;
import get_CSV_Data.Matches;

public class Main_IPL {
	  static List<String> teams = new ArrayList<String>();
	  static Matches matches = new Matches();
	  static Deliveries delivery = new Deliveries();
	  static int matches_size = Matches.hashmap.size();
	  static List<String> seasons = new ArrayList<String>();
	  static ArrayList<String> final_bowler = new ArrayList<String>();
	  static List<String> bowlers = new ArrayList<String>();
	  static String[] final_teams;
	  static float[] avg_teams;
	 
	  
	  
	  
	  
	public static void main(String[] args) throws Exception
	{
		
		matches.readData();					//fetches record from matches.csv
		delivery.read_Data();				//fetches record from deliveries.csv
		teamNames();						//get list of all teams present in tournaments
		getSeasons();						//get all seasons
		getBowlers();						//get list of all bowlers
		

		System.out.print("\na).First Query\nb).Second Query\nc).Third Query\nd).Fourth Query\n");
		System.out.println("\n Enter a/b/c/d : ");
		Scanner input = new Scanner(System.in);
		String inp = input.nextLine();
		
		switch(inp)
		{
			case "a":
				firstQuery();
				break;
				
			case "b":
				secondQuery();
				break;
				
			case "c":
				thirdQuery();
				break;
				
			case "d":
				fourthQuery();
				break;
				
			default:
				System.out.println("Enter correct input");
				break;
		}
		
		
	}
	
	/*This method is to retrieve all present team Names.*/
	public static void teamNames()
	{
		List<String> list1 = matches.get_data(Matches.TEAM1);
		Set<String> list = new HashSet<String>();
		list.addAll(list1);
		teams.addAll(list);
		
		
	/*	for(int i = 1 ; i < list1.size() ; i++)
		{
			if(!(teams.contains(list1.get(i))))
			{
				teams.add(list1.get(i));
			}
			
		}*/
		
	}
	
	
	
	/*Get list of all browsers*/
	public static void getBowlers()
	{
		List<String> bow = delivery.get_d(Deliveries.BOWLER);
		Set<String> list = new HashSet<String>();
		list.addAll(bow);
		bowlers.addAll(list);
	}
	
	
	
	/*Retrive list of seasons*/
	public static void getSeasons()
	{
		List<String> list1 = matches.get_data(Matches.SEASON);
		Set<String> list = new HashSet<String>();
		list.addAll(list1);
		
		seasons.addAll(list);
		Collections.sort(seasons);
	}
	
	/*Top 4 teams which elected to field first after winning toss in the year 2016 and 2017.*/
	public static void firstQuery()
	{
		int size = Matches.hashmap.size();
		String name = null,year = null,name1 = null,year1;
		
		List<ArrayList<String>> tmp = matches.get_nrows(Matches.SEASON, "2017");
		tmp.addAll(matches.get_nrows(Matches.SEASON, "2016"));
		
		
		for(int i = 0 ; i < tmp.size() ; i++)
		{
			if(!(tmp.get(i).contains("field")))		/*Remove all teams who win the toss but not choose to field*/
			{
				tmp.remove(i);
			}
		}
		
		
		
		List<String> s_2017 = new ArrayList<String>(); //holds record of all teams in season 2017
		List<String> s_2016 = new ArrayList<String>(); //holds record of all teams in season 2016
		int s_count = 0,s1=0;
		
		
		/*this for loop calculates top 4 teams of 2017*/
		for(int i = 0 ; i < teams.size() ; i++)
		{
			String team  = teams.get(i);
			s_count=0;
			for(int j = 0 ; j < tmp.size() ; j++)
			{
				String winner = tmp.get(j).get(Matches.WINNER).toString();
				if(tmp.get(j).get(Matches.SEASON).equals("2017"))
				{
					if((winner.equals(team)) && (winner.equals(tmp.get(j).get(Matches.TOSS_WINNER)))) 	
					{
					s_count = s_count + 1;
					}
				}
			}
			s_2017.add(team+"_"+s_count); //adds string which contains team name and winning count
		}
		
		
		/*this for loop calculates top 4 teams of 2016*/
		for(int i = 0 ; i < teams.size() ; i++)
		{
			String team  = teams.get(i);
			s_count=0;
			for(int j = 0 ; j < tmp.size() ; j++)
			{
				String winner = tmp.get(j).get(Matches.WINNER).toString();
				if(tmp.get(j).get(Matches.SEASON).equals("2016")) {
					if((winner.equals(team)) &&  (winner.equals(tmp.get(j).get(Matches.TOSS_WINNER))))
					{
						s_count = s_count + 1;
					}
				}
			}
			s_2016.add(team+"_"+s_count); //adds string which contains team name and winning count
		}
		
		sort_list(s_2016,2016);	//sorts list according to win count in descending order and displays top 4 records
	
		sort_list(s_2017,2017); //sorts list according to win count in descending order and displays top 4 records
	}
 	
	
	
	
	public static void secondQuery()
	{
		String T1,T2,season,m_id= null;
																										
		ArrayList<String> match_id = new ArrayList<String>();
		int count = 0;	
		for(int j = 0 ; j < seasons.size() ; j++)		//Get particular season
		{
			List<ArrayList<String>> list = matches.get_nrows(Matches.SEASON, seasons.get(j)); //Get all matches from particular season
			System.out.println("\n");
			System.out.println("---------------------------------------"+seasons.get(j)+"--------------------------------------------------");
			for(int k = 0 ; k < teams.size() ; k++)			//Search for individual team in above list
			{
				match_id.clear();
				for(int i = 0 ; i < list.size() ; i++)	// get match_id for same team
				{
					if(list.get(i).get(Matches.TEAM1).equals(teams.get(k)) || list.get(i).get(Matches.TEAM2).equals(teams.get(k)) )
					{
						match_id.add(list.get(i).get(Matches.MATCH_ID)); 	//add match id of particular team
					}	
				}
				
				//System.out.println(match_id);
				if(!match_id.isEmpty()) {
				countRun(match_id,teams.get(k),seasons.get(j));		//get total runs , fours , six count for particular team.
			
				}
			}
				
		}
		
		
		
	}
	
	
	
	/*This method counts the total_runs,fours, six with respect to team and season*/
	public static void countRun(ArrayList<String> m_id,String team1,String year)
	{
		
		
		String t1 = null,t2=null;
		int fours=0,six=0,total_runs=0;			
		
		for(int t = 0 ; t < m_id.size() ; t++)
		{
			List<ArrayList<String>> d_list = delivery.get_nrows(Matches.MATCH_ID, m_id.get(t));
		
			for(int i = 0 ; i < d_list.size() ; i++)
			{
				if((d_list.get(i).get(Deliveries.BATTING_TEAM).equals(team1)))
				{
					//t1 = team;
					int bye_r = Integer.parseInt(d_list.get(i).get(Deliveries.BYE_RUNS));
					int wide_r = Integer.parseInt(d_list.get(i).get(Deliveries.WIDE_RUNS));
					int total_r = Integer.parseInt(d_list.get(i).get(Deliveries.TOTAL_RUNS));
					int penelty = Integer.parseInt(d_list.get(i).get(Deliveries.PENALTY_RUNS));
					int legbye = Integer.parseInt(d_list.get(i).get(Deliveries.LEGBYE_RUNS));
					int batsmen = Integer.parseInt(d_list.get(i).get(Deliveries.BATSMAN_RUNS));
					int noball = Integer.parseInt(d_list.get(i).get(Deliveries.NOBALL_RUNS));
					int extra = Integer.parseInt(d_list.get(i).get(Deliveries.EXTRA_RUNS));
					total_runs = total_runs + bye_r + legbye + penelty + noball + extra + wide_r + total_r;
					
					if(batsmen == 4)
					{
						fours = fours + 1;
						//System.out.println(batsmen);
					}
					else if(batsmen == 6)
					{
						six = six + 1;
					}
					
				}
				else
				{
					break;	
				}
			}		
		}
		
		System.out.println(year+"\t\t"+team1+"\t\t"+fours+"\t"+six+"\t"+total_runs);
	}
	
	
	
	/*Sorts and display top 4 teams according to win count*/
	public static void sort_list(List<String> dat1,int year)
	{
		String tmp = null;
		List<String> data = dat1;
		
		
		for(int l = 0 ; l < data.size() ; l++)
		{
			for(int i = 0 ; i < data.size()-1  ; i++)
			{
				String[] t1 = data.get(i).split("_");
				String[] t2 = data.get(i+1).split("_");
				int j = Integer.parseInt(t1[1]);
				int k = Integer.parseInt(t2[1]);
			
				if(j < k)
				{
					Collections.swap(data, i, i+1);
				}
			
			}
		}
		
		System.out.println("---------------------------"+year+"----------------------------");
		for(int k = 0 ; k < 4 ; k++)
		{
				String[] result = data.get(k).split("_");
				System.out.println(result[0]+"\t\t"+year+"\t\t"+result[1]);
		}
		System.out.println("-------------------------------------------------------");
		
	}

	/*Top 10 best economy rate bowler with respect to year who bowled at least 10 overs */
	public static void thirdQuery()
	{
	
		for(int i = 0 ; i < seasons.size() ; i++)					//Extract data according to season
		{
			final_bowler.clear();
			List<ArrayList<String>> matches_1 = matches.get_nrows(Matches.SEASON, seasons.get(i)); 	
			
			for(int j = 0 ; j < bowlers.size() ; j++)				//Count economy rate of each bowler
			{
					//get bowler wise data
				countBscore(matches_1,bowlers.get(j));
			}
			sortBestEconomyBowler(final_bowler,seasons.get(i));			//Sort and display top 10 bowlers with economic rate
		}
	}

	
	
	/*This method calculates score of each bowler according to season*/
	static void countBscore(List<ArrayList<String>> matches_1, String bowler) {
		
		int over_count = 0;
		float total_runs = 0;
		float economy = 0;
						
		for(int i = 0 ; i < matches_1.size() ; i++) 																				//data for particular season
		{	
			List<ArrayList<String>> get_m = delivery.get_nrows(Deliveries.MATCH_ID, matches_1.get(i).get(Matches.MATCH_ID));		//get full innings score data for every match
			for(int j = 0 ; j < get_m.size() ; j++)			
			{				
				if(bowler.equals(get_m.get(j).get(Deliveries.BOWLER)))				/*search for particular bowler and calculate score*/
				{
					
					int wide_r = Integer.parseInt(get_m.get(j).get(Deliveries.WIDE_RUNS));					
					int penelty = Integer.parseInt(get_m.get(j).get(Deliveries.PENALTY_RUNS));				
					int batsmen = Integer.parseInt(get_m.get(j).get(Deliveries.BATSMAN_RUNS));
					int noball = Integer.parseInt(get_m.get(j).get(Deliveries.NOBALL_RUNS));
					int extra = Integer.parseInt(get_m.get(j).get(Deliveries.EXTRA_RUNS));
					total_runs = total_runs + penelty + noball + extra + wide_r + batsmen;
					
					if(get_m.get(j).get(Deliveries.BALL).equals("6")) {					/*Maintain count of overs bowled*/
						over_count = over_count + 1;
					}					
				}				
			}
			
		}
		economy = total_runs/over_count;						// calculate economy rate
		if(over_count >= 10)									//maintain list of bowlers who bowled at least 10 overs
		{
			final_bowler.add(bowler+"_"+economy);
		}
	//	System.out.println(season+"\t\t"+bowler+"\t\t"+over_count+"\t\t"+total_runs+"\t\t"+economy);			
	}
	
	
	
	
	/*Sort bowlers according to economic rate*/
	public static void sortBestEconomyBowler(ArrayList<String> economy_b,String year)
	{
		ArrayList<String> data = economy_b;
	
		
		
		
		for(int l = 0 ; l < data.size() ; l++)
		{
			for(int i = 0 ; i < data.size()-1  ; i++)
			{
				String[] t1 = data.get(i).split("_");
				String[] t2 = data.get(i+1).split("_");
			//System.out.println(t1[1]);
				float j = Float.parseFloat(t1[1]);
				float k = Float.parseFloat(t2[1]);
			
				if(j > k)
				{
					Collections.swap(data, i, i+1);
				}
			
			}
		}
		
		System.out.println("---------------------------"+year+"----------------------------");
		for(int k = 0 ; k < 10 ; k++)
		{
			//	System.out.println(data.get(k));
				String[] result = data.get(k).split("_");
				System.out.println(year+"\t\t"+result[0]+"\t\t"+result[1]);
		}
		System.out.println("-------------------------------------------------------");
		
	}
	
	
	//finds team with highest net run rate with respect to year
	public static void fourthQuery()
	{
			int c = 0;
			float over= 0;
			float avg;
			float[] average =  new float[2];
			String[] team = new String[2];
			float total_runs = 0;
			for(int l = 0 ; l < seasons.size() ; l++)
			{
				final_teams = new String[teams.size()];
				avg_teams = new float[teams.size()];
				teams.toArray(final_teams);
				List<ArrayList<String>> matches_2 = matches.get_nrows(Matches.SEASON, seasons.get(l));	//get data with respect to season.
				
				for(int i = 0  ; i < matches_2.size() ; i++)	//total match in present season
				{
					List<ArrayList<String>> temp_Data = delivery.get_nrows(Matches.MATCH_ID, matches_2.get(i).get(Matches.MATCH_ID)); //get all data for single match
					team[0] = matches_2.get(i).get(Matches.TEAM1); //team 1
					team[1] = matches_2.get(i).get(Matches.TEAM2); // team 2
					for(int j = 0 ; j < 2 ; j++) 			//avg for both teams(inning_1 and inning_2)
					{
						total_runs = 0;
						over = 0;
						for(int k = 0 ; k < temp_Data.size() ; k++)				//searches and caclculates total score and over for both teams
						{
							if(team[j].equals(temp_Data.get(k).get(Deliveries.BATTING_TEAM)))
							{
								int bye_r = Integer.parseInt(temp_Data.get(k).get(Deliveries.BYE_RUNS));
								int wide_r = Integer.parseInt(temp_Data.get(k).get(Deliveries.WIDE_RUNS));
								int total_r = Integer.parseInt(temp_Data.get(k).get(Deliveries.TOTAL_RUNS));
								int penelty = Integer.parseInt(temp_Data.get(k).get(Deliveries.PENALTY_RUNS));
								int legbye = Integer.parseInt(temp_Data.get(k).get(Deliveries.LEGBYE_RUNS));
								int batsmen = Integer.parseInt(temp_Data.get(k).get(Deliveries.BATSMAN_RUNS));
								int noball = Integer.parseInt(temp_Data.get(k).get(Deliveries.NOBALL_RUNS));
								int extra = Integer.parseInt(temp_Data.get(k).get(Deliveries.EXTRA_RUNS));
								over = Integer.parseInt(temp_Data.get(k).get(Deliveries.OVER));
								total_runs = total_runs + bye_r + legbye + penelty + noball + extra + wide_r + total_r;
								
							}
						
						}
						
						//checks for over
						if(over != 0.0)					
						{
						avg = (total_runs/over);	//calculate average score
						average[j] = avg;
				
						}
					}
					
				add_average_to_existing_team(team[0], (average[0] - average[1]));			//calculate run rate for team1 and maintain list
		
				add_average_to_existing_team(team[1], (average[1] - average[0]));			//calculate run rate for team2 and maintain list
			
				
			}
			display_top_team(seasons.get(l));			//displays top teams for respective season.
		
			}
	}
	
	
	//maintains overall net run rate of each team
	public static void add_average_to_existing_team(String team,float average)
	{
		for(int i = 0 ; i < final_teams.length; i++)
		{
			if(final_teams[i].equals(team))
			{
				avg_teams[i] = (avg_teams[i] + average);
				//System.out.println(final_teams[i]+" : "+avg_teams[i]);
			}
			
		}
	}
	
	// finds team with highest run rate
	public static void display_top_team(String season)
	{
		float tmp;
		String tm;
		for(int i = 0 ; i < final_teams.length ; i++)
		{
			for(int j = 0 ; j < final_teams.length - 1 ; j++)
			{
				if(avg_teams[j] < avg_teams[j+1])
				{
					tmp = avg_teams[j];
					tm = final_teams[j];
					
					avg_teams[j] = avg_teams[j+1];
					final_teams[j] = final_teams[j+1];
					
					avg_teams[j+1] = tmp;
					final_teams[j+1] = tm;
				}
			}
		}
		
		
		System.out.println("\n\n----------------Best Team of "+season+"--------------------\n");
		System.out.println(final_teams[0]+" : "+avg_teams[0]);
	}
	
	
}
