package com.dspit.stockExchange.uicomponents;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;
import com.dspit.stockExchange.data.Transaction;

/**
 * The main Graphical interface of this system. This is where
 * the user will create transactions and 
 * 
 * @author David Boivin (dSpit)
 */
public class MainWindow extends JFrame {
	
// Constants --------------------------------------------------------------- //
	
	private static String TITLE = "Stock Exchange";
	
// Members ----------------------------------------------------------------- //
	
	
	
// UI Components ----------------------------------------------------------- //
	
	private JPanel mMainPanel;  
	
// Constructor ------------------------------------------------------------- //
	
	public MainWindow(ArrayList<Transaction> log, PortfolioList protfolios, CompanyList companies){
		super(TITLE);
		
		mMainPanel = this.createSelectionPanel();
	}
	
// Private Methods --------------------------------------------------------- //
	
	
	
}
