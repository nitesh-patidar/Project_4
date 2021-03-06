package in.co.rays.bean;


// TODO: Auto-generated Javadoc
/**
 * Role JavaBean encapsulates Role attributes.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class RoleBean extends BaseBean 
{
	    
    	/** The Constant serialVersionUID. */
    	private static final long serialVersionUID = 1L;
	
	    /** Predefined Role constants. */
	    public static final int ADMIN=1;
		
		/** The Constant STUDENT. */
		public static final int STUDENT=2;
		
		/** The Constant COLLEGE. */
		public static final int COLLEGE=3;
		
		/** The Constant KIOSK. */
		public static final int KIOSK=4;
		
		/** The Constant FACULTY. */
		public static final int FACULTY=5;
		
		/** Role Name. */
        private String name;
		
		/** Role Description. */
		private String description;
		
		   /**
   		 * accessor.
   		 *
   		 * @return the name
   		 */

		public String getName() {
			return name;
		}
		
		/**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * Gets the description.
		 *
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		
		/**
		 * Sets the description.
		 *
		 * @param description the new description
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		
		/* (non-Javadoc)
		 * @see in.co.rays.bean.BaseBean#compareTo(in.co.rays.bean.BaseBean)
		 */
		public int compareTo(BaseBean o)
		{
			return 0;
		}
		
		/* (non-Javadoc)
		 * @see in.co.rays.bean.DropdownListBean#getKey()
		 */
		public String getKey() 
		{
			
			return id+"";
		}
		
		/* (non-Javadoc)
		 * @see in.co.rays.bean.DropdownListBean#getValue()
		 */
		public String getValue()
		{
			
			return name;
		}


}
