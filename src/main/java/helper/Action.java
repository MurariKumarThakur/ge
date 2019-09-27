package helper;


import java.awt.Robot;


import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class Action {
	private static Logger logger = Logger.getLogger(Action.class);

	public static void dragAndDrop( WebDriver driver, By sourceLoc, By targetLoc) {
		
		Actions act = new Actions(driver);

		if (Wait.checkElementExistance(driver, 30, 5, sourceLoc)
				&& Wait.checkElementExistance(driver, 30, 5, targetLoc)) {

			WebElement source = driver.findElement(sourceLoc);
			WebElement target = driver.findElement(targetLoc);
			act.dragAndDrop(source, target).perform();
			logger.info("dragAndDrop()...executing-- on sourceLocator [ "+sourceLoc +" ] And target locator ["+targetLoc +" ]");
			
		} else {
		
			logger.info("dragAndDrop()...failed from execution-- on sourceLocator [ "+sourceLoc +" ] And target locator ["+targetLoc +" ]");
		}
		
	}

	public static void mouseHover( WebDriver driver, By locator) {
		
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
			Actions act = new Actions(driver);

		WebElement elm = driver.findElement(locator);
		act.moveToElement(elm).perform();
		logger.info("mouseHover()...executing---on [ "+locator +" ]");
		}
		else{
		logger.info("mouseHover()...failed from execution --on [ "+locator +" ] ");	
		}
		
	}

	public static void mouseHoverAndClick( WebDriver driver, By locator) {
		Actions act = new Actions(driver);
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
		  act = new Actions(driver);
		WebElement elm = driver.findElement(locator);
		act.moveToElement(elm).click().build().perform();
		logger.info("mouseHoverAndClick()...executing on [ "+locator+ " ]");
		}
		else{
			logger.info("mouseHoverAndClick()...failed from execution  on [ "+locator+ " ]");	
		}
		
	}

	public static void rightclickOnElement( WebDriver driver, By locator) {
		Actions act = new Actions(driver);
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
		 act = new Actions(driver);
		WebElement elm = driver.findElement(locator);
		act.contextClick(elm).perform();
		logger.info("rightclickOnElement()...executing on [ "+locator+ " ]");
		}
		else{
			logger.info("rightclickOnElement()...failed from execution on [ "+locator+ " ]");
		}
		
	}

	public void clickOnElement( WebDriver driver, By locator) {
		Actions act = new Actions(driver);
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
		
		WebElement elm = driver.findElement(locator);
		act.click(elm).perform();
		logger.info("clickOnElement()...executing on [ "+locator+ " ]");
	}
	else{
		logger.info("clickOnElement()...failed from execution  on [ "+locator+ " ]");
	}
	
	}

	public void doubleClickOnElement( WebDriver driver, By locator) {
		Actions act = new Actions(driver);
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
	
		WebElement elm = driver.findElement(locator);
		act.doubleClick(elm).perform();
		logger.info("doubleClickOnElement()...executing on [ "+locator+ " ]");
	
		}
		else{
			logger.info("doubleClickOnElement()...failed from execution  on [ "+locator+ " ]");
		}
			

	}

	public static void clickAndHold( WebDriver driver, By locator){
		Actions act = new Actions(driver);
		if (Wait.checkElementExistance(driver, 30, 5, locator)){
	
		WebElement elm = driver.findElement(locator);
		act.clickAndHold(elm);
		logger.info("clickAndHold()...executing on [ "+locator+ " ]");
		}
		else{
			logger.info("clickAndHold()...failed from execution on [ "+locator+ " ]");
		}
			

	}

	// example of keyCombination = keys.CONTROL ;
	public static void Keydown( WebDriver driver, Keys keyCombination) {
		
		
		Actions act = new Actions(driver);
		act.keyDown(keyCombination).keyUp(keyCombination).build().perform();
		logger.info("Keydown() is executed on "+keyCombination);

	}

	public static void pressKeyCombination( WebDriver driver, Keys keyCombination, String keyValue) {
		
		Actions act = new Actions(driver);
		
		act.keyDown(keyCombination).sendKeys(keyValue).build().perform();
		 logger.info("pressKeyCombination() executed On " + keyCombination +" And "+ keyValue);
	}

	// key code = KeyEvent.VK_expectedKey
	public static void keyPressUsingRobotClass(WebDriver driver, int KeyEvent_VK_expectedKey) {
	
		try {
			Robot r = new Robot();

			r.keyPress(KeyEvent_VK_expectedKey);
			r.keyRelease(KeyEvent_VK_expectedKey);
			
			logger.info("keyPressUsingRobotClass()...executed on "+KeyEvent_VK_expectedKey);
		} catch (Exception e) {
			
			logger.info("keyPressUsingRobotClass()...failed from execution on "+KeyEvent_VK_expectedKey+ e.getLocalizedMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
