package com.mindtree.covidanalysis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.covidanalysis.entity.CovidCaseCompare;
import com.mindtree.covidanalysis.entity.CovidDataRange;
import com.mindtree.covidanalysis.entity.CovidStatecasesDto;
import com.mindtree.covidanalysis.exceptions.InvalidStateCodeException;

public class CovidAnalysisDaoImpli implements CovidAnalysisDaoInterface {
	
	
	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/covid_analysis";
	 private static final String JDBC_USERNAME = "root";
	 private static final String JDBC_PASSWORD = "root";
	 
	 
	 
	 public Connection connectTodatabase() throws ClassNotFoundException, SQLException {
		 Class.forName("com.mysql.cj.jdbc.Driver");
         
         // Create a connection to the database
         Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
          return connection;
	 }
	 
	 public boolean dateConfirm(Date d) throws ClassNotFoundException, SQLException{
		 
		 Connection connection =connectTodatabase();
		 List<Date> set=new ArrayList<>(); 
		// SQL query to retrieve data
         String sql = "SELECT DISTINCT(date) FROM covid_analysis.covid_data order by date;";
         
         // Create a prepared statement
         PreparedStatement statement = connection.prepareStatement(sql);
         
         // Execute the query
         ResultSet resultSet = statement.executeQuery();
         
         // Process the result set
         while (resultSet.next()) {
             set.add(resultSet.getDate("date"));
         }
       
         // Close resources
         resultSet.close();
         statement.close();
         connection.close();
         if(set.get(0).before(d) && set.get(set.size()-1).after(d)) {
        	 return true;
         }
         
         else {
        	 return false;
         }
		 
	 }
	 
	 
	public List<String> getAllStates() throws ClassNotFoundException, SQLException {
	
			List<String> set=new ArrayList<>();
		
            // Create a connection to the database
            Connection connection = connectTodatabase();
            
            // SQL query to retrieve data
            String sql = "SELECT DISTINCT(state) FROM covid_analysis.covid_data;";
            
            // Create a prepared statement
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Process the result set
            while (resultSet.next()) {
                set.add(resultSet.getString("state"));
            }
          
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            return set;
            
            
        } 		
	
	public List<String> getAllDistrict(String s1) throws ClassNotFoundException, SQLException   {
		// TODO Auto-generated method stub
		List<String> set=new ArrayList<>();

            
            // Create a connection to the database
            Connection connection = connectTodatabase();
            
            // SQL query to retrieve data
            String sql = "SELECT district FROM covid_analysis.covid_data WHERE state='"+s1+"';";
            
            // Create a prepared statement
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Process the result set
            while (resultSet.next()) {
                set.add(resultSet.getString("district"));
            }
            if(set.isEmpty()) {
            	throw new InvalidStateCodeException("Invalid State Code ,please check your input");
            }
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            return set;
            
            
        
		
	}
	public List<CovidDataRange> getStateByDateRange(String d1, String d2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		List<CovidDataRange> set=new ArrayList<>();
		
            // Load the MySQL JDBC driver
        
            
            // Create a connection to the database
            Connection connection = connectTodatabase();
            
            // SQL query to retrieve data
            String sql = "SELECT * FROM covid_analysis.covid_data WHERE date BETWEEN '"+d1+"' AND '"+d2+"'";
            
            // Create a prepared statement
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Execute the query
            ResultSet resultSet = statement.executeQuery();
            
            // Process the result set
            while (resultSet.next()) {
            
                set.add(new CovidDataRange(resultSet.getDate("date"),resultSet.getString("state") , resultSet.getInt("confirmed")));
            }
            
            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
            return set;
            
   
		
	}
	public List<CovidCaseCompare> getConfirmCaseByTwoState(String d1, String d2, String s1, String s2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<CovidCaseCompare> set=new ArrayList<>();
		List<CovidStatecasesDto> set2=new ArrayList<>();
		List<Date> dates=new ArrayList<>();
	
           
            
            // Create a connection to the database
            Connection connection = connectTodatabase();
            
            // SQL query to retrieve data
            String sql1 ="SELECT\r\n"
            		+ "	DISTINCT(date)\r\n"
            		+ "FROM\r\n"
            		+ "    covid_analysis.covid_data\r\n"
            		+ "WHERE\r\n"
            		+ "    state IN ('"+s1+"', '"+s2+"')\r\n"
            		+ "    AND date BETWEEN '"+d1+"' AND '"+d2+"'\r\n"
            		+ "ORDER BY\r\n"
            		+ "     date;";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
            String sql2="SELECT\r\n"
            		+ "    state,\r\n"
            		+ "    date,\r\n"
            		+ "    confirmed\r\n"
            		+ "FROM\r\n"
            		+ "    covid_analysis.covid_data\r\n"
            		+ "WHERE\r\n"
             		+ "    state IN ('"+s1+"', '"+s2+"')\r\n"
            		+ "    AND date BETWEEN '"+d1+"' AND '"+d2+"'\r\n"
            		+ "ORDER BY\r\n"
            		+ "     date;";
            
            // Create a prepared statement
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            
            // Execute the query
            ResultSet resultSet1 = statement1.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();
            
            // Process the result set
            while (resultSet2.next()) {
            	
                set2.add(new CovidStatecasesDto(resultSet2.getString("state"),
                		resultSet2.getDate("date"),resultSet2.getInt("confirmed") ));
            }
            while (resultSet1.next()) {
                
                dates.add(resultSet1.getDate("date"));
            }
            Date d;
            int totalS1;
            int totalS2;
            for(int i=0;i<dates.size();i++) {
            	d=dates.get(i);
            	totalS1=0;
            	totalS2=0;
            	for(int j=0;j<set2.size();j++) {
            		if(set2.get(j).getState().equalsIgnoreCase(s1) && d.compareTo(set2.get(j).getDate())==0) {
            			totalS1=totalS1+set2.get(j).getCasesConfirm();
            		}
            		else if(set2.get(j).getState().equalsIgnoreCase(s2) && d.compareTo(set2.get(j).getDate())==0) {
            			totalS2=totalS2+set2.get(j).getCasesConfirm();
            		}
            	}
            	set.add(new CovidCaseCompare(d, s1, totalS1, s2, totalS2));
            	
            }
            // Close resources
            resultSet1.close();
            statement2.close();
            resultSet2.close();
            statement1.close();
            connection.close();
            return set; 
            
        
		
		
	}
	 

	


}