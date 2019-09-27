package geProjectComponant;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import helper.Wait;
import invokeBrowser.StartBrowser;
import uiAction.Element;



public class TurbineGridColumnHideAndShow extends StartBrowser {

	private By hideShowIcon = By.xpath("//div[@class='turbine-filter-options']");
	private By HideAndShowContainer = By.xpath("//div[contains(@class,'ui left center popup')]");
	private By allCheckBoxList = By.xpath("//div[@class='checkBoxContianer']");
	private String allCheckBoxListText = "//div[@class='checkBoxContianer']";
    private By OnlyCheckBoxInPopup  = By.xpath("//div[contains(@class,'checkbox')]/input");
    private String onlycheckBoxText = "//div[contains(@class,'checkbox')]/input";
    
    
	@Test(priority=1)
	public void checkGridColumnHideShowIconExistance() {

		boolean isExistElement = Element.isElementExit(hideShowIcon);
		Assert.assertEquals(true, isExistElement);
		 Wait.staticWait(3000);
	}

	@Test(priority=2)
	public void checkGridColumnHideAndShowIconIsClickable() {

		Element.click(driver, hideShowIcon);
		boolean isHideAndShowColumnPopup = Element.isElementExit(HideAndShowContainer);
		Assert.assertEquals(true, isHideAndShowColumnPopup);

	}

	@Test(priority=3)
	public void verifyByDefaultAllCheckboxSelected() {
		int totalelementSize = Element.elementCount(driver, allCheckBoxList);
		for (int i = 1; i <= totalelementSize; i++) {
			By columnCheckBox = Element.getDynamicXpath(allCheckBoxListText, i);
			boolean isChecked = Element.isSelected(driver, columnCheckBox);
			Assert.assertEquals(true, isChecked);
		}

	}
	
	
	@Test(priority=4)
	public void verifyIsUserAbleToDeselectAllColumnExceptFirst(){
		
		
		 int totalelementSize =	 driver.findElements(OnlyCheckBoxInPopup).size(); 
		 
		  for(int i=1;i<=totalelementSize;i++){
			  
	        	By columnCheckBox = Element.getDynamicXpath(onlycheckBoxText, i);	  
			  
		       String value = driver.findElement(columnCheckBox).getAttribute("value");
					    String dynamicLoc = "//label[text()='"+value+"']";
			      
			       By checkBoxLoc = By.xpath(dynamicLoc);
			       
			       driver.findElement(checkBoxLoc).click();
			   boolean status = driver.findElement(checkBoxLoc).isSelected();      
			           if(i==1){
			     String  firstColumnStatus = driver.findElement(checkBoxLoc).getAttribute("class")	;  
			           Assert.assertTrue(firstColumnStatus.contains("checked"));
			           }else{
			        	   Assert.assertEquals(status, null);     
			           }
			     
			  
		  }
		
	
	}
	     @Test(priority=5)
		public void VerifyAllColumnAreSelectable(){
			
			 
			
		}

		
	
		
		
	
}
