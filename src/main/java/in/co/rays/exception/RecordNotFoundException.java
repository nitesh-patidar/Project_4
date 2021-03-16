package in.co.rays.exception;

// TODO: Auto-generated Javadoc
/**
 * RecordNotFoundException thrown when a record not found occurred.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class RecordNotFoundException extends Exception 
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

   /**
    * Instantiates a new record not found exception.
    *
    * @param msg    :error message
    */
	public RecordNotFoundException( String msg) 
	{
		super(msg);
	}

}
