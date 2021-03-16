package in.co.rays.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;

import in.co.rays.model.CourseModel;

import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Timetable functionality Controller. Performs operation for add, update, delete
 * and get Timetable
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="TimetableCtl" , urlPatterns="/ctl/TimetableCtl")
public class TimetableCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(TimetableCtl.class);
	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 */
@Override
protected void preload(HttpServletRequest request) 
	  {
	     
	        CourseModel cModel = new CourseModel();
	        SubjectModel sModel = new SubjectModel();
	        List<CourseBean> clist = new ArrayList<CourseBean>();
			List<SubjectBean> slist = new ArrayList<SubjectBean>();
	        try 
	        {
	            
	            clist = cModel.list();
	            request.setAttribute("courseList", clist);
	            
	            slist = sModel.list();
	            request.setAttribute("subjectList", slist);
	            
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
protected boolean validate(HttpServletRequest request) 
  {

	        log.debug("TimetableCtl Method validate Started");

	        boolean pass = true;

	       
	        if(DataValidator.isNull(request.getParameter("courseId"))) 
	         {
	            request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
	                   
	            pass = false;
	         }
	        
	        
	        if(DataValidator.isNull(request.getParameter("subjectId"))) 
	         {
	            request.setAttribute("subjectId",  PropertyReader.getValue("error.require", "Subject Name"));
	                  
	            pass = false;
	         }
	       
	        
	        if(DataValidator.isNull(request.getParameter("semester"))) 
	         {
	            request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));
	                   
	            pass = false;
	         }
	        
	        if(DataValidator.isNull(request.getParameter("examDate"))) 
	         {
	            request.setAttribute("examDate",
	                    PropertyReader.getValue("error.require", "Exam Date"));
	            pass = false;
	         } 
	        
	        else if(!DataValidator.isDate(request.getParameter("examDate"))) 
	         {
	            request.setAttribute("examDate",PropertyReader.getValue("error.date", "Exam Date"));
	                    
	            pass = false;
	         }
	        
	        if(DataValidator.isNull(request.getParameter("examTime"))) 
	         {
	            request.setAttribute("examTime",
	                    PropertyReader.getValue("error.require", "Exam Time"));
	            pass = false;
	         } 
	        
	       
	        
	       log.debug("TimetableCtl Method validate Ended");

	        return pass;
	    }

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
@Override
protected BaseBean populateBean(HttpServletRequest request) 
	      {

	        log.debug("TimetableCtl Method populatebean Started");

	        TimetableBean bean = new TimetableBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setSemester(DataUtility.getString(request.getParameter("semester")));

	        bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));

	        bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
	        
	        bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
	        
	        bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
	        
	        log.debug("TimetableCtl Method populatebean Ended");

	        return bean;
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
protected void doGet(HttpServletRequest request,HttpServletResponse response)
	                               throws ServletException, IOException 
	     {
	        
	    	log.debug("TimetableCtl Method doGet Started");
	    	
	    	String op = DataUtility.getString(request.getParameter("operation"));
			
			TimetableModel model = new TimetableModel();
		    
			long id = DataUtility.getLong(request.getParameter("id"));
			
			if(id>0)
			{
				TimetableBean bean ;
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
protected void doPost(HttpServletRequest request,HttpServletResponse response)
	                                          throws ServletException, IOException
	       {
		       
		        log.debug("TimetableCtl Method doPost Started");
		       
		        String op = DataUtility.getString(request.getParameter("operation"));
				
		        TimetableModel model = new TimetableModel();
				
		        long id = DataUtility.getLong(request.getParameter("id"));
			
				if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) 
				{
				  TimetableBean bean = (TimetableBean) populateBean(request);	
				
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
			           ServletUtility.setErrorMessage("Timetable already exists", request);
			           
				  }
				}
				 else if(OP_DELETE.equalsIgnoreCase(op))
				 {
					 TimetableBean bean = (TimetableBean) populateBean(request);
				     try 
				      {
						model.delete(bean);
						ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
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
						ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
						return ; 
					}
				else if(OP_CANCEL.equalsIgnoreCase(op))
				{
					ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
					return ; 
				}
				 ServletUtility.forward(getView(), request, response); 
		      log.debug("TimetableCtl Method doPost Ended");	
		        
	       }

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		
		return ORSView.TIMETABLE_VIEW;
	}

}
