package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.AppDetails;
import com.google.gson.Gson;

public class WST4_getAppDetails {

	@Test
	public void test() 
	{

		WebDriver driver = new HtmlUnitDriver();

        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/2");

        String rawJson = driver.getPageSource();
       
        Gson gson = new Gson();

        AppDetails appID = gson.fromJson(rawJson, AppDetails.class);

      Assert.assertTrue("Application Name Matches", appID.getName().equals("NiceMaths"));
      Assert.assertTrue("Application ID Matches", appID.getId() == 2);
      Assert.assertTrue("Application Description Matches",appID.getDescription().equals("NiceMaths_Description"));
      Assert.assertTrue("Application Category Matches", appID.getCategoryName().equals("Mathematics"));
      Assert.assertTrue("Application Developer Matches", appID.getDeveloper().equals("Chirag"));
	}
}
