package helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDown {

	public static Select sel;
	public static WebElement web;

	private static Logger logger = Logger.getLogger(DropDown.class);

	public static void SelectByVisiableText(WebDriver driver, By Locator, String ByVisiableText) {
		if (driver != null && Locator != null && ByVisiableText != null) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			sel.selectByVisibleText(ByVisiableText);
			logger.info(ByVisiableText + " is selected  " + Locator + " dropDown section");
		} else {
			System.out.println("arguments Not passed correctly ");
			logger.info("arguments Not passed  correctly ");
		}
	}

	public static void SelectByValue(WebDriver driver, By Locator, String ByValue) {
		if (driver != null && Locator != null && ByValue != null) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			sel.selectByValue(ByValue);
			logger.info(ByValue + " is selected  " + Locator + " dropDown section");
		} else {
		
			logger.info("arguments Not passed  correctly ");
		}

	}

	public static void SelectByIndex(WebDriver driver, By Locator, int ByIndex) {

		if (driver != null && Locator != null && ByIndex >= 0) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			sel.selectByIndex(ByIndex);

			logger.info(ByIndex + " is selected  " + Locator + " dropDown section");
		} else {
			System.out.println("arguments Not passed correctly ");
			logger.info("arguments Not passed  correctly ");
		}
	}

	public static String getSelectedValue(WebDriver driver, By Locator) {
		String selectedValueName = "";
		if (driver != null && Locator != null) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			selectedValueName = sel.getFirstSelectedOption().getText();
			if (selectedValueName != "") {
				logger.info("returing selected drop down value  -- " + Locator);
			} else {
				logger.info("Not able To Capture Drop Down value For this field --" + Locator);
			}

		}
		return selectedValueName;

	}

	public static int getTotalDropDownValueCount(WebDriver driver, By Locator) {
		int TotalValue = 0;
		if (driver != null && Locator != null) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			TotalValue = sel.getOptions().size();
			if (TotalValue > 0) {
				logger.info("returing Total drop down  value  For this field -- " + Locator);
			} else {
				logger.info(" Not able to get Total drop down value   For this field -- " + Locator);
			}
		}
		return TotalValue;

	}

	public static boolean checkDropDownValueExistance(WebDriver driver, By Locator, String DropDownvalue) {
		boolean b = false;
		if (driver != null && Locator != null) {
			web = driver.findElement(Locator);
			Select sel = new Select(web);
			int TotalValue = sel.getOptions().size();

			for (int i = 1; i < TotalValue; i++) {
				String text = sel.getOptions().get(i).getText();

				if (text.equalsIgnoreCase(DropDownvalue)) {

					b = true;

				}

			}

		}
		return b;

	}

}
