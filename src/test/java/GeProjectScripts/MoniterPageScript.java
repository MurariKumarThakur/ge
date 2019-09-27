package GeProjectScripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import GeProjectPages.MoniterPage;
import invokeBrowser.StartBrowser;

public class MoniterPageScript extends StartBrowser {
	
   
    
   @Test
	public  void verifyLogoExistance(){
		
	 boolean status = MoniterPage.logoVerify(driver);
	 
		  Assert.assertEquals(status, true);
	   
	
	}
    
    @Test
    public void verifyOneTabIsActivatedAndRelatedPageIsOpening(){
    	
    boolean isActivated =	MoniterPage.activatedTabVerify(driver);
    
        Assert.assertEquals(isActivated, true);
        
    	
    }
   @Test
    public void verifyTabNavigation(){
     boolean isNavigated =	MoniterPage.checkTabNavigation(driver);
        Assert.assertEquals(isNavigated, true);
    }
   @Test
   public void verifyOneSubMenuIsActivatedByDefault(){
	 boolean isSubMenuActive =   MoniterPage.checkSubMenuActivationByDefault(driver);
	  Assert.assertEquals(isSubMenuActive, true);
   }
   @Test
   public void  verifySubMenuNavigation(){
	    
	    boolean isSubMenuNavigationWorking = MoniterPage.checkSubMenuNavigation(driver);
	     Assert.assertEquals(isSubMenuNavigationWorking, true);
   }
   @Test
   public void verifyThemeSwitching(){
	 
	   
	 boolean  isThemeChange =  MoniterPage.checkThemeIsChaning(driver)  ;
	 
	   Assert.assertEquals(isThemeChange, true);
   }
   
   @Test
   public void verifyTemperatureFieldIsDisplaying(){
	    
	  boolean isTempFieldDisplaying =     MoniterPage.checkTemperatureExistance(driver);
	      Assert.assertEquals(isTempFieldDisplaying,true);
   }
   @Test
   public void verifyWindSpeedIsDisplaying(){
	   
	    boolean IsWindSpeedDisplaying =  MoniterPage.checkWindSpeedField(driver);
	    Assert.assertEquals(IsWindSpeedDisplaying, true);
   }
}
