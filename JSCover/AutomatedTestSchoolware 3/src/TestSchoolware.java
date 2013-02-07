import org.openqa.selenium.By;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSchoolware
{
    public static void main(String args[])
    {	
    	File file = new File("/Developer/Tools/chromedriver");
    	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
  	
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	List<String> arguments = Arrays.asList("--disable-web-security", "--allow-file-access-from-files");
    	
    	capabilities.setCapability("chrome.switches", arguments);

        WebDriver driver = new ChromeDriver(capabilities);
               
        driver.get("localhost:8080/jscoverage.html?index.html");
        
        driver.switchTo().frame(driver.findElement(By.id("browserIframe")));
        
        NavigateSchoolware navi = new NavigateSchoolware(driver);
        //navi.selectSidebar("br").selectCategory("Music").selectApp("General").clickClose();
        navi.wait(1000).selectSidebar(NavigateSchoolware.MENU_FAQ);
        navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("AppTest").clickInstall().clickClose();
        //navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("AppTest").clickInstall().clickClose();
        navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickInstall().clickClose();
        navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("AppTest").clickLaunch().clickClose();
        navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickLaunch().waitForHideAToast().clickClose();
        navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("AppTest").clickUninstall().clickClose();
        navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickUninstall().clickClose();
        navi.callfunction();
        System.out.println("Finished...");
    }
}
