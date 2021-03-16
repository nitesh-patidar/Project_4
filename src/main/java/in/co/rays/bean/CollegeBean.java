package in.co.rays.bean;


// TODO: Auto-generated Javadoc
/**
 * College JavaBean encapsulates College attributes.
 *
 * @author  Proxy
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class CollegeBean extends BaseBean
{
	
	 /** The Constant serialVersionUID. */
 	private static final long serialVersionUID = 1L;
	
	 /** Name of College. */
	 private String name;
	
	  /** Address of College. */
	 private String address;
	
	 /** City of College. */
	 private String city;
	
	 /** State of College. */
	 private String state;
	 
	   /** PhoneNo of College. */
	 private String phoneNo;
	
	 
	  /**
  	 * accessor.
  	 *
  	 * @return the name
  	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() 
	{
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() 
	{
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState()
	{
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state)
	{
		this.state = state;
	}
	
	/**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
	public String getPhoneNo() 
	{
		return phoneNo;
	}
	
	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo the new phone no
	 */
	public void setPhoneNo(String phoneNo) 
	{
		this.phoneNo = phoneNo;
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
