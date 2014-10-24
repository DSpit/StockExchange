package com.dspit.stockExchange.uicomponents;

import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
	
	private ArrayList<Transaction> mLog;
	private PortfolioList mPortfolios;
	private CompanyList mCompanies;
	
// UI Components ----------------------------------------------------------- //
	
	private JPanel mMainPanel;
	
// Constructor ------------------------------------------------------------- //
	
	public MainWindow(ArrayList<Transaction> log, PortfolioList portfolios, CompanyList companies){
		super(TITLE);
		
		mLog = log;
		mPortfolios = portfolios;
		mCompanies = companies;
		
		mMainPanel = this.createSelectionPanel();
	}
	
// Private Methods --------------------------------------------------------- //
	
	private JPanel createSelectionPanel(){
		
		JPanel mainPanel = new JPanel();
		LayoutManager lm = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		
		mainPanel.add(new PortfolioSelectionPanel(mPortfolios));
		mainPanel.add(new CompanySelectorPanel(mCompanies));
		mainPanel.add(controls)
		
		
		
		return mainPanel;
	}
	
}
