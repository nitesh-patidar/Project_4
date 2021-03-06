package in.co.rays.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.RecordNotFoundException;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Forget Password functionality Controller. Performs operation for Forget
 * Password
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="ForgetPasswordCtl",urlPatterns={"/ForgetPasswordCtl"})
public class ForgetPasswordCtl extends BaseCtl 
{
   
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(ForgetPasswordCtl.class);
    
  /* (non-Javadoc)
   * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
   */
  protected boolean validate(HttpServletRequest request)
   	{
		log.debug("ForgetPasswordCtl Method validate Started");
	
		boolean pass = true;

		String login = request.getParameter("login");
		
		if(DataValidator.isNull(login))
		{
			request.setAttribute("login",PropertyReader.getValue("error.require","Email Id"));
			pass = false;
			
		}
		else if(!DataValidator.isEmail(login))
		{
			request.setAttribute("login",PropertyReader.getValue("error.email","Email Id"));
		    pass = false ;
		
		}
		log.debug("ForgetPasswordCtl Method validate Ended");
		return pass;
	}
   	
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
     */
    protected BaseBean populateBean(HttpServletRequest request) 
    {

        log.debug("ForgetPasswordCtl Method populatebean Started");

        UserBean bean = new UserBean();

        bean.setLogin(DataUtility.getString(request.getParameter("login")));

        log.debug("ForgetPasswordCtl Method populatebean Ended");

        return bean;
    }

      /**
       * Contains Display logics.
       *
       * @param request the request
       * @param response the response
       * @throws IOException Signals that an I/O exception has occurred.
       * @throws ServletException the servlet exception
       * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
       */
 protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
   	{
		log.debug("ForgetPasswordCtl Method doGet Started");
	  
		ServletUtility.forward(getView(), request, response);
   	
   	}
   	
 /**
  * Contains Submit logics.
  *
  * @param request the request
  * @param response the response
  * @throws IOException Signals that an I/O exception has occurred.
  * @throws ServletException the servlet exception
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */			
 protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  
   	{
		log.debug("ForgetPasswordCtl Method doPost Started");
	   
		String op = DataUtility.getString(request.getParameter("operation"));
	    
	    UserBean bean = (UserBean) populateBean(request);
   	    
	    UserModel model = new UserModel();
   	    
   	    if(OP_GO.equalsIgnoreCase(op))
   	    {
   	    	try 
   	    	{
   	    		
			  model.forgetPassword(bean.getLogin());
			  
			  ServletUtility.setSuccessMessage("Password has been sent to your email id", request); 
   	    	} 
   	        catch(RecordNotFoundException e)
   	    	{
            	ServletUtility.setErrorMessage(e.getMessage(), request);
   	    	   log.error(e);
   	    	}
   	    	catch(ApplicationException e) 
   	    	{
   	    	    log.error(e);
 	    	    ServletUtility.handleException(e, request, response);
 	            return ;
   	    	}
           ServletUtility.forward(getView(), request, response);
   	    }
   	 else if(OP_RESET.equalsIgnoreCase(op))
		{
		  ServletUtility.redirect(ORSView.FORGET_PASSWORD_CTL, request, response);
		  return ; 
		}
   	log.debug("ForgetPasswordCtl Method doPost Ended");
   	}
   	
   	
   	
   	/* (non-Javadoc)
	    * @see in.co.rays.controller.BaseCtl#getView()
	    */
	   @Override
	protected String getView()
	{
		
		return ORSView.FORGET_PASSWORD_VIEW;
	}
    	

}
