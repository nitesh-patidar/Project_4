<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <%@page import="in.co.rays.controller.CollegeListCtl"%>
  
  <%@page import="in.co.rays.util.ServletUtility"%>
  
  <%@page import="in.co.rays.util.HTMLUtility"%>

  <%@page import="in.co.rays.bean.CollegeBean"%>
  
  <%@page import="java.util.List"%>
  
  <%@page import="java.util.Iterator"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CollegeList View Page</title>
</head>
<body>
    
       <%@include file="Header.jsp"%>
    <center>
        
      <form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">
         
      <jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean" scope="request"></jsp:useBean>
      
      <%
          List l = (List)request.getAttribute("collegeList");
        
          int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
      %>
           
         <h1>College List</h1>
        
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
           
          <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
             <% 
                  int pageNo = ServletUtility.getPageNo(request);
                  int pageSize = ServletUtility.getPageSize(request);
                  int index = ((pageNo - 1) * pageSize) + 1;

                  List list = ServletUtility.getList(request);
                  
                  Iterator<CollegeBean> it = list.iterator();
           
                  if(list.size() !=0){
                  
           %>
        
        
          <table width="100%">
               
                <tr>
                    <td align="center">
                    
                    <label style="font-weight: bold;">College Name :</label> 
                    <%=HTMLUtility.getList("collegeId",ServletUtility.getParameter("collegeId", request), l) %>
                    &emsp;
                    <label style="font-weight: bold;">City :</label> 
                    <input type="text" name="city"  placeholder="Enter City"value="<%=ServletUtility.getParameter("city", request)%>">
                    &emsp;
                    <input type="submit" name="operation" value="<%=CollegeListCtl.OP_SEARCH%>">
                     <input type="submit" name="operation" value="<%=CollegeListCtl.OP_RESET %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">Name</th>
                    <th style="background-color: lightblue">Address</th>
                    <th style="background-color: lightblue">State</th>
                    <th style="background-color: lightblue">City</th>
                    <th style="background-color: lightblue">PhoneNo</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>
               
              <%
                    while (it.hasNext()) 
                      {

                        bean = it.next();
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getAddress()%></td>
                    <td align="center"><%=bean.getState()%></td>
                    <td align="center"><%=bean.getCity()%></td>
                    <td align="center"><%=bean.getPhoneNo()%></td>
                    <td align="center"><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;&emsp;</td>
                    
                     <td><input type="submit" name="operation" value="<%=CollegeListCtl.OP_NEW%>">&emsp;&emsp;&nbsp;&emsp;</td>
                       
                     <td><input type="submit" name="operation" value="<%=CollegeListCtl.OP_DELETE%>"></td>
                    
                    <td align="right"><input type="submit" name="operation"
                        value="<%=CollegeListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
                </tr>
            </table>
              <%}if(list.size() == 0){ %>
              <br><input type="submit" name="operation" value="<%=CollegeListCtl.OP_BACK%>"> 
             <%} %>
             
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
            <br><br><br>
           
        </form>
    </center>
    <br><br>
 <%@ include file="Footer.jsp"%>
</body>
</html>