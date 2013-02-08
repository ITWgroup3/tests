package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.CategoryCollection;

import com.google.gson.Gson;

public class WST1_getCategories {

	@Test
	public void test() {
	
		WebDriver driver = new HtmlUnitDriver();
		
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/categories");
        
        String rawJson = driver.getPageSource();
    
        Gson gson = new Gson();
   
        CategoryCollection categories = gson.fromJson(rawJson, CategoryCollection.class);
        
        System.out.println(rawJson);
        Assert.assertTrue("Category Type match", categories.getCategories().get(0).getCategType().equals("Mathematics"));
        Assert.assertTrue("Category ID match", categories.getCategories().get(1).getCategId() == 2);
       
	}

}
