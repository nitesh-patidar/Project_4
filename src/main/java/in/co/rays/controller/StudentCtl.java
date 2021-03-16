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
import in.co.rays.bean.StudentBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.StudentModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


// TODO: Auto-generated Javadoc
/**
 * Student functionality Controller. Performs operation for add and update
 * operations of Student
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl"})
public class StudentCtl extends BaseCtl 
{
	  
   /** The Constant serialVersionUID. */
   private static final long serialVersionUID = 1L;
	
   /** The log. */
   private static Logger log = Logger.getLogger(StudentCtl.class);
      
   /* (non-Javadoc)
    * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
    */
   protected void preload(HttpServletRequest request) 
	  {
	        CollegeModel model = new CollegeModel();
	        try 
	        {
	            List l = model.list();
	            request.setAttribute("collegeList", l);
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

	        log.debug("StudentCtl Method validate Started");

	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));
	        String email = request.getParameter("email");
	        String dob = request.getParameter("dob");

	        if (DataValidator.isNull(request.getParameter("firstName"))) 
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
	        
	        
	        
	        if (DataValidator.isNull(request.getParameter("lastName")))
	        {
	            request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
	            pass = false;
	        }
	      
	        else if(DataValidator.isWhiteSpace(request.getParameter("lastName"))) 
	         {
	            request.setAttribute("lastName",PropertyReader.getValue("error.lname", "Last Name"));
	                    
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
	        
	        if (DataValidator.isNull(request.getParameter("mobileNo"))) 
	        {
	            request.setAttribute("mobileNo",PropertyReader.getValue("error.require", "Mobile No"));
	                    
	            pass = false;
	        }
	        else if(DataValidator.isMobileNo(request.getParameter("mobileNo"))) 
	        {
	            request.setAttribute("mobileNo",PropertyReader.getValue("error.mobile", "Mobile No"));
	                    
	            pass = false;
	        }
	       
	        if (DataValidator.isNull(email)) 
	        {
	            request.setAttribute("email",PropertyReader.getValue("error.require", "Email Id"));
	                    
	            pass = false;
	        } 
	        else if (!DataValidator.isEmail(email)) {
	            request.setAttribute("email",PropertyReader.getValue("error.email","Email "));
	            pass = false;
	        }
	        
	        if (DataValidator.isNull(request.getParameter("collegeId"))) 
	        {
	            request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
	                   
	            pass = false;
	        }
	        
	        if (DataValidator.isNull(dob)) 
	        {
	            request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));
	                   
	            pass = false;
	        } 
	        
	        else if(!DataValidator.isDate(dob)) 
	        {
	            request.setAttribute("dob", PropertyReader.getValue("error.date","Date Of Birth"));
	                    
	            pass = false;
	        }

	        log.debug("StudentCtl Method validate Ended");

	        return pass;
	    }
	  
	  
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
protected BaseBean populateBean(HttpServletRequest request) 
	   {

	        log.debug("StudentCtl Method populatebean Started");

	        StudentBean bean = new StudentBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	                
            bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        bean.setDob(DataUtility.getDate(request.getParameter("dob")));

	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

	        bean.setEmail(DataUtility.getString(request.getParameter("email")));

	        bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

	        populateDTO(bean, request);

	        log.debug("StudentCtl Method populatebean Ended");

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
protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	    {

	        log.debug("StudentCtl Method doGet Started");

	        String op = DataUtility.getString(request.getParameter("operation"));
	        
	        long id = DataUtility.getLong(request.getParameter("id"));

	       
	        StudentModel model = new StudentModel();
	        
	        if (id > 0 || op != null) 
	        {
	            StudentBean bean;
	            try 
	             {
	                bean = model.findByPK(id);
	              
	                ServletUtility.setBean(bean, request);
	             } 
	            catch(ApplicationException e) 
	             {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	             }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("StudentCtl Method doGett Ended");
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

	        log.debug("StudentCtl Method doPost Started");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        StudentModel model = new StudentModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op))
	        {

	            StudentBean bean = (StudentBean) populateBean(request);

	            try 
	             {
	                if (id > 0) 
	                 {
	                    model.update(bean);
	                    ServletUtility.setBean(bean, request);
		                ServletUtility.setSuccessMessage("Data is successfully Updated",request);

	                 }
	               else 
	                {
	                    long pk = model.add(bean);
	                    
	                    ServletUtility.setBean(bean, request);
		               
	                    ServletUtility.setSuccessMessage("Data is successfully Saved",request);
	                }

	               

	             } 
	            catch(ApplicationException e) 
	             {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	             } 
	            catch(DuplicateRecordException e) 
	             {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Email Id is already exists", request);
	                        
	             }

	          }

	        else if(OP_DELETE.equalsIgnoreCase(op)) 
	        {

	            StudentBean bean = (StudentBean) populateBean(request);
	            try 
	              {
		                model.delete(bean);
		                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,response);
		                        
		                return;

	              }
	            catch (ApplicationException e) 
	             {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	             }

	         } 
	        else if(OP_RESET.equalsIgnoreCase(op)) 
	        {

	            ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
	            return;

	        }
	        else if(OP_CANCEL.equalsIgnoreCase(op)) 
	        {

	            ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request, response);
	            return;

	        }
	        ServletUtility.forward(getView(), request, response);

	        log.debug("StudentCtl Method doPost Ended");
	    }

	  
	  
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	protected String getView() 
	{
		
		return ORSView.STUDENT_VIEW;
	}
	
}
