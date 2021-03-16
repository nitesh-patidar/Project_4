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
import in.co.rays.bean.CollegeBean;

import in.co.rays.exception.ApplicationException;
import in.co.rays.model.CollegeModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * College List functionality Controller. Performs operation for list, search
 * and delete operations of College
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="CollegeListCtl",urlPatterns={"/ctl/CollegeListCtl"})
public class CollegeListCtl extends BaseCtl  
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
	
    /** The log. */
    private static Logger log = Logger.getLogger(CollegeListCtl.class);
    
 /* (non-Javadoc)
  * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
  */
 @Override
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
 @Override     
protected BaseBean populateBean(HttpServletRequest request)
    {
		CollegeBean bean = new CollegeBean();
		
		bean.setId(DataUtility.getLong(request.getParameter("collegeId")));    	
	       
	    bean.setCity(DataUtility.getString(request.getParameter("city")));
    	
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
protected void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException 
    {
	  int pageNo = 1 ;
	  
	  int pageSize =DataUtility.getInt(PropertyReader.getValue("page.size"));
      
	  CollegeBean bean = (CollegeBean) populateBean(request);
    
	  CollegeModel model = new CollegeModel();
      
	  String op = DataUtility.getString(request.getParameter("operation"));
      
      String[] ids = request.getParameterValues("ids");
     
	  List list = null;
	  
	  List nextList = null;
       try 
         {
		   
    	   list = model.search(bean, pageNo, pageSize);
    	   
    	   nextList= model.search(bean, pageNo+1, pageSize);   
           
	       request.setAttribute("nextlist", nextList.size());
		   
		   if (list==null || list.size()==0) 
	       {
			ServletUtility.setErrorMessage("No Record Found", request);
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
	     return ;
       }
      
    
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
protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

    log.debug("CollegeListCtl doPost Start");

    List list = null;
    
    List  nextList=null;

    int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
    int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

    pageNo = (pageNo == 0) ? 1 : pageNo;
    pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

   
    String op = DataUtility.getString(request.getParameter("operation"));
    
    String [] ids = request.getParameterValues("ids");
    CollegeModel model = new CollegeModel();
    CollegeBean bean = (CollegeBean) populateBean(request);

            if (OP_SEARCH.equalsIgnoreCase(op)) {
                pageNo = 1;
            } 
            else if (OP_NEXT.equalsIgnoreCase(op)) {
                pageNo++;
            } 
            else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                pageNo--;
            }
            else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
			return;
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
			return;
		}  
        else if (OP_DELETE.equalsIgnoreCase(op)) {
            pageNo = 1;
            if (ids != null && ids.length > 0) {
               CollegeBean deletebean = new CollegeBean();
           // 	UserBean deletebean = new UserBean();
                for (String id : ids) {
                    deletebean.setId(DataUtility.getInt(id));
                    try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					}ServletUtility.setSuccessMessage("Data Successfully Deleted", request);
                }
            } 
            else {
                ServletUtility.setErrorMessage(
                        "Select at least one record", request);
            }
        }
        try {
			
        	list = model.search(bean, pageNo, pageSize);
			
        	nextList=model.search(bean,pageNo+1,pageSize);
			
        	request.setAttribute("nextlist", nextList.size());
			
		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
     //   ServletUtility.setList(list, request);
        
        if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
            ServletUtility.setErrorMessage("No record found ", request);
        }
        ServletUtility.setList(list, request);
        ServletUtility.setBean(bean, request);
        ServletUtility.setPageNo(pageNo, request);
        ServletUtility.setPageSize(pageSize, request);
        ServletUtility.forward(getView(), request, response);
        log.debug("CollegeListCtl doPost End");
}
    
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() 
	{
	 return ORSView.COLLEGE_LIST_VIEW;
	}

}
