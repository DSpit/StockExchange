

package com.dspit.stockExchange.mainframe;

import java.util.ArrayList;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.uicomponents.FailedTransactionReport;
import com.dspit.stockExchange.uicomponents.MainWindow;
import com.dspit.stockExchange.uicomponents.SuccessfulTransactionReport;
import com.dspit.stockExchange.uicomponents.TransactionReport;

/**
 * Main Stock Exchange SubSystem. Runs all GUI and 
 * computational processes
 *
 * @author David Boivin (Spit)
 */
public class MainFrame{
	
// Constants --------------------------------------------------------------- //
	
	public final long WAIT_TIME = 3600;	//TODO fine-tune this value
	private final String[] DEFAULT_COMPANY_NAMES = {"Asus", "Intel", "IBM", "Apple", "Google"};
	private final String[] DEFAULT_PORTFOLIO_NAMES = {"Ariel", "Matthew", "Marco", "Mary-Jane", "Molly"};
	
// Members ----------------------------------------------------------------- //
	
	private CompanyList mCompanies;
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
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Displays the log of all the user's activity during this session
	 */
	public void run(){
		
		//Display main window
		new MainWindow(mLog, mPortfolios, mCompanies);
		
		//FIXME main window not displaying companies and portfolios
		
		//display successful transactions
		TransactionReport successfulTrans = new SuccessfulTransactionReport(mLog);
		
		//wait for the user to see and understand report
		try {							//FIXME wait time implementation
			this.wait(WAIT_TIME);
		} catch (InterruptedException e) {
			System.out.println(e);
			//do nothing
		}
		successfulTrans.dispose();	//dispose of the window
		
		//display failed transactions
		TransactionReport failedTrans = new FailedTransactionReport(mLog);
		
		//wait for the user to see and understand report
		try {
			this.wait(WAIT_TIME);
		} catch (InterruptedException e) {
			System.out.println(e);
			//do nothing
		}
		failedTrans.dispose();	//dispose of the window
		
		/* 			NOTE:					*/
		/*  add portfolio & company saving code here if you so wish	*/
		/*								*/
		
	}
	
// MAIN -------------------------------------------------------------------- //
	
	public static void main(String[] args){
		
		MainFrame exchange = new MainFrame();
		
		exchange.run();
	}
}
