package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Alert {

	private static Logger logger = Logger.getLogger(Alert.class);

	public static boolean isAlertPresent(WebDriver driver) {

		boolean b = false;

		try {
			driver.switchTo().alert();
			logger.info("isAlertPresent()...executed  ");
			b = true;

		} catch (Exception e) {
			logger.info("isAlertPresent()...failed from execution ");

		}
		logger.info("isAlertPresent()...returned  " + b);
		return b;

	}

	public static void acceptAlert(WebDriver driver) {

		if (isAlertPresent(driver)) {

			driver.switchTo().alert().accept();
			logger.info("acceptAlert()...executed  Alert accepted  ");
		} else {

			logger.info("acceptAlert()...failed from execution Alert Not Present");

		}
	}

	public static void dismissAlert(WebDriver driver) {

		if (isAlertPresent(driver)) {

			driver.switchTo().alert().dismiss();
			logger.info("dismissAlert()...executed---  Alert dismissed  ");
		} else {
			logger.info("dismissAlert()...failed from execution ---Alert Not Present ");
		}

	}

	public static String getAlertText(WebDriver driver) {
		String text = null;
		if (isAlertPresent(driver)) {

			text = driver.switchTo().alert().getText();
			logger.info("getAlertText()...executed-- captured text is " + text);

		} else {

			logger.info("getAlertText()...failed from execution Alert Not Present");

		}
		logger.info("getAlertText()...returned " + text);
		return text;
	}

	public static void setDataInAlertBox(WebDriver driver, String enterData) {

		if (isAlertPresent(driver)) {

			driver.switchTo().alert().sendKeys(enterData);
			logger.info("setDataInAlertBox()...executed---data set on Prompt box " + enterData);
		} else {
			logger.info("setDataInAlertBox()...failed from execution Alert Not Present ");

		}
	}

}
