package in.co.rays.test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;


// TODO: Auto-generated Javadoc
/**
 * Role Model Test class.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class RoleModelTest 
{
    
    /** Model object to test. */
    public static RoleModel model = new RoleModel();
	
    /**
     * Main method to call test methods.
     *
     * @param args the arguments
     */
	public static void main(String[] args) 
	{
		
        System.out.println("Enter a Character: "+"\n A for Add"+"\n U for Update"
                                 +"\n D for Delete"+"\n P for FindByPK"+"\n N for FindByName  "+"\n L for List"
      			                 +"\n S for Search");
      	 
		try {
			
			 int Ch;
			 Ch = System.in.read();
			  switch(Ch) {
	      	  case 'A':
	      	  case 'a':  
	               {
	              	 testAdd();		
	                   break;    
	               }
	      	  case 'U':
	      	  case 'u':  
	      	  {
	      		  testUpdate();
	      		  break;
	      	  }
	      	 case 'D':
	      	 case 'd':
	      	 {
	      		 testDelete();
	      		 break;
	      	 }
	      	 case 'P':
	      	 case 'p':
	      	 {
	      		testFindByPK();
	      		 break;
	      	 }
	      	 case 'N':
	      	 case 'n':
	      	 {
	      		testFindByPK();
	      		 break;
	      	 }
	      	 case 'L':
	      	 case 'l':
	      	 {
	      		 testList();
	      		 break;
	      	 }
	      	 case 'S':
	      	 case 's':
	      	 {
	      		 testSearch();
	      		 break;
	      	 } 
		  }  
			
		} 
		catch (IOException e) 
		  {
			
			e.printStackTrace();
		  }
      
    }
        
	

    /**
     * Tests add a Role.
     */
   public static void testAdd()
	{
		
		try 
		{
			RoleBean bean = new RoleBean();
			bean.setName("Rajesh");
			bean.setDescription("Faculty");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			 RoleBean addedbean = model.findByPK(pk);
	            if (addedbean == null)
	            {
	                System.out.println("Test add fail");
	            }
		} 
		catch (ApplicationException e) 
		{
			e.printStackTrace();
		}
	   catch(DuplicateRecordException e)
		{
		   e.printStackTrace();
		}
	}
   
   

   /**
    * Tests delete a Role.
    */
	public static void testDelete() 
	{
		try 
		{
			RoleBean bean = new RoleBean();
		    long pk = 1;
		    bean.setId(pk);
		    model.delete(bean);
		    RoleBean deletebean = model.findByPK(pk);
		    if(deletebean != null)
		    {
		    	System.out.println("Test Delete fail");
		    }
		   
		} 
		catch(ApplicationException e)
	    {
	    	e.printStackTrace();
	    }
	}
	

    /**
     * Tests update a Role.
     */
	public static void testUpdate()
	{
		try 
		{
			RoleBean bean = model.findByPK(1);
		    bean.setName("Ajay");
		    bean.setDescription("Faculty");
		    model.update(bean);
		    RoleBean Updatebean = model.findByPK(1);
		    if(!"Ajay".equals(Updatebean.getName()))
		    {
		    	System.out.println("Test Update fail");
		    }
		   
		} 
		catch(ApplicationException e)
	    {
	    	e.printStackTrace();
	    }
		catch(DuplicateRecordException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	/**
     * Tests find a User by PK.
     */
	public static void testFindByPK()
	{
		try 
		{
			RoleBean bean = new RoleBean();
		     long pk = 6;
		     bean = model.findByPK(pk);
	         if (bean==null) 
	         {
				System.out.println("Test Find By PK fail");
			 }
		     System.out.println(bean.getId());
		     System.out.println(bean.getName());
		     System.out.println(bean.getDescription());
		     
		   
		} 
		catch(ApplicationException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	
	/**
     * Tests find a User by Name.
     */
	public static void testFindByName()
	{
		try 
		{
			RoleBean bean = new RoleBean();
		    
		     bean = model.findByName("Ajay");
	         if (bean==null) 
	         {
				System.out.println("Test Find By Name fail");
			 }
		     System.out.println(bean.getId());
		     System.out.println(bean.getName());
		     System.out.println(bean.getDescription());
		     
		   
		} 
		catch(ApplicationException e)
	    {
	    	e.printStackTrace();
	    }
	}	
	
	/**
	 * Tests get Search.
	 */
public static void testSearch()
		{
			try 
			{
				RoleBean bean = new RoleBean();
			    List list = new ArrayList();
			     bean.setName("Shivam");
		       list = model.search(bean,0,0);
		       if(list.size()<0)
		       {
		    	   System.out.println("Test Search Fail");
		       }
			   Iterator it =list.iterator();
			   while (it.hasNext()) 
			   {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
				
			   }
		       
			} 
			catch(ApplicationException e)
		    {
		    	e.printStackTrace();
		    }
			
		}


/**
 * Tests get List.
 */
public static void testList()
	{
		try 
		{
			RoleBean bean = new RoleBean();
		    List list = new ArrayList();
		     bean.setName("Shivam");
	       list = model.list(1,10);
	       if(list.size()<0)
	       {
	    	   System.out.println("Test Search Fail");
	       }
		   Iterator it =list.iterator();
		   while (it.hasNext()) 
		   {
			bean = (RoleBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			
		   }
	       
		} 
		catch(ApplicationException e)
	    {
	    	e.printStackTrace();
	    }
		
	}
	
	}

