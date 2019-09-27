package fileReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import getScreenJsonValues.GetJsonProperty;






//com.googlecode.json-simple
public class JsonReader {

	 public static JSONObject readAllJson(String filePath,String addRootProperty){
		 JSONObject allJsonDetails = null;
		
		String fileName = filePath;
		 System.out.println(fileName);
		 File file = new File(fileName);

		JSONParser parser = new JSONParser();
		try {
			FileReader reader = new FileReader(file.getAbsolutePath());
			Object obj = parser.parse(reader);
			JSONObject jsonObj = (JSONObject) obj;
		
			 allJsonDetails = (JSONObject)jsonObj.get(addRootProperty);
			
			  

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allJsonDetails;

	}
	 
	 
	 public static void  writeJson(String filePath ,String key , String value){
		  FileWriter file=null;
		  JSONObject obj = new JSONObject();
		   obj.put(key,value);
		try {
			file = new FileWriter(filePath);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		    		   
		 try {

	               // true to append at the end of file.
		      	 file.write(obj.toString());
	          
	            }catch (Exception E)
	            {
	                E.printStackTrace();

	            }finally{
	                try {
	                	file.flush();
						file.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						  e.printStackTrace();
					}
	            }
	       
		        System.out.println(obj);
	 
}


	 public static void main(String[] args) {
		
	}

}