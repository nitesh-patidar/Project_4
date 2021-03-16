<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
   <%@page import="in.co.rays.controller.MyProfileCtl"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>

   <%@page import="java.util.HashMap"%>

   <%@page import="in.co.rays.util.DataUtility"%>

   <%@page import="in.co.rays.util.ServletUtility"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile View Page</title>


</head>
<body>
      
     <%@ include file="Header.jsp"%>
     <form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

       
       
        <jsp:useBean id="bean" class="in.co.rays.bean.UserBean" scope="request"></jsp:useBean>

        <center>
            <h1>My Profile</h1>
            
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table>
                <tr>
                    <td>LoginId<font style="color: red">*</font></td>
                    <td>
                    <input type="text" name="login" placeholder="Enter LoginId"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>" readonly="readonly">
                    </td>
                    <td style="position: fixed;"> 
                     <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>

                <tr>
                    <td>First Name<font style="color: red">*</font></td>
                    <td><input type="text" name="firstName" placeholder="Enter First_Name"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                        </td>
                    <td style="position: fixed;">  
                        
                     <font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <td>Last Name<font style="color: red">*</font></td>
                    <td><input type="text" name="lastName"  placeholder="Enter Last_Name"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>">
                         </td>
                    <td style="position: fixed;"> 
                        
                     <font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <td>Gender<font style="color: red">*</font></td>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("Male", "Male");
                            map.put("Female", "Female");
                            
                            
                         String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
                        %> 
                        <%=htmlList%>
                    </td>
                    <td style="position: fixed;"> 
                        
                    <font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
                </tr>
                <tr>
                    <td>Mobile No<font style="color: red">*</font></td>
                    <td><input type="text" name="mobileNo"  placeholder="Enter Mobile_No" maxlength="10"
                        value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                         </td>
                    <td style="position: fixed;"> 
                        
                    <font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

                <tr>
                    <td>Date Of Birth<font style="color: red">*</font></td>
                    <td>
                    <input type="text" id="datepicker" name="dob"  placeholder="Date_Of_Birth" readonly="readonly" value="<%=DataUtility.getDateString(bean.getDob())%>">
                  </td>
                    <td style="position: fixed;"> 
                    <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
                
                <tr>
                <td></td>
                  <td colspan="2">
                   <input type="submit"
                        name="operation" value="<%=MyProfileCtl.OP_UPDATE %>"> 
                    <input type="submit" name="operation"
                        value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>">
                  </td> 
                </tr>
            </table>
           </center>
    </form>
   <br><br>
    <%@ include file="Footer.jsp"%>
     
</body>
</html>