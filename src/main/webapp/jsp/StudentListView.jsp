
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   <%@page import="in.co.rays.controller.StudentListCtl"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="java.text.SimpleDateFormat"%>
  
   <%@page import="in.co.rays.bean.StudentBean"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>StudentList View Page</title>
</head>
<body>
        
         <%
            List l = (List)request.getAttribute("collegeList");
          
            int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
         %>
        

        <%@include file="Header.jsp"%>
        
          <form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">
       
         <jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean" scope="request"></jsp:useBean>
      
         
    <center>
        <h1>Student List</h1>
        
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
       
          <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
          
              <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                   
                    Iterator<StudentBean> it = list.iterator();
                    
                    if(list.size()!=0){     
              %>
          
        
        <table width="100%">
                <tr>
                    <td align="center">
                    <label style="font-weight: bold;">First Name :</label> 
                    <input type="text" name="firstName" placeholder="Enter First_Name"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
                    
                    <label  style="font-weight: bold;">Last Name :</label> 
                    <input type="text" name="lastName" placeholder="Enter Last_Name"
                        value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
                        
                    <label  style="font-weight: bold;">College Name :</label> 
                     <%=HTMLUtility.getList("collegeId",ServletUtility.getParameter("collegeId", request), l) %>&nbsp;
                    
                    <input type="submit" name="operation" value="<%=StudentListCtl.OP_SEARCH %>">
                    
                    <input type="submit" name="operation" value="<%=StudentListCtl.OP_RESET %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">College</th>
                    <th style="background-color: lightblue">First Name</th>
                    <th style="background-color: lightblue">Last Name</th>
                    <th style="background-color: lightblue">Date Of Birth</th>
                    <th style="background-color: lightblue">Mobile No.</th>
                    <th style="background-color: lightblue">Email ID</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>
               
            <%
                    while (it.hasNext()) {

                        StudentBean sb = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=sb.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=sb.getCollegeName()%></td>
                    <td align="center"><%=sb.getFirstName()%></td>
                    <td align="center"><%=sb.getLastName()%></td>
                    
                         
                    <%
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				                String dob = sdf.format(sb.getDob());
					%>
                    
                    
                    <td align="center"><%=dob%></td>
                    <td align="center"><%=sb.getMobileNo()%></td>
                    <td align="center"><%=sb.getEmail()%></td>
                    <td align="center"><a href="StudentCtl?id=<%=sb.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                 <td><input type="submit" name="operation" value="<%=StudentListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;&nbsp;</td>
                   
                 <td><input type="submit" name="operation" value="<%=StudentListCtl.OP_NEW%>">&emsp;&emsp;&emsp;</td>
                        
                 <td><input type="submit" name="operation" value="<%=StudentListCtl.OP_DELETE%>"></td>
                        
                 <td align="right"><input type="submit" name="operation" value="<%=StudentListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
               </tr>
            </table>
            
             <%}if(list.size() == 0){ %>
             
                    <br><input type="submit" name="operation" value="<%=StudentListCtl.OP_BACK%>"> 
                    
               <% } %>
            
            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
                
        
       

        </center>
        
        </form>
   
   <br><br> 
     <%@ include file="Footer.jsp"%>

</body>
</html>