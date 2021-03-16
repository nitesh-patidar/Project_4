<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.controller.ChangePasswordCtl"%>

<%@page import="in.co.rays.util.DataUtility"%>

<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password Page</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">


		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>Change Password</h1>
			<h3>

				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>

			</h3>


			<h2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h2>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">

			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">

			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<td><strong>Old Password</strong><font style="color: red">*</font></td>
					<td><input type="password" name="oldPassword"
						placeholder="Enter Old Password" maxlength="15"
						value=<%=DataUtility.getString(request.getParameter("oldPassword") == null ? ""
					: DataUtility.getString(request.getParameter("oldPassword")))%>>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("oldPassword", request)%></font></td>
				</tr>

				<tr>
					<td><strong>New Password</strong><font style="color: red">*</font></td>
					<td><input type="password" name="newPassword"
						placeholder="Enter New Password" maxlength="15"
						value=<%=DataUtility.getString(request.getParameter("newPassword") == null ? ""
					: DataUtility.getString(request.getParameter("newPassword")))%>>

					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("newPassword", request)%></font></td>
				</tr>

				<tr>
					<td><strong>Confirm Password</strong><font style="color: red">*</font></td>
					<td><input type="password" name="confirmPassword"
						placeholder="Enter Confirm Password" maxlength="15"
						value=<%=DataUtility.getString(request.getParameter("confirmPassword") == null ? ""
					: DataUtility.getString(request.getParameter("confirmPassword")))%>>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>

				<tr>
					<td></td>
					<td colspan="1"><input type="submit" name="operation"
						value="<%=ChangePasswordCtl.OP_SAVE%>"> <input
						type="submit" name="operation"
						value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE%>">
						&nbsp;</td>

				</tr>

			</table>

		</center>
	</form>
	<br>
	<br>
	<%@ include file="Footer.jsp"%>


</body>
</html>