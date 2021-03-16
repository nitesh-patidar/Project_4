package in.co.rays.exception;

// TODO: Auto-generated Javadoc
/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class ApplicationException extends Exception
{
  
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	 /**
 	 * Instantiates a new application exception.
 	 *
 	 * @param msg      : Error message
 	 */	
   public ApplicationException(String msg)
   {
	super(msg);
   }
}
