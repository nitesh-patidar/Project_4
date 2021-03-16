<%@page import="in.co.rays.bean.TimetableBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

   <%@page import="in.co.rays.controller.TimetableListCtl"%>
   
   <%@page import="in.co.rays.util.ServletUtility"%>
   
   <%@page import="in.co.rays.util.DataUtility"%>
   
   <%@page import="in.co.rays.util.HTMLUtility"%>
   
   <%@page import="in.co.rays.bean.SubjectBean"%>
   
   <%@page import="java.util.List"%>
   
   <%@page import="java.util.Iterator"%>
   
   <%@page import="java.text.SimpleDateFormat" %>
 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Timetable List View Page</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <link rel="stylesheet" href="/resources/demos/style.css">
  
  <script>
  function disableSunday(d){
	  var day = d.getDay();
	  if(day==0)
	  {
	   return [false];
	  }else
	  {
		  return [true];
	  }
  }
  
  $( function() {
	   $( "#datepicker" ).datepicker({ 
		  beforeShowDay : disableSunday,
		  changeMonth :true,
		  changeYear :true,
		  yearRange:'0:+5',
		  dateFormat:'dd-mm-yy',
		  minDate : 0
		
		 }) ;
  
 } );
 
  </script>

</head>
<body>
 
  
        <%@include file="Header.jsp"%>
        
         <form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">
       
         <jsp:useBean id="cbean" class="in.co.rays.bean.CourseBean" scope="request"></jsp:useBean>
         
         <jsp:useBean id="sbean" class="in.co.rays.bean.StudentBean" scope="request"></jsp:useBean>
         
         <jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean" scope="request"></jsp:useBean>
      
          <%
             List l1 = (List)request.getAttribute("courseList");
             
             List l2 = (List)request.getAttribute("subjectList");
          
             int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
          %>
          
           <center>
        <h1>Timetable List</h1>
        
        <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
        
        <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        
        <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    
                    Iterator<TimetableBean> it = list.iterator();

                    if(list.size() !=0){
                    
         %>
         
       
      
      
        <table width="100%">
                <tr>
                    <td align="center">
                    
                   <label style="font-weight: bold;">Course Name :</label> 
                   <%=HTMLUtility.getList("courseId", ServletUtility.getParameter("courseId", request), l1) %>
                   &emsp;
                   <label style="font-weight: bold;">Subject Name :</label> 
                   <%=HTMLUtility.getList("subjectId",ServletUtility.getParameter("subjectId", request), l2) %>&nbsp;
                    &emsp;
                    
                   	<%
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							if (bean.getExamDate() != null) {
								String ss = sdf.format(bean.getExamDate());
					%>
						
							<label style="font-weight: bold;">Exam Date :</label>
							<input type="text" name="examDate" id="datepicker" placeholder="Select exam date"
								 readonly="readonly" value="<%=ss%>">
						
						<%
							} else {
						%>
						
						   <label style="font-weight: bold;">Exam Date :</label>
							<input type="text" name="examDate" id="datepicker"
								readonly="readonly" 
								placeholder="Select exam date"
								value="<%=DataUtility.getStringData(bean.getExamDate())%>">
								
						<%
							}
						%>
              
                    <input type="submit" name="operation" value="<%=TimetableListCtl.OP_SEARCH %>">
                    <input type="submit" name="operation" value="<%=TimetableListCtl.OP_RESET %>"></td>
                </tr>
            </table>
            <br>
            <table border="1" width="100%">
            
            
                <tr>
                    <th style="background-color: lightblue"><input type="checkbox" id="select_all" name="select">Select All</th>
                    <th style="background-color: lightblue">S.No.</th>
                    <th style="background-color: lightblue">Course</th>
                    <th style="background-color: lightblue">Subject</th>
                    <th style="background-color: lightblue">Semester</th>
                    <th style="background-color: lightblue">Exam Date</th>
                    <th style="background-color: lightblue">Exam Time</th>
                    <th style="background-color: lightblue">Edit</th>
                </tr>
               
             <%  
                     while (it.hasNext()) {

                        TimetableBean tb = it.next();
             %>
         
        
                <tr>
                    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=tb.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                    <td align="center"><%=tb.getCourseName()%></td>
                    <td align="center"><%=tb.getSubjectName()%></td>
                    <td align="center"><%=tb.getSemester()%></td>
                    
                              
                    <%
							SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
				                String exDate = sd.format(tb.getExamDate());
					%>
                    
                    
                    <td align="center"><%=exDate%></td>
                    <td align="center"><%=tb.getExamTime()%></td>
                    <td align="center"><a href="TimetableCtl?id=<%=tb.getId()%>">Edit</a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="100%">
                <tr>
                 <td><input type="submit" name="operation" value="<%=TimetableListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>&emsp;&emsp;</td>
                   
                 <td><input type="submit" name="operation" value="<%=TimetableListCtl.OP_NEW%>">&emsp;&emsp;</td>
                        
                 <td><input type="submit" name="operation" value="<%=TimetableListCtl.OP_DELETE%>"></td>
                        
                 <td align="right"><input type="submit" name="operation" value="<%=TimetableListCtl.OP_NEXT%>"<%=(list.size()<pageSize || next==0)?"disabled":"" %>></td>
               </tr>
            </table>
            
            <%}if(list.size() == 0){ %>
              
               <br><input type="submit" name="operation" value="<%=TimetableListCtl.OP_BACK%>"> 
        
            <% } %>
            <input type="hidden" name="pageNo" value="<%=pageNo%>">
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
                <br><br><br>
                
      

          </center>

        </form>
<br><br>
     <%@ include file="Footer.jsp"%>

</body>
</html>