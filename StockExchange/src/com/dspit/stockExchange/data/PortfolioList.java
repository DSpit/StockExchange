

package com.dspit.stockExchange.data;

import java.util.ArrayList;

/**
 * A list of Portfolio, allows for easy manipulation of Portfolio data and
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class PortfolioList extends ArrayList<Portfolio> 
{
	// Members ---------------------------------------------------------------- //
	
		private ArrayList<Portfolio> portfolioArray = new ArrayList<Portfolio>();
		
	// Constructors ---------------------------------------------------------------- //
		
	/**
	 * Constructor that creates a specified amount of Portfolio objects with name "Portfolio #"
	 * and adds them to the ArrayList of Portfolio objects portfolioArray
	 * 
	 * @param numberPortfolios : The amount of Portfolio objects to be created and added to the
	 * portfolioArray
	 */
	public PortfolioList(int numberPortfolios)
	{
		for(int i = 0; i < numberPortfolios; i++)
		{
			portfolioArray.add(new Portfolio("Portfolio " + Integer.toString(i + 1)));
		}
	}
	
	/**
	 * Constructor which initializes multiple portfolio objects according to a String
	 * array of portfolio names and adds it to the portfolioArray
	 * 
	 * @param names : The String array containing names of portfolios to be created
	 */
	public PortfolioList(String[] names)
	{		
		for (int i = 0; i < names.length; i++)
			portfolioArray.add(new Portfolio(names[i]));
	}
	
	/**
	 * Constructor which initializes a single portfolio with specified name and adds
	 * the created portfolio object to portfolioArray
	 * 
	 * @param name : The name of portfolio to be created
	 */
	public PortfolioList(String name)
	{
		portfolioArray.add(new Portfolio(name));
	}
}

///**
// * Generates 5 different portfolios and adds then to the list
// */
//public PortfolioList(){
//	super(5);
