package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.AppCollection;

import com.google.gson.Gson;

public class WST3_getApps {

	@Test
	public void test() {
		

		WebDriver driver = new HtmlUnitDriver();
		
		driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/applications/1");
        
        String rawJson = driver.getPageSource();
       
        Gson gson = new Gson();
        
        AppCollection apps = gson.fromJson(rawJson, AppCollection.class);
   
        Assert.assertEquals("App Name Matches", "BeautifulMaths" ,apps.getApps().get(0).getName());
        Assert.assertEquals("App ID Matches",2, apps.getApps().get(1).getId());
        Assert.assertEquals("App Size Matches", "size" ,apps.getApps().get(2).getSize());
        Assert.assertEquals("App Description Matches","This is the NEW Coursework", apps.getApps().get(3).getDescription());
        Assert.assertEquals("App url Matches", "http://schoolware.cs.ucl.ac.uk/web/Apps/Mathematics/LastApp/Coureswork.jar" ,apps.getApps().get(4).getUrl());
        Assert.assertEquals("App iconUrl Matches","http://schoolware.cs.ucl.ac.uk/web/Apps/Mathematics/AppTest/html_funny_pictures_12.jpg", apps.getApps().get(5).getIconUrl());
        Assert.assertEquals("App Category Matches", "Mathematics" ,apps.getApps().get(6).getCategoryName());
        Assert.assertEquals("App Developer Matches","knk,n,k", apps.getApps().get(7).getDeveloper());
       
	}
	
	
}



