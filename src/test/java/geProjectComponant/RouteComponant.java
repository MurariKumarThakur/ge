package geProjectComponant;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import getScreenJsonValues.GetJsonProperty;
import helper.Browser;
import helper.Wait;
import invokeBrowser.StartBrowser;
import uiAction.Element;

public class RouteComponant extends StartBrowser {
	
	

	private By pageNotFound = By.className("page-not-found");
	String moniter_aux_equ_Page = "/monitor/auxiliaryequipments";
	String moniter_siteOverview_page = "/monitor/siteoverview";
	String wrongUrl = "/Murari";
	
	
	

	String defaultUrl = StartBrowser.url;

	@Test
	public void VerifyIsDefaultUrlApplying() {

		String defaultRoute = GetJsonProperty.getDefaultRoute();
		Browser.navigateToUrl(driver, defaultUrl);
		Wait.staticWait(3000);
		String currentUrl = Browser.getCurrentUrl(driver);

		Assert.assertTrue(currentUrl.contains(defaultRoute));

	}

	@Test
	public void VerifyPageNotFoundInCaseOfWrongUrl() {

		String currentUrl = Browser.getCurrentUrl(driver);

		Browser.navigateToUrl(driver, currentUrl + wrongUrl);

		boolean isPageFound = Element.isElementFound(driver, pageNotFound);

		Assert.assertEquals(isPageFound, true);

		Browser.navigateToUrl(driver, defaultUrl);

	}

	@Test
	public void VerifyIsPageNavigationWorking() {

		Browser.navigateToUrl(driver, defaultUrl + moniter_aux_equ_Page);

		String mon_aux_equ_page = Browser.getCurrentUrl(driver);

		Browser.navigateToUrl(driver, defaultUrl + moniter_siteOverview_page);

		String def_Mon_site = Browser.getCurrentUrl(driver);

		Assert.assertTrue(!mon_aux_equ_page.equals(def_Mon_site));

	}

	@Test
	public void verifyAfterRefreshIsPageChanging() {
		   Wait.staticWait(1000);
		String firstUrl = Browser.getCurrentUrl(driver);

		Browser.refreshBrowser(driver);
        Wait.staticWait(1000);
		String secondUrl = Browser.getCurrentUrl(driver);

		Assert.assertEquals(firstUrl, secondUrl);

	}

	@Test
	public void verifyIsBrowserSavingPageNavigationHistory() {
		 Wait.staticWait(1000);
		String oldUrl_1 = Browser.getCurrentUrl(driver);
            
		Browser.navigateToUrl(driver, defaultUrl + moniter_aux_equ_Page);
		 Wait.staticWait(1000);
		String oldUrl_2 = Browser.getCurrentUrl(driver);

		Browser.navigateBack(driver);
		 Wait.staticWait(1000);
		String oldUrl_3 = Browser.getCurrentUrl(driver);

		Assert.assertEquals(oldUrl_1, oldUrl_3);
         
		Browser.navigateForward(driver);
          Wait.staticWait(1000);
		String oldUrl_4 = Browser.getCurrentUrl(driver);

		Assert.assertEquals(oldUrl_2, oldUrl_4);

	}

}
