package geProjectComponant;

import java.lang.reflect.Array;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import getScreenJsonValues.GetJsonProperty;
import helper.Wait;
import invokeBrowser.StartBrowser;
import uiAction.Element;
import org.openqa.selenium.support.Color;




public class Temperature extends StartBrowser {
	
	     private   By temloc_value =       By.xpath("(//b[contains(@class,'weather-report-signal-value quality_data')])[1]");
	     private  By TempText  = By.xpath("//div[contains(@class,'WeatherReport')]//p[text()='Temp']");
	   	 private By headerItemCount = By.xpath("//div[contains(@class,'wrapper-header')]/div");
	   	 private String headerCountStringLoc = "//div[contains(@class,'wrapper-header')]/div";
	   	 private By temperatureIcon = By.xpath("//div[contains(@class,'weather-report-icon-container')]/i[contains(@class,'cloud')]");
	@BeforeClass
	public void checkTemperatureConfig(){
		String TempComponent = GetJsonProperty.getHeaderComponantName("WeatherReport/Temp");
		if (TempComponent.equals("WeatherReport")) {

		} else {
			System.out.println("WeaterReport[Temperature] Configuration is not exit in json");
			throw new SkipException(null);
		}

		
	}
	@Test
	public void verifyIsTemperatureRenderOnUi(){
		// check json file Name is Temp  or not 
		  String isTempConfig = GetJsonProperty.getWeatherReportLabelText("Temp");
		  String labelName = Element.getText(driver, TempText);
		  Assert.assertEquals(isTempConfig, labelName);
		  
		
		     
	}
	@Test
	public void verifyTemperatureComponantPositionOnUi(){
		
		int position_accordingToJson =        GetJsonProperty.getHeaderComponantPosition("WeatherReport/Temp");
		int weatherReportTempPositionOnElm = Element.getElementPosoitionOnUi(driver, headerItemCount, headerCountStringLoc,
				"WeatherReport/Temp");
		  Assert.assertEquals(position_accordingToJson, weatherReportTempPositionOnElm);
		 
	}
	@Test
	public void verifyIsTemperatureDataDisplaying(){
		  
		String tempValue =  Element.getText(driver, temloc_value);
		   
		    Assert.assertFalse(tempValue.isEmpty());
		    
		    
	}
	

    
    @Test
    public void verifyAccordingToQualityDataColorIsChanging(){
    	
     
    
    	
    	   String colorHexaValue = "";
    	   String className =           Element.getAttribute(driver, temloc_value, "class");
    	   String values []      =         className.split("_");
    	   String value = values[values.length -1 ];
    	   System.out.println(value);
    	     
    	    
    	    	  
    	    	String temColor =    driver.findElement(temloc_value).getCssValue("color");
    	    	
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
    public void veifyTemperatureIcon(){
    	
      String iconNameFromJson  = 	GetJsonProperty.getTemperatureIconValue();
      String iconNameFromUi =       Element.getAttribute(driver,temperatureIcon, "class");
         Assert.assertTrue(iconNameFromUi.contains(iconNameFromJson));
    	
    }
    
}
