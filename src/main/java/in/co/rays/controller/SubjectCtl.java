package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CourseModel;
import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Subject functionality Controller. Performs operation for add, update, delete
 * and get Subject
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {

/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1L;
	
/** The log. */
private static Logger log = Logger.getLogger(SubjectCtl.class);

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 */
@Override
protected void preload(HttpServletRequest request) 
	  {
	        CourseModel model = new CourseModel();
	        try 
	        {
	            List l = model.list();
	            request.setAttribute("courseList", l);
	        } 
	        catch(ApplicationException e) 
	        {
	            log.error(e);
	        }

	  }

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 */
@Override
protected boolean  validate(HttpServletRequest request) 
		{
			log.debug("SubjectCtl Method Valid Started");
			
			boolean pass = true ;
			 if(DataValidator.isNull(request.getParameter("courseId")))
			    {
			      request.setAttribute("courseId", PropertyReader.getValue("error.require","Course Name"));
			      pass = false;
			    }
			
			 if(DataValidator.isNull(request.getParameter("name")))
			    {
			      request.setAttribute("name", PropertyReader.getValue("error.require","Subject Name"));
			      pass = false;
			    }
		      else if (DataValidator.isSpecial(request.getParameter("name"))) 
		        {
		            request.setAttribute("name", PropertyReader.getValue("error.special", "Subject Name"));
		            pass = false;
		        }
		      else if (DataValidator.isNumber(request.getParameter("name"))) 
	           {
	              request.setAttribute("name", PropertyReader.getValue("error.number", "Subject Name"));
	              pass = false;
	           }
			        
		        
		        
		   if(DataValidator.isNull(request.getParameter("description")))
			{
			    request.setAttribute("description",PropertyReader.getValue("error.require","Description of Subject"));	
			    pass = false ;
			}
		   
			
			log.debug("SubjectCtl Method validate Ended");
			
			return pass;
			
		}
	
	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
protected SubjectBean populateBean(HttpServletRequest request) 
    {
		log.debug("SubjectCtl Method populatebean Started");
	    
		SubjectBean bean = new SubjectBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setName(DataUtility.getString(request.getParameter("name")));
		
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		
		populateDTO(bean, request);
		 
		log.debug("SubjectCtl Method populatebean Ended");
		
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
		
		SubjectModel model = new SubjectModel();
	    
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(id>0)
		{
			SubjectBean bean ;
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
		log.debug("SubjectCtl Method doPost Started");
	
		String op = DataUtility.getString(request.getParameter("operation"));
		
		SubjectModel model = new SubjectModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
	
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) 
		{
			SubjectBean bean = populateBean(request);	
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
	           ServletUtility.setErrorMessage("Subject Name Already Exists", request);
	           
		  }
		}
		 else if(OP_DELETE.equalsIgnoreCase(op))
		 {
			 SubjectBean bean = (SubjectBean) populateBean(request);
		     try 
		      {
				model.delete(bean);
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
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
				ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
				return ; 
			}
		else if(OP_CANCEL.equalsIgnoreCase(op))
		{
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return ; 
		}
		 ServletUtility.forward(getView(), request, response); 
      log.debug("SubjectCtl Method doPost Ended");	
 }

	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		
		return ORSView.SUBJECT_VIEW;
	}

	
}
