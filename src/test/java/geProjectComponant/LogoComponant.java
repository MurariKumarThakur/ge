package geProjectComponant;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import getScreenJsonValues.GetJsonProperty;
import helper.Wait;
import invokeBrowser.StartBrowser;
import report.ExtentReport;
import uiAction.Element;

public class LogoComponant extends StartBrowser {

	private static By logoLoc = By.xpath("//div[contains(@class ,'logoImage')]");
	private By headerItemCount = By.xpath("//div[contains(@class,'wrapper-header')]/div");
	private String headerCountStringLoc = "//div[contains(@class,'wrapper-header')]/div";
	private By logoImageSrc = By.xpath("//img[@class='geLogo']");

	@BeforeClass
	public void verifyIsLogoDisplaying() {

		String logoComponantName = GetJsonProperty.getHeaderComponantName("LogoImage");
		String logoClassName = GetJsonProperty.getHeaderComponantClassName("logoImage");

		if (logoComponantName != null && logoClassName != null) {
               return ;
		} else {

			System.out.println("Logo is not configured");
			throw new SkipException(null);
		}

	}

	@Test
	public void vreifyLogoIsDisplayingOnUi() {
		
		String className = GetJsonProperty.getHeaderComponantClassName("logoImage");
		
		String logoClassNameFromUi = Element.getAttribute(driver, logoLoc, "class");
	
		Assert.assertTrue(logoClassNameFromUi.equals(className));

	}

	@Test
	public void verifyLogPositionAccordingToConfiguration() {
		
		String logoClassNameFromJson = GetJsonProperty.getHeaderComponantClassName("logoImage");
		
		int LogoImagePosition = GetJsonProperty.getHeaderComponantPosition("logoImage");
	    int logoPosition = Element.getElementPosoitionOnUi(driver, headerItemCount, headerCountStringLoc, logoClassNameFromJson);
	

				Assert.assertEquals(LogoImagePosition, logoPosition);
			
		}
		

	

	@Test
	public void verifyLogoSrc() {
		
	String logoSrc =  GetJsonProperty.getLogoSrc();
	String srcValue  =Element.getAttribute(driver, logoImageSrc, "src");
	 Assert.assertEquals(logoSrc, srcValue);
      
	}
	
	

}
