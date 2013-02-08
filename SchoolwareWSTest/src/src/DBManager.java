package src;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBManager {
			
		public static String MySQLrootUser="temp";
		public static String MySQLrootPassword="temp";
		public static String MySQLdriver="com.mysql.jdbc.Driver";
		public static String MySQLConUrl = "jdbc:mysql://127.0.0.1:3306/schoolware";
		
		 private static Connection conn = null;
	     private static Statement statement = null;
	     private static ResultSet resultSet = null;
	
	     public static void getConn() throws Exception{
	         try 
	         {
	         	Class.forName(MySQLdriver).newInstance();
	 		    conn  = DriverManager.getConnection(MySQLConUrl, MySQLrootUser, MySQLrootPassword);
	         } 
	         catch (Exception e) 
	         {
	             System.err.println("Got an exception! ");
	             System.err.println(e.getMessage());
	         }
	         
	      }
	     	     
	     public static void closeConn() throws Exception{
	    	 try
	    	 {
	    		 conn.close();
	    	 }
	    	 catch (Exception e) 
	         {
	             System.err.println("Got an exception! ");
	             System.err.println(e.getMessage());
	         }
	     }
	     
	     public static ArrayList<Integer> getTestQuestions(int testId) throws Exception{
	    	 
	    	 ArrayList<Integer> questionIDs = new ArrayList<Integer>();
	    	    	 
	    	 getConn();
	    	 statement = conn.createStatement();
	    	 
	    	 resultSet = statement.executeQuery("select question_id from question_test where test_id = " + testId);
	    	 
	    	 while (resultSet.next()){
	        
	    		 questionIDs.add(Integer.parseInt(resultSet.getString(1)));
	    		 
	         }
	    	 
	    	closeConn();
	    	return questionIDs; 
	    	 
	     }
	     
	     public static HashMap<Double,Integer> getQuestionDetails(int questionId) throws Exception{
	    	 
	    	 HashMap<Double,Integer> questionDetails = new HashMap<Double,Integer>();
	    	    	 
	    	 getConn();
	    	 statement = conn.createStatement();
	    	 
	    	 resultSet = statement.executeQuery("select time, number_of_clicks from user_question_test where question_id = " + questionId);
	    	 
	    	 while (resultSet.next()){
	        
	    		 questionDetails.put(resultSet.getDouble(1),resultSet.getInt(2));
	    		 
	         }
	    	 
	    	closeConn();
	    	return questionDetails; 
	    	 
	     }
	     

	     
}
