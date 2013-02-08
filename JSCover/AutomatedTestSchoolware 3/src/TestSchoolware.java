import org.openqa.selenium.By;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class TestSchoolware
{
	private static ChromeDriverService service;
	private static WebDriver driver;
	private static JavascriptExecutor js;
	
	private static String CHROMEDRIVER_PATH = "/Users/hideki/workspace/study/ucl/chromedriver";
	private static String SCRIPT_PATH =
			"/Users/hideki/workspace/study/ucl/schoolware_cpp/Schoolware---Client-App/SchoolWareWebSite/";
	private static String LAUNCH_JSCOVER_SCRIPT_NAME = "Schoolware-server.sh";
	private static String MERGE_REPORT_SCRIPT_NAME = "add_merge_report.sh";
	private static String LAUNCH_JSCOVER_SCRIPT_FULLPATH = SCRIPT_PATH + LAUNCH_JSCOVER_SCRIPT_NAME;
	private static String MERGE_REPORT_SCRIPT_FULLPATH = SCRIPT_PATH + MERGE_REPORT_SCRIPT_NAME;

    public static void main(String args[])
    {	
    	//File file = new File(CHROMEDRIVER_PATH);
    	//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
    	launchShellScript(LAUNCH_JSCOVER_SCRIPT_FULLPATH, SCRIPT_PATH);
    	
    	createAndStartService();
    	
    	//DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	//List<String> arguments = Arrays.asList("--disable-web-security", "--allow-file-access-from-files");
    	//capabilities.setCapability("chrome.switches", arguments);

        //driver = new ChromeDriver(capabilities);
    	createDriver();
    	
    	try
    	{
    		NavigateSchoolware navi = new NavigateSchoolware(driver);
            // 1st Time.
    		driver.get("localhost:8080/jscoverage.html?index.html");
    		driver.switchTo().frame(driver.findElement(By.id("browserIframe")));
    		navi.wait(3000);
    		
            // 2nd Time.
    		driver.get("localhost:8080/jscoverage.html?index.html");
    		driver.switchTo().frame(driver.findElement(By.id("browserIframe")));
    		
    		//navi.selectSidebar("br").selectCategory("Music").selectApp("General").clickClose();
    		navi.wait(1000).selectSidebar(NavigateSchoolware.MENU_FAQ);
    		navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("AppTest").clickInstall().clickClose();
    		navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickInstall().clickClose();
    		
    		// 3rd Time.
    		driver.get("localhost:8080/jscoverage.html?index.html");
    		driver.switchTo().frame(driver.findElement(By.id("browserIframe")));
    		navi.selectSidebar(NavigateSchoolware.MENU_BROWSE_APPS).selectCategory("Mathematics").selectApp("AppTest").clickInstall().clickClose();
        	navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("AppTest").clickLaunch().clickClose();
        	navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickLaunch().waitForHideAToast().clickClose();
        	navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("AppTest").clickUninstall().clickClose();
        	navi.selectSidebar(NavigateSchoolware.MENU_INSTALLED_APPS).selectCategory("Mathematics").selectApp("MathsAppNew").clickUninstall().clickClose();
        
        	// Switch back to the main document
        	driver.switchTo().defaultContent();
        	// Store the coverage report
        	js.executeScript("jscoverage_selectTab('storeTab');");
        	js.executeScript("jscoverage_storeButton_click();");
        	navi.wait(2000);
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
        // Stop the Server 
        driver.get("localhost:8080/stop");
        
        quitDriver();
        
        createAndStopService();
        
    	launchShellScript(MERGE_REPORT_SCRIPT_FULLPATH, SCRIPT_PATH);
        System.out.println("Finished...");
    }
    
    public static void launchShellScript(String cmd, String dir) {
    	System.out.println("launchShellScript()");
    	System.out.println("cmd:" + cmd);
    	System.out.println("dir:" + dir);
    	try
    	{
    		ProcessBuilder pb = new ProcessBuilder(cmd);
    		pb.directory(new File(dir));
    		Process p = pb.start();
    	} catch (Exception e) {
    		System.out.println("[launchShellScript]\n" + e.toString());
    	}
    }
    
    public static void createAndStartService() {
    	System.out.println("createAndStartService()");
    	try
    	{
    		service = new ChromeDriverService.Builder()
    			.usingChromeDriverExecutable(new File(CHROMEDRIVER_PATH))
    			.usingAnyFreePort()
    			.build();
    		service.start();
    	} catch (IOException e) {
    		System.out.println("[createAnsStartService()]\n" + e.toString());
    	}
    }
    
    public static void createAndStopService() {
    	System.out.println("createAndStopService()");
        service.stop();
    }
    
    public static void createDriver() {
    	System.out.println("createDriver()");
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    	List<String> arguments = Arrays.asList("--disable-web-security", "--allow-file-access-from-files");
    	capabilities.setCapability("chrome.switches", arguments);
    	
        driver = new RemoteWebDriver(service.getUrl(), capabilities);
        js = (JavascriptExecutor)driver;
    }
    
    public static void quitDriver() {
    	System.out.println("quitDriver()");
        driver.quit();
    }
}
