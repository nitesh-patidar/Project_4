<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.controller.SubjectCtl"%>

<%@page import="java.util.HashMap"%>

<%@page import="java.util.List"%>

<%@page import="in.co.rays.bean.SubjectBean"%>

<%@page import="in.co.rays.util.DataUtility"%>

<%@page import="in.co.rays.util.HTMLUtility"%>

<%@page import="in.co.rays.util.ServletUtility"%>
 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject Registration Page</title>
</head>
<body>
   
     <%@include file="Header.jsp"%>
     
     <form action="<%=ORSView.SUBJECT_CTL%>" method="post">
       
    <jsp:useBean id="bean" class="in.co.rays.bean.SubjectBean" scope="request"></jsp:useBean>
           
         <%
            List l = (List)request.getAttribute("courseList");
        %>
        

     <center>  
    <%if(bean.getId()>0){ %>
        
            <h1>Update Subject</h1>
    <%
    }else{%>
   
            <h1>Add Subject</h1>
            
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
                 <td>Course Name<font style="color:red">*</font></td>
                 <td>
                 
                  <%=HTMLUtility.getList("courseId",String.valueOf(bean.getCourseId()),l) %>
                </td>
                <td style="position: fixed;"> 
                <font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
                 </tr>
                <tr>
                    <td>Subject Name<font style="color:red">*</font></td>
                    <td>
                    <input type="text" name="name"  placeholder="Enter Subject Name" value="<%=DataUtility.getStringData(bean.getName())%>">
                    </td>
                     <td style="position: fixed;"> 
                    <font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                
               <tr>
                    <td>Description<font style="color: red">*</font></td>
                   <td>
                   <input type="text" name="description" placeholder="Enter some description"
							style="height: 70px; font-size: 10pt; width: 169px"value="<%=DataUtility.getStringData(bean.getDescription())%>">
                     </td>
                     <td style="position: fixed;"> 
                     <font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font>
                     </td>
                </tr>
                
                
                 <tr>
                   <td></td>
                    <td>
                    <%
			     if (bean.getId() > 0) {
			 %>
		  <input type="submit" name="operation" value="<%=SubjectCtl.OP_UPDATE%>">  
		  
		  <input type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>"> 
		   <%
			   }
			  else {
			%> 
	    
	      <input type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE%>">  
		  
		  <input type="submit" name="operation" value="<%=SubjectCtl.OP_RESET%>"> 
	    
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