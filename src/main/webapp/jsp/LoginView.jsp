<%@page import="in.co.rays.controller.ORSView"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="in.co.rays.controller.LoginCtl"%>
<%@ page import="in.co.rays.util.DataUtility"%>
<%@ page import="in.co.rays.util.ServletUtility"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
  
    <%@include file="Header.jsp" %>

  <form action="<%=ORSView.LOGIN_CTL%>" method="post"> 
  <jsp:useBean id="bean" class="in.co.rays.bean.UserBean" scope="request"></jsp:useBean>
        
   <center>
  
     <h1>Login</h1>  
     
         <%
			String message = (String) request.getAttribute("message");
			if (message!=null) {
		%>
		<h2><font color="Red"><%=message%></font></h2>
		<%
			}
		%>
     
     <h2> <font color="red"><%=ServletUtility.getErrorMessage(request) %></font></h2>
     
      <h2> <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font></h2>
 
     <input type="hidden" name="id" value="<%=bean.getId()%>">
  
	 <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">   
	 
	 <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
	 
	 <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
	
	 <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                
                <tr>
                    <td>LoginId<font style="color: red">*</font></td>
                    <td>
                    <input type="text" name="login" size=30 placeholder="Enter LoginID"  value="<%=DataUtility.getStringData(bean.getLogin())%>">
                    </td>
                    <td style="position: fixed;">
                    <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                    </td>
                </tr>
                <tr>
                    <td>Password<font style="color: red">*</font></td>
                    <td>
                    <input type="password" name="password" size=30 placeholder="Enter Password" maxlength="15"
                            value="<%=DataUtility.getStringData(bean.getPassword())%>">
                    </td>
                    <td style="position: fixed;">
                    <font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font>
                    
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                    <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_IN %>">  
                    <input type="submit" name="operation" value="<%=LoginCtl.OP_SIGN_UP %>" >
                    </td>
                </tr>
                
                <tr>
                <td>&nbsp;&nbsp;</td>
                <td><a href="<%=ORSView.FORGET_PASSWORD_CTL%>"><b>Forget my password</b></a></td>
                </tr>
            </table>
          
    </form>
     </center> 
  
  
      <br><br>
    <%@ include file="Footer.jsp"%>
 </body>
</html>