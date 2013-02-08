package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import com.google.gson.Gson;

import src.UserUQuestionsCollection;


public class WST11_getQuestionDetails {
	@Test
	public void test() {
		
		WebDriver driver = new HtmlUnitDriver();
        
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/question/1");    
        
		String rawJson = driver.getPageSource();
        
		System.out.println(rawJson);
        
		Gson gson = new Gson();
        
		UserUQuestionsCollection userUQuestion =gson.fromJson(rawJson, UserUQuestionsCollection.class);
        
		Assert.assertTrue("Duration",userUQuestion.getUserUQuestion().get(0).getTime()==0 );
		Assert.assertTrue("Number of Clicks ",userUQuestion.getUserUQuestion().get(0).getNumberOfClicks()==1 );
	}

}
