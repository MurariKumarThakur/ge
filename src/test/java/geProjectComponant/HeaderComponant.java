package geProjectComponant;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



import getScreenJsonValues.GetJsonProperty;
import helper.Browser;
import helper.Wait;
import invokeBrowser.StartBrowser;

import uiAction.Element;

public class HeaderComponant extends StartBrowser {

	private static Logger logger = Logger.getLogger(HeaderComponant.class);

	private By headerComponent = By.xpath("//div[contains(@class,'wrapper-header')]");
	private By errorPage = By.xpath("//div[contains(text(),'Error: Cannot find module' )]");
	private By headerItemCount = By.xpath("//div[contains(@class,'wrapper-header')]/div");
	private String headerRanderedClass = "//div[contains(@class,'wrapper-header')]/div";
	private By mainMenuCount = By
			.xpath("//div[contains(@class,'main-menu-items')]//a");
	private String menuText = "//div[contains(@class,'main-menu-items')]//a";
	private static By ActivatedTab = By.xpath("//div[contains(@class,'menu-item active')]");
	
	
	
	
	

	@Test
	public void verify_IsHeaderDisplaying() {
		Element.stepInfo("getting headerClassName value form json");
		String getHeaderClassNameFromJson = GetJsonProperty.getHeaderLayoutMetaDataClassName();

		Element.stepInfo("checking header className ");

		
		String gettingHederClassNameFromUi = Element.getAttribute(driver, headerComponent, "class");
             Element.stepInfo("checking both place className");
		Assert.assertTrue(gettingHederClassNameFromUi.contains(getHeaderClassNameFromJson));

	}

	@Test
	public void verify_ItemCountOnHeaderSection() {
		Element.stepInfo("getting header's  item count from json");
		int headerItemsCountFromJson = GetJsonProperty.getHeaderItemsCount();
		Element.stepInfo("getting header's  item count from Ui");
		int headerItemCountFromApplication = Element.elementCount(driver, headerItemCount);
		Element.stepInfo("checking both items counts are same or not");
		Assert.assertEquals(headerItemsCountFromJson, headerItemCountFromApplication);

	}

	@Test
	public void verify_correctHeaderItemsAreDispalying() {

		Element.stepInfo("getting header's items className from json");
		List classNameFromJson = GetJsonProperty.getHeaderItemsClassName();

		int headerClassCount = Element.elementCount(driver, headerItemCount);
		Element.stepInfo("getting header's items className from Ui");
		for (int i = 1; i <= headerClassCount; i++) {
			int j = i - 1;
			By elm = Element.getDynamicXpath(headerRanderedClass, i);

			String classNameFromApplication = Element.getAttribute(driver, elm, "class");

			String classNameFromeJson = (String) classNameFromJson.get(j);

			Assert.assertTrue(classNameFromApplication.contains(classNameFromeJson));

		}
		Element.stepInfo("checking items class name is matching or not ");

	}

	@Test
	public void verify_MainMenuCountOnHeaderSection() {
		Element.stepInfo("getting main menu count from json");
		int actualMenuCount = GetJsonProperty.getMainMenuCount();
		Element.stepInfo("getting main menu count from Ui");
		int expectedMenuCount = Element.elementCount(driver, mainMenuCount);
		Element.stepInfo("checking both count is same or not ");
		Assert.assertEquals(actualMenuCount, expectedMenuCount);

	}

	@Test
	public void verify_MainMenuTitleOnHeaderSection() {
		Element.stepInfo("getting main menu title from json");
		List menuListActual = GetJsonProperty.getMenuProperty("title");
		int menuCount = Element.elementCount(driver, mainMenuCount);
		ArrayList list = new ArrayList();
		Element.stepInfo("getting main menu title from Ui");
		for (int i = 1; i <= menuCount; i++) {
			By menuElm = Element.getDynamicXpath(menuText, i);
			String menuListExpected = Element.getText(driver, menuElm);

			list.add(menuListExpected);

		}
		Element.stepInfo("checking both are equal or not ");
		Assert.assertEquals(list, menuListActual);

	}

	@Test
	public void verify_MainMenuNavigation() {
		Element.stepInfo("getting url path from json ");
		int menuCount = Element.elementCount(driver, mainMenuCount);
		List actualUrl = GetJsonProperty.getMenuProperty("pathName");
		Element.stepInfo("clicking on main menu one by one and taking current url ");
		for (int i = 1; i <= menuCount; i++) {
			int s = i - 1;
			By menuElm = Element.getDynamicXpath(menuText, i);

			Element.click(driver, menuElm);
			Wait.staticWait(2000);
			String currentUrl = Browser.getCurrentUrl(driver);

			String pathFromJson = (String) actualUrl.get(s);

			Assert.assertTrue(currentUrl.contains(pathFromJson));

			Browser.navigateBack(driver);

		}
		Element.stepInfo("checking current url is containing json path or not");

	}

	@Test
	public void verify_DefaultMenuSelection() {

		String defaultSelection = GetJsonProperty.getDefaultActiveItem();
		String gettingActiveItemFromUi = Element.getText(driver, ActivatedTab);
		Assert.assertEquals(gettingActiveItemFromUi, defaultSelection);

	}

}
