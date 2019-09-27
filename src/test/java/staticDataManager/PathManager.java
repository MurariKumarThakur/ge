package staticDataManager;

public class PathManager {
	
	
	public static String getProjectLocation(){
		
		
		
	return System.getProperty("user.dir");
		
	}
	
	public static String getExtentReportFileLocation(){
		
	return 	getProjectLocation()+"/extendsReportFolder/report.html";
	}

	public static String getLoggerFileLocation(){
		
		return getProjectLocation()+"/runTimeLog/application.html";
	}
	public static String getExcelFileLocation(String excelName){
		
		
		
		return getProjectLocation()+"/TestData/"+excelName;
		
		
	}
	
	public static String getConfigJson(){
	    System.out.println(getProjectLocation());
		String path = getProjectLocation()+"/projectJson/getScreens.json";
		 System.out.println(path);
		return path;
		
	}
	public static String getConfigPropertiesFile(){
		
	return System.getProperty("user.dir")+"/config/geConfigFile.properties";
	
}
	
}
