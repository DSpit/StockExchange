

package com.dspit.stockExchange.uicomponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;

import com.dspit.stockExchange.data.Company;
import com.dspit.stockExchange.data.Portfolio;

/**
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class TabPanel extends JPanel {

	
// Constructors ------------------------------------------------------------ //
	
	/**
	 *
	 * @param selectedPortfolios
	 * @param selectedCompanies
	 */
	public TabPanel(ArrayList<Portfolio> selectedPortfolios,
			ArrayList<Company> selectedCompanies) {
		// TODO Auto-generated constructor stub
	}

// Public Methods ---------------------------------------------------------- //
	
	/**
	 *
	 * @return
	 */
	public HashMap<Portfolio, Vector<String>> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

}
