<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="in.co.rays.bean.RoleBean"%>
<%@ page import="in.co.rays.bean.UserBean"%>
<%@ page import="in.co.rays.controller.ORSView"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Page</title>
</head>
<body>
   <%@ include file="Header.jsp"%>
  
  <form action="<%=ORSView.WELCOME_CTL%>">
  
  <br><br>
  <h1 align="center">
  <font size="10px" color="red">Welcome to ORS</font></h1>
   <% 
      UserBean bean = (UserBean)session.getAttribute("user");
      if(bean != null)
      {
    	  if(bean.getRoleId()==RoleBean.STUDENT)
    	  {
    		  %> 
    	 
    	 <h2 align="center"><a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your Marksheet</a></h2>
    	 
    	 <% 
    	  }
      }
   %>
   </form>
  <%@ include file="Footer.jsp" %>
    <br><br> 
</body>
</html>