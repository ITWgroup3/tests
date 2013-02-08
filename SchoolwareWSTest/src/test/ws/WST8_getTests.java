package test.ws;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.TestsCollection;

import com.google.gson.Gson;

public class WST8_getTests {
	
	@Test
	public void test() {
		
		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/tests/1");
        
  
        String rawJson = driver.getPageSource();
        Gson gson = new Gson();
        
        TestsCollection tests = gson.fromJson(rawJson, TestsCollection.class);
        
        Assert.assertTrue("Test Name Matches", tests.getTest().get(0).getTestName().equals("BeautifulTest1"));
        Assert.assertTrue("Test ID Matches", tests.getTest().get(1).getTestId() == 2);
        Assert.assertEquals("Test Name Matches", "abccc",tests.getTest().get(2).getTestName());
        Assert.assertFalse("Test ID does not Match", tests.getTest().get(3).getTestId() == 32);
        
	}

}
