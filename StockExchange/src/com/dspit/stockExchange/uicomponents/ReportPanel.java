

package com.dspit.stockExchange.uicomponents;

import javax.swing.JPanel;

import com.dspit.stockExchange.data.Transaction;

/**
 * The main GUI look and feel of the report section of this program.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class ReportPanel extends JPanel {
	
// Constants --------------------------------------------------------------- //
	
	
	
// Members ----------------------------------------------------------------- //
	
	
	
// Constructors ------------------------------------------------------------ //
	
	public ReportPanel(){
		super();
	}
	
// Public Methods ---------------------------------------------------------- //
	
	public void include(Transaction trans){
		
	}
	
	public void setEmpty(){
		
	}
	
	public boolean isEmpty(){
		return false;
	}

}
