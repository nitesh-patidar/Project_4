package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;

import in.co.rays.bean.SubjectBean;
import in.co.rays.exception.ApplicationException;

import in.co.rays.model.CourseModel;

import in.co.rays.model.SubjectModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Subject List functionality Controller. Performs operation for list, search
 * and delete operations of Subject
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="SubjectListCtl" , urlPatterns="/ctl/SubjectListCtl")
public class SubjectListCtl  extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
 
	/** The log. */
	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	
/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 */
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
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
@Override
protected BaseBean populateBean(HttpServletRequest request) 
	    {

	        SubjectBean bean = new SubjectBean();

	        bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
	       
	        bean.setName(DataUtility.getString(request.getParameter("name")));
	        
	        bean.setDescription(DataUtility.getString(request.getParameter("description")));
	       
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
	        log.debug("SubjectListCtl doGet Start");
	       
	        List list = null;
	        
	        List nextList = null;
	 	       
            int pageNo = 1;

	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

	        SubjectBean bean = (SubjectBean) populateBean(request);

	        String op = DataUtility.getString(request.getParameter("operation"));

	        SubjectModel model = new SubjectModel();
	       
	        try 
	          {
		            list = model.search(bean, pageNo, pageSize);
		            
		            
	 	            nextList= model.search(bean, pageNo+1, pageSize);   
		           
		            request.setAttribute("nextlist", nextList.size());
	 	            
		            
		            ServletUtility.setList(list, request);
		            
		            if (list == null || list.size() == 0) 
		             {
		                ServletUtility.setErrorMessage("No record found ", request);
		             }
		            
		            ServletUtility.setList(list, request);
	                ServletUtility.setPageNo(pageNo, request);
		            ServletUtility.setPageSize(pageSize, request);
		            ServletUtility.forward(getView(), request, response);

	          } 
	        catch(ApplicationException e) 
	         {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	         }
	        log.debug("SubjectListCtl doGet End");
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
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	log.debug("DoPost of SubjectlistCtl started");

	List list;
	List nextList=null;


	int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	pageNo = (pageNo == 0) ? 1 : pageNo;
	pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

	String op = DataUtility.getString(request.getParameter("operation"));
	String[] ids = request.getParameterValues("ids");
	SubjectModel model = new SubjectModel();
	SubjectBean bean = (SubjectBean) populateBean(request);

	if (OP_SEARCH.equalsIgnoreCase(op)) {
		pageNo = 1;
	} else if (OP_NEXT.equalsIgnoreCase(op)) {
		pageNo++;
	} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
		if (pageNo > 1) {
			pageNo--;
		} else {
			pageNo = 1;
		}
	} else if (OP_NEW.equalsIgnoreCase(op)) {
		ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
		return;
	} else if (OP_RESET.equalsIgnoreCase(op)) {
		ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
		return;
	} else if (OP_DELETE.equalsIgnoreCase(op)) {
		pageNo = 1;
		if (ids != null && ids.length > 0) {
			SubjectBean deletebean = new SubjectBean();

			for (String id : ids) {
				deletebean.setId(DataUtility.getInt(id));
				try {
					model.delete(deletebean);
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully ", request);
			}
		} else {
			ServletUtility.setErrorMessage("Select at least one record", request);
		}
	}
	try {
		list = model.search(bean, pageNo, pageSize);
		
		 nextList=model.search(bean,pageNo+1,pageSize);
			
		request.setAttribute("nextlist", nextList.size());
			
		ServletUtility.setBean(bean, request);

	} catch (ApplicationException e) {
		e.printStackTrace();
		log.error(e);
		ServletUtility.handleException(e, request, response);
		return;
	}
	
	if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
		ServletUtility.setErrorMessage("No Record Found", request);
	}
     ServletUtility.setBean(bean, request);
	ServletUtility.setList(list, request);
	ServletUtility.setPageNo(pageNo, request);
	ServletUtility.setPageSize(pageSize, request);
	ServletUtility.forward(getView(), request, response);
}

	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		
		return ORSView.SUBJECT_LIST_VIEW;
	}

}
