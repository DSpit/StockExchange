

package com.dspit.stockExchange.mainframe;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.dspit.stockExchange.data.BuyTransaction;
import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;
import com.dspit.stockExchange.data.SellTransaction;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.uicomponents.MainWindow;

/**
 * Main Stock Exchange SubSystem. Runs all GUI and 
 * computational processes
 *
 * @author David Boivin (Spit)
 */
public class MainFrame{
	
// Constants --------------------------------------------------------------- //
	
	private final String[] DEFAULT_COMPANY_NAMES = {"Asus", "Intel", "IBM", "Apple", "Google"};
	private final String[] DEFAULT_PORTFOLIO_NAMES = {"Ariel", "Matthew", "Marco", "Mary-Jane", "Molly"};
	
// Members ----------------------------------------------------------------- //
	
	private CompanyList  mCompanies;
	private PortfolioList mPortfolios;
	private ArrayList<Transaction> mLog;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Default constructor which initializes all the members with proper values.
	 */
	public MainFrame(){
		
		/*			NOTE:				*/
		/* If Saving retrieve is implemented, it goes here 	*/
		/*							*/					
		
		mCompanies = new CompanyList(DEFAULT_COMPANY_NAMES);
		mPortfolios = new PortfolioList(DEFAULT_PORTFOLIO_NAMES);
		mLog = new ArrayList<Transaction>();
		
		//create mock transactions
		
		Transaction trans1 = new BuyTransaction(mPortfolios.get(0), mCompanies.get(2), 50, new BigDecimal(5.00));
		mPortfolios.get(0).add(trans1);
		
		Transaction trans2 = new BuyTransaction(mPortfolios.get(0), mCompanies.get(2), 70, new BigDecimal(6.00));
		mPortfolios.get(0).add(trans2);
		
		Transaction trans3 = new SellTransaction(mPortfolios.get(0), mCompanies.get(2), new BigDecimal(7.00));
		mPortfolios.get(0).add(trans3);
		
		Transaction trans4 = new BuyTransaction(mPortfolios.get(2), mCompanies.get(4), 60, new BigDecimal(100.00));
		mPortfolios.get(2).add(trans4);
		
		Transaction trans5 = new BuyTransaction(mPortfolios.get(1), mCompanies.get(1), 1000, new BigDecimal(4.00));
		mPortfolios.get(1).add(trans5);
		
		Transaction trans6 = new SellTransaction(mPortfolios.get(1), mCompanies.get(2), new BigDecimal(4.00));
		mPortfolios.get(1).add(trans6);
		
		mLog.add(trans1);
		mLog.add(trans2);
		mLog.add(trans3);
		mLog.add(trans4);
		mLog.add(trans5);
		mLog.add(trans6);
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Displays the log of all the user's activity during this session
	 */
	public void run(){
		
		//Display main window
		new MainWindow(mLog, mPortfolios, mCompanies);
		
	}
	
// MAIN -------------------------------------------------------------------- //
	
	public static void main(String[] args){
		
		MainFrame exchange = new MainFrame();
		
		exchange.run();
	}
}
