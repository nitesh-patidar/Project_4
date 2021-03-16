<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration Page</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


  
  <script>
  $( function() {
	   $( "#datepicker" ).datepicker({ 
   	      changeMonth :true,
		  changeYear :true,
		  yearRange:'-40 : -18 ',
		  dateFormat:'dd-mm-yy'
		
		 }) ;
  
 } );
 
 
  </script>

</head>
<body>

	<%@ include file="Header.jsp"%>

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>User Registration</h1>

			<H3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H3>
			<H3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H3>

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
					<td><strong>First Name</strong><font style="color: red">*</font></td>
					<td><input type="text" name="firstName"
						placeholder="First_Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				
				<tr>
					<td><strong>Last Name</strong><font style="color: red">*</font></td>
					<td><input type="text" name="lastName" placeholder="Last_Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
			
				<tr>
					<td><strong>LoginId</strong><font style="color: red">*</font></td>
					<td><input type="text" name="login"
						placeholder="Must be Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
					</td>
				</tr>
              
				<tr>
					<td><strong>Password</strong><font style="color: red">*</font></td>
					<td><input type="password" name="password"
						placeholder=" Password" maxlength="15"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
           
				<tr>
					<td><strong>Confirm Password</strong><font style="color: red">*</font></td>
					<td><input type="password" name="confirmPassword"
						placeholder="Confirm Password" maxlength="15"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
					</td>
				</tr>
              
				<tr>
					<td><strong>Gender</strong><font style="color: red">*</font></td>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("Male", "Male");
							map.put("Female", "Female");

							String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
						%> <%=htmlList%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>

					</td>
				</tr>
			
				<tr>
					<td><strong>Mobile No.</strong><font color="red">*</font></td>
					<td><input type="text" name="mobileNo"
						placeholder="Enter Mobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
						maxlength="10"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>
             
				<tr>
					<td><strong>Date Of Birth</strong><font color="red">*</font></td>
					<td><input type="text" id="datepicker" name="dob"
						placeholder="dd/mm/yyyy" readonly="readonly"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>
			
				<tr>
					<th></th>
					<td>
				<input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
					 
			    <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</center>
	</form>

	<%@ include file="Footer.jsp"%>
 <br><br>

</body>
</html>