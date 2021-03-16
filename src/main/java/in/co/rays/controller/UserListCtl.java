package in.co.rays.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.model.RoleModel;
import in.co.rays.model.UserModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;


// TODO: Auto-generated Javadoc
/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */
@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl
 {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
	/** The log. */
	private static Logger log = Logger.getLogger(UserListCtl.class);
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	protected void preload(HttpServletRequest request)
	     {
	        RoleModel Rolemodel = new RoleModel();
	        UserModel Usermodel = new UserModel();
	        try 
	         {
	            List l = Rolemodel.list();
	            request.setAttribute("roleList", l);
	          
	            List l1 = Usermodel.list();
	            request.setAttribute("loginList", l1);
	           
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
	        UserBean bean = new UserBean();
	        
	        bean.setId(DataUtility.getLong(request.getParameter("loginId")));
	      
	        bean.setRoleId(DataUtility.getLong(request .getParameter("roleId")));

	        bean.setFirstName(DataUtility.getString(request .getParameter("firstName")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        

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
	        log.debug("UserListCtl doGet Start");
	        
	        List list = null;
	        
	        List nextList = null;
	        
	        int pageNo = 1;
	      
	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
	        
	        UserBean bean = (UserBean) populateBean(request);
	        
	        UserModel model = new UserModel();
	    
	        String op = DataUtility.getString(request.getParameter("operation"));
	        
	        String[] ids = request.getParameterValues("ids");
	       
	        
	        try 
	          {
		            list = model.search(bean, pageNo, pageSize);
		            
		            nextList= model.search(bean, pageNo+1, pageSize);   
			           
		            request.setAttribute("nextlist", nextList.size());
		           
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
	      
	      log.debug("UserListCtl doGet End");
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
protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    log.debug("UserListCtl doPost Start");
    
    List list;
    List nextList=null;
    
    int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
    int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
    pageNo = (pageNo == 0) ? 1 : pageNo;
    pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
    
    String op = DataUtility.getString(request.getParameter("operation"));
    UserBean bean = (UserBean) populateBean(request);
    // get the selected checkbox ids array for delete list
    String[] ids = request.getParameterValues("ids");
    UserModel model = new UserModel();
    
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
            ServletUtility.redirect(ORSView.USER_CTL, request, response);
            return;
            }
            else if (OP_RESET.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
                return;
            } else if (OP_DELETE.equalsIgnoreCase(op)) {
            	pageNo = 1;
            	if (ids != null && ids.length > 0) {
                UserBean deletebean = new UserBean();
                for (String id : ids) {
                    deletebean.setId(DataUtility.getInt(id));
                    try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						log.error(e);
			            ServletUtility.handleException(e, request, response);
			            return;
					}
                    
                    ServletUtility.setSuccessMessage("Data Deleted Successfully", request);       
                }
            } else {
                ServletUtility.setErrorMessage("Select at least one record", request);
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
        if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op) ) {
            ServletUtility.setErrorMessage("No record found ", request);
        }
        ServletUtility.setList(list, request);
        ServletUtility.setBean(bean, request);
        ServletUtility.setPageNo(pageNo, request);
        ServletUtility.setPageSize(pageSize, request);
        ServletUtility.forward(getView(), request, response);
        log.debug("UserListCtl doGet End");
        
    }


	
  /* (non-Javadoc)
   * @see in.co.rays.controller.BaseCtl#getView()
   */
  protected String getView() 
	{
		return ORSView.USER_LIST_VIEW;
	}

}
