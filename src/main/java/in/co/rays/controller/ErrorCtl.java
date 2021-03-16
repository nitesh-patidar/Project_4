package in.co.rays.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ErrorCtl.
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) SunilOS
 */

@WebServlet (name= "ErrorCtl" , urlPatterns ={"/ErrorCtl"})
public class ErrorCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(ErrorCtl.class);
	 
	  /**
  	 * Contains Display logics.
  	 *
  	 * @param request the request
  	 * @param response the response
  	 * @throws ServletException the servlet exception
  	 * @throws IOException Signals that an I/O exception has occurred.
  	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	    log.debug("Do get Method of Error Ctl started");
	
		ServletUtility.forward(getView(), request, response);
		
		log.debug("Do get Method of Error Ctl End");
		
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
	
	    log.debug("Do Post Method of Error Ctl started");
		
	    ServletUtility.forward(getView(), request, response);
		
	    log.debug("Do Post Method of Error Ctl End");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.ERROR_VIEW;
	}

}