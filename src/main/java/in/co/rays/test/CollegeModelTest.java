

package in.co.rays.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;

// TODO: Auto-generated Javadoc
/**
 * College Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class CollegeModelTest 
{
    
    /** Model object to test. */
    public static CollegeModel model = new CollegeModel();
	
	 /**
 	 * Main method to call test methods.
 	 *
 	 * @param args the arguments
 	 * @throws DuplicateRecordException the duplicate record exception
 	 */
    public static void main(String[] args) throws DuplicateRecordException 
    {
	     testAdd();
		// testDelete();
        // testUpdate();
        // testFindByName();
       //  testFindByPK();
       //  testSearch();
       //  testList();

		
   }
  
    /**
     * Tests add a College.
     *
     * @throws DuplicateRecordException the duplicate record exception
     */
    public static void testAdd() throws DuplicateRecordException 
    {

        try 
        {
            CollegeBean bean = new CollegeBean();
            
            bean.setName("SGSITS");
            bean.setAddress("Indore");
            bean.setState("MP");
            bean.setCity("Indore");
            bean.setPhoneNo("073122634");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
            bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(bean);
            
            CollegeBean addedBean = model.findByPK(pk);
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
     * Tests delete a College.
     *
     * @throws DuplicateRecordException the duplicate record exception
     */ 
   public static void  testDelete() throws DuplicateRecordException 
    {
	  try 
	  {
		CollegeBean bean = new CollegeBean();
		long pk = 2;
		bean.setId(pk);
        model.delete(bean);
      
       CollegeBean deletedBean = model.findByPK(pk);
       if(deletedBean!=null)
       {
    	   System.out.println("Test Delete Fail");
       }
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}

   /**
    * Tests update a College.
    *
    * @throws DuplicateRecordException the duplicate record exception
    */
  public static void  testUpdate() throws DuplicateRecordException 
    {
	  try 
	  {
		CollegeBean bean = model.findByPK(2);
		bean.setName("JIT");
		bean.setAddress("Borawa");
		bean.setState("MP");
		bean.setCity("Indore");
		bean.setPhoneNo("073124425");
		bean.setCreatedBy("Admin");
        bean.setModifiedBy("Admin");
        bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
        bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
        model.update(bean);
       CollegeBean updatedBean = model.findByPK(2);
       if(!"JIT".equals(updatedBean.getName()))
       {
    	   System.out.println("Test Update Fail");
       }
	  } 
	 catch (ApplicationException e) 
	  {
		e.printStackTrace();
	  }	
	}
    
  
  /**
   * Tests find a College by Name.
   *
   * @throws DuplicateRecordException the duplicate record exception
   */
  public static void  testFindByName() throws DuplicateRecordException 
    {
	  try 
	  {
		CollegeBean bean = model.findByName("SGSITS");
		   if (bean == null) 
		   {
               System.out.println("Test Find By Name fail");
           }
           System.out.println(bean.getId());
           System.out.println(bean.getName());
           System.out.println(bean.getAddress());
           System.out.println(bean.getState());
           System.out.println(bean.getCity());
           System.out.println(bean.getPhoneNo());
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
   * Tests find a College by PK.
   *
   * @throws DuplicateRecordException the duplicate record exception
   */
  public static void  testFindByPK() throws DuplicateRecordException 
    {
	  try 
	  {
		CollegeBean bean = new CollegeBean();
		 long pk = 2;
         bean = model.findByPK(pk);
         if (bean == null) 
         {
             System.out.println("Test Find By PK fail");
         }
         System.out.println(bean.getId());
         System.out.println(bean.getName());
         System.out.println(bean.getAddress());
         System.out.println(bean.getState());
         System.out.println(bean.getCity());
         System.out.println(bean.getPhoneNo());
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
   * Tests search a College by Name.
   *
   * @throws DuplicateRecordException the duplicate record exception
   */ 
 public static void  testSearch() throws DuplicateRecordException 
    {
	  try 
	  {
		CollegeBean bean = new CollegeBean();
		  List list = new ArrayList();
          bean.setName("SGSITS");
          
          list = model.search(bean, 1, 10);
          if (list.size() < 0) 
          {
              System.out.println("Test Search fail");
          }
          Iterator it = list.iterator();
          while (it.hasNext()) 
          {
              bean = (CollegeBean) it.next();
              System.out.println(bean.getId());
              System.out.println(bean.getName());
              System.out.println(bean.getAddress());
              System.out.println(bean.getState());
              System.out.println(bean.getCity());
              System.out.println(bean.getPhoneNo());
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
  * Tests get List a College.
  */
 public static void testList() 
    {

        try 
        {
            CollegeBean bean = new CollegeBean();
            List list = new ArrayList();
            list = model.list(1, 10);
            
            if (list.size() < 0) 
            {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) 
            {
                bean = (CollegeBean) it.next();
                System.out.println(bean.getId());
                System.out.println(bean.getName());
                System.out.println(bean.getAddress());
                System.out.println(bean.getState());
                System.out.println(bean.getCity());
                System.out.println(bean.getPhoneNo());
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
