<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   <%@page import="in.co.rays.controller.ForgetPasswordCtl"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget Password Page</title>
</head>
<body>
     <%@ include file="Header.jsp"%>
    
        <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

       
        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean" scope="request"></jsp:useBean>

        <center>
            <h1>Forgot your password?</h1>
           
            <input type="hidden" name="id" value="<%=bean.getId()%>">
           <table> 
               <label>Submit your email address and we'll send you password.</lable> 
          
              <H3>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H3>
            
              <H3>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H3>
     
            
            
               <tr>
               <td>Email Id<font color="red">*</font></td>&nbsp;
               <td>  
                <input type="text" name="login" placeholder="Enter Email ID" value="<%=ServletUtility.getParameter("login", request)%>">&nbsp;
                
                <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>"> &nbsp;
                <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_RESET%>">
                
                <br>
               </td>
               </tr>
               <tr><td></td>
                <td style="position: fixed;">
                <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
                </td>
                </tr>
            </table>
          
        </center>
    </form>
          <br><br>
    <%@ include file="Footer.jsp"%>
    
</body>
</html>