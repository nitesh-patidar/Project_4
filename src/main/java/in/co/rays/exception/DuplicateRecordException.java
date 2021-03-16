package in.co.rays.exception;


// TODO: Auto-generated Javadoc
/**
 * DuplicateRecordException thrown when a duplicate record occurred.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class DuplicateRecordException  extends Exception
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

   /**
    * Instantiates a new duplicate record exception.
    *
    * @param msg    : error message
    */
	public DuplicateRecordException( String msg) 
	{
		super(msg);
	}

}
