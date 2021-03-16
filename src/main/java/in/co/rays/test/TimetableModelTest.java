package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

import in.co.rays.model.TimetableModel;


// TODO: Auto-generated Javadoc
/**
 * Timetable Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */	
public class TimetableModelTest {


	   /** Model object to test. */	
public static TimetableModel model = new TimetableModel();
	
/**
 * Main method to call test methods.
 *
 * @param args the arguments
 * @throws DuplicateRecordException the duplicate record exception
 * @throws ParseException the parse exception
 */
public static void main(String[] args) throws DuplicateRecordException, ParseException 
    {
	    // testAdd();
	    // testDelete();
        // testUpdate();
       //  testFindByCSS();
	    // testFindByExamDateTime();
	    // testFindByAll();
        // testFindByPK();
       //  testSearch();
           testList();

		
   }
 
/**
 * Tests add a Timetable.
 *
 * @throws DuplicateRecordException , ParseException
 * @throws ParseException the parse exception
 */
 public static void testAdd() throws DuplicateRecordException, ParseException 
    {

        try 
        {
            TimetableBean bean = new TimetableBean();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            
             bean.setCourseId(15);
	   	     bean.setCourseName("ME");
	   	     bean.setSubjectId(12);
	   	     bean.setSubjectName("Environment and Energy Studies");
	   	     bean.setSemester("5th");
	   	    
	   	     bean.setExamDate(sdf.parse("23-06-2020"));
	   	     bean.setExamTime("12:00 PM to 03:00 PM");
	   	    
	   	     bean.setCreatedBy("nitespatidar2203@gmail.com");
	   	     bean.setModifiedBy("nitespatidar2203@gmail.com");
	   	     bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	   	     bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	            
            long pk = model.add(bean);
           
            TimetableBean addedBean = model.findByPK(pk);
           
            System.out.println(model.findByPK(pk));
            
            if (addedBean == null) 
            {
                System.out.println("Test add fail");
            }
        } 
        catch (ApplicationException e)
        {
            e.printStackTrace();
        }

    }

 
 /**
  * Tests delete a Timetable.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
  public static void  testDelete() throws DuplicateRecordException 
    {
	  try 
	  {
		TimetableBean bean = new TimetableBean();
		int pk = 28;
		bean.setId(pk);
        model.delete(bean);
      
       TimetableBean deletedBean = model.findByPK(pk);
       if(deletedBean!=null)
       {
    	   System.out.println("Test Delete Fail");
       }
       else
       {
    	   System.out.println("Data Deleted Successfully");
       }
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}

  
  /**
   * Tests update a Timetable.
   *
   * @throws DuplicateRecordException , ParseException
   * @throws ParseException the parse exception
   */
   public static void  testUpdate() throws DuplicateRecordException, ParseException 
    {
	  try 
	  {
		   
		     TimetableBean bean = model.findByPK(27);
		    		 
		     SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");
	            
	         bean.setCourseId(6);
	   	     bean.setCourseName("BBA");
	   	     bean.setSubjectId(5);
	   	     bean.setSubjectName("Marketing");
	   	     bean.setSemester("Second");
	   	    
	   	     bean.setExamDate(sdf.parse("31-12-2019"));
	   	     bean.setExamTime("12:00 PM to 03:00 PM");
	   	    
	   	     bean.setCreatedBy("nitespatidar2203@gmail.com");
	   	     bean.setModifiedBy("nitespatidar2203@gmail.com");
	   	     bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
	   	     bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
	   	     model.update(bean);
      
       TimetableBean updatedBean = model.findByPK(27);
       
       if(!("BBA".equals(updatedBean.getCourseName())))
       {
    	   System.out.println("Test Update Fail");
       }
       else
       {
    	   System.out.println("Test Update Successfully");
       }
	 } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
    
   /**
    * Tests find a Timetable by PK.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
   public static void  testFindByPK() throws DuplicateRecordException 
     {
  	  try 
  	  {
  		  TimetableBean bean = new TimetableBean();
  		  long pk = 2;
          bean = model.findByPK(pk);
          if (bean == null) 
          {
              System.out.println("Test Find By PK fail");
          }
          System.out.println(bean.getId());
          System.out.println(bean.getCourseId());
          System.out.println(bean.getCourseName());
          System.out.println(bean.getSubjectId());
          System.out.println(bean.getSubjectName());
          System.out.println(bean.getSemester());
         
          System.out.println(bean.getExamDate());
          System.out.println(bean.getExamTime());
          System.out.println(bean.getCreatedBy());
          System.out.println(bean.getCreatedDatetime());
          System.out.println(bean.getModifiedBy());
          System.out.println(bean.getModifiedDatetime());
  	  } 
  	 catch (ApplicationException e) 
  	  {
  		e.printStackTrace();
  	  }	
  	}
     
   
   /**
    * Tests find a Timetable by Exam Date and Time.
    *
    * @throws DuplicateRecordException the duplicate record exception
    * @throws ParseException the parse exception
    */
   public static void  testFindByExamDateTime() throws DuplicateRecordException, ParseException 
     {
  	  try 
  	  {
  		  TimetableBean bean = new TimetableBean();
  		
  		  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
  		  
  		  bean.setExamDate(sdf.parse("23-06-2020"));
    	     
  		  bean.setExamTime("12:00 PM to 03:00 PM");
    	     
  	      bean = model.findByExamDateTime(bean);
     
  	      if(bean == null) 
          {
              System.out.println("Test Find By Exam Date and Time fail");
          }
          System.out.println(bean.getId());
          System.out.println(bean.getCourseId());
          System.out.println(bean.getCourseName());
          System.out.println(bean.getSubjectId());
          System.out.println(bean.getSubjectName());
          System.out.println(bean.getSemester());
          System.out.println(bean.getExamDate());
          System.out.println(bean.getExamTime());
          System.out.println(bean.getCreatedBy());
          System.out.println(bean.getCreatedDatetime());
          System.out.println(bean.getModifiedBy());
          System.out.println(bean.getModifiedDatetime());
  	  } 
  	 catch (ApplicationException e) 
  	  {
  		e.printStackTrace();
  	  }	
  	}
     
   /**
    * Tests find a Timetable by Course ,Subject And Semester.
    *
    * @throws DuplicateRecordException the duplicate record exception
    * @throws ParseException the parse exception
    */
public static void  testFindByCSS() throws DuplicateRecordException, ParseException 
     {
  	  try 
  	  {
  		     TimetableBean bean = new TimetableBean();
         
             bean.setCourseId(15L);
	   	     bean.setSubjectId(12L);
	   	     bean.setSemester("5th");
	
	   	     bean = model.findByCSS(bean);
     
  	       if(bean == null) 
            {
               System.out.println("Test Find By Course ,Subject and Semester fail");
            }
        
  	      System.out.println(bean.getId());
          System.out.println(bean.getCourseId());
          System.out.println(bean.getCourseName());
          System.out.println(bean.getSubjectId());
          System.out.println(bean.getSubjectName());
          System.out.println(bean.getSemester());
         
          System.out.println(bean.getExamDate());
          System.out.println(bean.getExamTime());
          System.out.println(bean.getCreatedBy());
          System.out.println(bean.getCreatedDatetime());
          System.out.println(bean.getModifiedBy());
          System.out.println(bean.getModifiedDatetime());
  	  } 
  	 catch (ApplicationException e) 
  	  {
  		e.printStackTrace();
  	  }	
  	}
     
/**
 * Tests find a Timetable by All.
 *
 * @throws DuplicateRecordException the duplicate record exception
 * @throws ParseException the parse exception
 */   
public static void  testFindByAll() throws DuplicateRecordException, ParseException 
{
	  try 
	  {
		 TimetableBean bean = new TimetableBean();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    
        bean.setCourseId(15L);
  	     bean.setCourseName("ME");
  	     bean.setSubjectId(12L);
  	     bean.setSubjectName("Environment and Energy Studies");
  	     bean.setSemester("5th");
  	     bean.setExamDate(sdf.parse("23-06-2020"));
  	     bean.setExamTime("12:00 PM to 03:00 PM");
  	     
  	     bean = model.findByAll(bean);

	     if (bean == null) 
     {
         System.out.println("Test Find By All fail");
     }
     System.out.println(bean.getId());
     System.out.println(bean.getCourseId());
     System.out.println(bean.getCourseName());
     System.out.println(bean.getSubjectId());
     System.out.println(bean.getSubjectName());
     System.out.println(bean.getSemester());
    
     System.out.println(bean.getExamDate());
     System.out.println(bean.getExamTime());
     System.out.println(bean.getCreatedBy());
     System.out.println(bean.getCreatedDatetime());
     System.out.println(bean.getModifiedBy());
     System.out.println(bean.getModifiedDatetime());
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}



   /**
    * Tests search a Timetable.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
  public static void  testSearch() throws DuplicateRecordException 
    {
	  try 
	  {
		TimetableBean bean = new TimetableBean();
		  List list = new ArrayList();
          bean.setSubjectName("Art of Programming");
          
          list = model.search(bean, 1, 10);
          if (list.size() < 0) 
          {
              System.out.println("Test Search fail");
          }
          Iterator it = list.iterator();
          System.out.println(it.hasNext());
          while (it.hasNext()) 
          {
              bean = (TimetableBean) it.next();
             
              System.out.println(bean.getId());
              System.out.println(bean.getCourseId());
              System.out.println(bean.getCourseName());
              System.out.println(bean.getSubjectId());
              System.out.println(bean.getSubjectName());
              System.out.println(bean.getSemester());
            
              System.out.println(bean.getExamDate());
              System.out.println(bean.getExamTime());
              System.out.println(bean.getCreatedBy());
              System.out.println(bean.getCreatedDatetime());
              System.out.println(bean.getModifiedBy());
              System.out.println(bean.getModifiedDatetime());
	      } 
      }
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
     
  
  /**
   * Tests get List a Timetable.
   */
  public static void testList() 
  {

      try 
      {
          TimetableBean bean = new TimetableBean();
          List list = new ArrayList();
          list = model.list(1, 10);
          
          if (list.size() < 0) 
          {
              System.out.println("Test list fail");
          }
          Iterator it = list.iterator();
         
          System.out.println(it.hasNext());
          while (it.hasNext()) 
          {
        	  bean = (TimetableBean) it.next();
              
              System.out.println(bean.getId());
              System.out.println(bean.getCourseId());
              System.out.println(bean.getCourseName());
              System.out.println(bean.getSubjectId());
              System.out.println(bean.getSubjectName());
              System.out.println(bean.getSemester());
              System.out.println(bean.getExamDate());
              System.out.println(bean.getExamTime());
              System.out.println(bean.getCreatedBy());
              System.out.println(bean.getCreatedDatetime());
              System.out.println(bean.getModifiedBy());
              System.out.println(bean.getModifiedDatetime());
              
              System.out.println("\n");
          }

      } 
      catch (ApplicationException e) 
      {
          e.printStackTrace();
      }
  }


}
