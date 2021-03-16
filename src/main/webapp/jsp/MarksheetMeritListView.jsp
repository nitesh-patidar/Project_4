 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   
   <%@page import="in.co.rays.controller.MarksheetMeritListCtl"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="in.co.rays.bean.MarksheetBean"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet Merit List  View Page</title>
</head>
<body>

    <%@include file="Header.jsp"%>
    <center>
        <h1>Marksheet Merit List</h1>

        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
          
            <table border="1" width="100%">
                <tr>

                    <th style="background-color: lightblue">S. No.</th>
                    <th style="background-color: lightblue">Roll No</th>
                    <th style="background-color: lightblue">Name</th>
                    <th style="background-color: lightblue">Physics</th>
                    <th style="background-color: lightblue">Chemistry</th>
                    <th style="background-color: lightblue">Maths</th>
                    <th style="background-color: lightblue">Total</th>
                    <th style="background-color: lightblue">Percentange</th>

                
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                        
                        int total = (bean.getPhysics()+bean.getChemistry()+bean.getMaths());
                        
                        float perc = total/3 ; 
                        
                        
                %>
                <tr>

                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getRollNo()%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getPhysics()%></td>
                    <td align="center"><%=bean.getChemistry()%></td>
                    <td align="center"><%=bean.getMaths()%></td>
                    <td align="center"><%=total%></td>
                    <td align="center"><%=((perc)+"%")%></td>
                    
                </tr>

                <%
                    }
                %>
            </table>
            <table>
                <tr>
                    <td align="right">
                    <input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
        <br><br>
    </center>
   
   <br><br>
    <%@include file="Footer.jsp"%>

</body>
</html>