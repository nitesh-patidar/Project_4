<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
   <%@page import="in.co.rays.controller.RoleListCtl"%>
   
   <%@page import="in.co.rays.controller.BaseCtl"%>
   
   <%@page import="in.co.rays.bean.RoleBean"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RoleList View Page</title>
</head>
<body>
    
       <%@include file="Header.jsp"%>
       
       <% 
         int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
       %>

    <center>
      <h1>Role List</h1>
      
       <form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
      
       <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
       
       <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
    
    
      <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    
                    Iterator<RoleBean> it = list.iterator();
                    
                    if(list.size()!=0){
    %>
      
            <table width="100%">
                <tr>
                    <td align="center">
                    <label style="font-weight: bold;">Name :</label>
                     <input type="text" name="name"  placeholder="Enter Name"
                        value="<%=ServletUtility.getParameter("name", request)%>"> 
                   <font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font>&nbsp; 
                     
                     <input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>">
                      <input type="submit" name="operation" value="<%=RoleListCtl.OP_RESET %>">
                    </td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
                <tr style="background-color: lightblue;">
                    <th><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th>S.No.</th>
                    <th>Name</th>
                    <th>Descriptiop</th>
                    <th>Edit</th>
                </tr>
               
              <%
                    while (it.hasNext()) {
                        RoleBean bean = it.next();
               %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"
                      <%if(userBean.getId()==bean.getId() || bean.getId()==RoleBean.ADMIN) {%>
                       <%="disabled"%>
                    <% } %>></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getDescription()%></td>
                    <% 
                    if(bean.getId()==1){
                    %>
                    <td align="center">----</td>
                     <%}else{ %>
                    <td align="center"><a href="RoleCtl?id=<%=bean.getId()%>">Edit</a></td>
                    <% } %>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&nbsp;&nbsp;&emsp;</td>
                   
                    <td><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_NEW%>">&nbsp;&emsp;&emsp;</td>
                   
                    <td><input type="submit"
                        name="operation" value="<%=RoleListCtl.OP_DELETE%>"></td>
                   
                    <td align="right"><input type="submit" name="operation"
                        value="<%=RoleListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
                </tr>
            </table>
            
               <%}if(list.size() == 0){ %>
            
              <br><input type="submit" name="operation" value="<%=RoleListCtl.OP_BACK%>"> 
              
                <% } %>
         
            
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
            
       
          </form>
        
       </center>
 
 <br><br>
    <%@include file="Footer.jsp"%>
    
</body>
</html>