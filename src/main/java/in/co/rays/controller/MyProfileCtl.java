package in.co.rays.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * 
 * @author Proxy
 * @version 1.0o
 * Copyright (c) SunilOS
 */
@WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl 
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
	/** The Constant OP_CHANGE_MY_PASSWORD. */
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";
    
	/** The log. */
	private static Logger log = Logger.getLogger(MyProfileCtl.class);
	
   /* (non-Javadoc)
    * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
    */
   protected boolean validate(HttpServletRequest request)
    {
    	log.debug("MyProfileCtl Method validate Started");
    	
    	boolean pass = true ;
    	
    	String op = DataUtility.getString(request.getParameter("operation"));
    	
    	if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op==null)
    	{
    		return pass ;
    	}
    	
    	  if(DataValidator.isNull(request.getParameter("firstName"))) 
	         {
	            request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
	                   
	            pass = false;
	         }
	        else if(DataValidator.isWhiteSpace(request.getParameter("firstName"))) 
	        {
	           request.setAttribute("firstName", PropertyReader.getValue("error.name", "First Name"));
	                  
	           pass = false;
	        }
	    	
	      else if (DataValidator.isSpecial(request.getParameter("firstName"))) 
	        {
	            request.setAttribute("firstName", PropertyReader.getValue("error.special", "First Name"));
	            pass = false;
	        }
	      else if (DataValidator.isNumber(request.getParameter("firstName"))) 
       {
           request.setAttribute("firstName", PropertyReader.getValue("error.number", "First Name"));
           pass = false;
       }
	        
	      if(DataValidator.isNull(request.getParameter("lastName"))) 
	         {
	            request.setAttribute("lastName",  PropertyReader.getValue("error.require", "Last Name"));
	                  
	            pass = false;
	         }
	      
	     else if(DataValidator.isWhiteSpace(request.getParameter("lastName"))) 
	        {
	           request.setAttribute("lastName", PropertyReader.getValue("error.name", "Last Name"));
	                  
	           pass = false;
	        }
	    	
	     else if (DataValidator.isSpecial(request.getParameter("lastName"))) 
	        {
	            request.setAttribute("lastName", PropertyReader.getValue("error.special", "Last Name"));
	            pass = false;
	        }
	    else if (DataValidator.isNumber(request.getParameter("lastName"))) 
         {
            request.setAttribute("lastName", PropertyReader.getValue("error.number", "Last Name"));
            pass = false;
         }

    	if(DataValidator.isNull(request.getParameter("gender")))
    	{
      		
    	    request.setAttribute("gender", PropertyReader.getValue("error.require","gender"));
    	   pass = false;
    	}
    	
    	if(DataValidator.isNull(request.getParameter("mobileNo")))
    	{
      		
    	    request.setAttribute("mobileNo", PropertyReader.getValue("error.require","MobileNo"));
    	   pass = false;
    	}
    	else if(DataValidator.isMobileNo(request.getParameter("mobileNo"))) 
	        {
	            request.setAttribute("mobileNo",PropertyReader.getValue("error.mobile", "Mobile No"));
	                    
	            pass = false;
	        }
	       
    	
    	if(DataValidator.isNull(request.getParameter("dob")))
    	{
      		
    	    request.setAttribute("dob", PropertyReader.getValue("error.require","Date Of Birth"));
    	   pass = false;
    	}
    	
    
       log.debug("MyProfileCtl Method validate Ended");
    
    	return pass ;
    }

   
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
     */
    protected BaseBean populateBean(HttpServletRequest request) 
    {
		log.debug("MyProfileCtl Method populateBean Started");
    	UserBean bean = new UserBean();
    	
    	bean.setId(DataUtility.getLong(request.getParameter("id")));
    	bean.setLogin(DataUtility.getString(request.getParameter("login")));
    	bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
    	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
    	bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
    	bean.setGender(DataUtility.getString(request.getParameter("gender")));
    	bean.setDob(DataUtility.getDate(request.getParameter("dob")));
    	
    	populateDTO(bean,request);
    	return bean ;
	}
    

    /**
     * Contains Display logics.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		log.debug("MyProfileCtl Method doGet Started");
		
		HttpSession session = request.getSession(true);
    	
		UserBean userBean = (UserBean) session.getAttribute("user");
    	
    	long id = userBean.getId();
    	
    	String op = DataUtility.getString(request.getParameter("operation"));
    	
    	UserModel model = new UserModel();
    	
    	if (id >0 || op != null) 
    	{
			/*System.out.println("in id>0 condition in doGet");*/
    		
			UserBean bean ;
			try 
			{
				bean = model.findByPK(id);
				
				ServletUtility.setBean(bean, request);
			} 
			catch(ApplicationException e) 
			{
				log.error(e);
				ServletUtility.handleException(e, request, response);
			    return ;
			}
		}
    	
    	ServletUtility.forward(getView(), request, response);
    	log.debug("MyProfileCtl Method doGet Ended");
	}


/**
 * Contains Submit logics.
 *
 * @param request the request
 * @param response the response
 * @throws ServletException the servlet exception
 * @throws IOException Signals that an I/O exception has occurred.
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		HttpSession session = request.getSession(true);
		
		log.debug("MyProfileCtl Method doPost Started");
    	
		UserBean UserBean = (UserBean) session.getAttribute("user");
    	
    	long id = UserBean.getId();
    	
    	String op = DataUtility.getString(request.getParameter("operation"));
    	
    	UserModel model = new UserModel();
    	
        if(OP_UPDATE.equalsIgnoreCase(op))
        {
        	UserBean bean = (UserBean) populateBean(request);
            
        	try 
        	{
				if(id>0)
				{
					UserBean.setLogin(bean.getLogin());
					UserBean.setFirstName(bean.getFirstName());
					UserBean.setLastName(bean.getLastName());
					UserBean.setGender(bean.getGender());
					UserBean.setMobileNo(bean.getMobileNo());
					UserBean.setDob(bean.getDob());
					model.update(UserBean);
				}
			  ServletUtility.setBean(bean, request);
			  ServletUtility.setSuccessMessage("Profile has been updated Successfully", request);
			} 
        	catch(ApplicationException e) 
        	{
				log.error(e);
				ServletUtility.handleException(e, request, response);
			    return ;
        	}
        	catch(DuplicateRecordException e) 
        	{
				ServletUtility.setBean(bean, request);
			    ServletUtility.setErrorMessage("Login id already exist", request);
        	}
        }
       else if(OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op))
        {
        	ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request, response);
           
        	return ;
        
        }
	ServletUtility.forward(getView(), request, response);
	log.debug("MyProfileCtl Method doPost Ended");	
 }


	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() 
	{
		
		return ORSView.MY_PROFILE_VIEW ;
	}

}
