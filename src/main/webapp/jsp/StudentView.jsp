<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.controller.StudentCtl"%>

<%@page import="java.util.HashMap"%>

<%@page import="java.util.List"%>

<%@page import="in.co.rays.bean.StudentBean"%>

<%@page import="in.co.rays.util.DataUtility"%>

<%@page import="in.co.rays.util.HTMLUtility"%>

<%@page import="in.co.rays.util.ServletUtility"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Registration Page</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <script>
  $( function() {
	  
	  var date = new Date();
	  var first = date.getFullYear() - 18;
	  var last = date.getFullYear() - 40;
	
	$( "#datepicker" ).datepicker({ 
       	  changeMonth :true,
		  changeYear :true,
		  yearRange:last + ':' + first,
		  dateFormat:'dd-mm-yy',
		
		 
		  
		 }) ;
  
 } );
 
 
  </script>

</head>
<body>
     
      <%@include file="Header.jsp"%>
     
     <form action="<%=ORSView.STUDENT_CTL%>" method="post">
       
    <jsp:useBean id="bean" class="in.co.rays.bean.StudentBean" scope="request"></jsp:useBean>
           
         <%
            List l = (List)request.getAttribute("collegeList");
        %>
        

     <center>    
    <% if(bean.getId()>0){ %>
      
            <h1>Update Student</h1>
    <%
    }else{%>
     
            <h1>Add Student</h1>
            
      <%} %>
            <h2>
               
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%> </font>
               
            </h2>
           
            <h2>
                
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
                
            </h2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            
            <table>
                <tr>   
                 <td>College Name<font style="color:red">*</font></td>
                 <td>
                 
                  <%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()),l) %>
                </td>
                <td style="position: fixed;"> 
                <font color="red"> <%=ServletUtility.getErrorMessage("collegeId", request)%></font></td>
                 </tr>
                <tr>
                    <td>First Name<font style="color:red">*</font></td>
                    <td>
                    <input type="text" name="firstName"  placeholder="Enter First Name" 
                                           value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                    </td>
                     <td style="position: fixed;"> 
                    <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                
                <tr>
                    <td>Last Name<font style="color:red">*</font></td>
                    <td>
                      <input type="text" name="lastName"  placeholder="Enter Last Name" value="<%=DataUtility.getStringData(bean.getLastName())%>">
                      </td>
                      <td style="position: fixed;"> 
                      <font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                    </td>
                </tr>
                
                <tr>
                    <td>Email<font style="color:red">*</font></td>
                    <td>
                    <input type="text" name="email"  placeholder="Enter Email Id"  value="<%=DataUtility.getStringData(bean.getEmail())%>" <%-- <%=(bean.getId() > 0) ? "readonly" : ""%> --%>>
                     </td>
                      <td style="position: fixed;">   
                    <font color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font>
                     </td>
                </tr>
                
                <tr>
                    <td>MobileNo<font style="color:red">*</font></td>
                    <td>
                    <input type="text" name="mobileNo"  placeholder="Enter Mobile No" 
                            maxlength="10" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                    </td>
                    <td style="position: fixed;">  
                    <font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>
                
                <tr>
                    <td>Date Of Birth<font style="color:red">*</font></td>
                    <td>
                    <input type="text" id="datepicker"  placeholder="Enter DOB" name="dob" readonly="readonly" value="<%=DataUtility.getDateString(bean.getDob())%>">
                    </td>
                     <td style="position: fixed;">  
                     <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
                 <tr>
                   <td></td>
                    <td>
                    <%
			     if (bean.getId() > 0) {
			 %>
		  <input type="submit" name="operation" value="<%=StudentCtl.OP_UPDATE%>">  
		  
		  <input type="submit" name="operation" value="<%=StudentCtl.OP_CANCEL%>"> 
		   <%
			   }
			  else {
			%> 
	    
	      <input type="submit" name="operation" value="<%=StudentCtl.OP_SAVE%>">  
		  
		  <input type="submit" name="operation" value="<%=StudentCtl.OP_RESET%>"> 
	    
	        <%
			   }
			%> 
                    </td>
                </tr>
            </table>
        </center>
    </form>
   <br><br>
    <%@ include file="Footer.jsp"%>
           
   
</body>
</html>