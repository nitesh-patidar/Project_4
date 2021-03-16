package in.co.rays.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.*;

import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.RoleModel;

import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


// TODO: Auto-generated Javadoc
/**
 * Role functionality Controller. Performs operation for add
 * and update operations of Role
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="RoleCtl" , urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl extends BaseCtl
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
     
	/** The log. */
	private static Logger log = Logger.getLogger(RoleCtl.class);
   
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 	 */
 	protected boolean validate(HttpServletRequest request)
	    {
	    	log.debug("RoleCtl Method validate Started");
	    	
	    	boolean pass = true ;
	    	
	   if(DataValidator.isNull(request.getParameter("name")))
		 {
		      request.setAttribute("name", PropertyReader.getValue("error.require","Name"));
		      pass = false;
		  }
	    else if (DataValidator.isSpecial(request.getParameter("name"))) 
	        {
	            request.setAttribute("name", PropertyReader.getValue("error.special", "Name"));
	            pass = false;
	        }
	    else if (DataValidator.isNumber(request.getParameter("name"))) 
        {
            request.setAttribute("name", PropertyReader.getValue("error.number", "Name"));
            pass = false;
        }
	        

	     
	     if(DataValidator.isNull(request.getParameter("description")))
	    	{
	           request.setAttribute("description", PropertyReader.getValue("error.require","Description"));
	    	   pass = false;
	    	}
	    	
	    log.debug("RoleCtl Method validate Ended");
    		
	  return pass ;
	 }
 
	  /* (non-Javadoc)
  	 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
  	 */
  	protected BaseBean populateBean(HttpServletRequest request) 
	    {
			log.debug("RoleCtl Method populateBean Started");
	    	
			RoleBean bean = new RoleBean();
	    	
	    	bean.setId(DataUtility.getLong(request.getParameter("id")));
	    	
	    	System.out.println("IN POPULATE-->"+DataUtility.getLong(request.getParameter("id")));
	    	
	    	bean.setName(DataUtility.getString(request.getParameter("name")));
	    	
	    	bean.setDescription(DataUtility.getString(request.getParameter("description")));
	    	
	    	
	    	populateDTO(bean,request);
	    	
	    	log.debug("RoleCtl Method populateBean Ended");
	    	
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
	
		log.debug("RoleCtl Method doGet Started");
		
        String op = DataUtility.getString(request.getParameter("operation"));
    	
    	RoleModel model = new RoleModel();
    	
    	long id = DataUtility.getLong(request.getParameter("id"));
    	
    	if (id >0 || op != null) 
    	{
			RoleBean bean ;
		   
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
		log.debug("RoleCtl Method doGet Ended");
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
		log.debug("RoleCtl Method doPost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		System.out.println("operation---->"+op);
    	
    	long id = DataUtility.getLong(request.getParameter("id"));
    	
    	RoleModel model = new RoleModel();
    	
        if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
        {
        	RoleBean bean = (RoleBean) populateBean(request);
            
        	try 
        	{
				if(id>0)
				{
					model.update(bean);
					ServletUtility.setBean(bean, request);
				    ServletUtility.setSuccessMessage("Data Successfully Updated", request);
				}
				else
				{
					long pk = model.add(bean);
				    ServletUtility.setBean(bean, request);
				    ServletUtility.setSuccessMessage("Data Successfully Saved", request);
				}
			 
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
			    ServletUtility.setErrorMessage("Role Already Exist", request);
        	}
        }
       else if(OP_DELETE.equalsIgnoreCase(op))
        {
    	   RoleBean bean = (RoleBean) populateBean(request);
           try 
           {
			model.delete(bean);
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
		    return ;
           } 
         catch (Exception e)
           {
			 log.error(e);
			 ServletUtility.handleException(e, request, response);
		     return ;
           }
    	}
       else if(OP_RESET.equalsIgnoreCase(op))
       {
    	   ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
           return ;
       }
       else if(OP_CANCEL.equalsIgnoreCase(op))
       {
    	   ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
           return ;
       }
	
		
        ServletUtility.forward(getView(), request, response);
		
		log.debug("RoleCtl Method doPost Ended");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() 
	{
		return ORSView.ROLE_VIEW;
	}

}
