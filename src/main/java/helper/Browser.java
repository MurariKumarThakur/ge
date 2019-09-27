package helper;


import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;

import org.openqa.selenium.WebDriver;


public class Browser {
	
	private static Logger logger = Logger.getLogger(Browser.class);

	public static void maximizeBrowser(WebDriver driver) {

		driver.manage().window().maximize();
		logger.info("maximizeBrowser()...executed[ Browser maximized ]");
		 

	}

	public static void loadUrl(WebDriver driver, String enterUrl) {

		driver.get(enterUrl);
		logger.info("loadUrl()...executed [ Loaded url is :- "+enterUrl+ " ]");
	}

	public static void navigateToUrl(WebDriver driver, String enterUrl) {

		driver.navigate().to(enterUrl);
		
		logger.info( "navigateToUrl()...executed [ Browser Navigated To "+enterUrl+" ]");
	}

	public static void navigateBack(WebDriver driver) {

		driver.navigate().back();
		String currentUrl = driver.getCurrentUrl();
		logger.info("navigateBack()...executed [ Browser Navigated Back To "+currentUrl +" ]");
		
	}

	public static void navigateForward(WebDriver driver) {

		driver.navigate().forward();
		String currentUrl = driver.getCurrentUrl();
		logger.info("navigateForward()...executed [ Browser Navigated Forward to"+currentUrl +" ]");

	}

	public static void refreshBrowser(WebDriver driver) {

		driver.navigate().refresh();
	    logger.info(" refreshBrowser()...executed---Browser refreshed ");
	}
	public static String getCurrentUrl(WebDriver driver){
		   logger.info(" getCurrentUrl()...executed");	
	String url =	driver.getCurrentUrl();
	  logger.info(" getCurrentUrl()...executed---returned url- "+url);	
	 return url ;
	}

	public static String getMainWindowName(WebDriver driver) {
        
		String windowName = driver.getWindowHandle();
		logger.info("getMainWindowName()...executed");
		logger.info("getMainWindowName()...returned windowName"+windowName);
		return windowName ;
	}

	public static Set<String> getWindowHandles(WebDriver driver) {
		logger.info("returned set of windows name ");

		return driver.getWindowHandles();
	}

	public static void switchToWindowUsingIndex(WebDriver driver, int index) {

		LinkedList<String> windowId = new LinkedList<String>(getWindowHandles(driver));
		logger.info("Total windows size is "+windowId.size());
		if (index < 0 || index > windowId.size()) {
			logger.info("given index value is invalid");
			throw new IllegalArgumentException("Invalid Index : " + index);
		}
		driver.switchTo().window(windowId.get(index));
		logger.info(index +" th window Handled");
	}
    public static int getTotalOpenedWindow(WebDriver driver){
       int i = 0;
       
       LinkedList<String> windowId = new LinkedList<String>(getWindowHandles(driver));
       
       
		i = windowId.size();
       logger.info("getTotalOpenedWindow()...Executing and returning "+i);
       return i ;
    	
    	
    	
    }
	public static void switchToParentWindow(WebDriver driver) {

		LinkedList<String> windowId = new LinkedList<String>(getWindowHandles(driver));
		driver.switchTo().window(windowId.get(0));
		logger.info("handled Parent window");
	}
	public static void closeBrowser(WebDriver driver){
		if(driver !=null){
			driver.close();
			logger.info("closeBrowser()...Executed ----Browser Closed ");
		}else{
			logger.info("driver not fount");
		}
	}
}