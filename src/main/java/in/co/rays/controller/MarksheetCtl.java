package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.MarksheetModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 * 
 * @author Proxy
 * @version 1.0 
 * Copyright (c) SunilOS
 */
@WebServlet(name="MarksheetCtl", urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl
{
  
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 */
@Override
protected void  preload(HttpServletRequest request) 
	{
		StudentModel model = new StudentModel();
		try 
		 {
			List l = model.list();
			request.setAttribute("studentList", l);
		 } 
		catch(ApplicationException e) 
		{
			log.error(e);
		}
	}
	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
 */
protected boolean validate(HttpServletRequest request) 
	{
		log.debug("MarksheetCtl Method validate Started");
		
		boolean pass = true ;
	
		if(DataValidator.isNull(request.getParameter("rollNo")))
		{
			request.setAttribute("rollNo", PropertyReader.getValue("error.require","Roll Number"));
			pass = false ;
		}
		else if(!DataValidator.isRollNo(request.getParameter("rollNo")))
		{
			request.setAttribute("rollNo","RollNo should be in the formate of 0000XX0000");
			pass = false ;
		}
		
		if(DataValidator.isNull(request.getParameter("physics")))
		{
			request.setAttribute("physics", PropertyReader.getValue("error.require","Marks"));
			pass = false ;
		}
		else if(!DataValidator.isInteger(request.getParameter("physics")))
    	{
    		request.setAttribute("physics", PropertyReader.getValue("error.integer","Marks"));
			pass = false ;
    	}
		
		if(DataUtility.getInt(request.getParameter("physics")) > 100 || DataUtility.getInt(request.getParameter("physics")) < 0)
		{
			request.setAttribute("physics", "Please Enter Valid Marks");
		    pass = false ;
		}
		
		if(DataValidator.isNull(request.getParameter("chemistry")))
		{
			request.setAttribute("chemistry", PropertyReader.getValue("error.require","Marks"));
			pass = false ;
		}
		else if( !DataValidator.isInteger(request.getParameter("chemistry")))
    	{
    		request.setAttribute("chemistry", PropertyReader.getValue("error.integer","Marks"));
			pass = false ;
    	}
		
		if(DataUtility.getInt(request.getParameter("chemistry")) > 100 || DataUtility.getInt(request.getParameter("chemistry")) < 0)
		{
			request.setAttribute("chemistry", "Please Enter Valid Marks");
		    pass = false ;
		}
		
    	if(DataValidator.isNull(request.getParameter("maths")))
		{
			request.setAttribute("maths", PropertyReader.getValue("error.require","Marks"));
			pass = false ;
		}
		
    	else if(!DataValidator.isInteger(request.getParameter("maths")))
    	{
    		request.setAttribute("maths", PropertyReader.getValue("error.integer","Marks"));
			pass = false ;
    	}
		
       if(DataUtility.getInt(request.getParameter("maths")) > 100 || DataUtility.getInt(request.getParameter("maths")) < 0)
		{
			request.setAttribute("maths", "Please Enter Valid Marks");
		    pass = false ;
		}
	   
		if(DataValidator.isNull(request.getParameter("studentId")))
		{
			request.setAttribute("studentId", PropertyReader.getValue("error.require","Student Name"));
			pass = false ;
		}
		
		
	   log.debug("MarksheetCtl Method validate Ended");
	
		return pass;
		
	}
	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
protected BaseBean populateBean(HttpServletRequest request) 
	{
		log.debug("MarksheetCtl Method populateBean started");
		
		MarksheetBean bean = new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		
		populateDTO(bean, request);
		System.out.println("Population done");
		log.debug("MarksheetCtl method populateBean Ended");
		
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
protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		log.debug("MarksheetCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
	   
		long id = DataUtility.getLong(request.getParameter("id"));
		
		MarksheetModel model = new MarksheetModel();
	   
	    
	    if(id>0) 
	    {
			MarksheetBean bean ;
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
	log.debug("MarksheetCtl Method doGet Ended");
	
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
protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		log.debug("MarksheetCtl Method doPost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
	    MarksheetModel model = new MarksheetModel();
	    long id = DataUtility.getLong(request.getParameter("id"));
	    
	    if(OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op))
	    {
	    	MarksheetBean bean = (MarksheetBean) populateBean(request);
	        
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
				log.error(e);
				ServletUtility.handleException(e, request, response);
			    return ;
	    	}
	       catch (DuplicateRecordException e)
	    	{
			  ServletUtility.setBean(bean, request);
		      ServletUtility.setErrorMessage("RollNo is Already Exist", request);
	    	
	    	}
	    }
	    else if(OP_DELETE.equalsIgnoreCase(op))
	    {
	    	MarksheetBean bean = (MarksheetBean) populateBean(request);
	        try 
	        {
				model.delete(bean);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
	            return ;		
	        } 
	        catch(ApplicationException e) 
	        {
			     log.error(e);
	            ServletUtility.handleException(e, request, response);		
                return ;	        
	        }
	    
	    }
        else if(OP_CANCEL.equalsIgnoreCase(op)) 
        {
			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
            return ;		
        }
       
	    ServletUtility.forward(getView(), request, response);
	    log.debug("MarksheetCtl Method doPost Ended");
	    
	}
	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() 
	{
		
		return  ORSView.MARKSHEET_VIEW;
	}

}
