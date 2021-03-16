<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   <%@page import="in.co.rays.controller.CourseCtl"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>
    
    <%@page import="java.util.LinkedHashMap"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Registration Page</title>
</head>
<body>
     
     <%@ include file="Header.jsp"%>

     <jsp:useBean id="bean" class="in.co.rays.bean.CourseBean" scope="request"></jsp:useBean>
   
     <form action="<%=ORSView.COURSE_CTL%>" method="post">
     
    <center>   
    <%if(bean.getId()>0){ %>
       
            <h1>Update Course</h1>
    <%
    }else{%>
     
            <h1>Add Course</h1>
            
      <%} %>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 
             
            <input type="hidden" name="createdBy"  value="<%=bean.getCreatedBy()%>">
            
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
           
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <td>Name<font style="color: red">*</font></td>
                    <td>
                    <input type="text" name="name" placeholder="Enter Course Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>">
                    </td>
                    <td style="position: fixed;">
                    <font color="red"><%=ServletUtility.getErrorMessage("name",request) %> </font></td>
                </tr>
               
                <tr>
                    <td>Duration<font style="color: red">*</font></td>
             <td>   
	<%
	    LinkedHashMap<String , String > map = new LinkedHashMap<String  ,String >();
		map.put("1 year","1 year");
		map.put("2 year","2 year");
		map.put("3 year","3 year");
		map.put("4 year","4 year");
		map.put("5 year","5 year");
		map.put("6 year","6 year");
		
		String htmlList1 = HTMLUtility.getList("duration",bean.getDuration(), map);
	%>
	<%=htmlList1 %>
	</td>
                    <td style="position: fixed;">    
                   <font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font></td>
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
                 <td colspan="2">
                 <%
                     if (bean.getId() > 0) {
		  	 %>
		  <input type="submit" name="operation" value="<%=CourseCtl.OP_UPDATE%>">  
		  
		  <input type="submit" name="operation" value="<%=CourseCtl.OP_CANCEL%>"> 
		   <%
             }
            else
             {
		   %>
	    
	      <input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">  
		  
		  <input type="submit" name="operation" value="<%=CourseCtl.OP_RESET%>"> 
	    
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