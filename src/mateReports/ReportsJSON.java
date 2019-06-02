package mateReports;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReportsJSON {
    @SuppressWarnings("unused")
	public static void main(String[] args) {

    	JSONParser parser = new JSONParser();
		JSONArray data = new JSONArray();
		JSONArray definition = new JSONArray();
		float score = 0;
		try {
			    data = (JSONArray) parser.parse(new FileReader("D:\\JULIEN\\Uni\\Java\\ReportsMentormate\\resources\\data.json"));
			    definition = (JSONArray) parser.parse(new FileReader("D:\\JULIEN\\Uni\\Java\\ReportsMentormate\\resources\\definition.json"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e)
		{
			e.printStackTrace();
		} 
		JSONObject person = new JSONObject();
		String name ;
		String totalSales ;
		String salesPeriod ;
		String experienceMultiplier ;
		JSONObject rule = new JSONObject();
    	String topPerformersThreshold;
    	String useExprienceMultiplier;
    	String periodLimit;
    	
    	
    	  for (Object o : data)
    	  {
    	      person = (JSONObject) o ;
    	      name = (String) person.get("name");
   	          totalSales = (String) person.get("totalSales");
   	          salesPeriod = (String) person.get("salesPeriod");
    	      experienceMultiplier = (String) person.get("experienceMultiplier"); 
			  int totalSalesToInt =Integer.parseInt(totalSales);	
			  int salesPeriodToInt =Integer.parseInt(salesPeriod);	
			  float experienceMultiplierToFloat =Float.parseFloat(experienceMultiplier);
			  int periodLimitToInt = 0;
			  int topPerformersThresholdToInt =0;
              boolean  name1= name.contains("J");
			  
    	      for (Object p : definition)
    	  {
    	      rule = (JSONObject) p ;
    	      topPerformersThreshold = (String) rule.get("topPerformersThreshold");
   	          useExprienceMultiplier = (String) rule.get("useExprienceMultiplier");
  	          periodLimit = (String) rule.get("periodLimit");
  	          if(useExprienceMultiplier.equals("false"))
  	          {
  	        	score = totalSalesToInt/salesPeriodToInt*experienceMultiplierToFloat;
  	          }
  	          else
  	          {
  	        	score = totalSalesToInt/salesPeriodToInt; 
  	          } 
  	         periodLimitToInt = Integer.parseInt(periodLimit);
  	         topPerformersThresholdToInt = Integer.parseInt(topPerformersThreshold);
    	  } 
    	       List<List<String>> rows= new ArrayList<List<String>>();
    	      String scoreS= Float.toString(score) ;	
    	      if (name.contains("J"))
    	      {
    	    	 rows = Arrays.asList(Arrays.asList(name, scoreS));
    	    	 System.out.println();
    	      }else if (name.contains("D"))
    	      {
    	    	 rows = Arrays.asList(Arrays.asList(name, scoreS));
    	    	 System.out.println();
    	      }
    	      FileWriter csvWriter;
    	      if(salesPeriodToInt<=periodLimitToInt)
    	      {
    	    	  try {
			  csvWriter = new FileWriter("result.csv");
			  csvWriter.append("Name         ");  
    	      csvWriter.append(",");  
    	      csvWriter.append("score");  
    	      csvWriter.append(",");  
    	      csvWriter.append("\n");

    	      for (List<String> rowData : rows) {  
    	          csvWriter.append(String.join(",", rowData));
    	          csvWriter.append("\n");
    	          System.out.println(rows);
    	      }
    	      csvWriter.flush();  
    	      csvWriter.close(); 
      }
			 catch (IOException e) 
		{
				e.printStackTrace();
		}
    	 
    	  	}else {
    	  		System.out.println("Cant be created!");
    	  	}
    	  }
    	      }

    }