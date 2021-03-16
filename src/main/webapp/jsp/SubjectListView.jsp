<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
   <%@page import="in.co.rays.controller.SubjectListCtl"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
     <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>
   
   <%@page import="in.co.rays.bean.SubjectBean"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>

 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subject List View Page</title>
</head>
<body>
   
   
        <%@include file="Header.jsp"%>
        
          <form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">
       
         <jsp:useBean id="bean" class="in.co.rays.bean.CourseBean" scope="request"></jsp:useBean>
      
          <%
             List l = (List)request.getAttribute("courseList");
          
            int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
         %>
    <center>
        <h1>Subject List</h1>
        
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3> <br>
       
        <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
          <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
         
                    Iterator<SubjectBean> it = list.iterator();
     
                    if(list.size()!=0){         
           %>
        
        <table width="100%">
                <tr>
                    <td align="center">
                    <label style="font-weight: bold;">Subject Name :</label> 
                    <input type="text" name="name" placeholder="Enter Subject Name"
                        value="<%=ServletUtility.getParameter("name", request)%>">
                    
                     &emsp;
                    <label style="font-weight: bold;">Course Name :</label> 
                     <%=HTMLUtility.getList("courseId", ServletUtility.getParameter("courseId", request), l) %>
                    <input type="submit" name="operation" value="<%=SubjectListCtl.OP_SEARCH %>">
                    <input type="submit" name="operation" value="<%=SubjectListCtl.OP_RESET %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">Subject Name</th>
                    <th style="background-color: lightblue">Course</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>
               
              <%
                    while (it.hasNext()) {

                        SubjectBean sb = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=sb.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=sb.getName()%></td>
                    <td align="center"><%=sb.getCourseName()%></td>
                    <td align="center"><a href="SubjectCtl?id=<%=sb.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                 <td><input type="submit" name="operation" value="<%=SubjectListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;</td>
                   
                 <td><input type="submit" name="operation" value="<%=SubjectListCtl.OP_NEW%>">&emsp;&nbsp;</td>
                        
                 <td><input type="submit" name="operation" value="<%=SubjectListCtl.OP_DELETE%>"></td>
                        
                 <td align="right"><input type="submit" name="operation" value="<%=SubjectListCtl.OP_NEXT%>"<%=(list.size()<pageSize  || next==0)?"disabled":"" %>></td>
               </tr>
            </table>
            
              <%}if(list.size() == 0){ %>
              
              <br><input type="submit" name="operation" value="<%=SubjectListCtl.OP_BACK%>"> 
      
             <% } %>
           
      
            <input type="hidden" name="pageNo" value="<%=pageNo%>"><input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                <br><br><br>
                
              
          </center>

        </form>

<br><br>
     <%@ include file="Footer.jsp"%>

</body>
</html>