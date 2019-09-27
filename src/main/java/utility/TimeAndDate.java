package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class TimeAndDate {
	static DateFormat dateFormat;	
	static Date date ;
	
	public static String getTodayDate(){
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
	  return 	dateFormat.format(date);
		
	}
	
	public static String getCurrentTime(){
		dateFormat = new SimpleDateFormat("HH:mm:ss");
		date = new Date();
	  return 	dateFormat.format(date);	
		
	}
	
	public static  String getFutureDate(int EnterFutureDate){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
		Calendar cal = Calendar.getInstance();
	  
	   cal.add(Calendar.DATE, EnterFutureDate);
	
	   return   dateFormat.format(cal.getTime());
		
		
	}
	
	public static String getFutureMonth(int EnterFutureMonth){
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
		Calendar cal = Calendar.getInstance();
	  
	   cal.add(Calendar.MONTH, EnterFutureMonth);
	
	   return   dateFormat.format(cal.getTime());
		
	}
	public static String getFutureYear(int EnterFutureYear){
		
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = new Date();
		Calendar cal = Calendar.getInstance();
	  
	   cal.add(Calendar.YEAR, EnterFutureYear);
	
	   return   dateFormat.format(cal.getTime());
		
	}
	public static String  getTimeDiffInSecond(String initialTime , String finalTime) throws ParseException{
		String   result ;
		String time1 = initialTime;
		String time2 = finalTime;
 
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		long  difference = date2.getTime() - date1.getTime();
		long  calculatedTime = difference / 1000 ;
		    result =   "Time difference is --- "+ String.valueOf(calculatedTime)+ " Sec"   ; 
		
		return result ;
		
	}
	
	public static void main(String[] args) throws InterruptedException, ParseException {
		String  time1 = getCurrentTime();
		
		Thread.sleep(70000);
		
		String time2 = getCurrentTime();
		
		System.out.println(getTimeDiffInSecond(time1, time2));
	    	
	}
	public static String getFormatedDate(String formate , Date date){
		
		dateFormat = new SimpleDateFormat(formate);
		return  dateFormat.format(date);
	}
	

}
