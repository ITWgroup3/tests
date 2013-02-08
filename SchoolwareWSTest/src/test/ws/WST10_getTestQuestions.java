package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.UQuestionsCollection;

import com.google.gson.Gson;

public class WST10_getTestQuestions {
	@Test
	public void test() {
		
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/questions/2");
  
        String rawJson = driver.getPageSource();
        
        Gson gson = new Gson();
        
        UQuestionsCollection uquestion = gson.fromJson(rawJson, UQuestionsCollection.class);
        
        System.out.println(rawJson);
        
        Assert.assertTrue("Test Score Matches for Record 1", uquestion.getUQuestion().get(0).getUQuestionId() == 4);
        Assert.assertTrue("Test Score Matches for Record 2", uquestion.getUQuestion().get(1).getUQuestionId() == 5);
        Assert.assertTrue("Test Score Matches for Record 3", uquestion.getUQuestion().get(2).getUQuestionId() == 6);
        
	}
}
