package in.co.rays.exception;

// TODO: Auto-generated Javadoc
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class DatabaseException extends Exception {

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

   /**
    * Instantiates a new database exception.
    *
    * @param msg        : Error message
    */
	public DatabaseException(String msg)
	{
		super(msg);
	}

}
