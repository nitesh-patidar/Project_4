<%@page import="in.co.rays.controller.GetMarksheetCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Marksheet View Page</title>

<style type="text/css">
input[type=text],input[type=password]{
width:160px;
padding:3px

}
select{

width:180px;
padding:3px


}

</style>
</head>
<body>
<%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean" scope="request"></jsp:useBean>
    
       
    <center>
        <h2>Get Marksheet</h2>

      <h3> 
      <font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
      </h3>

        <h2>
        <font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
        </h2> 

        <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

            <input type="hidden" name="id" value="<%=bean.getId()%>">
           
      
                <label style="font-weight: bold;">RollNo :</label>&nbsp;
                
                <input type="text" name="rollNo" placeholder="Enter Roll No" value="<%=ServletUtility.getParameter("rollNo", request)%>">&nbsp;
               
                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">&nbsp;
                
                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_RESET%>">
                <br>
                <br>
             
                <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font> 
                
                <%
                    if ( bean.getName()!= null && bean.getName().length() >0) {
                	

    					int phy = bean.getPhysics();
    					int chem = bean.getChemistry();
    					int math = bean.getMaths();
    					int total = phy + chem + math;
    					float percentage = total / 3;
                 %>
              <br>
               <table border="1px" width="100%">
                <tr style="background-color: lightblue">
                <th colspan="4">RollNo : <%=bean.getRollNo() %></th>
               
                <th colspan="4">Name : <%=bean.getName() %></th>
                </tr>
                <tr>
                <th colspan="2">Subject</th>
                <th colspan="2">Maximum marks.</th>
                <th colspan="2">Minimum marks.</th>
                <th colspan="2">Marks Obtained</th>
                </tr>
                <tr >
                <th colspan="6"></th>
                <th>marks</th>
                <th>Remarks*</th>
                
                </tr>
                <tr>
                <th colspan="2">Physics</th>
                <td colspan="2" align="center">100</td>
                <td colspan="2" align="center">35</td>
                
               <%if(bean.getPhysics()<35) {%>
                <td colspan="1" align="center"><%=bean.getPhysics() %><span style="color: red">*</span></td>
                 <%}else{ %>
                <td colspan="1" align="center"><%=bean.getPhysics() %></td>
                 <%} %>
                 
                 
                <%if(bean.getPhysics()>=35) {%>
                <td align="center"  style="color:green;">pass             
                </td>
                <%}else{ %>
                <td align="center" style="color: red">fail*             
                </td>
                <%} %>
                </tr>
                
                <tr>
                <th colspan="2">Chemistry</th>
                <td colspan="2" align="center">100</td>
                <td colspan="2" align="center">35</td>
                
                 <%if(bean.getChemistry()<35) {%>
               <td colspan="1" align="center"><%=bean.getChemistry() %><font style="color: red">*</font></td>
                 <%}else{ %>
                  <td colspan="1" align="center"><%=bean.getChemistry() %></td>
                 <%} %>
                 
                <%if(bean.getChemistry()>=35) {%>
                <td align="center"  style="color:green;">pass             
                </td>
                <%}else{ %>
                <td align="center" style="color: red">fail*             
                </td>
                <%} %>
                </tr>
                 <tr>
                <th colspan="2">Maths</th>
                <td colspan="2" align="center">100</td>
                <td colspan="2" align="center">35</td>
                
                   <%if(bean.getMaths()<35) {%>
                 <td colspan="1" align="center"><%=bean.getMaths() %><span style="color: red">*</span></td>
                 <%}else{ %>
                     <td colspan="1" align="center"><%=bean.getMaths() %></td>
                 <%} %>
                
                
             
                <%if(bean.getMaths()>=35) {%>
                <td align="center" style="color:green;">pass             
                </td>
                <%}else{ %>
                <td align="center" style="color: red">fail*             
                </td>
                <%} %>
                </tr>
                <tr>
                <th colspan="2">Total</th>
                <td colspan="2" align="center">300</td>
                <th colspan="4" align="center"></th>
                </tr>
                <tr>
                <th colspan="4">Grand Total</th>
                <th colspan="4" align="center"><%=total %>/300</th>
                </tr>
                <tr>
                <th colspan="4">Percentage</th>
                <th colspan="4"><%=total/3 %>%</th>
                </tr>
                <tr>
                <th colspan="4">Result</th>
                <% if(bean.getPhysics()>35&&bean.getChemistry()>35&&bean.getMaths()>35){%>
                <th colspan="4"  style="color: green;">PASS</th>
                <%}else{ %>
                <th colspan="4" style="color: red;">FAIL</th>
                <%} %>
                </tr>
                </table>
               <% 
                    }
                %>
                
        
        </form>
    </center>
    
    <br><br>
    <%@ include file="Footer.jsp"%>

</body>
</html>