package uiAction;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import getScreenJsonValues.GetJsonProperty;
import helper.Browser;
import helper.Wait;
import invokeBrowser.StartBrowser;
import report.ExtentReport;
import utility.RandomData;
import utility.ScriptExecutor;

public class Element extends StartBrowser {

	private static Logger logger = Logger.getLogger(Element.class);

	public static String click(WebDriver driver, By locator) {

		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			Wait.waitForElementClickable(driver, 30, locator);
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			ScriptExecutor.scrollInToView(locator);
			elm.click();
			logger.info(" click()... executed on " + locator);
			return "Pass";

		} else {
			logger.info(" click () failed from execution on element  " + locator);
			// ExtentReport.et.log(LogStatus.INFO, " click ()... failed from
			// execution on element " + locator);

			return "Fail";
		}

	}

	public static void submit(WebDriver driver, By locator) {

		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			Wait.waitForElementClickable(driver, 30, locator);
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			elm.submit();
			logger.info("submit()... Executed On" + locator);
			// ExtentReport.et.log(LogStatus.INFO, "submit()... Executed On" +
			// locator);
		} else {
			logger.info("submit()... failed from execution on " + locator);
			// ExtentReport.et.log(LogStatus.INFO, "submit()... failed from
			// execution on " + locator);
		}

	}

	public static void clear(WebDriver driver, By locator) {

		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			ScriptExecutor.scrollInToView(locator);
			elm.clear();
			logger.info(" clear() Executed  on  " + locator);
		} else {
			logger.info(" clear() failed from execution on  " + locator);
		}

	}

	public static void sendKeys(WebDriver driver, By locator, String data) {

		if (Wait.checkElementExistance(driver, 30, 5, locator) && data != "" && data != null
				&& !data.contains("Blank")) {
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			ScriptExecutor.scrollInToView(locator);
			elm.clear();
			elm.sendKeys(data);
			logger.info(" sendKeys() Executed on " + locator + " with data " + data);
		} else {
			logger.info("sendKeys() failed from execution on " + locator + " with data " + data);
		}

	}

	public static String getText(WebDriver driver, By locator) {

		String captureText = null;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			captureText = elm.getText();

			logger.info("getText() executed on " + locator + " captured Text is " + captureText);

		} else {
			logger.info("getText() failed from execution on " + locator);
			ExtentReport.et.log(LogStatus.INFO, "Not getting text on this locator " + locator);
		}
		// ExtentReport.et.log(LogStatus.INFO,"" );
		logger.info("getText() returned " + captureText);

		return captureText;

	}

	public static String getAttribute(WebDriver driver, By locator, String parameterName) {

		String captureText = null;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			ScriptExecutor.drawBorder(elm);
			captureText = elm.getAttribute(parameterName);

			logger.info("getAttribute() executed  on " + locator + " with parameterName " + parameterName);

		} else {
			logger.info("getAttribute() failed from execution on " + locator + " with parameter name " + parameterName);
		}
		logger.info("getAttribute() returned " + captureText);
		return captureText;

	}

	public static boolean isEnabled(WebDriver driver, By locator) {

		boolean b = false;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {

			WebElement elm = driver.findElement(locator);
			elm.isEnabled();
			b = true;
			logger.info("isEnabled() Executed on " + locator);

		}

		logger.info("isEnabled() returned " + b);
		return b;
	}

	public static boolean isDisplayed(WebDriver driver, By locator) {

		boolean b = false;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			elm.isDisplayed();
			b = true;
			logger.info("isDisplayed() Executed on " + locator);
		}

		logger.info("isDisplayed() returned " + b);
		return b;

	}

	public static boolean isSelected(WebDriver driver, By locator) {

		boolean b = false;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {

			WebElement elm = driver.findElement(locator);
			elm.isSelected();
			b = true;
			logger.info("isSelected() Executed on " + locator);
		}

		logger.info("isSelected() returned " + b);
		return b;

	}

	public static int elementCount(WebDriver driver, By locator) {
		  int count = 0;
		  if(Wait.checkElementExistance(driver, 30, 5, locator)){
			  
				count = driver.findElements(locator).size();

				logger.info("elementCount()... executed on " + locator);
				logger.info("elementCount()... returned " + count);
				 
			  
		  }
		return count;

		

	}

	public static String getTitle(WebDriver driver) {
		logger.info("getTitle() executed ");
		String title = driver.getTitle();
		logger.info("getTitle() executed returned title is [ " + title + " ]");
		return title;
	}

	public static String verifyAnyLink(WebDriver driver, By Locator) {

		Element.click(driver, Locator);

		int i = Browser.getTotalOpenedWindow(driver);

		if (i > 2) {
			Wait.staticWait(5000);
			Browser.switchToWindowUsingIndex(driver, 2);
			Browser.closeBrowser(driver);

		}
		Wait.staticWait(5000);
		Browser.switchToWindowUsingIndex(driver, 1);
		String title = Element.getTitle(driver);
		logger.info("verifyAnyLink()... executed... Returned title is --- " + title);
		return title;
	}

	public static String verifyTextboxRelatedSearch(WebDriver driver, By searchBoxField, String searchData,
			By SearchButton, By resultValidation) {

		String result = null;
		Element.sendKeys(driver, searchBoxField, searchData);
		Element.click(driver, SearchButton);
		result = Element.getText(driver, resultValidation);
		logger.info("verifyTextboxRelatedSearch()... Executed ... Returned Text is --- " + result);
		return result;

	}

	public static boolean resetButtonTesting(WebDriver driver, By targetField, By resetButton) {
		boolean b = false;
		String dataInTargetSectionBeforeReset = Element.getText(driver, targetField);
		Element.click(driver, resetButton);
		String dataInTargetSectionAfterReset = Element.getText(driver, targetField);
		if (dataInTargetSectionBeforeReset != dataInTargetSectionAfterReset) {

			logger.info("resetButtonTesting()...Executing successfully ");
			b = true;

		} else {
			logger.info("resetButtonTesting()...failed from  Execution ");

		}

		logger.info("resetButtonTesting()...returning  " + b);
		return b;

	}

	public static boolean isElementFound(WebDriver driver, By locator) {
		boolean status;
		if (Wait.checkElementExistance(driver, 30, 3, locator)) {
			status = true;
		} else {

			status = false;
		}
		return status;
	}

	public static boolean isValidImage(WebDriver driver, By locator) {
		logger.info("isValidImage()...started ");
		boolean status = false;
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {

			WebElement image = driver.findElement(locator);
			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					image);

			if (ImagePresent) {
				status = true;
				logger.info("image is displaying");
			} else {
				logger.info("Sorry image is Not displaying !!!");
				status = false;
			}
		}
		return status;

	}

	public static By getDynamicXpath(String loc, int size) {

		String dynamicLoc = "(" + loc + ")[" + size + "]";
		By dynamicElm = By.xpath(dynamicLoc);

		return dynamicElm;

	}

	public static void stepInfo(String enterStepInfo) {
		ExtentReport.et.log(LogStatus.INFO, enterStepInfo);
	}

	public static int getElementPosoitionOnUi(WebDriver driver, By locator_by, String locator_str, String className) {
		int position = 0;

		int size = Element.elementCount(driver, locator_by);

		for (int i = 1; i <= size; i++) {
			By elm = Element.getDynamicXpath(locator_str, i);
			String classNameOnUi = Element.getAttribute(driver, elm, "class");

			String classNameFromJson = GetJsonProperty.getHeaderComponantClassName(className);

			if (classNameFromJson.contains(classNameOnUi)) {

				if (classNameOnUi.contains("WeatherReport")) {

					if (classNameFromJson.contains("Temp")) {

						position = i;
						return position;

					} else if (classNameFromJson.contains("Windspeed")) {
						position = i + 1;
						return position;

					}

				} else {

					position = i;
					return position;

				}

			}

		}
		return position;

	}
	
	
	public static boolean  isElementExit(By locator){
		
		boolean isExit =    Wait.checkElementExistance(driver, 30, 5, locator);
		
		  return isExit;
	}

}
