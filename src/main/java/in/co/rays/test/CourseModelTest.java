package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.CourseModel;

// TODO: Auto-generated Javadoc
/**
 * Course Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class CourseModelTest {

	 /** Model object to test. */
public static CourseModel model = new CourseModel();
	
/**
 * Main method to call test methods.
 *
 * @param args the arguments
 * @throws DuplicateRecordException the duplicate record exception
 */
public static void main(String[] args) throws DuplicateRecordException 
    {
	   //  testAdd();
		//testDelete();
       // testUpdate();
        // testFindByName();
       //  testFindByPK();
       //  testSearch();
         testList();

		
   }
 


/**
 * Tests add a Course.
 *
 * @throws DuplicateRecordException the duplicate record exception
 */
 public static void testAdd() throws DuplicateRecordException 
    {

        try 
        {
            CourseBean bean = new CourseBean();
            
            bean.setName("B Tech");
            bean.setDuration("4 years");
            bean.setDescription("Bachelors Of Engineering");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(bean);
           
            CourseBean addedBean = model.findByPK(pk);
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
  * Tests delete a Course.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testDelete() throws DuplicateRecordException 
    {
	  try 
	  {
		CourseBean bean = new CourseBean();
		long pk = 18;
		bean.setId(pk);
        model.delete(bean);
      
       CourseBean deletedBean = model.findByPK(pk);
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
  * Tests update a Course.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testUpdate() throws DuplicateRecordException 
    {
	  try 
	  {
		CourseBean bean = model.findByPK(18);
		bean.setName("B Tech");
        bean.setDuration("4 years");
        bean.setDescription("Bachelors Of Engineering");
		bean.setCreatedBy("Admin");
        bean.setModifiedBy("Admin");
        bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
        bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
        model.update(bean);
       CourseBean updatedBean = model.findByPK(18);
       if(!("B Tech".equals(updatedBean.getName())))
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
  * Tests find a Course by Name.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testFindByName() throws DuplicateRecordException 
   {
	  try 
	  {
		CourseBean bean = model.findByName("MBA");
		   if (bean == null) 
		   {
              System.out.println("Test Find By Name fail");
          }
		   System.out.println(bean.getId());
           System.out.println(bean.getName());
           System.out.println(bean.getDuration());
           System.out.println(bean.getDescription());
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
  * Tests find a Course by PK.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
 public static void  testFindByPK() throws DuplicateRecordException 
   {
	  try 
	  {
		CourseBean bean = new CourseBean();
		 long pk = 2;
        bean = model.findByPK(pk);
        if (bean == null) 
        {
            System.out.println("Test Find By PK fail");
        }
        System.out.println(bean.getId());
        System.out.println(bean.getName());
        System.out.println(bean.getDuration());
        System.out.println(bean.getDescription());
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
  * Tests search a Course by Name.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
  public static void  testSearch() throws DuplicateRecordException 
    {
	  try 
	  {
		CourseBean bean = new CourseBean();
		  List list = new ArrayList();
          bean.setName("B Tech");
          
          list = model.search(bean, 1, 10);
          if (list.size() < 0) 
          {
              System.out.println("Test Search fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
              bean = (CourseBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getDuration());
              System.out.println(bean.getDescription());
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
   * Tests get List a Course.
   */
  public static void testList() 
  {

      try 
      {
          CourseBean bean = new CourseBean();
          List list = new ArrayList();
          list = model.list(1, 10);
          
          if (list.size() < 0) 
          {
              System.out.println("Test list fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
              bean = (CourseBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getDuration());
              System.out.println(bean.getDescription());
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

}
