package utility;

import org.apache.log4j.Logger;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Address;
import io.codearte.jfairy.producer.person.Person;


public class RandomData {
	private static Logger logger = Logger.getLogger(RandomData.class);	
	public static Fairy fairy = Fairy.create();
	public static Person person = fairy.person();
	public static String getPersonFullName(){
		
	   String personFullName =	person.getFullName();
	   logger.info("getPersonFullName()...Executing and Returning ...."+personFullName);
	   return personFullName ; 
	}
	public static Address getAddress(){
		
	Address personAddress = person.getAddress();
	  logger.info("getAddress()...Executing and Returning ...."+personAddress);
	return personAddress ;
		
		 
	}
	public static String getPhoneNumber(){
		
	 	String phonenumber = person.getTelephoneNumber();
	    String newPhoneNumber =  phonenumber.replace("-", "");
	    logger.info("getPhoneNumber()...Executing and Returning ...."+newPhoneNumber);
	       return newPhoneNumber ;     
	 	
	}
	public static String getEmail(){
		String email = person.getEmail();
		  logger.info("getEmail()...Executing and Returning ...."+email);		
		return email ;
	}
	
	
	
  
}
