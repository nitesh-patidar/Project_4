package in.co.rays.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.bean.TimetableBean;
import in.co.rays.exception.ApplicationException;
import in.co.rays.exception.DatabaseException;
import in.co.rays.exception.DuplicateRecordException;
import in.co.rays.util.JDBCDataSource;

// TODO: Auto-generated Javadoc
/**
 * JDBC Implementation of Timetable Model.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class TimetableModel {

	/** The log. */
	private static Logger log = Logger.getLogger(TimetableModel.class);

	/**
	 * Find next PK of Timetable.
	 *
	 * @return the integer
	 * @throws DatabaseException the database exception
	 */

public Integer nextPK() throws DatabaseException
		    {
		        log.debug("Model nextPK Started");
		       
		        Connection conn = null;
		        
		        int pk = 0;

		        try {
		            conn = JDBCDataSource.getConnection();
		            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
		            ResultSet rs = pstmt.executeQuery();
		            while(rs.next())
		            { 
		               pk = rs.getInt(1);
		            }
		            rs.close();

		        } 
		        catch (Exception e) 
		        {
		            log.error("Database Exception..", e);
		            throw new DatabaseException("Exception : Exception in getting PK");
		        } 
		        finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }
		        log.debug("Model nextPK End");
		        return pk + 1;
		    }

/**
 * Add a Timetable.
 *
 * @param bean the bean
 * @return the long
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 */   
 public long add(TimetableBean bean) throws ApplicationException,  DuplicateRecordException 
		           
		    {
		        log.debug("Model add Started");
		      
		        Connection conn = null;
		        
		        int pk = 0;


		        CourseModel cModel = new CourseModel();
		       
		        CourseBean courseBean = cModel.findByPK(bean.getCourseId());
		        
		        bean.setCourseName(courseBean.getName());


		        SubjectModel subModel = new SubjectModel();
		       
		        SubjectBean subjectBean = subModel.findByPK(bean.getSubjectId());
		        
		        bean.setSubjectName(subjectBean.getName());

		       
		        TimetableBean existbean = findByPK(bean.getId());
		     
		        TimetableBean duplicateAll = findByAll(bean);
		        
		        TimetableBean duplicateCSS = findByCSS(bean);
		     
                TimetableBean duplicateDateTime = findByExamDateTime(bean);
				     

		        if (existbean != null) 
		        {   
		        	 throw new DuplicateRecordException("Id Already Exists");
		        }
		       else if(duplicateAll != null)
		        {
		    	   
		        	throw new DuplicateRecordException("TimeTable Already Exist");
		        }
		       else if(duplicateCSS != null)
		        {
		    	   
		        	throw new DuplicateRecordException("TimeTable for this subject already exist");
		        }
		        else if(duplicateDateTime != null)
		        {
		        
		        	throw new DuplicateRecordException("TimeTable for this date Already sheduled");
		        }

		        try {
		            conn = JDBCDataSource.getConnection();
		            pk = nextPK();
		            conn.setAutoCommit(false); 
		            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
		            pstmt.setInt(1, pk);
		            pstmt.setLong(2,bean.getCourseId());
		            pstmt.setString(3, bean.getCourseName());
		            pstmt.setLong(4,bean.getSubjectId());
		            pstmt.setString(5, bean.getSubjectName());
		            pstmt.setString(6, bean.getSemester());
		          
		            pstmt.setDate(7,  new java.sql.Date(bean.getExamDate().getTime()));
		            pstmt.setString(8, bean.getExamTime());
			        pstmt.setString(9, bean.getCreatedBy());
		            pstmt.setString(10, bean.getModifiedBy());
		            pstmt.setTimestamp(11, bean.getCreatedDatetime());
		            pstmt.setTimestamp(12, bean.getModifiedDatetime());
		            pstmt.executeUpdate();
		            conn.commit(); 
		            pstmt.close();
		        } 
		        catch (Exception e) 
		        {
		            log.error("Database Exception..", e);
		            try
		            {
		                conn.rollback();
		            } 
		            catch (Exception ex)
		            {
		                ex.printStackTrace();
		                throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
		            }
		            throw new ApplicationException("Exception : Exception in add Faculty");
		        } 
		        finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }
		        log.debug("Model add End");
		        return pk;
   }

 /**
  * Delete a Timetable.
  *
  * @param bean the bean
  * @throws ApplicationException the application exception
  */

