package in.co.rays.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Data Utility class to format data from one format to another.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class DataUtility 
{
 
	/** Application Date Format. */
   public static final String APP_DATE_FORMATE="dd-MM-yyyy";

	/** Application Time Format. */
   public static final String APP_TIME_FORMATE="dd-MM-yyyy HH:mm:ss";

   /** Date formatter. */
   private static final SimpleDateFormat formatter= new SimpleDateFormat(APP_DATE_FORMATE);
 
   /** The Constant timeFormatter. */
   private static final SimpleDateFormat timeFormatter= new SimpleDateFormat(APP_TIME_FORMATE);

   /**
    * Trims and trailing and leading spaces of a String.
    *
    * @param val the val
    * @return the string
    */
 public static String getString(String val) 
 {
	if(DataValidator.isNotNull(val))
	{
		return val.trim();
	}
	else
	{
		return val;
	}
 }
 

 /**
  * Converts and Object to String.
  *
  * @param val the val
  * @return the string data
  */
 public static String getStringData(Object val) 
 {
	if(val!=null)
	{
		return val.toString();
	}
	else
	{
		return "";
	}
 }
 
 /**
  * Converts String into Integer.
  *
  * @param val the val
  * @return the int
  */
 public static int getInt(String val) 
 {
	if(DataValidator.isInteger(val))
	{
		return Integer.parseInt(val);
	}
	else
	{
		return 0;
	}
 }
 
 /**
  * Converts String into Long.
  *
  * @param val the val
  * @return the long
  */
 public static long getLong(String val) 
 {
	if(DataValidator.isLong(val))
	{
		return Long.parseLong(val);
	}
	else
	{
		return 0;
	}
 }

 /**
  * Converts String into Date.
  *
  * @param val the val
  * @return the date
  */
 public static Date getDate(String val) 
 {
	 Date date = null;
	try 
	{
		date = formatter.parse(val);
	} 
	catch (Exception e) 
	{

	}
 return date;
  }
 
 /**
  * Converts Date into String.
  *
  * @param date the date
  * @return the date string
  */
 public static String getDateString(Date date) 
 {
	 
	try 
	{
		return formatter.format(date);
	} 
	catch (Exception e) 
	{

	}
 return "";
  }
 
 /**
  * Gets date after n number of days.
  *
  * @param date the date
  * @param day the day
  * @return the date
  */
 public static Date getDate(Date date, int day) 
 {
	return null;
  }
 
 /**
  * Converts String into Time.
  *
  * @param val the val
  * @return the timestamp
  */
 public static Timestamp geTimestamp(String val) 
 {
	 Timestamp timestamp = null;
	try 
	{
		timestamp = new Timestamp(timeFormatter.parse(val).getTime());
	} 
	catch (Exception e) 
	{
     return null;
	}
 return timestamp;
  }
 
 /**
  * Ge timestamp.
  *
  * @param l the l
  * @return the timestamp
  */
 public static Timestamp geTimestamp(long l) 
 {
	 Timestamp timestamp = null;
	try 
	{
		timestamp = new Timestamp(l);
	} 
	catch (Exception e) 
	{
     return null;
	}
 return timestamp;
  }
 
 /**
  * Gets the current timestamp.
  *
  * @return the current timestamp
  */
 public static Timestamp getCurrentTimestamp() 
 {
	 Timestamp timestamp = null;
	try 
	{
		timestamp = new Timestamp(new Date().getTime());
	} 
	catch (Exception e) 
	{
     
	}
 return timestamp;
  }
 
 /**
  * Gets the timestamp.
  *
  * @param tm the tm
  * @return the timestamp
  */
 public static long getTimestamp (Timestamp tm)
 {
	 
	try 
	{
		return tm.getTime();
	} 
	catch (Exception e) 
	{
     return 0;
	}
 }
 
 /**
  * The main method.
  *
  * @param args the arguments
  */
 public static void main(String[] args)
 {
	System.out.println(getInt("124"));
}
	
}
 

