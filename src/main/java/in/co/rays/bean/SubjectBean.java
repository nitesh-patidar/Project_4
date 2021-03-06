package in.co.rays.bean;


// TODO: Auto-generated Javadoc
/**
 * Subject JavaBean encapsulates Subject attributes.
 *
 * @author Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class SubjectBean extends BaseBean {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  Name of Subject. */
	private String name;
	
	/** CourseId of Subject. */
	private long courseId;
	
	/** CourseName of Subject. */
	private String courseName;
	
	/** Description of Subject. */
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
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
		
		return id+"";
	}

	/* (non-Javadoc)
	 * @see in.co.rays.bean.DropdownListBean#getValue()
	 */
	public String getValue() {
		
		return name;
	}

}
