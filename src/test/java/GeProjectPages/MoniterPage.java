package GeProjectPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import helper.Browser;
import invokeBrowser.StartBrowser;
import uiAction.Element;
import utility.TimeAndDate;

public class MoniterPage {

	private static By logoImage = By.xpath("//img[@class='ui image geLogo']");
	private static By ActivatedTab = By.xpath("//div[contains(@class,'menu-item active')]");
	private static By InactivatedTab = By.xpath("//div[@class=' menu-item']");
	private static String InactiveTab = "//div[@class=' menu-item']";
	private static By ActivatedSubMenu = By.xpath("//div[@class='item sub-menuitem active']");
	private static String InactiveSubMenu = "//div[@class='item sub-menuitem']";
	private static By InactiveSubTabMenuLoc = By.xpath(InactiveSubMenu);
	private static By ThemeDropDown = By
			.xpath("//div[@class='right floated one wide column theming']//div[@role='listbox']");
	private static By ThemeDropDownValue = By.xpath("//div[@class='visible menu transition']//div[@role='option']");
	private static String themeValue = "//div[@class='visible menu transition']//div[@role='option']";
	private static By temperatureExistance = By.xpath("//p[text()='Temp']/following-sibling::p//b");
	private static By windSpeed = By.xpath("//p[text()='Windspeed']/following-sibling::p//b");
	private static By  timeFieldLoc =  By.xpath("//div[@class='time-stamp timestamp-cls']");
	private static String  timeField =  "//div[@class='time-stamp timestamp-cls']";
	
	
	

	public static boolean logoVerify(WebDriver driver) {

		boolean isImageValid = Element.isValidImage(driver, logoImage);

		return isImageValid;
	}

	public static boolean activatedTabVerify(WebDriver driver) {

		boolean isActivated = verifyActiveTab(driver, ActivatedTab, "name");

		return isActivated;

	}

	public static boolean checkTabNavigation(WebDriver driver) {
		boolean status = verifyTabNavigation(driver, InactivatedTab, InactiveTab, "name");
		return status;
	}

	public static boolean checkSubMenuActivationByDefault(WebDriver driver) {

		boolean status = verifyActiveTab(driver, ActivatedSubMenu, "name");

		return status;
	}

	public static boolean checkSubMenuNavigation(WebDriver driver) {
		boolean status = verifyTabNavigation(driver, InactiveSubTabMenuLoc, InactiveSubMenu, "name");
		return status;
	}

	public static boolean checkThemeIsChaning(WebDriver driver) {

		boolean status = false;

		Element.click(driver, ThemeDropDown);

		int totalThemeSize = Element.elementCount(driver, ThemeDropDownValue);

		Element.click(driver, ThemeDropDown);
		for (int i = 1; i <= totalThemeSize; i++) {

			Element.click(driver, ThemeDropDown);
			String locator = "(" + themeValue + ")[" + i + "]";
			By elm = By.xpath(locator);

			Element.click(driver, elm);
			String themeN = Element.getText(driver, ThemeDropDown);
			WebElement element = driver.findElement(By.xpath("//html"));

			String actualThemeName = element.getAttribute("class");

			if (actualThemeName.contains(themeN.toLowerCase())) {
				status = true;

			} else {
				 locator = "(" + themeValue + ")[" + 1 + "]";
				  elm = By.xpath(locator);
				Element.click(driver, ThemeDropDown);
				status = false;
				return status;
				

			}

		}
		String locator = "(" + themeValue + ")[" + 1 + "]";
		By elm = By.xpath(locator);
		Element.click(driver, ThemeDropDown);
		Element.click(driver, elm);
		return status;

	}

	public static boolean  checkTemperatureExistance(WebDriver driver) {

		
		     boolean status = false ;
		int elm = Element.elementCount(driver, temperatureExistance);

		if (elm > 0) {

			String Temp = Element.getText(driver, temperatureExistance);
			
			     double temp =    Double.parseDouble(Temp);
			    
			  
			    if(temp > 0){
			    
			     status = true ;
			    	
			    }

		} else {
			
			 status = false ;
			System.out.println("Temprature Configuration is not there ");
		}
		return status;

	}

	public static boolean checkWindSpeedField(WebDriver driver ){
		
		   boolean status = false ;
			int elm = Element.elementCount(driver, windSpeed);

			if (elm > 0) {

				String Temp = Element.getText(driver, windSpeed);
				
				     double temp =    Double.parseDouble(Temp);
				    
				  
				    if(temp > 0){
				    
				     status = true ;
				    	
				    }

			} else {
				
				 status = false ;
				System.out.println("Temprature Configuration is not there ");
				return status ;
			}
			
			return status;
    
	}
	
	
    public static void checkDateAndTime(WebDriver driver){
    	
           int elmCount =  Element.elementCount(driver, timeFieldLoc);
           
              if(elmCount>0){
            	  
            	  
            	  
            	    for(int i = 1;i<=elmCount;i++){
            	    	 String loc = "("+timeField+")["+i+"]";
            	    	 By elm = By.xpath(loc);
            	    	String dateField =  Element.getText(driver, elm);
            	    	
            	     	TimeAndDate.getTodayDate();	
            	    	
            	    	
            	    	
            	    	 
            	    	
            	    }
            	  
            	 
            	  
            	  
            	  
              }else {
            	  
            	  
            	  System.out.println("** Date field is not displaying **");
              }
    	
    	
    	
    }
	
	
	
	
	
	

	// verify the activated tab
	public static boolean verifyActiveTab(WebDriver driver, By loc, String attributeName) {
		boolean isActivated = false;
		String currentUrl = Browser.getCurrentUrl(driver);
		String activatedTabName = Element.getAttribute(driver, loc, attributeName);

		if (currentUrl.contains(activatedTabName)) {
			isActivated = true;

		}
		return isActivated;
	}

	public static boolean verifyTabNavigation(WebDriver driver, By InactiveTab, String inactive, String attributeName) {
		boolean status = false;

		int totalTabSize = Element.elementCount(driver, InactiveTab);

		for (int i = 1; i <= totalTabSize; i++) {

			String loca = "(" + inactive + ")[" + i + "]";
			By elm = By.xpath(loca);

			String tabName = Element.getAttribute(driver, elm, attributeName);

			Element.click(driver, elm);

			String currentUrl = Browser.getCurrentUrl(driver);

			if (currentUrl.contains(tabName)) {

				status = true;
				Browser.navigateBack(driver);

			} else {
				Browser.navigateBack(driver);
				status = false;
				return status;
			}

		}
		return status;
	}
	
}
