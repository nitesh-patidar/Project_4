package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;

import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;

import in.co.rays.model.CourseModel;

import in.co.rays.model.SubjectModel;
import in.co.rays.model.TimetableModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Timetable List functionality Controller. Performs operation for list, search
 * and delete operations of Timetable
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="TimetableListCtl" , urlPatterns="/ctl/TimetableListCtl")
public class TimetableListCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(TimetableListCtl.class);

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 */
@Override
protected void preload(HttpServletRequest request)
    {
       CourseModel model1 = new CourseModel();
       SubjectModel model2 = new SubjectModel();
       try 
        {
    	   List l1 = model1.list();
           request.setAttribute("courseList", l1);
           
           List l2 = model2.list();
           request.setAttribute("subjectList", l2);
           
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

	        TimetableBean bean = new TimetableBean();
	        
	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
	        
	        bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
	       
	        bean.setSemester(DataUtility.getString(request.getParameter("semester")));
	        
	        bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
	       
	        //System.out.println(bean.getExamDate());
	        
	        bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));

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
	        log.debug("TimetableListCtl doGet Start");
	       
	        List list = null;
	        
	        List nextList = null;
	 	   
            int pageNo = 1;

	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

	        TimetableBean bean = (TimetableBean) populateBean(request);

	        String op = DataUtility.getString(request.getParameter("operation"));

	        TimetableModel model = new TimetableModel();
	       
	        try 
	          {
		            list = model.search(bean, pageNo, pageSize);
		            
		            nextList= model.search(bean, pageNo+1, pageSize);   
		           
		            request.setAttribute("nextlist", nextList.size());
	 	            
		          /*  ServletUtility.setList(list, request);*/
		            
		            ServletUtility.setBean(bean, request);
		            
		            if(list == null || list.size() == 0) 
		             {
		                ServletUtility.setErrorMessage("No record found", request);
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
	        log.debug("TimetableListCtl doGet End");
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

	List list=null;
	List nextList=null;
	
	String op = DataUtility.getString(request.getParameter("operation"));

	int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	
	int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	
	pageNo = (pageNo == 0) ? 1 : pageNo;
	
	pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;


	TimetableBean bean = (TimetableBean) populateBean(request);	
	
	TimetableModel model = new TimetableModel();
	
	String[] ids = (String[]) request.getParameterValues("ids");
			
		        if (OP_SEARCH.equalsIgnoreCase(op)) {
			    pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;	
				} 
		        
				else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
					pageNo--;
				}
				else if (OP_NEW.equalsIgnoreCase(op)) 
				{
					ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
					return ;
				}
				
				else if (OP_RESET.equalsIgnoreCase(op)||OP_BACK.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
					return;
				}
				else if (OP_DELETE.equalsIgnoreCase(op)) {
					pageNo=1;
					if (ids != null && ids.length > 0) {
						TimetableBean bean3 = new TimetableBean();

						for (String id2 : ids) {
							int id1 = DataUtility.getInt(id2);
							bean3.setId(id1);
							try {
								model.delete(bean3);
							} catch (ApplicationException e) {
								e.printStackTrace();
								ServletUtility.handleException(e, request, response);
								return;
							}
							ServletUtility.setSuccessMessage("Data Deleted Succesfully", request);
						}
					
					}else{
						ServletUtility.setErrorMessage("Select at least one Record", request);
					}
				}
		    	try {
					list = model.search(bean, pageNo, pageSize);
					
					nextList=model.search(bean,pageNo+1,pageSize);
					
					request.setAttribute("nextlist", nextList.size());
					
					ServletUtility.setBean(bean, request);
				}
				catch(ApplicationException e){	
					ServletUtility.handleException(e, request, response);
					return;
				}
		   if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)) 
		{
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
		
		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
