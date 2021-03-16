<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.rays.controller.TimetableCtl"%>
  
  <%@page import="java.util.List"%>

  <%@page import="in.co.rays.util.HTMLUtility"%>

  <%@page import="java.util.LinkedHashMap"%>

  <%@page import="in.co.rays.util.DataUtility"%>

  <%@page import="in.co.rays.util.ServletUtility"%>
  
  <%@page import="java.text.SimpleDateFormat"%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Timetable View Page</title>

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

   <%@ include file="Header.jsp"%>
  
   <form action="<%=ORSView.TIMETABLE_CTL%>" method="post">
        
   <jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean" scope="request"></jsp:useBean>

        <%
           
            List l1 = (List)request.getAttribute("courseList");
        
            List l2 = (List)request.getAttribute("subjectList");
        %>

 <center>
    <%if(bean.getId()>0){ %>
       
            <h1>Update Timetable</h1>
    <%
    }else{%>
     
            <h1>Add Timetable</h1>
            
      <%} %>
            <h2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </h2>

            <h2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </h2>
            
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


            <table>
             
              <tr>
               <td><strong>Course Name</strong><font style="color:red">*</font></td>
                 <td>
                 
                  <%=HTMLUtility.getList("courseId",String.valueOf(bean.getCourseId()),l1) %>
                </td>
                <td style="position: fixed;"> 
                <font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
                 </tr>
               
             <tr>
               <td><strong>Subject Name</strong><font style="color:red">*</font></td>
                 <td>
                 
                  <%=HTMLUtility.getList("subjectId",String.valueOf(bean.getSubjectId()),l2) %>
                </td>
                <td style="position: fixed;"> 
                <font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%></font></td>
                 </tr>
                
               <tr>
	           <td><strong>Semester</strong><font style="color: red">*</font></td>
	      <td>
	        <%
	        LinkedHashMap<String , String> map = new LinkedHashMap< String , String>();
		    map.put("1st","1st");
		    map.put("2nd","2nd");
		    map.put("3rd","3rd");
		    map.put("4th","4th");
		    map.put("5th","5th");
		    map.put("6th","6th");
		    map.put("7th","7th");
		    map.put("8th","8th");
		
        String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
	        %>
	<%=htmlList %>
	</td>
	 
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("semester",request) %></font> 
	</td></tr>

			
						<%
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							if (bean.getExamDate() != null) {
								String ss = sdf.format(bean.getExamDate());
						%>
						<tr>
							<td><strong>Exam Date</strong><font color="red">*</font></td>
							<td><input type="text" name="examDate" id="datepicker" placeholder="Select exam date"
								 readonly="readonly" value="<%=ss%>"></td>
							<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
						</tr>
						<%
							} else {
						%>
						
						<tr>
							<td><strong>Exam Date</strong><font color="red">*</font></td>
							<td><input type="text" name="examDate" id="datepicker"
								readonly="readonly" 
								placeholder="Select exam date"
								value="<%=DataUtility.getStringData(bean.getExamDate())%>"></td>
								<td style="position: fixed;">
								<font color="red"><%=ServletUtility.getErrorMessage("examDate", request)%></font></td>
						</tr>
						<%
							}
						%>
			
			
			
			
			
	<tr><td><strong>Exam Time</strong><font style="color: red">*</font></td>
	<td>
	<%
	    LinkedHashMap<String , String > map1 = new LinkedHashMap<String  ,String >();
		map1.put("08:00 AM to 11:00 AM","08:00 AM to 11:00 AM");
		map1.put("12:00 PM to 03:00 PM","12:00 PM to 03:00 PM");
		map1.put("04:00 PM to 07:00 PM","04:00 PM to 07:00 PM");
	
		String htmlList1 = HTMLUtility.getList("examTime", bean.getExamTime(), map1);
	%>
	<%=htmlList1 %>
	</td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("examTime",request) %></font> 
	</td></tr> 
	
                
                 <tr>
                   <td></td>
                    <td colspan="2">
                    
                     <%
                     if (bean.getId() > 0) {
		  	 %>
		  <input type="submit" name="operation" value="<%=TimetableCtl.OP_UPDATE%>">  
		  
		  <input type="submit" name="operation" value="<%=TimetableCtl.OP_CANCEL%>"> 
		   <%
             }
            else
             {
		   %>
	    
	      <input type="submit" name="operation" value="<%=TimetableCtl.OP_SAVE%>">
		  
		  <input type="submit" name="operation" value="<%=TimetableCtl.OP_RESET%>"> 
	    
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