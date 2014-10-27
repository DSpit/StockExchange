

package com.dspit.stockExchange.uicomponents;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.Company;
import com.dspit.stockExchange.data.Portfolio;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.data.TransactionBuilder;

/**
 * This panel is where the user will decide what each portfolio
 * is selling and buying stocks for.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class TransactionInputPanel extends JPanel{

	
// Members ----------------------------------------------------------------- //
	
	
	
// UI Components ----------------------------------------------------------- //
	
	private TabPanel mTPanel;	//almost called this TPain xP
	private ControlPanel mControlPanel;
	
// Constructor ------------------------------------------------------------- //

	/**
	 * Main constructor which initializes each member variable and sets up
	 * UI components.
	 * 
	 * @param selectedPortfolios The Portfolios the user wishes to use.
	 * @param selectedCompanies The Companies which the user wishes 
	 * 	the selectedPortfolios to buy and sell stocks for.
	 * @param log The log which will contain all the transactions which occur 
	 * 	when user finishes entering all the information. 
	 */
	public TransactionInputPanel(ArrayList<Portfolio> selectedPortfolios, ArrayList<Company> selectedCompanies,
			ArrayList<Transaction> log, ActionListener controlListener) {
		super();
		
		//set the layout to this panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		mTPanel = new TabPanel(selectedPortfolios, selectedCompanies);
		mControlPanel = new ControlPanel(ControlPanel.CONTROL_BUTTON_DONE, controlListener);
		
		this.add(mTPanel);
		this.add(mControlPanel);
	}
	
// Public Methods ---------------------------------------------------------- //
	
	public HashMap<Portfolio, ArrayList<TransactionBuilder>> getInputValues() throws IllegalArgumentException{
		try{
			return mTPanel.getValues();
		}catch(IllegalArgumentException e){
			throw e;
		}
	}
	
	public ControlPanel getControlPanel(){
		return mControlPanel;
	}


}
