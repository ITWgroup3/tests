package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.UserTestCollection;

import com.google.gson.Gson;

public class WST9_getTestDetails {
	@Test
	public void test() {
		
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/test/1");
  
        String rawJson = driver.getPageSource();
        Gson gson = new Gson();
        
        UserTestCollection userTests = gson.fromJson(rawJson, UserTestCollection.class);
        
        Assert.assertTrue("Test Time Matches", userTests.getUserTests().get(0).getTime() == 12.0);
        Assert.assertTrue("Test ID Matches", userTests.getUserTests().get(1).getTestId() == 1);
        Assert.assertTrue("Test Questions Attented Matches", userTests.getUserTests().get(2).getQuesAttented() != 6);
        Assert.assertFalse("Test Score does not Match", userTests.getUserTests().get(0).getScore() == 44);
	}
}
