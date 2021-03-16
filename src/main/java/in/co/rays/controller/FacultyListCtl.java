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
import in.co.rays.bean.FacultyBean;

import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CollegeModel;
import in.co.rays.model.FacultyModel;

import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


// TODO: Auto-generated Javadoc
/**
 * Faculty List functionality Controller. Performs operation for list, search
 * and delete operations of Faculty
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="FacultyListCtl" , urlPatterns="/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The log. */
	private static Logger log = Logger.getLogger(FacultyListCtl.class);
	
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
 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
 */
protected BaseBean populateBean(HttpServletRequest request) 
   {
       FacultyBean bean = new FacultyBean();
       
       bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
       
       bean.setFirstName(DataUtility.getString(request .getParameter("firstName")));

       bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
       
       bean.setEmail(DataUtility.getString(request .getParameter("email")));

       bean.setCourseName(DataUtility.getString(request .getParameter("courseName")));

       bean.setSubjectName(DataUtility.getString(request .getParameter("subjectName")));

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
       log.debug("FacultyListCtl doGet Start");
       
       List list = null;
       
       List nextList = null;
       
       int pageNo = 1;
      
       int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
       
       FacultyBean bean = (FacultyBean) populateBean(request);
      
       String op = DataUtility.getString(request.getParameter("operation"));
       
       String[] ids = request.getParameterValues("ids");
       
       FacultyModel model = new FacultyModel();
       
       try 
         {
	            list = model.search(bean, pageNo, pageSize);
	            
	            nextList= model.search(bean, pageNo+1, pageSize);   
		           
	            request.setAttribute("nextlist", nextList.size());
	            
	            ServletUtility.setList(list, request);
	           
	            if(list == null || list.size() == 0) 
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
     
     log.debug("FacultyListCtl doGet End");
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
		List list;
		List nextList=null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyBean bean = (FacultyBean)populateBean(request);
		FacultyModel model = new FacultyModel();

		String[] ids = (String[]) request.getParameterValues("ids");

		
				if (OP_SEARCH.equalsIgnoreCase(op)) 
				{
					pageNo = 1;
				} 
				else if (OP_NEXT.equalsIgnoreCase(op)) 
				{
					pageNo++;
				} 
				else if (OP_PREVIOUS.equalsIgnoreCase(op)) 
				{
					if(pageNo>1){
						pageNo--;
					}
					else{
					pageNo=1;
				}
				}
				else if (OP_NEW.equalsIgnoreCase(op)) 
				{
					ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
					return ;
				}				
				else if (OP_RESET.equalsIgnoreCase(op) || OP_BACK.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
					return;
				}
				
				else if (OP_DELETE.equalsIgnoreCase(op)) 
				{	
						pageNo =1;
						if(ids !=null && ids.length !=0){
						FacultyBean deletebean = new FacultyBean();
							for (String id : ids) {
								deletebean.setId(DataUtility.getInt(id));
								try {
									model.delete(deletebean);
								} catch (ApplicationException e) {
									e.printStackTrace();
									log.error(e);
									ServletUtility.handleException(e, request, response);
									return;
								}
								ServletUtility.setSuccessMessage("Data Deleted Succesfully", request);
							}
							
						}else{
							ServletUtility.setErrorMessage("Select at least one record", request);
						}
					}	
				try {
					list=model.search(bean, pageNo, pageSize);
					
					  nextList=model.search(bean,pageNo+1,pageSize);
						
						request.setAttribute("nextlist", nextList.size());
						
				   ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					e.printStackTrace();
					ServletUtility.handleException(e, request, response);
					return;
				}
				
				if(list == null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)){
					ServletUtility.setErrorMessage("No Record Found", request);
				}
				
				ServletUtility.setList(list, request);
				ServletUtility.setBean(bean, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.forward(getView(), request, response);
	
				
				 log.debug("UserListCtl doPost End");	
		}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		
		return ORSView.FACULTY_LIST_VIEW;
	}

}
