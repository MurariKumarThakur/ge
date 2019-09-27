package helper;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.cookie.BrowserCompatSpecFactory.SecurityLevel;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public static WebDriverWait wait;

	/*
	 * visibilityOfElementLocated() this method will check in dom section
	 * element is present or not and it will check height and width
	 * 
	 */
	private static Logger logger = Logger.getLogger(Wait.class);

	private static WebDriverWait getWait(WebDriver driver, int timeOutInSeconds, int pollingEveryInMiliSec) {

		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.ignoring(Exception.class);
		logger.info("getWait()... Executed Assigned  time is [ " + timeOutInSeconds + "] polling time is [ "
				+ pollingEveryInMiliSec + " ]");
		return wait;
	}

	public static void waitForVisibilityOfElement(Method m, WebDriver driver, int timeInSec, int pollingEveryInMiliSec,
			By locator) {
		try {
			WebDriverWait wait = getWait(driver, timeInSec, pollingEveryInMiliSec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info("waitForVisibilityOfElement()... executed For this  locator :-- [ " + locator + " ]");
		} catch (Exception e) {
			logger.info("waitForVisibilityOfElement() failed from execution on [ " + locator + " ]message is "
					+ e.getMessage());

		}

	}

	public static boolean checkElementExistance(WebDriver driver, int timeInSec, int pollingEveryInMiliSec,
			By locator) {
		boolean b = false;
		try {
			WebDriverWait wait = getWait(driver, timeInSec, pollingEveryInMiliSec);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			logger.info("checkElementExistance()... executed on  locator [ " + locator + " ]");
			b = true;
		} catch (Exception e) {
			logger.info("checkElementExistance() failed from execution on  --[" + locator + " ] " + e.getMessage());

		}
		logger.info("checkElementExistance() --- returning element existance on webpage " + b);
		return b;

	}

	/*
	 * presenceOfElementLocated() method will check element is present or not in
	 * dom, this method will not check it is visible on ui or not
	 */
	public static void waitForElementPresence(WebDriver driver, int timeInSec, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			logger.info(
					"waitForElementPresence()-- executing on [ " + locator + " ] for timeInSec [ " + timeInSec + " ]");
		} catch (Exception e) {
			logger.info("waitForElementPresence()--failed from execution on [ " + locator + " ] for timeInSec [ "
					+ timeInSec + " ]" + e.getMessage());

		}
	}

	public static void waitForElementClickable(WebDriver driver, int timeInSec, By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			logger.info("waitForElementClickable() -- executing on [ " + locator + " ] for timeInSec [ " + timeInSec
					+ " ]");
		} catch (Exception e) {
			logger.info("waitForElementClickable()--failed from execution on [ " + locator + " ] for timeInSec [ "
					+ timeInSec + " ]" + e.getMessage());

		}
	}

	public static void waitForTextToBePresent(WebDriver driver, int timeInSec, By locator, String textValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.textToBePresentInElement(locator, textValue));

			logger.info("waitForTextToBePresent()--- executed on  [" + locator + " ] founded text is -- [ " + textValue
					+ " ]");

		} catch (Exception e) {
			logger.info("waitForTextToBePresent()--- failed from execution on   [" + locator + " ] waiting for text [ "
					+ textValue + " ]" + e.getMessage());

		}
	}

	public static void waitForTitleLoad(WebDriver driver, int timeInSec, String title) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.titleIs(title));
			logger.info("waitForTitleLoad()... executed ....  founded Title is  [ " + title + " ]");
		} catch (Exception e) {
			logger.info("waitForTextToBePresent()... failed from execution waiting for Title [" + title + " ] "
					+ e.getMessage());
		}
	}

	public static void waitForUrlLoad(WebDriver driver, int timeInSec, String url) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.urlToBe(url));
			logger.info("waitForUrlLoad()... executed-- founded url is " + url);
		} catch (Exception e) {
			logger.info("waitForUrlLoad()...failed from execution waiting for [ " + url + " ]" + e.getMessage());

		}
	}

	public static void waitForlAlert(WebDriver driver, int timeInSec) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.alertIsPresent());
			logger.info("waitForlAlert()...executed -- Founded Alert");
		} catch (NoAlertPresentException e) {
			logger.info("waitForlAlert()...failed from exection --- Alert Not Found ");
		}

	}

	public static void waitForSelection(WebDriver driver, int timeInSec, By locator) {
		try {
			WebElement web = driver.findElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, timeInSec);
			wait.until(ExpectedConditions.elementToBeSelected(web));
			logger.info("waitForSelection()...executed --- on " + locator);
		} catch (Exception e) {
			logger.info("waitForSelection()...failed from execution on" + locator);

		}
	}

	public static void implicitWait(WebDriver driver, int timeInSec) {

		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
		logger.info("implicitWait()...Executed---provided time is " + timeInSec);

	}

	public static void waitTillPageLoad(WebDriver driver, int time) {

		driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);

	}

	public static void staticWait(int timeInMillesec) {
		try {
			Thread.sleep(timeInMillesec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
