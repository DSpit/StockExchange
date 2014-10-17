

package com.dspit.stockExchange.data;

import java.util.ArrayList;

/**
 * A list of Portfolio, allows for easy manipulation of Portfolio data and
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class PortfolioList extends ArrayList<Portfolio> {
	
	/**
	 * Generates 5 different portfolios and adds then to the list
	 */
	public PortfolioList(){
		super(5);
		
		
	}
}
