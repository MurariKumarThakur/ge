package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import invokeBrowser.StartBrowser;

public class ScreenShot extends StartBrowser {
	static String screenShotPath;
	private static Logger logger = Logger.getLogger(ScreenShot.class);

	public static void getScreenShot(WebDriver driver, String screenshotName) {

		TakesScreenshot tc = (TakesScreenshot) driver;

		File file = tc.getScreenshotAs(OutputType.FILE);

		String ScreenShotpath = "./screenShotFolder/" + screenshotName + ".jpeg";
		try {

			FileUtils.copyFile(file, new File(ScreenShotpath), true);
			logger.info(" * screenShot captured  * ");
		} catch (IOException e) {

			e.printStackTrace();
			logger.info(" * Not able to capture screenShot   * ");
		}

	}

	public static String getScreenShotPath( String screenShotName) {

		TakesScreenshot tc = (TakesScreenshot) driver;

		File file = tc.getScreenshotAs(OutputType.FILE);

		try {
			screenShotPath = System.getProperty("user.dir") + "/screenShotFolder/" + screenShotName + ".jpeg";
			logger.info(" * screenShot captured  * ");
			FileUtils.copyFile(file, new File(screenShotPath), true);
		} catch (IOException e) {

			e.printStackTrace();
			logger.info(" * Not able to capture screenShot   * ");
		}
		return screenShotPath;

	}

}
