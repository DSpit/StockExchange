

package com.dspit.stockExchange.data;

import java.util.ArrayList;

/**
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class CompanyList extends ArrayList<Company> 
{
	// Constructors ---------------------------------------------------------------- //
	
		/**
		 * Constructor that creates a specified amount of Company objects with name "Company #"
		 * and adds them to the ArrayList of Company objects companyArray
		 * 
		 * @param numberCompanies : The amount of company objects to be created and added to the
		 * companyArray
		 */
		public CompanyList(int numberCompanies)
		{
			for(int i = 0; i < numberCompanies; i++)
			{
				this.add(new Company("Company " + Integer.toString(i + 1)));
			}
		}
		
		/**
		 * Constructor which initializes multiple company objects according to a String
		 * array of company names and adds it to the companyArray containing Company objects
		 * 
		 * @param names : The String array containing names of companies to be created
		 */
		public CompanyList(String[] names)
		{		
			for (int i = 0; i < names.length; i++)
				this.add(new Company(names[i]));
		}
		
		/**
		 * Constructor which initializes a single company with specified name and adds
		 * the created company object to companyArray
		 * 
		 * @param name : The name of company to be created
		 */
		public CompanyList(String name)
		{
			this.add(new Company(name));
		}
		
	// Methods ---------------------------------------------------------------- //
	}



//	/**
//* Generates 5 new companies for the 
//*/
//public CompanyList()
//{
//	super(5);
//	
//}
