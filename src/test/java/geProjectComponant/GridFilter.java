
package geProjectComponant;

import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.Test;

import helper.Browser;
import helper.Wait;
import invokeBrowser.StartBrowser;
import uiAction.Element;
import org.openqa.selenium.support.Color;;

public class GridFilter extends StartBrowser {

	private By filterBy = By.xpath("//div[@class='turbine-filters']");
	private By filterStatusCheckBox = By.xpath("//input[@class='turbine-filter-check']");
	private String filterStatusText = "//input[@class='turbine-filter-check']";
	private By statusBackGround = By.xpath("//span[contains(@class,'turbine-count turbineStatus')]");
	private String statusbackgroundText = "//span[contains(@class,'turbine-count turbineStatus')]";
	private By rowCount = By.xpath("//div[@class='grid-status-wrapper']");
	private String rowCount_text = "//div[@class='grid-status-wrapper']";
	private By turbineRowDataColor = By.xpath("//div[contains(@class,'turbine-status turbineStatus')]");
	private String turbineRowDataColorText = "//div[contains(@class,'turbine-status turbineStatus')]";
	private By turbineSectionCountLoc = By.xpath("//span[contains(@class,'turbine-count turbineStatus')]");
	private String turbineSectionCountText = "//span[contains(@class,'turbine-count turbineStatus')]";
	private By filterStatusName = By.xpath("//span[@class='turbine-filter-status']");
	private String filterStatusNameText = "//span[@class='turbine-filter-status']";
	private By gridSectionAppliedFilterNameElm = By.xpath("//div[contains(@class,'turbine-status turbineStatus')]/p");
	private String gridSectionAppliedFilterNameText = "//div[contains(@class,'turbine-status turbineStatus')]/p";

	@Test
	public void verify_FilterBy_FeatureExistance() {
         Browser.navigateToUrl(driver,"http://localhost:3001/monitor/siteoverview");
		boolean actualStatus = Wait.checkElementExistance(driver, 15, 2, filterBy);
		Assert.assertEquals(actualStatus, true);

	}

	@Test(priority = 1)
	public void verify_AllFilterStatusByDefaultSelected() {

		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);

			String isChecked = Element.getAttribute(driver, elm, "checked");

			Assert.assertEquals(isChecked, "true");
		}

	}

	@Test(priority = 2)
	public void verify_AllFilterAreUnselactable() {

		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);
			Element.click(driver, elm);
			String isChecked = Element.getAttribute(driver, elm, "checked");

			Assert.assertEquals(isChecked, null);
		}

	}

	@Test(priority = 3)
	public void verify_AllFilterStatusAreSelectable() {

		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);
			Element.click(driver, elm);
			String isChecked = Element.getAttribute(driver, elm, "checked");

			Assert.assertEquals(isChecked, "true");
		}

	}

	@Test(priority = 4)
	public void verify_filterSectionColorAndTurbineDataColorInGrid() {
		/*
		 * 1) after applying filter one by one and getting status color from
		 * filterBy section 2) turbine data (in grid) getting status color for
		 * all displaying data 3) if applied filter color and grid all data
		 * color are matching then test case pass
		 * 
		 */

		int backgroundStatusColorSize = Element.elementCount(driver, statusBackGround);

		uncheckAllStatusBox();
		for (int i = 1; i <= backgroundStatusColorSize; i++) {
			// getting all filter status color one by one
			By elm = Element.getDynamicXpath(statusbackgroundText, i);
			By checkBox = Element.getDynamicXpath(filterStatusText, i);

			String colorNameFilterSection = driver.findElement(elm).getCssValue("background-color");

			String statusBoxColor = Color.fromString(colorNameFilterSection).asHex();

			Element.click(driver, checkBox);
			Wait.staticWait(500);

			// getting turbine data color
			int rowSize = getTurbineRowCount();
			for (int j = 1; j <= rowSize; j++) {
				// validating turbine status color in grid section one by one
				By colorElm = Element.getDynamicXpath(turbineRowDataColorText, j);

				String turnineFilterColor = driver.findElement(elm).getCssValue("background-color");

				String turnineFilterColorHexa = Color.fromString(turnineFilterColor).asHex();

				// compair turbine left side section color and filter status

				Assert.assertEquals(statusBoxColor, turnineFilterColorHexa);

				By checkboxLast = Element.getDynamicXpath(filterStatusText, i);

				if (j == rowSize) {
					// before finishing loop , unchecking applied filter
					Element.click(driver, checkboxLast);
				}

			}

		}

	}

	@Test(priority = 5)
	public void verify_TurbineDataCountInGridAndFilterSectionDataCount() {
		/*
		 * 1) After applying filter get the count which is displaying filter By
		 * section in status by box section 2) get Turbine count in grid 3) if
		 * both are equal then test case pass
		 */

		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);
			Element.click(driver, elm);
			Wait.staticWait(500);

			By filterTextElm = Element.getDynamicXpath(turbineSectionCountText, i);
			
			String filterTextcount = Element.getText(driver, filterTextElm);
			int gridSectionCount = Element.elementCount(driver, turbineRowDataColor);

			int filterSectionCount = Integer.parseInt(filterTextcount);

			Assert.assertEquals(filterSectionCount, gridSectionCount);

			Element.click(driver, elm);

		}

	}

	@Test(priority = 6)
	public void verify_appliedFileterNameFirstLetterIsComingInGridSection() {

		/*
		 * 1) applied filter 2) getting the applied filter status name 3)
		 * getting first latter 4) turbine grid section getting the status box
		 * section displaying name 5) checking both Letter is matching
		 * 
		 */
		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);
			Element.click(driver, elm);
			Wait.staticWait(500);
			
			By filterNameElm = Element.getDynamicXpath(filterStatusNameText, i);

			String filterNameText = Element.getText(driver, filterNameElm);
			// getting first letter from filter By section (applied filter first
			// letter)
			char firstLatterInFilterSection = filterNameText.charAt(0);

			int appliedFilterText = Element.elementCount(driver, turbineRowDataColor);

			for (int j = 1; j <= appliedFilterText; j++) {

				By gridFilterStatusElem = Element.getDynamicXpath(gridSectionAppliedFilterNameText, j);
				char gridSectionFilterStatusName = Element.getText(driver, gridFilterStatusElem).charAt(0);

				if (gridSectionFilterStatusName == 'N') {

					if (j == 2) {

						return;
					}

				}

				Assert.assertEquals(firstLatterInFilterSection, gridSectionFilterStatusName);

			}

			Element.click(driver, elm);
			  

		}

	}

	public void uncheckAllStatusBox() {
		int totalCheckBoxCount = Element.elementCount(driver, filterStatusCheckBox);
		for (int i = 1; i <= totalCheckBoxCount; i++) {

			By elm = Element.getDynamicXpath(filterStatusText, i);
			Element.click(driver, elm);
		}
	}

	public int getTurbineRowCount() {

		int count = 0;

		count = Element.elementCount(driver, rowCount);

		return count;

	}

}
