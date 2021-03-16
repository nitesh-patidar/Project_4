package in.co.rays.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class CourseBean.
 * 
 * @author  Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class CourseBean extends BaseBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	 /** The name. */
 	private String name;
	 
 	/** The duration. */
 	private String duration;
	 
 	/** The description. */
 	private String description;
	
	 

	/**
	 * Gets the name.
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
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(String duration) {
		this.duration = duration;
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
	 * @see in.co.rays.bean.DropdownListBean#getKey()
	 */
	public String getKey() {
		
		return ""+id;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.bean.DropdownListBean#getValue()
	 */
	public String getValue() {
		
		return name;
	}

	
	
}
