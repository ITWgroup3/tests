package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.TypesCollection;

import com.google.gson.Gson;

public class WST2_getTypes {

	@Test
	public void test() {
		
		WebDriver driver = new HtmlUnitDriver();
		
		
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/types");
       
        String rawJson = driver.getPageSource();
        
        Gson gson = new Gson();
        
        TypesCollection types = gson.fromJson(rawJson, TypesCollection.class);
       
        Assert.assertTrue("Application Type Match", types.getTypes().get(0).getAppType().equals("Jar File"));
        Assert.assertTrue("Type Id Match", types.getTypes().get(1).getTypeId() == 2);
        Assert.assertTrue("Application Extension", types.getTypes().get(2).getAppExtention().equals(".apk"));
       
	}
	
}

