<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  
   <%@page import="in.co.rays.controller.CollegeCtl"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="java.util.HashMap"%>
   
   <%@page import="java.util.LinkedHashMap"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College Registration Page</title>
</head>
<body>
   
    <form action="<%=ORSView.COLLEGE_CTL%>" method="post">
       
    <%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean" scope="request"></jsp:useBean>

    <center>   
    <%if(bean.getId()>0){ %>
       
            <h1>Update College</h1>
    <%
    }else{%>
     
            <h1>Add College</h1>
            
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
            
            <input type="hidden" name="modifiedBy"
                      value="<%=bean.getModifiedBy()%>"> <input type="hidden" name="createdDatetime"
                      value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
           
            <input type="hidden" name="modifiedDatetime"
                         value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <td><strong>Name</strong><font style="color: red">*</font></td>
                    <td>
                    <input type="text" name="name" placeholder="Enter College Name"
                        value="<%=DataUtility.getStringData(bean.getName())%>">
                    </td>
                    <td style="position: fixed;">
                    <font color="red"><%=ServletUtility.getErrorMessage("name",request) %> </font></td>
                </tr>
              
                  <tr>
                    <td><strong>Address</strong><font style="color: red">*</font></td>
                   <td>
                   <input type="text" name="address" placeholder="Enter College Address"
							style="height: 70px; font-size: 10pt; width: 169px"value="<%=DataUtility.getStringData(bean.getAddress())%>">
                     </td>
                     <td style="position: fixed;"> 
                     <font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font>
                     </td>
                     </tr>
                   
                 <tr>
                    <td><strong>PhoneNo</strong><font style="color: red">*</font></td>
                    <td><input type="text" name="phoneNo" placeholder="Enter PhoneNo"
                        maxlength="10" value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">
                     </td>
                    <td style="position: fixed;">
                        
                     <font color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
                </tr>
               
                  <tr>
                    <td><strong>State</strong><font style="color: red">*</font></td>
                       <td>
                       
                        <%
                            HashMap map = new HashMap();
                            map.put("Madhya Pradesh", "Madhya Pradesh");
                            map.put("Maharashtra","Maharashtra");
                            map.put("Chhattisgarh", "Chhattisgarh");
                            map.put("Andhra Pradesh","Andhra Pradesh");
                           
                       
                            String htmlList = HTMLUtility.getList("state", bean.getState(),map);
                        %> 
                        <%=htmlList%>
                    </td>
                    <td style="position: fixed;">
                    <font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
               
                 <tr>
                  <td><strong>City</strong><font style="color: red">*</font></td>
                    <td>
                       
                        <%
                           LinkedHashMap Lmap = new LinkedHashMap();
                            Lmap.put("Indore", "Indore");
                            Lmap.put("Bhopal", "Bhopal");
                            Lmap.put("Ujjain","Ujjain");
                            Lmap.put("Khandwa","Khandwa");
                            Lmap.put("Khargone","Khargone"); 
                            Lmap.put("Sehore","Sehore");
                            Lmap.put("Pune","Pune");
                            Lmap.put("Nashik","Nashik");
                      
                            String htmlList1 = HTMLUtility.getList("city", bean.getCity(),Lmap);
                        %> 
                        <%=htmlList1%>
                    </td>
                      <td style="position: fixed;">
                   <font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
                </tr>
                
                <tr>
                 <td></td>  
                 <td colspan="2">
                 <%
                     if (bean.getId() > 0) {
		  	 %>
		  <input type="submit" name="operation" value="<%=CollegeCtl.OP_UPDATE%>">
		  
		  <input type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>"> 
		   <%
             }
            else
             {
		   %>
	    
	      <input type="submit" name="operation" value="<%=CollegeCtl.OP_SAVE%>">&nbsp; 
		  
		  <input type="submit" name="operation" value="<%=CollegeCtl.OP_RESET%>"> 
	    
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