package test.ws;

import java.util.ArrayList;
import java.util.HashSet;

//import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.DBManager;
import src.UQuestionsCollection2;

import com.google.gson.Gson;


public class WST10b_getTestQuestions {
	
	@Test
	public void test() {
		
		int testId = 1;
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/questions/" + testId);
  
        String rawJson = driver.getPageSource();
        
        Gson gson = new Gson();

        UQuestionsCollection2 uquestion = gson.fromJson(rawJson, UQuestionsCollection2.class);
        
        
        try
        {
        	ArrayList<Integer> QuestionListDB = DBManager.getTestQuestions(testId);
        	ArrayList<Integer> QuestionListWS = new ArrayList<Integer>();
        	ArrayList<Integer> Diff = new ArrayList<Integer>();
        	
        	int count = uquestion.getUQuestion().size();
        
        	for(int i=0;i < count;i++)
        	{
        		QuestionListWS.add(i,(int) uquestion.getUQuestion().get(i).getUQuestionId());
        	}  
        	
        	HashSet<Integer> hs = new HashSet<Integer>();

        	for(int i : QuestionListDB) hs.add(i);

        	for(int i : QuestionListWS)  
        	{
        		if(hs.add(i))
        		Diff.add(i);
        	}
        	
        	if (Diff.size() == 0){
        		
        	  	System.out.println("Question IDs in DB: " + QuestionListDB);
            	System.out.println("Question IDs in Json Object: " + QuestionListWS);
            	System.out.println("Question IDs in Json Object matches Question IDs in DB");
            }
        	else{
        		
        		System.out.println("Question IDs in DB: " + QuestionListDB);
            	System.out.println("Question IDs in Json Object: " + QuestionListWS);
            	System.out.println("Question IDs Discrepencies:" + Diff);
            	System.out.println("Question ID in Json Object does not match Question IDs in DB");
        	}
        }
        catch(Exception e) 
        {
        	System.out.println("Exception:" + e);
        }
        
                
        
        
	}
}
