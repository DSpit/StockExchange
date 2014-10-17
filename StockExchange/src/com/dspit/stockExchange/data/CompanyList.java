

package com.dspit.stockExchange.data;

import java.util.ArrayList;

/**
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class CompanyList extends ArrayList<Company> {
	
	/**
	 * Generates 5 new companies for the 
	 */
	public CompanyList(){
		super(5);
		
	}
}
