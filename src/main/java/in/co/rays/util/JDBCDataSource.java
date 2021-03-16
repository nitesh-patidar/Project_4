package in.co.rays.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.co.rays.exception.ApplicationException;

// TODO: Auto-generated Javadoc

/**
 * JDBC DataSource is a Data Connection Pool.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public final class JDBCDataSource {
	
	/** JDBC Database connection pool ( DCP ). */
	private static JDBCDataSource dataSource;
	
	/** The cpds. */
	private ComboPooledDataSource cpds = null;

	/**
	 * Instantiates a new JDBC data source.
	 */
	private JDBCDataSource() {

	}

	/**
	 * Create instance of Connection Pool.
	 *
	 * @return single instance of JDBCDataSource
	 */
	public static JDBCDataSource getInstance() 
	{
		if (dataSource == null) 
		{
	    	ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");
			
		    dataSource = new JDBCDataSource();
			
			dataSource.cpds = new ComboPooledDataSource();
           
			try {
				dataSource.cpds.setDriverClass(rb.getString("driver"));
				} 
           catch (Exception e) 
            {
				e.printStackTrace();
			}
	
		dataSource.cpds.setJdbcUrl(rb.getString("url"));
		
		dataSource.cpds.setUser(rb.getString("username"));
		
		dataSource.cpds.setPassword(rb.getString("password"));
		
		dataSource.cpds.setInitialPoolSize(new Integer((String)rb.getString("initialPoolSize")));
		
		dataSource.cpds.setAcquireIncrement(new Integer((String)rb.getString("acquireIncrement")));
	
		dataSource.cpds.setMaxPoolSize(new Integer((String)rb.getString("maxPoolSize")));
		
	    dataSource.cpds.setMinPoolSize(new Integer((String)rb.getString("minPoolSize")));
		
	    dataSource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
		
	   }
		return dataSource;
	}

   /**
    * Gets the connection from ComboPooledDataSource.
    *
    * @return connection
    * @throws Exception the exception
    */
   public static Connection getConnection() throws Exception 
   {
	  return getInstance().cpds.getConnection();
   }

  /**
   * Closes a connection.
   *
   * @param connection the connection
   */
  public static void  closeConnection(Connection connection)
  {
	if(connection!=null) 
	{
		try {
			connection.close();
		   } 
		catch (Exception e) 
		{
			
		}
	}
  }

/**
 * Trn rollback.
 *
 * @param connection the connection
 * @throws ApplicationException the application exception
 */
public static void  trnRollback(Connection connection) throws ApplicationException
{
	if(connection!=null) 
	{
		try {
			connection.rollback();
		   } 
		catch (SQLException ex) 
		{
		  throw new ApplicationException(ex.toString());
		}
	}
}

}
