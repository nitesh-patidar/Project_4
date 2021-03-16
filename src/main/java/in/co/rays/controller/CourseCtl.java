package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Course functionality Controller. Performs operation for add, update, delete
 * and get Course
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="CourseCtl",urlPatterns={"/ctl/CourseCtl"})
public class CourseCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(CourseCtl.class);

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 */
@Override
protected boolean  validate(HttpServletRequest request) 
		{
			log.debug("CourseCtl Method Valid Started");
			
			boolean pass = true ;
			
			if(DataValidator.isNull(request.getParameter("name")))
		    {
		      request.setAttribute("name", PropertyReader.getValue("error.require","Course Name"));
		      pass = false;
		    }
	      else if (DataValidator.isSpecial(request.getParameter("name"))) 
	        {
	            request.setAttribute("name", PropertyReader.getValue("error.special", "Course Name"));
	            pass = false;
	        }
	      else if (DataValidator.isNumber(request.getParameter("name"))) 
           {
              request.setAttribute("name", PropertyReader.getValue("error.number", "Course Name"));
              pass = false;
           }
		        
		    
			if(DataValidator.isNull(request.getParameter("duration")))
			{
			    request.setAttribute("duration",PropertyReader.getValue("error.require","Duration Of Course"));	
			    pass = false ;
			}
			if(DataValidator.isNull(request.getParameter("description")))
			{
			    request.setAttribute("description",PropertyReader.getValue("error.require","Description of Course"));	
			    pass = false ;
			}
			
			log.debug("CourseCtl Method validate Ended");
			
			return pass;
			
		}
	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
@Override	
protected CourseBean populateBean(HttpServletRequest request) 
    {
		log.debug("CourseCtl Method populatebean Started");
	    
		CourseBean bean = new CourseBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		populateDTO(bean, request);
		 
		log.debug("CourseCtl Method populatebean Ended");
		
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
		String op = DataUtility.getString(request.getParameter("operation"));
		
		CourseModel model = new CourseModel();
	    
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id>0)
		{
			CourseBean bean ;
			try 
			{
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} 
			catch (ApplicationException e) 
			{
				log.error(e);
			    ServletUtility.handleException(e, request, response);
			    return ;
			}
		}
		ServletUtility.forward(getView(), request, response);
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
		log.debug("CourseCtl Method doPost Started");
	
		String op = DataUtility.getString(request.getParameter("operation"));
		
		CourseModel model = new CourseModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
	
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) 
		{
			CourseBean bean = (CourseBean) populateBean(request);	
		  try 
		  {
			if(id>0)
			{
				model.update(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is Successfully Updated", request);
				
			}
			else
			{
				
				long pk = model.add(bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.setSuccessMessage("Data is Successfully Saved", request);
				
			}
			
		  } 
		  catch (ApplicationException e) 
		    {
			   e.printStackTrace();
			   log.error(e);
			   ServletUtility.handleException(e, request, response);
	    	   return ;
		    }
		  catch (DuplicateRecordException e) 
		  {
	           ServletUtility.setBean(bean, request);
	           ServletUtility.setErrorMessage("Course Name Already Exists", request);
	           
		  }
		}
		 else if(OP_DELETE.equalsIgnoreCase(op))
		 {
			 CourseBean bean = (CourseBean) populateBean(request);
		     try 
		      {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			    return ;	
			  } 
		    catch (ApplicationException e) 
		     {
			  log.error(e);
			  ServletUtility.handleException(e, request, response);
			  return ;
		     }
		 }
	   else if(OP_RESET.equalsIgnoreCase(op))
			{
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return ; 
			}
		else if(OP_CANCEL.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return ; 
		}
		 ServletUtility.forward(getView(), request, response); 
      log.debug("CollegeCtl Method doPost Ended");	
 }

	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		
		return ORSView.COURSE_VIEW;
	}

}
