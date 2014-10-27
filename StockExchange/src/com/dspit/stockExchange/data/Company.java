

package com.dspit.stockExchange.data;

/**
 * A class which represents a company in the stock exchange
 *
 * @author David Boivin (Spit)
 */
public class Company
{
	// Static Members ---------------------------------------------------------------- //
	
	private static int sCompId = 0;
	
	// Members ---------------------------------------------------------------- //

	private String mName;
	private int mCompId;
	
	// Constructors ---------------------------------------------------------------- //
	
	/**
	 * Constructor which initializes the company name to default value "Burger King"
	 * and sets it's respective Company ID number
	 */
	public Company()
	{
		mName = "Burger King";
		mCompId = ++sCompId;
	}
	
	/**
	 * Constructor which initializes the company name to specified value
	 * and sets it's respective Company ID number
	 * 
	 * @param name : The company's name
	 */
	public Company(String name)
	{
		mName = name;
		mCompId = ++sCompId;
	}

	// Methods ---------------------------------------------------------------- //
	
	/**
	 * Method which determines if two companies are equal on the basis of their respective
	 * Company ID numbers and their Company names. 
	 * 
	 * @param comp : Company object to be compared with calling object
	 * @return : Boolean value (true if companies are equal)
	 */
	protected boolean equals(Company comp)
	{
		if(mCompId == comp.getCompanyId())
			return true;
		else if(mName.equalsIgnoreCase(comp.getName()))
			return true;
		else
			return false;
	}
	
	/**
	 * Method returning Company name
	 * @return : Company name
	 */
	public String getName()
	{
		return mName;
	}
	
	/**
	 * Method returning company ID number
	 * @return : Company ID number
	 */
	protected int getCompanyId()
	{
		return mCompId;
	}
	
// Overrides --------------------------------------------------------------- //
	
	/**
	 * the String representation of this class.
	 * 
	 * @return The name of the company
	 */
	@Override
	public String toString(){
		return mName;
	}
}
