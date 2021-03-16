<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   <%@page import="in.co.rays.controller.CourseListCtl"%>
   
   <%@page import="in.co.rays.controller.BaseCtl"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>
   
    <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.bean.CourseBean"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>
 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CourseList View Page</title>
</head>
<body>
 <jsp:useBean id="cbean" class="in.co.rays.bean.CourseBean" scope="request" ></jsp:useBean>
           <%@include file="Header.jsp"%>
           
          <%  
              List l= (List)ServletUtility.getList(request); 
          
		      List<CourseBean> courselist = (List<CourseBean>)request.getAttribute("courseList");
		      
		      int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
	             
	      %>
  

    <center>
      <h1>Course List</h1>
      
        
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
           
         <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
    
      <form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
      
         <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    
                    Iterator<CourseBean> it = list.iterator();
                    
                    if(list.size()!=0){
                    
            %>        
          
            <table width="100%">
                <tr>
                    <td align="center">
                    <label style="font-weight: bold;">Course Name :</label> 
                    <%=HTMLUtility.getList("courseId",ServletUtility.getParameter("courseId", request), courselist) %>
                   <font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font>&nbsp; 
                     
                     <input type="submit" name="operation" value="<%=CourseListCtl.OP_SEARCH %>">
                      <input type="submit" name="operation" value="<%=CourseListCtl.OP_RESET %>">
                    </td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">Name</th>
                    <th style="background-color: lightblue">Duration</th>
                    <th style="background-color: lightblue">Descriptiop</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>
               
             <%
                    while (it.hasNext()) {
                        CourseBean bean = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getDuration()%></td>
                    <td align="center"><%=bean.getDescription()%></td>
                    <td align="center"><a href="CourseCtl?id=<%=bean.getId()%>">Edit</a></td>
                  
              </tr>
                <%
                  }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=CourseListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;</td>
                   
                    <td><input type="submit" name="operation"
                        value="<%=CourseListCtl.OP_NEW%>">&nbsp;&emsp;</td>
                   
                    <td><input type="submit"
                        name="operation" value="<%=CourseListCtl.OP_DELETE%>"></td>
                   
                    <td align="right"><input type="submit" name="operation"
                        value="<%=CourseListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
                </tr>
            </table>
              <%}if(list.size() == 0){ %>
              
               <br><input type="submit" name="operation" value="<%=CourseListCtl.OP_BACK%>"> 
    
                <% } %>
            
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
            <br><br><br>
       
        </form>
    </center>
    
    <br><br>
    <%@include file="Footer.jsp"%>
</body>
</html>