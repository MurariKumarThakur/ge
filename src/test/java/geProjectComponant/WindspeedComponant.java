package geProjectComponant;

import org.openqa.selenium.By;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import getScreenJsonValues.GetJsonProperty;
import invokeBrowser.StartBrowser;
import uiAction.Element;

public class WindspeedComponant extends StartBrowser{
	
	  private   By windSpeed_value =       By.xpath("(//b[contains(@class,'weather-report-signal-value quality_data')])[2]");
	     private  By windText  = By.xpath("//div[contains(@class,'WeatherReport')]//p[text()='Windspeed']");
	   	 private By headerItemCount = By.xpath("//div[contains(@class,'wrapper-header')]/div");
	   	 private String headerCountStringLoc = "//div[contains(@class,'wrapper-header')]/div";
	   	 private By windspeedIcon = By.xpath("//div[contains(@class,'weather-report-icon-container')]/i[contains(@class,'cloud')]");
	@BeforeClass
	public void checkTemperatureConfig(){
		String WindspeedComponent = GetJsonProperty.getHeaderComponantName("WeatherReport/Windspeed");
		if (WindspeedComponent.equals("WeatherReport")) {

		} else {
			System.out.println("WeaterReport[Windspeed] Configuration is not exit in json");
			throw new SkipException(null);
		}

		
	}
	@Test
	public void verifyIsWindspeedRenderOnUi(){
		// check json file Name is Temp  or not 
		  String isTempConfig = GetJsonProperty.getWeatherReportLabelText("Windspeed");
		  String labelName = Element.getText(driver, windText);
		  Assert.assertEquals(isTempConfig, labelName);
		  
		
		     
	}
	@Test
	public void verifyWindSpeedComponantPositionOnUi(){
		
		int position_accordingToJson =        GetJsonProperty.getHeaderComponantPosition("WeatherReport/Windspeed");
		int weatherReportWindspeedPositionOnElm = Element.getElementPosoitionOnUi(driver, headerItemCount, headerCountStringLoc,
				"WeatherReport/Windspeed");
		  Assert.assertEquals(position_accordingToJson, weatherReportWindspeedPositionOnElm);
		 
	}
	@Test
	public void verifyIsWindspeedDataDisplaying(){
		  
		String tempValue =  Element.getText(driver, windSpeed_value);
		   
		    Assert.assertFalse(tempValue.isEmpty());
		    
		    
	}
	

 
 @Test
 public void verifyAccordingToQualityDataColorIsChanging(){
 	
  
 
 	
 	   String colorHexaValue = "";
 	   String className =           Element.getAttribute(driver, windSpeed_value, "class");
 	  String temColor =             driver.findElement(windSpeed_value).getCssValue("color");
 	   String values []      =         className.split("_");
 	   String value = values[values.length -1 ];
 	   System.out.println(value);
 	     
 	    
 	    	  
 	    	
 	    	
 	    	   if(value.contains("0")){
 	    		   colorHexaValue = "#ffffff";	 // white
 	    	   }else if(value.contains("1")){
 	    		   
 	    		  colorHexaValue = "#008000"; //green;
 	    	   }
 	    	   
 	    	   else if(value.contains("2")){
 	    		   colorHexaValue = "#808080";  // grey
 	    	   }else if(value.contains("3")){
 	    		   colorHexaValue = "#0000ff"; // blue
 	    	   }else {
 	    		   
 	    		   colorHexaValue = "#808080";  // grey 
 	    		   
 	    	   }
 	    
     	   String hexaValue = Color.fromString(temColor).asHex();
     	      
     	   Assert.assertEquals(hexaValue,colorHexaValue );
 	      
 	   
 	  
 	   
 	                 
 	
     	   
 }
 
 @Test
 public void veifyWindspeedIcon(){
 	
   String iconNameFromJson  = 	GetJsonProperty.getWindspeedIconValue();
   String iconNameFromUi =       Element.getAttribute(driver,windspeedIcon, "class");
      Assert.assertTrue(iconNameFromUi.contains(iconNameFromJson));
 	
 }
 

}
