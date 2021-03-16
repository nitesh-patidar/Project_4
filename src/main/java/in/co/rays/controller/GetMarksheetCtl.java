package in.co.rays.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.PropertyReader;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Get Marksheet functionality Controller. Performs operation for Get Marksheet
 * 
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl"})
public class GetMarksheetCtl extends BaseCtl
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
	/** The log. */
	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	protected boolean validate(HttpServletRequest request) 
	{
		log.debug("GetMarksheetCtl Method validate Started");
		boolean pass = true ;
		if (DataValidator.isNull(request.getParameter("rollNo")))
		{
		  request.setAttribute("rollNo",PropertyReader.getValue("error.require","Roll Number"));	
		  pass = false;
		
		}
		else if(!DataValidator.isRollNo(request.getParameter("rollNo")))
		{
			request.setAttribute("rollNo","RollNo should be in the formate of 0000XX0000");
			pass = false ;
		}
	log.debug("GetMarkshhetCtl Method validate Ended");	
		
 	 return	pass ;
	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	protected BaseBean populateBean(HttpServletRequest request) 
	{
        log.debug("GetMarksheetCtl Method populatebean Started");
        MarksheetBean bean = new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		
		log.debug("GetMarksheetCtl Method populatebean Ended");
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
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		
		log.debug("GetMarksheetCtl Method dopost Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		MarksheetModel model = new MarksheetModel();
		
		MarksheetBean bean = (MarksheetBean) populateBean(request);
	    
		long id = DataUtility.getLong(request.getParameter("id"));
		
		if(OP_GO.equalsIgnoreCase(op)) 
		{
			try 
			  {
				bean = model.findByRollNo(bean.getRollNo());
			  
				if(bean!=null) 
			    {
					ServletUtility.setBean(bean, request);
				}
			    else
			    {
			    	ServletUtility.setErrorMessage(" RollNo Does Not Exist ", request);
			    }
			  } 
			catch (Exception e) 
			{
				log.error(e);
				ServletUtility.handleException(e, request, response);
			    return ;
			}
		
			 ServletUtility.forward(getView(), request, response);
		}
		else if(OP_RESET.equalsIgnoreCase(op))
		{
		  ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
		  return ; 
		}
		  
			log.debug("MarksheetCtl Method doPost Ended");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() 
	{
			return ORSView.GET_MARKSHEET_VIEW;
	}

}
