package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Frame {

	public static int getTotalFrameCount(WebDriver driver) {

		int frameCount = driver.findElements(By.xpath("iframe")).size();
		return frameCount;

	}

	public static int getTotalFrameCountUsingJavaScript(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());
		return numberOfFrames;

	}

	public static void handleFrameUsingIndex(WebDriver driver, int indexValue) {

		if (getTotalFrameCount(driver) > 0 || getTotalFrameCountUsingJavaScript(driver) > 0) {
			try {
				driver.switchTo().frame(indexValue);
			} catch (NoSuchFrameException e) {

				System.out.println("Frame index is not matched " + e.getMessage());
			}

		} else {

			System.out.println("Frame is not available ");
		}

	}

	public static void handleFrameUsingName(WebDriver driver, String FramenameOrId) {

		if (getTotalFrameCount(driver) > 0 || getTotalFrameCountUsingJavaScript(driver) > 0) {
			try {
				driver.switchTo().frame(FramenameOrId);

			} catch (NoSuchFrameException e) {

				System.out.println("Frame Name Not exit" + e.getMessage());
			}
		} else {

			System.out.println("Frame is not available  ");
		}

	}

	public static void handleFrameUsingWebElement(WebDriver driver, By Locator) {
		if (getTotalFrameCount(driver) > 0 || getTotalFrameCountUsingJavaScript(driver) > 0) {

			try {
				WebElement web = driver.findElement(Locator);
				driver.switchTo().frame(web);
			} catch (Exception e) {

				System.out.println("Frame Element is not available" + e.getMessage());

			}
		} else {

			System.out.println("Frame is not available  ");
		}
	}

	public static void switchingBackToMainWindowFromFrame(WebDriver driver) {

		driver.switchTo().defaultContent();

	}

}