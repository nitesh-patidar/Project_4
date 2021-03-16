package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

import in.co.rays.model.SubjectModel;


// TODO: Auto-generated Javadoc
/**
 * Subject Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */	
public class SubjectModelTest {

   /** Model object to test. */	
public static SubjectModel model = new SubjectModel();
	
/**
 * Main method to call test methods.
 *
 * @param args the arguments
 * @throws DuplicateRecordException the duplicate record exception
 */
public static void main(String[] args) throws DuplicateRecordException 
    {
	    // testAdd();
		//testDelete();
        // testUpdate();
        //   testFindByName();
        // testFindByPK();
        // testSearch();
        testList();

		
   }
 

/**
 * Tests add a Subject.
 *
 * @throws DuplicateRecordException the duplicate record exception
 */
 public static void testAdd() throws DuplicateRecordException 
    {

        try 
        {
            SubjectBean bean = new SubjectBean();
            
            bean.setName("Zoology");
            bean.setCourseId(3);
            bean.setCourseName("Ph D");
            bean.setDescription("BioLogy");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(bean);
           
            SubjectBean addedBean = model.findByPK(pk);
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
  * Tests delete a Subject.
  *
  * @throws DuplicateRecordException the duplicate record exception
  */
   public static void  testDelete() throws DuplicateRecordException 
    {
	  try 
	  {
		SubjectBean bean = new SubjectBean();
		long pk = 41;
		bean.setId(pk);
        model.delete(bean);
      
       SubjectBean deletedBean = model.findByPK(pk);
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
    * Tests update a Subject.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
   public static void  testUpdate() throws DuplicateRecordException 
    {
	  try 
	  {
		SubjectBean bean = model.findByPK(43);
		 bean.setName("Botony");
         bean.setCourseId(4);
         bean.setCourseName("Ph D");
         bean.setDescription("BioLogy");
		 bean.setCreatedBy("Admin");
         bean.setModifiedBy("Admin");
         bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
         bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
         model.update(bean);
       SubjectBean updatedBean = model.findByPK(19);
       if(!("Botony".equals(updatedBean.getName())))
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
    * Tests find a Subject by Name.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
   public static void  testFindByName() throws DuplicateRecordException 
     {
  	  try 
  	  {
  		  SubjectBean bean = model.findByName("Macro Economics");
  		   if (bean == null) 
  		   {
                System.out.println("Test Find By Name fail");
            }
  		   System.out.println(bean.getId());
           System.out.println(bean.getName());
           System.out.println(bean.getCourseId());
           System.out.println(bean.getCourseName());
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
    * Tests find a Subject by PK.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
   public static void  testFindByPK() throws DuplicateRecordException 
     {
  	  try 
  	  {
  		  SubjectBean bean = new SubjectBean();
  		  long pk = 2;
          bean = model.findByPK(pk);
          if (bean == null) 
          {
              System.out.println("Test Find By PK fail");
          }
          System.out.println(bean.getId());
          System.out.println(bean.getName());
          System.out.println(bean.getCourseId());
          System.out.println(bean.getCourseName());
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
    * Tests search a Subject by Name.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
  public static void  testSearch() throws DuplicateRecordException 
    {
	  try 
	  {
		SubjectBean bean = new SubjectBean();
		  List list = new ArrayList();
          bean.setName("Botony");
          
          list = model.search(bean, 1, 10);
          if (list.size() < 0) 
          {
              System.out.println("Test Search fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
              bean = (SubjectBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getCourseId());
              System.out.println(bean.getCourseName());
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
   * Test list.
   */
  public static void testList() 
  {

      try 
      {
          SubjectBean bean = new SubjectBean();
          List list = new ArrayList();
          list = model.list(1, 10);
          
          if (list.size() < 0) 
          {
              System.out.println("Test list fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
        	  bean = (SubjectBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getCourseId());
              System.out.println(bean.getCourseName());
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
