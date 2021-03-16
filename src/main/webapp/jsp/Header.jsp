<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="in.co.rays.bean.RoleBean"%>

<%@ page import="in.co.rays.controller.LoginCtl"%>

<%@ page import="in.co.rays.bean.UserBean"%>

<%@ page import="in.co.rays.controller.ORSView"%>

<%
	UserBean userBean = (UserBean) session.getAttribute("user");

	boolean userLoggedIn = userBean != null;

	String welcomeMsg = "Hi,";

	if (userLoggedIn) {
		
		String role = (String) session.getAttribute("role");
		
		welcomeMsg += userBean.getFirstName() + "(" + role + ")";
		
	} else {
		
		welcomeMsg += "Guest";
	}
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>

  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

 <link rel="stylesheet" href="/resources/demos/style.css">

<script>
	$(document).ready(function() {
		$('#select_all').on('click', function() {
			if (this.checked) {
				$('.checkbox').each(function() {
					this.checked = true;
				});
			} else {
				$('.checkbox').each(function() {
					this.checked = false;
				});
			}
		});

		$('.checkbox').on('click', function() {
			if ($('.checkbox:checked').length == $('.checkbox').length) {
				$('#select_all').prop('checked', true);
			} else {
				$('#select_all').prop('checked', false);
			}
		});
	});

	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : '-40:-18',
			dateFormat : 'dd-mm-yy',

		});

	});
</script>



</head>
<body>

	<table width="100%" border="0">
		<tr>
			<td width="90%">
			
			<a href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b><b>|</b></a> 
			<%
 	if (userLoggedIn) {
 %> 
			<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>

				<%
					} else {
				%> <a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a> <%
 	}
 %></td>

			<td rowspan="2">
				<h1 style="margin-top: 0">
					<img src="<%=ORSView.APP_CONTEXT%>/img/customLogo.png" width="200" height="70">
				</h1>
			</td>

		</tr>

		<tr>
			<td>
				<h3>
					<%=welcomeMsg%>
				</h3>
			</td>
		</tr>


		<%
			if (userLoggedIn) {
		%>

		<tr>
			<td colspan="2">
			<a href="<%=ORSView.GET_MARKSHEET_CTL%>"><b>GetMarksheet
						| </b></a> 
			<a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>"><b>Marksheet
						Merit List | </b></a> 
			<a href="<%=ORSView.MY_PROFILE_CTL%>"><b>MyProfile
						| </b></a> 
			<a href="<%=ORSView.CHANGE_PASSWORD_CTL%>"><b>Change
						Password | </b></a> 
 <%
 	if (userBean.getRoleId() == RoleBean.ADMIN) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet | </b></a> 
	<a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet List | </b></a> 
	<a href="<%=ORSView.USER_CTL%>"><b>Add User | </b></a> 
	<a href="<%=ORSView.USER_LIST_CTL%>"><b>User List | </b></a> 
	<a href="<%=ORSView.COLLEGE_CTL%>"><b>Add College | </b></a> 
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College List | </b></a> 
	<a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student | </b></a> 
	<a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List | </b></a> 
				<a href="<%=ORSView.COURSE_CTL%>"><b>Add Course | </b></a> 
				<a href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List | </b></a> 
				<a href="<%=ORSView.SUBJECT_CTL%>"><b>Add Subject | </b></a> <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject List | </b></a> <a
				href="<%=ORSView.FACULTY_CTL%>"><b>Add Faculty | </b></a> <a
				href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty List | </b></a> <a
				href="<%=ORSView.TIMETABLE_CTL%>"><b>Add Timetable | </b></a> <a
				href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>Timetable List | </b></a>
				<a href="<%=ORSView.ROLE_CTL%>"><b>Add Role | </b></a> <a
				href="<%=ORSView.ROLE_LIST_CTL%>"><b>Role List | </b></a> <a
				href="<%=ORSView.JAVA_DOC_VIEW%>" target="_blank"><b>Java
						Doc </b></a> <%
 	}
 %> <%-- <%
 	if (userBean.getRoleId() == RoleBean.STUDENT) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College
						List</b></a> | <a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student
						List</b></a> | <a href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course
						List</b></a> | <a href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject
						List</b></a> | <a href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty
						List</b></a> | <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>TimeTable
						List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.KIOSK) {
 %> <a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College
						List</b></a> | <a href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>TimeTable
						List</b></a> | <a href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course
						List</b></a> | <%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.FACULTY) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add Marksheet</b></a>
				| <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
						List</b></a> | <a href="<%=ORSView.COLLEGE_LIST_CTL%>"><b>College
						List</b></a> | <a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> |
				<a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> | <a
				href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a> | <a
				href="<%=ORSView.SUBJECT_CTL%>"><b>Add Subject</b></a> | <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>"><b>Subject List</b></a> | <a
				href="<%=ORSView.TIMETABLE_CTL%>"><b>Add TimeTable</b></a> | <a
				href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>TimeTable List</b></a> |
				<%
 	}
 %> <%
 	if (userBean.getRoleId() == RoleBean.COLLEGE) {
 %> <a href="<%=ORSView.MARKSHEET_CTL%>"><b>Add
						Marksheet</b></a> | <a href="<%=ORSView.MARKSHEET_LIST_CTL%>"><b>Marksheet
						List</b></a> | <a href="<%=ORSView.STUDENT_CTL%>"><b>Add Student</b></a> |
				<a href="<%=ORSView.STUDENT_LIST_CTL%>"><b>Student List</b></a> | <a
				href="<%=ORSView.FACULTY_LIST_CTL%>"><b>Faculty List</b></a> | <a
				href="<%=ORSView.TIMETABLE_LIST_CTL%>"><b>TimeTable List</b></a> |
				<a href="<%=ORSView.COURSE_LIST_CTL%>"><b>Course List</b></a> | <%
 	}
 %> --%></td>
		</tr>
		<%
			}
		%>

	</table>
	<hr width="100%">

</body>
</html>