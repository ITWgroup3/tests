package test.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.google.gson.Gson;

import src.DBManager;
import src.UserUQuestionsCollection2;


public class WST11b_getQuestionDetails {
	@Test
	public void test() {
		
		int questionId = 1;
		
		WebDriver driver = new HtmlUnitDriver();
        
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/question/1");    
        
		String rawJson = driver.getPageSource();
        
		System.out.println(rawJson);
        
		Gson gson = new Gson();
        
		UserUQuestionsCollection2 userUQuestion =gson.fromJson(rawJson, UserUQuestionsCollection2.class);
        
		 try
	        {
			 HashMap<Double,Integer> QuestionListDB = DBManager.getQuestionDetails(questionId);
			 Map<Double,Integer> QuestionListWS = new HashMap<Double,Integer>();
			 Map<Double,Integer> Diff = new HashMap<Double,Integer>();
	        	
	        	int count = userUQuestion.getUserUQuestion().size();
	        
	        	for(int i=0;i < count;i++)
	        	{
	        		QuestionListWS.put((double) userUQuestion.getUserUQuestion().get(i).getTime(),(int) userUQuestion.getUserUQuestion().get(i).getNumberOfClicks());
	        	}  
	        	System.out.println(count);
	        	System.out.println("Question IDs in DB: " + QuestionListDB);
	        	System.out.println("Question IDs in Json Object: " + QuestionListWS);
	        	
//	        	HashSet<Integer> hs = new HashSet<Integer>();
//
//	        	for(int i : QuestionListDB) hs.add(i);
//
//	        	for(int i : QuestionListWS)  
//	        	{
//	        		if(hs.add(i))
//	        		Diff.add(i);
//	        	}
//	        	
//	        	if (Diff.size() == 0){
//	        		
//	        	  	System.out.println("Question IDs in DB: " + QuestionListDB);
//	            	System.out.println("Question IDs in Json Object: " + QuestionListWS);
//	            	System.out.println("Question IDs in Json Object matches Question IDs in DB");
//	            }
//	        	else{
//	        		
//	        		System.out.println("Question IDs in DB: " + QuestionListDB);
//	            	System.out.println("Question IDs in Json Object: " + QuestionListWS);
//	            	System.out.println("Question IDs Discrepencies:" + Diff);
//	            	System.out.println("Question ID in Json Object does not match Question IDs in DB");
//	        	}
	        }
	        catch(Exception e) 
	        {
	        	System.out.println("Exception:" + e);
	        }
	        
		
		
		
		
		Assert.assertTrue("Duration",userUQuestion.getUserUQuestion().get(0).getTime()==0 );
		Assert.assertTrue("Number of Clicks ",userUQuestion.getUserUQuestion().get(0).getNumberOfClicks()==1 );
	}

}
