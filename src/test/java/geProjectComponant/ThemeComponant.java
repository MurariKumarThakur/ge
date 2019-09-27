package geProjectComponant;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import getScreenJsonValues.GetJsonProperty;
import helper.Browser;
import helper.Wait;
import invokeBrowser.StartBrowser;
import report.ExtentReport;
import uiAction.Element;

public class ThemeComponant extends StartBrowser {
	
	
	

	private By themeClassName = By.xpath("//div[contains(@class,'theming p-col')]");
	private By headerItemCount = By.xpath("//div[contains(@class,'wrapper-header')]/div");
	private String headerCountStringLoc = "//div[contains(@class,'wrapper-header')]/div";
	private static By ThemeDropDown = By
			.xpath("//div[contains(@class,'theming p-col')]//div[@role='listbox']");
	private static By ThemeDropDownValue = By.xpath("//div[@class='visible menu transition']//div[@role='option']");
	private static String themeValue = "//div[@class='visible menu transition']//div[@role='option']";
	private static By ThemeNameAfterAppling = By.xpath("//html");
	
	
	
	

	@BeforeClass
	public void checkThemeConfig() {
		String themeComponant = GetJsonProperty.getHeaderComponantName("Theme");
		if (themeComponant.equals("Theme")) {

		} else {
			System.out.println("Theme Configuration is not exit in json");
			throw new SkipException(null);
		}

	}

	@Test
	public void verifyIsThemeDisplaying() {

		String gettingThemeConfigUsingJson = GetJsonProperty.getHeaderComponantClassName("theming");

		String getting_hemeConfigUsingUi = Element.getAttribute(driver, themeClassName, "class");

		Assert.assertTrue(getting_hemeConfigUsingUi.contains(gettingThemeConfigUsingJson));

	}

	@Test
	public void verifyThemeComponentPositionOnHeaderSection() {

		int ThemePositionInJson = GetJsonProperty.getHeaderComponantPosition("theming");
		int themePositionOnElm = Element.getElementPosoitionOnUi(driver, headerItemCount, headerCountStringLoc,
				"theming");
		Assert.assertEquals(ThemePositionInJson, themePositionOnElm);

	}

	@Test
	public void veifyIsValueDisplayingInThemeDropDown() {

		Element.click(driver, ThemeDropDown);
		int size = Element.elementCount(driver, ThemeDropDownValue);
		Element.click(driver, ThemeDropDown); //minimizing  dropdown
		Assert.assertTrue(size > 0);

	}

	@Test
	public void verifyIsThemeChanging() {
       Element.click(driver, ThemeDropDown);
       int dropDownValue = Element.elementCount(driver, ThemeDropDownValue);
         if( dropDownValue>0){
        	 Element.click(driver, ThemeDropDown);
             for(int i=1;i<=dropDownValue;i++){
             	Element.click(driver, ThemeDropDown);
              By elm = Element.getDynamicXpath(themeValue, i);
                 Element.click(driver, elm);
                 String themeN = Element.getText(driver, ThemeDropDown);
     			String actualThemeName = Element.getAttribute(driver, ThemeNameAfterAppling, "class");
     			
     			Assert.assertTrue(actualThemeName.contains(themeN.toLowerCase()));
     		
             } 
         }else{
        	 System.out.println("Theme section drop section value is not there ");
         }
      
       
        
		  
		
	}

	@Test
	public void verifyThemeAfterNavigation() {
         Element.click(driver, ThemeDropDown);
        int dropDownValue = Element.elementCount(driver, ThemeDropDownValue);
        if(dropDownValue>1){
        	Element.click(driver, By.xpath("("+themeValue+")["+2+"]"));
        	  
        
        	 
        	 Element.click(driver, By.xpath("(//div[@class=' menu-item'])[1]"));
        	 
        	 Browser.navigateBack(driver);
        	 
        	 String themeN = Element.getText(driver, ThemeDropDown);
  			String actualThemeName = Element.getAttribute(driver, ThemeNameAfterAppling, "class");
  			Assert.assertTrue(actualThemeName.contains(themeN.toLowerCase()));
  			 
        	 
        }
		 
		
	}

	@Test
	public void verifySelectedThemeNameAfterSelection() {
		  Element.click(driver, ThemeDropDown);
	int optionCount = 	  Element.elementCount(driver, ThemeDropDownValue);
	  Element.click(driver, ThemeDropDown);
		   if(optionCount>0){
			   
			   for(int i=1;i<=optionCount;i++){
				   Element.click(driver, ThemeDropDown);
				  By elm = Element.getDynamicXpath(themeValue, i);
				  String optionText =          Element.getText(driver, elm);
				   Element.click(driver, elm);
				  String themeN = Element.getText(driver, ThemeDropDown);  
				 Assert.assertEquals(optionText, themeN);
				  
			   }
			   
		   }
	  	
		

	}
	@Test
	public void verifyIsDefaultThemeApplying(){
		
		  Browser.refreshBrowser(driver);
		  String themeN = Element.getText(driver, ThemeDropDown);  
		  Assert.assertEquals(themeN, "Light");
		
		
	}
  
}
