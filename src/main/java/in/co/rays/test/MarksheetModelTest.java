package in.co.rays.test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModel;

// TODO: Auto-generated Javadoc
/**
 * Marksheet Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class MarksheetModelTest 
{
	
	/** Model object to test. */
	 public static MarksheetModel model = new MarksheetModel();

	
	 /**
 	 * Main method to call test methods.
 	 *
 	 * @param args the arguments
 	 */
	 public static void main(String[] args) 
	    {
	         testAdd();
	        // testDelete();
	        // testUpdate();
	         //testFindByRollNo();
	       //  testFindByPK();
	        // testSearch();
	      //  testMeritList();
	        //   testList();

	    }

	 /**
 	 * Tests add a Marksheet.
 	 */	  
 public static void testAdd()
	    {

	        try 
	        {
	            MarksheetBean bean = new MarksheetBean();
	            bean.setRollNo("0701CS09105");
	            bean.setPhysics(90);
	            bean.setChemistry(90);
	            bean.setMaths(90);
	            
	            bean.setStudentId(1L);
	            long pk = model.add(bean);
	            MarksheetBean addedbean = model.findByPK(pk);
	            if (addedbean == null) {
	                System.out.println("Test add fail");
	            }
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        } 
	        catch (DuplicateRecordException e)
	        {
	            e.printStackTrace();
	        }

	    }

 /**
  * Tests delete a Marksheet.
  */	  	   
 public static void testDelete()
	    {

	        try 
	        {
	            MarksheetBean bean = new MarksheetBean();
	            long pk = 1;
	            bean.setId(pk);
	            model.delete(bean);
	            MarksheetBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

 /**
  * Tests update a Marksheet.
  */	  	  
public static void testUpdate() 
	    {

	        try
	        {
	            MarksheetBean bean = model.findByPK(2L);
	            bean.setName("Ajay");
	            bean.setChemistry(65);
	            bean.setMaths(70);
	            bean.setPhysics(93);;
	            model.update(bean);

	            MarksheetBean updatedbean = model.findByPK(2L);
	            System.out.println("Test Update succ");
	            if ("Ajay".equals(updatedbean.getName())) 
	            {
	                System.out.println("Test Update fail");
	            }
	        }
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }
	        catch (DuplicateRecordException e)
	        {
	            e.printStackTrace();
	        }

	    }

	    
/**
 * Tests find a Marksheet by RollNo.
 */	  
public static void testFindByRollNo() 
	    {

	        try {
	            MarksheetBean bean = model.findByRollNo("102");
	            if (bean == null) 
	            {
	                System.out.println("Test Find By RollNo fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getRollNo());
	            System.out.println(bean.getName());
	            System.out.println(bean.getPhysics());
	            System.out.println(bean.getChemistry());
	            System.out.println(bean.getMaths());
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }

	    }

/**
 * Tests find a Marksheet by PK.
 */	  	 
public static void testFindByPK() 
	    {
	        try 
	        {
	            MarksheetBean bean = new MarksheetBean();
	            long pk = 2L;
	            bean = model.findByPK(pk);
	            if (bean == null)
	            {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getRollNo());
	            System.out.println(bean.getName());
	            System.out.println(bean.getPhysics());
	            System.out.println(bean.getChemistry());
	            System.out.println(bean.getMaths());
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }

	    }

	  
/**
 * Tests search a Marksheet.
 */
public static void testSearch()
	    {
	        try
	        {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            bean.setName("Yash Rajput");
	            list = model.search(bean, 1, 10);
	            if (list.size() < 0)
	            {
	                System.out.println("Test Search fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) 
	            {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
	            }
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }
	    }

	 
/**
 * Tests get the meritlist of Marksheet.
 */	   
public static void testMeritList() 
	    {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.getMeritList(1, 5);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
	            }
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }

	    }

	   
/**
 * Tests list of Marksheet.
 */	  
public static void testList() 
	    {
	        try {
	            MarksheetBean bean = new MarksheetBean();
	            List list = new ArrayList();
	            list = model.list(1, 6);
	            if (list.size() < 0) 
	            {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) 
	            {
	                bean = (MarksheetBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getRollNo());
	                System.out.println(bean.getName());
	                System.out.println(bean.getPhysics());
	                System.out.println(bean.getChemistry());
	                System.out.println(bean.getMaths());
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
