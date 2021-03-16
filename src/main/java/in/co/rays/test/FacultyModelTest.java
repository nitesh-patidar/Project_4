package in.co.rays.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.FacultyBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.FacultyModel;

// TODO: Auto-generated Javadoc
/**
 * Faculty Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class FacultyModelTest {

	 /** Model object to test. */	
public static FacultyModel model = new FacultyModel();
	
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
		
        //  testUpdate();
     	//  testDelete();
        //  testFindByName();
       //   testFindByPK();
        //  testSearch();
          testList();

		
   }
 
/**
 * Tests add a Faculty.
 *
 * @throws DuplicateRecordException the duplicate record exception
 * @throws ParseException the parse exception
 */
 public static void testAdd() throws DuplicateRecordException, ParseException 
    {

        try 
        {
            FacultyBean bean = new FacultyBean();
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");

            
            bean.setCollegeId(5);
            bean.setCourseId(6);
            bean.setSubjectId(7);
            bean.setFirstName("Rahul");
            bean.setLastName("Bordiya");
            bean.setGender("Male");
            bean.setDob(sdf.parse("31-12-1990"));
            bean.setEmail("Rahul99@gmail.com");
            bean.setMobileNo("7899977700");
            bean.setCourseName("MBA");
            bean.setCollegeName("LNCT");
            bean.setSubjectName("Managerial Accounting");
            bean.setCreatedBy("nitespatidar2203@gmail.com");
            bean.setModifiedBy("nitespatidar2203@gmail.com");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(bean);
           
            FacultyBean addedBean = model.findByPK(pk);
           
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
  * Tests update a Faculty.
  *
  * @throws DuplicateRecordException the duplicate record exception
  * @throws ParseException the parse exception
  */
 public static void  testUpdate() throws DuplicateRecordException, ParseException 
    {
	  try 
	  {
		 FacultyBean bean = model.findByPK(8);
		
		 SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");
		
	    bean.setCollegeId(5);
        bean.setCourseId(6);
        bean.setSubjectId(7);
        bean.setFirstName("Vishal");
        bean.setLastName("Bordiya");
        bean.setGender("Male");
        bean.setDob(sdf.parse("31-12-1990"));
        bean.setEmail("Rahul99@gmail.com");
        bean.setMobileNo("7899977700");
        bean.setCourseName("MBA");
        bean.setCollegeName("LNCT");
        bean.setSubjectName("Managerial Accounting");
        bean.setCreatedBy("nitespatidar2203@gmail.com");
        bean.setModifiedBy("nitespatidar2203@gmail.com");
        bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
        bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
        
		
        model.update(bean);
       
        FacultyBean updatedBean = model.findByPK(8);
       
       if(!("Vishal".equals(updatedBean.getFirstName())))
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
  * Tests delete a Faculty.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testDelete() throws DuplicateRecordException 
 {
	  try 
	  {
		FacultyBean bean = new FacultyBean();
		long pk = 9;
		
		bean.setId(pk);
    
		model.delete(bean);
   
    FacultyBean deletedBean = model.findByPK(pk);
  
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
  * Tests find a Faculty by Email.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testFindByEmail() throws DuplicateRecordException 
   {
	  try 
	  {
		FacultyBean bean = model.findByEmail("arush12@gmail.com");
		   if (bean == null) 
		   {
              System.out.println("Test Find By Name fail");
          }
		   System.out.println(bean.getId());
           System.out.println(bean.getCollegeId());
           System.out.println(bean.getCourseId());
           System.out.println(bean.getSubjectId());
           System.out.println(bean.getFirstName());
           System.out.println(bean.getLastName());
           System.out.println(bean.getGender());
           System.out.println(bean.getDob());
           System.out.println(bean.getEmail());
           System.out.println(bean.getMobileNo());
           System.out.println(bean.getCourseName());
           System.out.println(bean.getCollegeName());
           System.out.println(bean.getSubjectName());
           System.out.println(bean.getCreatedBy());
           System.out.println(bean.getModifiedBy());
           System.out.println(bean.getCreatedDatetime());
           System.out.println(bean.getModifiedDatetime());
             
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
    
 
 /**
  * Tests find a Faculty by PK.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testFindByPK() throws DuplicateRecordException 
   {
	  try 
	  {
		FacultyBean bean = new FacultyBean();
		 long pk = 2;
        bean = model.findByPK(pk);
        if (bean == null) 
        {
            System.out.println("Test Find By PK fail");
        }
      
        System.out.println(bean.getId());
        System.out.println(bean.getCollegeId());
        System.out.println(bean.getCourseId());
        System.out.println(bean.getSubjectId());
        System.out.println(bean.getFirstName());
        System.out.println(bean.getLastName());
        System.out.println(bean.getGender());
        System.out.println(bean.getDob());
        System.out.println(bean.getEmail());
        System.out.println(bean.getMobileNo());
        System.out.println(bean.getCourseName());
        System.out.println(bean.getCollegeName());
        System.out.println(bean.getSubjectName());
        System.out.println(bean.getCreatedBy());
        System.out.println(bean.getModifiedBy());
        System.out.println(bean.getCreatedDatetime());
        System.out.println(bean.getModifiedDatetime());
          
        
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
   
  
 
 
 /**
  * Tests search a Faculty by Name.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testSearch() throws DuplicateRecordException 
    {
	  try 
	  {
		FacultyBean bean = new FacultyBean();
		  List list = new ArrayList();
          bean.setFirstName("Vishal");
          
          list = model.search(bean, 1, 10);
          if (list.size() < 0) 
          {
              System.out.println("Test Search fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
              bean = (FacultyBean) it.next();
            System.out.println(bean.getId());
            System.out.println(bean.getCollegeId());
            System.out.println(bean.getCourseId());
            System.out.println(bean.getSubjectId());
            System.out.println(bean.getFirstName());
            System.out.println(bean.getLastName());
            System.out.println(bean.getGender());
            System.out.println(bean.getDob());
            System.out.println(bean.getEmail());
            System.out.println(bean.getMobileNo());
            System.out.println(bean.getCourseName());
            System.out.println(bean.getCollegeName());
            System.out.println(bean.getSubjectName());
            System.out.println(bean.getCreatedBy());
            System.out.println(bean.getModifiedBy());
            System.out.println(bean.getCreatedDatetime());
            System.out.println(bean.getModifiedDatetime());
              
          } 
      }
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
  
 
 
 /**
  * Tests get List of Faculty.
  */ 
public static void testList() 
  {

      try 
      {
          FacultyBean bean = new FacultyBean();
          List list = new ArrayList();
          list = model.list(1, 10);
          
          if (list.size() < 0) 
          {
              System.out.println("Test list fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
        	  bean = (FacultyBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getCollegeId());
              System.out.println(bean.getCourseId());
              System.out.println(bean.getSubjectId());
              System.out.println(bean.getFirstName());
              System.out.println(bean.getLastName());
              System.out.println(bean.getGender());
              System.out.println(bean.getDob());
              System.out.println(bean.getEmail());
              System.out.println(bean.getMobileNo());
              System.out.println(bean.getCourseName());
              System.out.println(bean.getCollegeName());
              System.out.println(bean.getSubjectName());
              System.out.println(bean.getCreatedBy());
              System.out.println(bean.getModifiedBy());
              System.out.println(bean.getCreatedDatetime());
              System.out.println(bean.getModifiedDatetime());
                
             
          }

      } 
      catch (ApplicationException e) 
      {
          e.printStackTrace();
      }
  }

	
}
