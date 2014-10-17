

package com.dspit.stockExchange.mainframe;

import java.util.ArrayList;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.uicomponents.FailedTransactionReport;
import com.dspit.stockExchange.uicomponents.ReportWindow;
import com.dspit.stockExchange.uicomponents.SuccessfulTransactionReport;

/**
 * Main Stock Exchange SubSystem. Runs all GUI and 
 * computational processes
 *
 * @author David Boivin (Spit)
 */
public class MainFrame{
	
// Constants --------------------------------------------------------------- //
	
	public final long WAIT_TIME = 3600;	//TODO fine-tune this value
	
// Members ----------------------------------------------------------------- //
	
	private CompanyList mCompanies;
	private PortfolioList mPortfolios;
	private ArrayList<Transaction> mLog;
	
// Constructors ------------------------------------------------------------ //
	
	public MainFrame(){
		
		/*			NOTE:				*/
		/* If Saving retrieve is implemented, it goes here 	*/
		/*							*/					
		
		mCompanies = new CompanyList();
		mPortfolios = new PortfolioList();
		mLog = new ArrayList<Transaction>();
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Displays a GUI with all the information to start off the investor 
	 * stock exchange program.
	 */
	public void mainWindow(){
		MainWindow window = new MainWindow(mLog, mPortfolios, mCompanies);
	}
	
	/**
	 * Displays the log of all the user's activity during this session
	 */
	public void exit(){
		ReportWindow successfulTrans = new SuccessfulTransactionReport(mLog);
		try {
			this.wait(WAIT_TIME);
		} catch (InterruptedException e) {
			System.out.println(e);
			//do nothing
		}
		successfulTrans.dispose();
		
		ReportWindow failedTrans = new FailedTransactionReport(mLog);
		try {
			this.wait(WAIT_TIME);
		} catch (InterruptedException e) {
			System.out.println(e);
			//do nothing
		}
		failedTrans.dispose();
		
		/* 			NOTE:					*/
		/*  add portfolio & company saving code here if you so wish	*/
		/*								*/
		
	}
	
// MAIN -------------------------------------------------------------------- //
	
	public static void main(String[] args){
		
		MainFrame exchange = new MainFrame();
		
		exchange.mainWindow();
		exchange.exit();
	}
}
