package in.co.rays.test; 

	import java.sql.Timestamp;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.Iterator;
	import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModel;

// TODO: Auto-generated Javadoc
/**
 * User Model Test classes.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */	
public class UserModelTest 
	{

    /** Model object to test. */
   public static UserModel model = new UserModel();

   /**
    * Main method to call test methods.
    *
    * @param args the arguments
    * @throws ParseException the parse exception
    * @throws DuplicateRecordException the duplicate record exception
    */   
 public static void main(String[] args) throws ParseException,DuplicateRecordException 
	    {
	         //testAdd();
	        // testDelete();
	       // testUpdate();
	       //  testFindByPK();
	        // testFindByLogin();
	        // testSearch();
	       //  testGetRoles();
	         testList();
	         //testAuthenticate();
	       // testRegisterUser();
	        // testchangePassword();
	      //   testforgetPassword();
	        testresetPassword();

	    }

 /**
  * Tests add a User.
  *
  * @throws ParseException the parse exception
  * @throws DuplicateRecordException the duplicate record exception
  */  
  public static void testAdd() throws ParseException,DuplicateRecordException 
	    {

	        try 
	        {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyyy");

	            
	            bean.setFirstName("Loken");
	            bean.setLastName("Mewada");
	            bean.setLogin("loken@gmail.com");
	            bean.setPassword("pass1234");
	            bean.setDob(sdf.parse("31-12-1990"));
	            bean.setRoleId(1);
	            bean.setUnSuccessfulLogin(2);
	            bean.setGender("Male");
	            bean.setLastLogin(new Timestamp(new Date().getTime()));
	            bean.setLock("Yes");
	            bean.setConfirmPassword("pass1234");
	            long pk = model.add(bean);
	            UserBean addedbean = model.findByPK(pk);
	            
	            if (addedbean == null)
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
   * Tests delete a User.
   */
public static void testDelete() 
	    {

	        try {
	            UserBean bean = new UserBean();
	            long pk = 1;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	            UserBean deletedbean = model.findByPK(pk);
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

/**
 * Tests update a User.
 *
 * @throws ParseException the parse exception
 */	   
public static void testUpdate() throws ParseException 
	     {
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");
 
	    	
	        try {
	            UserBean bean = model.findByPK(2);
	            bean.setFirstName("Pravin");
	            bean.setLastName("Verma");
	            bean.setLogin("PravinVerma@gmail.com");
	            bean.setPassword("Raystech");
                bean.setDob(sdf.parse("31-12-1990"));
	            bean.setRoleId(2);
	            bean.setUnSuccessfulLogin(2);
	            bean.setGender("Male");
	            bean.setLastLogin(new Timestamp(new Date().getTime()));
	            bean.setLock("Yes");
	            bean.setConfirmPassword("pass1234");
	            model.update(bean);

	        
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
 * Tests find a User by PK.
 */
public static void testFindByPK() 
	    {
	        try 
	        {
	            UserBean bean = new UserBean();
	            long pk = 2;
	            bean = model.findByPK(pk);
	            if (bean == null)
	            {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getPassword());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getRoleId());
	            System.out.println(bean.getUnSuccessfulLogin());
	            System.out.println(bean.getGender());
	            System.out.println(bean.getLastLogin());
	            System.out.println(bean.getLock());
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }

	    }

/**
 * Tests find a User by Login.
 */	   
public static void testFindByLogin() 
	    {
	        try 
	        {
	            UserBean bean = new UserBean();
	            bean = model.findByLogin("loken@gmail.com");
	            if (bean == null) 
	            {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getPassword());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getRoleId());
	            System.out.println(bean.getUnSuccessfulLogin());
	            System.out.println(bean.getGender());
	            System.out.println(bean.getLastLogin());
	            System.out.println(bean.getLock());
	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }
	    }

/**
 * Tests get Roles.
 */	   
public static void testGetRoles() 
	    {

	        try
	        {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            bean.setRoleId(2);
	            list = model.getRoles(bean);
	            if (list.size() < 0)
	            {
	                System.out.println("Test Get Roles fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) 
	            {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	            }
	        }
	        catch (ApplicationException e) 
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
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            bean.setFirstName("Loken");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0)
	            {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) 
	            {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	            }

	        } catch (ApplicationException e) {
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
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) 
	            {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	                System.out.println(bean.getMobileNo());
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
 * Tests authenticate User.
 */	   
public static void testAuthenticate() 
	    {

	        try 
	        {
	            UserBean bean = new UserBean();
	            bean.setLogin("Loken@gmail.com");
	            bean.setPassword("pass1234");
	            bean = model.authenticate(bean.getLogin(), bean.getPassword());
	            if (bean != null) 
	            {
	                System.out.println("Successfully login");

	            } 
	            else 
	            {
	                System.out.println("Invalid login Id & password");
	            }

	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }
	    }

	 
/**
 * Tests add a User register.
 *
 * @throws ParseException the parse exception
 */
public static void testRegisterUser() throws ParseException
	    {
	        try 
	        {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	            
	            bean.setFirstName("Vijay");
	            
	            bean.setLogin("Kumar@gmail.com");
	            bean.setPassword("pass123");
	            bean.setConfirmPassword("pass123");
	            bean.setDob(sdf.parse("02/12/1990"));
	            bean.setGender("Male");
	            bean.setRoleId(1);
	            long pk = model.registerUser(bean);
	            System.out.println("Successfully register");
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getDob());
	            UserBean registerbean = model.findByPK(pk);
	            if (registerbean != null) 
	            {
	                System.out.println("Test registation fail");
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
 * Tests changePassword.
 */
public static void testchangePassword()
	    {

	        try {
	            UserBean bean = model.findByLogin("PravinVerma@gmail.com");
	            String oldPassword = bean.getPassword();
	            bean.setId(2);
	            bean.setPassword("Rays");
	            //bean.setConfirmPassword("");
	            String newPassword = bean.getPassword();
	            try {
	                model.changePassword(2L, oldPassword, newPassword);
	                System.out.println("password has been change successfully");
	            } 
	            catch (RecordNotFoundException e) 
	            {
	                e.printStackTrace();
	            }

	        } 
	        catch (ApplicationException e)
	        {
	            e.printStackTrace();
	        }

	    }

/**
 * Tests fogetPassword.
 */	   
public static void testforgetPassword() 
	    {
	        try 
	        {
	            boolean b = model.forgetPassword("nitespatidar2203@gmail.com");

	            System.out.println("Suucess : Test Forget Password Success");

	        } 
	        catch (RecordNotFoundException e)
	        {
	            e.printStackTrace();
	        }
	        catch (ApplicationException e)
	        {
	            e.printStackTrace();
	        }
	    }

/**
 * Tests resetPassword.
 */	   
public static void testresetPassword()
	    {
	        UserBean bean = new UserBean();
	        try 
	        {
	            bean = model.findByLogin("nitespatidar2203@gmail.com");
	            if (bean != null) 
	            {
	                boolean pass = model.resetPassword(bean);
	                if (pass = false) 
	                {
	                    System.out.println("Test Update fail");
	                }
	            }

	        } 
	        catch (ApplicationException e) 
	        {
	            e.printStackTrace();
	        }

	    }
}