public void delete(TimetableBean bean) throws ApplicationException 
		    {
		        log.debug("Model delete Started");
		        Connection conn = null;
		        try {
		            conn = JDBCDataSource.getConnection();
		            conn.setAutoCommit(false); 
		            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
		            pstmt.setLong(1, bean.getId());
		            pstmt.executeUpdate();
		            conn.commit(); 
		            pstmt.close();

		        } 
		        catch(Exception e) 
		         {
		            log.error("Database Exception..", e);
		            try {
		                conn.rollback();
		            } 
		            catch (Exception ex) 
		            {
		                throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
		            }
		            throw new ApplicationException("Exception : Exception in delete Timetable");
		         }
		        finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }
		       log.debug("Model delete Started");
		    }


/**
 * Find User by Timetable PK.
 *
 * @param pk            : get parameter
 * @return bean
 * @throws ApplicationException the application exception
 */
public TimetableBean findByPK(long pk) throws ApplicationException
{
	    log.debug("Model findByPK Started");
	   
	    StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
	           
	    TimetableBean bean = null;
	 
	    Connection conn = null;
	    
	    try {
	
	        conn = JDBCDataSource.getConnection();
	        
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        
	        pstmt.setLong(1, pk);
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	       // System.out.println("findByPK---->");
	        
	        while(rs.next())
	        {
               
	           bean = new TimetableBean() ;
               
               bean.setId(rs.getLong(1));
              
               bean.setCourseId(rs.getLong(2));
             
               bean.setCourseName(rs.getString(3));
             
               bean.setSubjectId(rs.getLong(4));
             
               bean.setSubjectName(rs.getString(5));
            
               bean.setSemester(rs.getString(6));
              
               bean.setExamDate(rs.getDate(7));
            
               bean.setExamTime(rs.getString(8));
            
               bean.setCreatedBy(rs.getString(9));
             
               bean.setModifiedBy(rs.getString(10));
             
               bean.setCreatedDatetime(rs.getTimestamp(11));
            
               bean.setModifiedDatetime(rs.getTimestamp(12));
            
            }
	      rs.close();
		 } 
	    catch (Exception e)
	    {
	        log.error("Database Exception..", e);
	        throw new ApplicationException("Exception : Exception in getting Timetable by pk");
	    } 
	    finally 
	    {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model findByPK End");
	    return bean;
}

/**
 * Find by all.
 *
 * @param bean the bean
 * @return the timetable bean
 * @throws ApplicationException the application exception
 */
public TimetableBean findByAll(TimetableBean bean) throws ApplicationException 
{
	    log.debug("Model findByAll Started");
	    
	   
	    StringBuffer sql = new StringBuffer( "SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_Id=? AND SEMESTER=? AND  EXAM_DATE=? AND EXAM_TIME=?");
	           
	    Connection conn = null;
	    
	    TimetableBean tbean = null ;

	    
	    try {
	
	        conn = JDBCDataSource.getConnection();
	        
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        
	        pstmt.setLong(1, bean.getCourseId());
	        
	        pstmt.setLong(2, bean.getSubjectId());
	        
	        pstmt.setString(3, bean.getSemester());
	        
            pstmt.setDate(4,  new java.sql.Date(bean.getExamDate().getTime()));
	        
	        pstmt.setString(5, bean.getExamTime());
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	       // System.out.println("findByAll----->");
	        
	        while(rs.next()) {
	        	
	        	   tbean = new TimetableBean() ;
	               
	        	   tbean.setId(rs.getLong(1));
	              
	        	   tbean.setCourseId(rs.getLong(2));
	             
	        	   tbean.setCourseName(rs.getString(3));
	             
	        	   tbean.setSubjectId(rs.getLong(4));
	             
	        	   tbean.setSubjectName(rs.getString(5));
	            
	        	   tbean.setSemester(rs.getString(6));
	              
	        	   tbean.setExamDate(rs.getDate(7));
	            
	        	   tbean.setExamTime(rs.getString(8));
	            
	        	   tbean.setCreatedBy(rs.getString(9));
	             
	        	   tbean.setModifiedBy(rs.getString(10));
	             
	        	   tbean.setCreatedDatetime(rs.getTimestamp(11));
	            
	        	   tbean.setModifiedDatetime(rs.getTimestamp(12));
	               
	         }
	        rs.close();
		    } 
	    catch (Exception e)
	    {
	        log.error("Database Exception..", e);
	        throw new ApplicationException("Exception : Exception in getting Timetable");
	    } 
	    finally 
	    {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model findByAll End");
	
	return tbean;
}



/**
 * Find User by Timetable Name.
 *
 * @param bean the bean
 * @return bean
 * @throws ApplicationException the application exception
 */
public TimetableBean findByCSS(TimetableBean bean) throws ApplicationException 
{
	
	    log.debug("Model findByName Started");
	   
	    StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SUBJECT_Id=? AND SEMESTER=?");
	           
	    Connection conn = null;
	    
	    TimetableBean tbean = null ;
	    
	    try {
	
	        conn = JDBCDataSource.getConnection();
	        
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        
	        pstmt.setLong(1, bean.getCourseId());
	        
	        pstmt.setLong(2, bean.getSubjectId());
	        
	        pstmt.setString(3, bean.getSemester());
	       
	        ResultSet rs = pstmt.executeQuery();
	        
	     //   System.out.println("findByCSS---->");
	        
	       
	        
	        while(rs.next()) {
	             
	        	 //  System.out.println("In While Loop ------>");
                   
	        	tbean = new TimetableBean() ;
	               
	        	tbean.setId(rs.getLong(1));
	              
	        	tbean.setCourseId(rs.getLong(2));
	             
	        	tbean.setCourseName(rs.getString(3));
	             
	        	tbean.setSubjectId(rs.getLong(4));
	             
	        	tbean.setSubjectName(rs.getString(5));
	            
	        	tbean.setSemester(rs.getString(6));
	              
	        	tbean.setExamDate(rs.getDate(7));
	            
	        	tbean.setExamTime(rs.getString(8));
	            
	        	tbean.setCreatedBy(rs.getString(9));
	             
	        	tbean.setModifiedBy(rs.getString(10));
	             
	        	tbean.setCreatedDatetime(rs.getTimestamp(11));
	            
	        	tbean.setModifiedDatetime(rs.getTimestamp(12));
	            
	        }
	        rs.close();
		    } 
	    catch (Exception e)
	    {
	        log.error("Database Exception..", e);
	        throw new ApplicationException("Exception : Exception in getting Timetable by Name");
	    } 
	    finally 
	    {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model findByName End");
	return tbean;
}

/**
 * Find by exam date time.
 *
 * @param bean the bean
 * @return the timetable bean
 * @throws ApplicationException the application exception
 */
public TimetableBean findByExamDateTime(TimetableBean bean) throws ApplicationException 
{
	    log.debug("Model findByExamDateTime Started");
	    
	    StringBuffer sql = new StringBuffer( "SELECT * FROM ST_TIMETABLE WHERE EXAM_DATE=? AND EXAM_TIME=? ");
	           
	    Connection conn = null;
	    
 	    TimetableBean tbean = null;
	    
	    try {
	
	        conn = JDBCDataSource.getConnection();
	        
	        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	        
	        pstmt.setDate(1, new java.sql.Date(bean.getExamDate().getTime()));
	        
	        pstmt.setString(2, bean.getExamTime());
	        
	        ResultSet rs = pstmt.executeQuery();
	        
	      //  System.out.println("------->");
	        
	        while(rs.next()) {
	        	
	        	//   System.out.println("In While loop Of findByExamDateTime-->");
	            
	        	   tbean = new TimetableBean() ;
	               
	        	   tbean.setId(rs.getLong(1));
	              
	        	   tbean.setCourseId(rs.getLong(2));
	             
	        	   tbean.setCourseName(rs.getString(3));
	             
	        	   tbean.setSubjectId(rs.getLong(4));
	             
	        	   tbean.setSubjectName(rs.getString(5));
	            
	        	   tbean.setSemester(rs.getString(6));
	              
	        	   tbean.setExamDate(rs.getDate(7));
	            
	        	   tbean.setExamTime(rs.getString(8));
	            
	        	   tbean.setCreatedBy(rs.getString(9));
	             
	        	   tbean.setModifiedBy(rs.getString(10));
	             
	        	   tbean.setCreatedDatetime(rs.getTimestamp(11));
	            
	        	   tbean.setModifiedDatetime(rs.getTimestamp(12));
	            
	           
	        }
	        rs.close();
		    } 
	    catch (Exception e)
	    {
	        log.error("Database Exception..", e);
	        throw new ApplicationException("Exception : Exception in getting Timetable by Exam Date and Time");
	    } 
	    finally 
	    {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model findByExamDateTime End");
	return tbean;
}

/**
 * Update a Timetable.
 *
 * @param bean the bean
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 */
public void update(TimetableBean bean) throws ApplicationException,DuplicateRecordException 
{
     log.debug("Model update Started");
   
     Connection conn = null;
     

     CourseModel cModel = new CourseModel();
    
     CourseBean courseBean = cModel.findByPK(bean.getCourseId());
     
     bean.setCourseName(courseBean.getName());


     SubjectModel subModel = new SubjectModel();
    
     SubjectBean subjectBean = subModel.findByPK(bean.getSubjectId());
     
     bean.setSubjectName(subjectBean.getName());

 
     Date examDate = new java.sql.Date(bean.getExamDate().getTime()); 
     
     TimetableBean beanExist = findByAll(bean);
    
     TimetableBean ebean = findByExamDateTime(bean);
     
    
    if (beanExist != null || ebean != null ) 
     {

        throw new DuplicateRecordException("Timetable Is Already Exist");
     }
  

    try {

        conn = JDBCDataSource.getConnection();

        conn.setAutoCommit(false); 
      
        PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_TIMETABLE SET COURSE_ID=?, COURSE_NAME=?,SUBJECT_ID=?, SUBJECT_NAME=?,SEMESTER=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
        
        pstmt.setLong(1,bean.getCourseId());
        pstmt.setString(2, bean.getCourseName());
        pstmt.setLong(3,bean.getSubjectId());
        pstmt.setString(4, bean.getSubjectName());
        pstmt.setString(5, bean.getSemester());
        pstmt.setDate(6,  new java.sql.Date(bean.getExamDate().getTime()));
        pstmt.setString(7, bean.getExamTime());
        pstmt.setString(8, bean.getCreatedBy());
        pstmt.setString(9, bean.getModifiedBy());
        pstmt.setTimestamp(10, bean.getCreatedDatetime());
        pstmt.setTimestamp(11, bean.getModifiedDatetime());
        pstmt.setLong(12, bean.getId());
        
        pstmt.executeUpdate();
        conn.commit(); 
        pstmt.close();
    } 
   catch(Exception e) 
    {
        log.error("Database Exception..", e);
        try {
            conn.rollback();
           } 
        catch(Exception ex) 
        {
            
        throw new ApplicationException("Exception : Update rollback exception " + ex.getMessage());
                    
        }
       throw new ApplicationException("Exception in updating Timetable ");
    } 
    
   finally 
     {
        
	   JDBCDataSource.closeConnection(conn);
     }
   log.debug("Model update End");
}


/**
 * Search Timetable.
 *
 * @param bean            : Search Parameters
 * @return the list
 * @throws ApplicationException the application exception
 */

 public List search(TimetableBean bean) throws ApplicationException 
	{
	  return search(bean, 0, 0);
	}

 /**
  * Search TimeTable with pagination.
  *
  * @param bean            : Search Parameters
  * @param pageNo            : Current Page No.
  * @param pageSize            : Size of Page
  * @return list : List of Users
  * @throws ApplicationException the application exception
  */
public List search(TimetableBean bean, int pageNo, int pageSize) throws ApplicationException 

{
	log.debug("Model search Started");
	
	StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");
	
	if (bean != null) 
	{
		 if (bean.getId() > 0) 
		 {
		     sql.append(" AND id = " + bean.getId());
		 }
		 if (bean.getCourseId() > 0) 
		 {
		     sql.append(" AND COURSE_ID = " + bean.getCourseId());
		 }
		
		 if (bean.getSubjectId() > 0) 
		 {
		     sql.append(" AND SUBJECT_ID = " + bean.getSubjectId());
		 }
		
		 if (bean.getCourseName() != null && bean.getCourseName().length() > 0) 
		 {
		     sql.append(" AND COURSE_NAME like " + bean.getCourseName()+ "%'");
		 }
		
		 if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) 
		 {
		     sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
		 }
		
		 if (bean.getSemester() != null && bean.getSemester().length() > 0) 
		 {
		     sql.append(" AND SEMESTER like " + bean.getSemester()+ "%'");
		 }
		 
		 if (bean.getExamDate() != null && bean.getExamDate().getTime() > 0) 
		 {
		     sql.append(" AND EXAM_DATE like '" + new Date(bean.getExamDate().getTime()) + "%'");
		 }
		 if (bean.getExamTime() != null && bean.getExamTime().length() > 0) 
		 {
		     sql.append(" AND EXAM_TIME like '" + bean.getExamTime() + "%'");
		 }

	}
	
	
	if (pageSize > 0) 
	{
	pageNo = (pageNo - 1) * pageSize;
	
	 sql.append(" Limit " + pageNo + ", " + pageSize);
	
	}
	
	ArrayList list = new ArrayList();
	Connection conn = null;
	try {
	 conn = JDBCDataSource.getConnection();
	 PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	 ResultSet rs = pstmt.executeQuery();
	 while (rs.next()) 
	 {
	     bean = new TimetableBean();
	    
	     bean.setId(rs.getLong(1));
	     bean.setCourseId(rs.getLong(2));
	     bean.setCourseName(rs.getString(3));
	     bean.setSubjectId(rs.getLong(4));
	     bean.setSubjectName(rs.getString(5));
	     bean.setSemester(rs.getString(6));
	   
	     bean.setExamDate(rs.getDate(7));
	     bean.setExamTime(rs.getString(8));
	     bean.setCreatedBy(rs.getString(9));
         bean.setModifiedBy(rs.getString(10));
         bean.setCreatedDatetime(rs.getTimestamp(11));
         bean.setModifiedDatetime(rs.getTimestamp(12));
         
	    
	     list.add(bean);
	 }
	 rs.close();
	} catch (Exception e) {
	 log.error("Database Exception..", e);
	 throw new ApplicationException(
	         "Exception : Exception in search Timetable");
	} finally {
	 JDBCDataSource.closeConnection(conn);
	}
	
	log.debug("Model search End");
	return list;
}
	

/**
 * Get List of TimeTable.
 *
 * @return list : List of TimeTable
 * @throws ApplicationException the application exception
 */
public List list() throws ApplicationException 
 {
   return list(0, 0);
 }

/**
 * Get List of TimeTable with pagination.
 *
 * @param pageNo            : Current Page No.
 * @param pageSize            : Size of Page
 * @return list : List of TimeTable
 * @throws ApplicationException the application exception
 */
public List list(int pageNo, int pageSize) throws ApplicationException 
{
			log.debug("Model list Started");
			ArrayList list = new ArrayList();
			StringBuffer sql = new StringBuffer("select * from St_TIMETABLE");
			
			if (pageSize > 0)
			{
			 
			 pageNo = (pageNo - 1) * pageSize;
			 sql.append(" limit " + pageNo + "," + pageSize);
			}
			
			Connection conn = null;
			
			try {
			 conn = JDBCDataSource.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			 ResultSet rs = pstmt.executeQuery();
			 TimetableBean bean = null;
			 while (rs.next()) 
			 {
			     bean = new TimetableBean();
			    
			     bean.setId(rs.getLong(1));
			     bean.setCourseId(rs.getLong(2));
			     bean.setCourseName(rs.getString(3));
			     bean.setSubjectId(rs.getLong(4));
			     bean.setSubjectName(rs.getString(5));
			     bean.setSemester(rs.getString(6));
			    
			     bean.setExamDate(rs.getDate(7));
			     bean.setExamTime(rs.getString(8));
			     bean.setCreatedBy(rs.getString(9));
		         bean.setModifiedBy(rs.getString(10));
		         bean.setCreatedDatetime(rs.getTimestamp(11));
		         bean.setModifiedDatetime(rs.getTimestamp(12));
		         
		         list.add(bean);
			    
			 }
			rs.close();
		 } 
		catch (Exception e) 
		  {
			 log.error("Database Exception..", e);
			 throw new ApplicationException("Exception : Exception in getting list of Timetable");
		  } 
		finally 
		 {
			 JDBCDataSource.closeConnection(conn);
		 }
			
			log.debug("Model list End");
	return list;
			
	 }
  
  
}
