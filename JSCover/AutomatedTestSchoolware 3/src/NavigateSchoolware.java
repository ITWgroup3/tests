import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class NavigateSchoolware {
	WebDriver	m_driver;
	int			m_nSelectedSideBar;
	String		m_sSelectedCategory;
	static final int CLICK_WAIT = 1000;
	
	static final int MENU_INSTALLED_APPS	= 1;
	static final int MENU_BROWSE_APPS		= 2;
	static final int MENU_FAQ				= 3;
	
	public NavigateSchoolware(WebDriver driver)
	{
		m_driver = driver;
		m_nSelectedSideBar	= 0;
		m_sSelectedCategory	= null;
	}
	
	public NavigateSchoolware selectSidebar(int type)
	{
		List<WebElement> elmList = m_driver.findElements(By.cssSelector("#container #sidebar nav ul li"));
		for ( WebElement elm : elmList )
		{
			wait(CLICK_WAIT);
			elm.click();
			String attrId = elm.getAttribute("id");
			System.out.println("id: " + attrId);
			if ( (attrId != null) && (attrId.equals(getIDinSidebarMenu(type))) )
			{
				m_nSelectedSideBar = type;
				m_sSelectedCategory= null;
				wait(CLICK_WAIT);
				return this;
			}
		}
		m_nSelectedSideBar = 0;
		m_sSelectedCategory= null;
		return this;
	}
	
	public NavigateSchoolware selectCategory(String catName)
	{
		if ( isValidMenuType(m_nSelectedSideBar) )
		{
			String selector = "#container #content #" + getIDinCategoryMenu(m_nSelectedSideBar) + " ul li a"; 
			List<WebElement> elmList = m_driver.findElements(By.cssSelector(selector));
			for ( WebElement elm : elmList )
			{
				wait(CLICK_WAIT);
				elm.click();
				String value = elm.getText();
				System.out.println("val: " + value);
				if ( (value != null) && (value.equals(catName)) )
				{
					m_sSelectedCategory = catName;
					waitForLoading();
					return this;
				}
			}
		}
		return this;
	}
	
	public NavigateSchoolware selectApp(String appName)
	{
		if ( isValidMenuType(m_nSelectedSideBar) && (m_sSelectedCategory != null) )
		{
			String selector = null;
			switch ( m_nSelectedSideBar )
			{
			case MENU_BROWSE_APPS:
				// Browse Apps
				selector = "#container #content #main-content #gen-content #app-pane .browse-app";
				break;
			case MENU_INSTALLED_APPS:
				// Installed Apps
				selector = "#container #content #main-content #gen-content #app-pane .installed-app";
				break;
			case MENU_FAQ:
			default:
				break;	
			}
			if ( selector != null )
			{
				wait(CLICK_WAIT);
				List<WebElement> elmList = m_driver.findElements(By.cssSelector(selector));
				for ( WebElement elm : elmList )
				{
					String category = elm.getAttribute("category");
					if ( m_sSelectedCategory.equals(category) )
					{
						WebElement ahref = elm.findElement(By.cssSelector("a"));
						String value = ahref.getText();
						System.out.println("app: " + value);
						if ( (value != null) && (value.equals(appName)) )
						{
							elm.click();
							//ahref.click();
							wait(CLICK_WAIT);
							return this;
						}
					}
				}
			}
		}
		return this;
	}

	public NavigateSchoolware clickClose()
	{
		List<WebElement> elmList = m_driver.findElements(By.cssSelector("#container #content #main-content #cover #full-app-desc #x"));
		for ( WebElement elm : elmList )
		{
			WebElement ahref = elm.findElement(By.tagName("a"));
			String txt = ahref.getText();
			if ( (txt != null) && (txt.equals("CLOSE")) )
			{
				elm.click();
				wait(CLICK_WAIT);
			}
		}
		return this;
	}
	
	public NavigateSchoolware clickInstall()
	{
		List<WebElement> elmList = m_driver.findElements(By.cssSelector("#container #content #main-content #cover #full-app-desc .install"));
		for ( WebElement elm : elmList )
		{
			WebElement ahref = elm.findElement(By.tagName("a"));
			String txt = ahref.getText();
			if ( (txt != null) && (txt.equals("INSTALL")) )
			{
				ahref.click();
				wait(CLICK_WAIT);
				return this;
			}
		}
		return this;
	}
	
	public NavigateSchoolware clickUninstall()
	{
		List<WebElement> elmList = m_driver.findElements(By.cssSelector("#container #content #main-content #cover #full-app-desc .uninstall"));
		for ( WebElement elm : elmList )
		{
			WebElement ahref = elm.findElement(By.tagName("a"));
			String txt = ahref.getText();
			if ( (txt != null) && (txt.equals("UNINSTALL")) )
			{
				ahref.click();
				wait(CLICK_WAIT);
				return this;
			}
		}
		return this;
	}
	
	public NavigateSchoolware clickLaunch()
	{
		List<WebElement> elmList = m_driver.findElements(By.cssSelector("#container #content #main-content #cover #full-app-desc #launch"));
		for ( WebElement elm : elmList )
		{
			WebElement ahref = elm.findElement(By.tagName("a"));
			String txt = ahref.getText();
			if ( (txt != null) && (txt.equals("LAUNCH")) )
			{
				ahref.click();
				wait(CLICK_WAIT);
				return this;
			}
		}
		return this;
	}
	
	public WebElement[] getWebElements(String cssSelector)
	{
		return null;
	}
	
	public NavigateSchoolware waitForLoading()
	{
		WebElement	elm = m_driver.findElement(By.cssSelector("#container #sidebar #loading-wrapper"));
		if ( elm != null )
		{
			wait(100);
			String styleStr = elm.getAttribute("style");
			while ( styleStr.contains("-70px") == false )
			{
				wait(100);
				styleStr = elm.getAttribute("style");
			}
		}
		return this;
	}
	
	public NavigateSchoolware waitForHideAToast()
	{
		WebElement	elm = m_driver.findElement(By.cssSelector(".toast-container"));
		if ( elm != null )
		{
			wait(100);
			try
			{
				WebElement toastElement = elm.findElement(By.cssSelector(".toast-item-wrapper")); 
				while ( toastElement != null )
				{
					wait(1000);
					try {
						toastElement = elm.findElement(By.cssSelector(".toast-item-wrapper"));
					} catch (Exception e) {
						toastElement = null;
						System.out.println("[waitForHideAToast] occured Exception!");
					}
				}
			} catch (Exception e) {
				System.out.println("[waitForHideAToast] occured Error!");
			}
		}
		return this;
	}
	
	public String getIDinSidebarMenu(int type)
	{
		String	retStr = "";
		switch ( type )
		{
		case MENU_INSTALLED_APPS:
			retStr = "ins";
			break;
		case MENU_BROWSE_APPS:
			retStr = "br";
			break;
		case MENU_FAQ:
			break;
		default:
			break;
		}
		return retStr;
	}
	
	public String getIDinCategoryMenu(int type)
	{
		String	retStr = "";
		switch ( type )
		{
		case MENU_INSTALLED_APPS:
			retStr = "inst-cat";
			break;
		case MENU_BROWSE_APPS:
			retStr = "br-cat";
			break;
		case MENU_FAQ:
			
			break;
		default:
			break;
		}
		return retStr;
	}
	
	public boolean isValidMenuType(int type)
	{
		switch ( type )
		{
		case MENU_INSTALLED_APPS:
		case MENU_BROWSE_APPS:
		case MENU_FAQ:
			return true;
		default:
			return false;
		}
	}
	
	public NavigateSchoolware wait(int ms)
	{
		try
		{
			Thread.sleep(ms);
		} catch (Exception e) {
		}
		return this;
	}

}
