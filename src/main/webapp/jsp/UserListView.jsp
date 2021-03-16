<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

  <%@page import="in.co.rays.controller.UserListCtl"%>
  
  <%@page import="in.co.rays.model.RoleModel"%> 
  
  <%@page import="in.co.rays.util.ServletUtility"%>
  
  <%@page import="in.co.rays.util.HTMLUtility"%>
  
  <%@page import="java.util.List"%>
  
  <%@page import="java.util.Iterator"%>
  
  <%@page import="java.text.SimpleDateFormat"%>
  
  

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UserList View Page</title>
</head>
<body>
     
    <%@include file="Header.jsp"%>

    <center>
        
       <form action="<%=ORSView.USER_LIST_CTL%>" method="post">
      
        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean" scope="request"></jsp:useBean>

        <%
            List l = (List)request.getAttribute("roleList");
            
            List l1 = (List)request.getAttribute("loginList");
            
            int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
         
        %>
        <h1>User List</h1>
         
          <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
        
          <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
            <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                
                    Iterator<UserBean> it = list.iterator();
                    
                    if(list.size()!=0){
        
        %>
            <table width="100%">
               <tr>
                    <td align="center">
                    
                    <label style="font-weight: bold;">Role :</label>
                    <%=HTMLUtility.getList("roleId",ServletUtility.getParameter("roleId", request), l)%>
                     &emsp; 
                   <label style="font-weight: bold;">First Name :</label> 
                    <input type="text" name="firstName"  placeholder="Enter First_Name"
                        value="<%=ServletUtility.getParameter("firstName", request)%>">
                        &emsp; 
                   <label style="font-weight: bold;">LoginId :</label> 
                   <%=HTMLUtility.getList("loginId",ServletUtility.getParameter("loginId", request), l1)%> 
                 
                  &emsp; 
                  <input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH %>">
                  <input type="submit" name="operation" value="<%=UserListCtl.OP_RESET %>">
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
                    <th style="background-color: lightblue">Role</th>
                    <th style="background-color: lightblue">LoginId</th>
                    <th style="background-color: lightblue">Gender</th>
                    <th style="background-color: lightblue">DOB</th>
                    <th style="background-color: lightblue">Mobile No</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>

               
            
                <%
                    while (it.hasNext()) {
                       bean = it.next();
                       RoleModel model = new RoleModel();
					   RoleBean rolebean = new RoleBean();
					   rolebean = model.findByPK(bean.getRoleId());
				
                %>
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"
                      <%if(userBean.getId()==bean.getId() || bean.getRoleId()==RoleBean.ADMIN) {%>
                       <%="disabled" %>
                    <% } %>></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getFirstName()%></td>
                    <td align="center"><%=bean.getLastName()%></td>
                    <td align="center"><%=rolebean.getName() %></td>
                    <td align="center"><%=bean.getLogin()%></td>
                    <td align="center"><%=bean.getGender()%></td>
                    
                    <%
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				                String dob = sdf.format(bean.getDob());
					%>
                    
                    <td align="center"><%=dob%></td>
                    <td align="center"><%=bean.getMobileNo()%></td>
                   
                    <% 
                    if(userBean.getId()==bean.getId() || bean.getRoleId()==RoleBean.ADMIN){
                    %>
                    <td align="center">----</td>
                     <%}else{ %>
                     <td align="center"><a href="UserCtl?id=<%=bean.getId()%>">Edit</a></td>
                    <% } %>
                </tr>
                <%
                    }
                %>
                
                
            </table>
            <table width="100%">
                <tr>
                    <td><input type="submit" name="operation"
                        value="<%=UserListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;&nbsp;&nbsp;</td>
               
                    <td><input type="submit" name="operation" value="<%=UserListCtl.OP_NEW%>">&emsp;&emsp;&emsp;</td>
                   
                    <td><input type="submit" name="operation" value="<%=UserListCtl.OP_DELETE%>"></td>
                   
                    <td align="right">
                    <input type="submit" name="operation" value="<%=UserListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
                </tr>
            </table>
             <%}if(list.size() == 0){ %>
               
                       
             <br><input type="submit" name="operation" value="<%=UserListCtl.OP_BACK%>"> 
        
        
            <% } %>
            <br>
            <br>
            <br>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
            
           
            
            
        </form>
    </center>
     <br><br>
 </body>
   <%@include file="Footer.jsp"%> 
</html>