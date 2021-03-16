<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.bean.FacultyBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


  <%@page import="in.co.rays.controller.FacultyListCtl"%>
  
  <%@page import="in.co.rays.util.ServletUtility"%>
  
  <%@page import="in.co.rays.util.HTMLUtility"%>
  
  <%@page import="java.util.List"%>
  
  <%@page import="java.util.Iterator"%>
  
  <%@page import="java.text.SimpleDateFormat"%>
  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FacultyList View page</title>
</head>
<body>

 <%@include file="Header.jsp"%>

    <center>
        
       <form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">
      
        <jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean" scope="request"></jsp:useBean>

        <%
           
             List l = (List)request.getAttribute("collegeList");
        
             int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
             
         
        %>
        <h1>Faculty List</h1>
        
     
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
           
         <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
         
            <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    
                    Iterator<FacultyBean> it = list.iterator();
                    
                    if(list.size()!=0){
                    	
               %>
        
                   
             <table width="100%">
               <tr>
                    <td align="center">
                    
                 
                   <label style="font-weight: bold;">FirstName :</label> 
                    <input type="text" name="firstName"  placeholder="Enter First_Name"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &emsp; 
                   <label style="font-weight: bold;">College Name:</label> 
                   <%=HTMLUtility.getList("collegeId",ServletUtility.getParameter("collegeId", request), l)%> 
                 
                  &emsp; 
                  <input type="submit" name="operation" value="<%=FacultyListCtl.OP_SEARCH %>">
                  <input type="submit" name="operation" value="<%=FacultyListCtl.OP_RESET %>">
                  </td>
                </tr>
            </table>
            <br>

            <table border="1" width="100%">
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">FirstName</th>
                    <th style="background-color: lightblue">LastName</th>
                    <th style="background-color: lightblue">EmailId</th>
                    <th style="background-color: lightblue">Gender</th>
                    <th style="background-color: lightblue">DOB</th>
                    <th style="background-color: lightblue">Mobile No</th>
                    <th style="background-color: lightblue">CollegeName</th>
                    <th style="background-color: lightblue">CourseName</th>
                    <th style="background-color: lightblue">SubjectName</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>

               
             <%
                    while (it.hasNext()) {
                       FacultyBean fb = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=fb.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=fb.getFirstName()%></td>
                    <td align="center"><%=fb.getLastName()%></td>
                    <td align="center"><%=fb.getEmail()%></td>
                    <td align="center"><%=fb.getGender()%></td>
                    
                              
                    <%
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				                String dob = sdf.format(fb.getDob());
					%>
                    
                    
                    
                    <td align="center"><%=dob%></td>
                    <td align="center"><%=fb.getMobileNo()%></td>
                    <td align="center"><%=fb.getCollegeName()%></td>
                    <td align="center"><%=fb.getCourseName()%></td>
                    <td align="center"><%=fb.getSubjectName()%></td>
                    
                    <td align="center"><a href="FacultyCtl?id=<%=fb.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td ><input type="submit" name="operation"
                        value="<%=FacultyListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;</td>
                    
                    <td><input type="submit" name="operation" value="<%=FacultyListCtl.OP_NEW%>">&nbsp;&emsp;&emsp;&nbsp;</td>
                   
                    <td><input type="submit" name="operation" value="<%=FacultyListCtl.OP_DELETE%>"></td>
                   
                    <td align="right">
                    <input type="submit" name="operation" value="<%=FacultyListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
                </tr>
            </table>
            
             <%}if(list.size() == 0){ %>
             
              <br><input type="submit" name="operation" value="<%=FacultyListCtl.OP_BACK%>"> 
    
             <%} %>
         
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
       
            <br>
            <br>
            <br>
        </form>
    </center>
 </body>
 
 <br><br><br><br>
   <%@include file="Footer.jsp"%> 

</body>
</html>