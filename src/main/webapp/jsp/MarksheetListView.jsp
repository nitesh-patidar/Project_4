<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
  <%@page import="in.co.rays.controller.MarksheetListCtl"%>

  <%@page import="in.co.rays.controller.BaseCtl"%>

  <%@page import="in.co.rays.util.ServletUtility"%>
  
  <%@page import="in.co.rays.util.HTMLUtility"%>
  
  <%@page import="in.co.rays.bean.MarksheetBean"%>
  
  <%@page import="java.util.List"%>
  
  <%@page import="java.util.Iterator"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet List View Page</title>
</head>
<body>
     
     <%@include file="Header.jsp"%>
    <center>
     <form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
      
        <jsp:useBean id="bean" class="in.co.rays.bean.StudentBean" scope="request"></jsp:useBean>
      
        
        <%
            List l = (List)request.getAttribute("studentList");
        
            int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
            
        %>
        
       <h1>Marksheet List</h1>
       
     
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
        
         <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
          <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                 
                    Iterator<MarksheetBean> it = list.iterator();
                    
                    if(list.size()!=0){
        %>
        
        <table width="100%">
             <tr>
                    <td align="center">
                    <label style="font-weight: bold;"> RollNo :</label> 
                    <input type="text" name="rollNo" placeholder="Enter RollNo"
                        value="<%=ServletUtility.getParameter("rollNo", request)%>">  &emsp;
                       
                    <label style="font-weight: bold;">Name :</label> 
                   
                   <%=HTMLUtility.getList("studentId",ServletUtility.getParameter("studentId", request) ,l)%>
                    &emsp;
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH %>">
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_RESET %>">
                   </td>
                </tr>
                 
            </table>
            <br>
            <table border="1" width="100%">
                <tr style="background-color: lightblue;">
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No.</th>
                    <th>RollNo</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Total</th>
                    <th>Percentage</th>
                    <th>Result</th>
                    <th>Edit</th>
                </tr>
               
              <%
                    while (it.hasNext()) {

                        MarksheetBean b = it.next();
                        int phy = b.getPhysics();
                        int chem = b.getChemistry();
                        int math = b.getMaths();
                        
                        float percent =(b.getPhysics()+b.getPhysics()+b.getMaths())/3 ;
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=b.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=b.getRollNo()%></td>
                    <td align="center"><%=b.getName()%></td>
                    <td align="center"><%=b.getPhysics()%></td>
                    <td align="center"><%=b.getChemistry()%></td>
                    <td align="center"><%=b.getMaths()%></td>
                    <td align="center"><%=(b.getPhysics()+b.getPhysics()+b.getMaths()) %></td>
                    <td align="center"><%=(percent+" %")%></td>
                    <td align="center">
                    <%if(phy >=33 && chem >=33 &&   math >=33){ %>    
                       <font style="color: green;">Pass</font>
                    <%}else{%>                 
                    <font style="color: red;">Fail*</font>
                     <% } %>
                    </td>
                    <td align="center"><a href="MarksheetCtl?id=<%=b.getId()%>">Edit</a></td>
                </tr>

                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=MarksheetListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;</td>
                    <td><input type="submit" name="operation"
                        value="<%=MarksheetListCtl.OP_NEW%>">&nbsp;&emsp;</td>
                    <td><input type="submit"
                        name="operation" value="<%=MarksheetListCtl.OP_DELETE%>"></td>
                    <td align="right"><input type="submit" name="operation"
                        value="<%=MarksheetListCtl.OP_NEXT%>"<%=(next==0 || list.size()<pageSize)?"disabled":"" %>></td>
                </tr>
            </table>
            
             <%}if(list.size() == 0){ %>
               <br><input type="submit" name="operation" value="<%=MarksheetListCtl.OP_BACK%>">
               
                <% } %>
            
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
                
            
        </form>
    </center>
<br><br>
    <%@include file="Footer.jsp"%>
    
</body>
</html>