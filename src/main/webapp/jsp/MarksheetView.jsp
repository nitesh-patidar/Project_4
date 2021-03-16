<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="in.co.rays.controller.MarksheetCtl"%>

<%@page import="java.util.List"%>

<%@page import="in.co.rays.util.HTMLUtility"%>

<%@page import="in.co.rays.util.DataUtility"%>

<%@page import="in.co.rays.util.ServletUtility"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet Registration Page</title>

<script type="text/javascript">
  function isNumberKey(event) {
	  
	  var charCode = (event.which) ? event.which : event.keyCode
	     if(charCode > 31 && (charCode < 48 || charCode > 57 ))
	        return false ;
	     else
	        return true ;
}
</script>
</head>
<body>
 
   <%@include file="Header.jsp"%>
   
   <form action="<%=ORSView.MARKSHEET_CTL %>" method="post">
   
   <jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean" scope="request"></jsp:useBean>
   
   <% List l = (List)request.getAttribute("studentList");%>
   
   <center>
    
   
    <% if(bean.getId()>0){ %>
       
        <h1>Update Marksheet</h1>
    <%
       }else{%>
     
            <h1>Add Marksheet</h1>
            
      <%} %>
    
    <h2>
    <font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
    </h2>
    
     <h2>
    <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
    </h2>
    
    <input type="hidden" name="id" value="<%=bean.getId()%>">
    <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
    <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
    <input type="hidden" name="createdDatetime" value="<%=DataUtility.geTimestamp(bean.getCreatedBy())%>">
    <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.geTimestamp(bean.getModifiedBy())%>">
   
     <table>
      <tr>
        <td>RollNo<font style="color: red">*</font></td>
        <td>
        <input type="text" name="rollNo"  placeholder="Enter RollNo" value="<%=DataUtility.getStringData(bean.getRollNo()) %>" 
               <%=(bean.getId()>0)?"readonly":"" %> >
        </td>
        <td style="position: fixed;">
        <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request) %></font>
        </td>
        </tr>
      
        <tr>
        <td>Student Name<font style="color: red">*</font></td>
        <td>
          <%=HTMLUtility.getList("studentId",String.valueOf(bean.getStudentId()),l) %>
        </td>
        <td style="position: fixed;">
        <font color="red"><%=ServletUtility.getErrorMessage("studentId", request) %></font>
        </td>
        </tr>
        
        <tr>
         <td>physics<font style="color: red">*</font></td>
        <td>
           <input onkeypress="return isNumberKey(event)" type="text" name="physics"  placeholder="Enter Physics Marks" maxlength="3"
           value="<%=DataUtility.getStringData(bean.getPhysics()).equals("0")?"":DataUtility.getStringData(bean.getPhysics())%>" >
        </td>
        <td style="position: fixed;">
        <font color="red" ><%=ServletUtility.getErrorMessage("physics", request) %></font>
        </td>
        </tr>
        
          <tr>
         <td>Chemistry<font style="color: red">*</font></td>
        <td>
           <input onkeypress="return isNumberKey(event)"  type="text" name="chemistry" placeholder="Enter Chemistry Marks" maxlength="3"
           value="<%=DataUtility.getStringData(bean.getChemistry()).equals("0")?"":DataUtility.getStringData(bean.getChemistry())%>">
        </td>
        <td style="position: fixed;">
        <font color="red"><%=ServletUtility.getErrorMessage("chemistry", request) %></font>
        </td>
        </tr>
        
         <tr>
         <td>Maths<font style="color: red">*</font></td>
        <td>
           <input onkeypress="return isNumberKey(event)"  type="text" name="maths"  placeholder="Enter Maths Marks" maxlength="3"
           value="<%=DataUtility.getStringData(bean.getMaths()).equals("0")?"":DataUtility.getStringData(bean.getMaths())%>" >
        </td>
        <td style="position: fixed;">
        <font color="red"> <%=ServletUtility.getErrorMessage("maths", request) %></font>
        </td>
        </tr>
       
         <tr>
         <th></th>
          <td>
           <%
			     if (bean.getId() > 0) {
			 %>
		  <input type="submit" name="operation" value="<%=MarksheetCtl.OP_UPDATE%>">  
		  
		  <input type="submit" name="operation" value="<%=MarksheetCtl.OP_CANCEL%>"> 
		   <%
			   }
			  else {
			%> 
	    
	       <input type="submit" name="operation" value="<%=MarksheetCtl.OP_SAVE%>">  
		  
		  <input type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>"> 
	    
	        <%
			   }
			%> 
	    
	    </td>
	    </tr>
			          
     </table>
     </center>
     </form>
	<br><br>
      <%@include file="Footer.jsp"%>
    
</body>
</html>