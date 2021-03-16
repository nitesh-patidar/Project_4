package in.co.rays.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.bean.BaseBean;
import in.co.rays.bean.UserBean;
import in.co.rays.util.DataUtility;
import in.co.rays.util.DataValidator;
import in.co.rays.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 * 
 * @author Proxy
 * @version 1.0 Copyright (c) SunilOS
 */

public abstract class BaseCtl extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Update key constant. */
	public static final String OP_UPDATE = "Update";

	/** Save key constant. */
	public static final String OP_SAVE = "Save";

	/** Cancel key constant. */
	public static final String OP_CANCEL = "Cancel";

	/** Reset key constant. */
	public static final String OP_RESET = "Reset";

	/** Delete key constant. */
	public static final String OP_DELETE = "Delete";

	/** List key constant. */
	public static final String OP_LIST = "List";

	/** Search key constant. */
	public static final String OP_SEARCH = "Search";

	/** View key constant. */
	public static final String OP_VIEW = "View";

	/** Next key constant. */
	public static final String OP_NEXT = "Next";

	/** Previous key constant. */
	public static final String OP_PREVIOUS = "Previous";

	/** New key constant. */
	public static final String OP_NEW = "New";

	/** Go key constant. */
	public static final String OP_GO = "Go";

	/** Back key constant. */
	public static final String OP_BACK = "Back";

	/** Logout key constant. */
	public static final String OP_LOG_OUT = "Logout";

	/** Success message key constant. */
	public static final String MSG_SUCCESS = "success";

	/** Error message key constant. */
	public static final String MSG_ERROR = "error";

	/**
	 * Validates input data entered by User.
	 *
	 * @param request
	 *            the request
	 * @return true, if successful
	 */
	protected boolean validate(HttpServletRequest request) {
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request
	 *            the request
	 */
	protected void preload(HttpServletRequest request) {

	}

	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request
	 *            the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request) {
		return null;
	}

	/**
	 * Populates Generic attributes in DTO.
	 *
	 * @param dto
	 *            the dto
	 * @param request
	 *            the request
	 * @return object of the bean
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {
		String createdBy = request.getParameter("createdBy");

		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			createdBy = "root";
			modifiedBy = "root";
		} else {
			modifiedBy = userbean.getLogin();

			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}
		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.geTimestamp(cdt));

		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;

	}

	/**
	 * Override service method to perform generic workflow.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Load the preloaded data required to display at HTML form */

		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		/*
		 * Check validation, If fail then send back to page with error messages
		 */
		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
			if (!validate(request)) {
				BaseBean bean = (BaseBean) populateBean(request);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
		}
		super.service(request, response);

	}

	/**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	protected abstract String getView();
}
