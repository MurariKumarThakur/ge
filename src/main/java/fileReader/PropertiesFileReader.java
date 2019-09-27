package fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class PropertiesFileReader {

	private static Logger logger = Logger.getLogger(PropertiesFileReader.class);
	private FileInputStream fis;
	private String propertyFilePath;
	Properties property = new Properties();

	public PropertiesFileReader(String propertyFilePath) {

		this.propertyFilePath = propertyFilePath;

		try {
			FileInputStream fis = new FileInputStream(propertyFilePath);
			if (fis != null) {
				try {

					property.load(fis);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {

         logger.info("property file path not configured");
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String ReadPropertyValueUsingKey(String enterKey) {
		String value = null;

		value = property.getProperty(enterKey);

		if (value != null) {
			logger.info("ReadPropertyValueUsingKey()...executing");
		} else {

			logger.info("[ " + enterKey + "] key missmatch  ");
		}
		logger.info("ReadPropertyValueUsingKey()...returned Value is " + value);

		return value;

	}

	public void SetKeyValueInPropertiesFile(String enterKey, String enterValue) {

		property.setProperty(enterKey, enterValue);
		try {
			property.store(new FileOutputStream(propertyFilePath), "value added in to the properties file section");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map getAllPropertyData() {
		Map map = new LinkedHashMap();
		if (property != null) {

			Set keys = property.keySet();

			for (Object data : keys) {

				String keydata = (String) data;

				String value = property.getProperty(keydata);

				map.put(keydata, value);

			}

		}
		return map;

	}
	
	//method is responsible for returning property file name 

	public static String getPropertyFileName(String path) {

		String fileNameNew = null ;

		if (path.contains("/")) {

		 fileNameNew = returnNameInformation(path);

		} else if (path.contains("\\")) {

			String newPath = path.replace("\\", "/");
			fileNameNew =	returnNameInformation(newPath);
		
		}
		return fileNameNew;
		

	

	}

	public static String returnNameInformation(String fileName) {
		String finalFileName = null;

		String[] dataName = fileName.split("/");

		for (String name : dataName) {

			String data = name;

			if (data.contains(".properties")) {

				String[] dataName2 = name.split(".properties");

				for (String finalName : dataName2) {

					finalFileName = finalName;
					// System.out.println(finalFileName);
					break;

				}

			}

		}
		return finalFileName;

	}

	

}
