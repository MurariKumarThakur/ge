package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import helper.Wait;
import invokeBrowser.StartBrowser;

public class ScriptExecutor extends StartBrowser {
	private static Logger logger = Logger.getLogger(ScriptExecutor.class);
	static JavascriptExecutor js;

	public static void hightLightElement(WebElement elm) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" + "grey" + "'", elm);

	}

	public static void drawBorder(WebElement elm) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='2px solid " + "red" + "'", elm);

	}

	public static void generateAlert(String message) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("alert('" + message + "')");
		logger.info("generateAlert() Executed Successfully with message -  " + message);

	}

	public static void clickOnElementUsingJavaScript(By locator) {
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", elm);
			logger.info(" clickOnElementUsingJavaScript() executed   " + locator);
		} else {
			logger.info(" clickOnElementUsingJavaScript() failed from Execution   " + locator);
		}

	}

	public static void refreshBrowserUsingJs() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.reload()");
		logger.info(" refreshBrowserUsingJs() Executed ");
		/*
		 * and we can use also this line to refresh browser
		 * 
		 * JavascriptExecutor js = (JavascriptExecutor)driver ;
		 * js.executeScript("history.go(0)");
		 * 
		 */

	}

	public static String getPageTitleUsingJs() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String title = js.executeScript("return document.title;").toString();
		logger.info(" Returned title is " + title);
		return title;

	}

	public static String getWebPageInnerText() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		String innerText = js.executeScript("return document.documentElement.innerText;").toString();
		logger.info(" Returned inner text is " + innerText);
		return innerText;

	}

	public static void scrollTillBottom() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

		logger.info("scrollTillBottom() Executed  ");
	}

	public static void scrollInToView(By locator) {
		if (Wait.checkElementExistance(driver, 30, 5, locator)) {
			WebElement elm = driver.findElement(locator);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", elm);
			logger.info("scrollInToView() executed on " + locator);
		} else {

			logger.info("scrollInToView() failed from execution on " + locator);
		}

	}

	public static void openNewTab(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.open()");
		logger.info("openNewTab() Executed successfully");

	}
	
	public static void convertRgbToHexa(String enterRgbColorName){
		
		  
		
		
		
	}

}
