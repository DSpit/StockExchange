

package com.dspit.stockExchange.mainframe;

import javax.swing.JFrame;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.Log;
import com.dspit.stockExchange.data.PortfolioList;

/**
 * Main Stock Exchange SubSystem. Runs all GUI and 
 * computational processes
 *
 * @author David Boivin (Spit)
 */
public class MainFrame{
	
// Constants --------------------------------------------------------------- //
	
	public final long WAIT_TIME = 3600;
	
// Members ----------------------------------------------------------------- //
	
	private CompanyList mCompanies;
	private PortfolioList mPortfolios;
	private Log mLog;
	
// Constructors ------------------------------------------------------------ //
	
	public MainFrame(){
		
		/*			NOTE:				*/
		/* If Saving retrieve is implemented, it goes here 	*/
		/*							*/					
		
		mCompanies = new CompanyList();
		mPortfolios = new PortfolioList();
		mLog = new Log();
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Displays a GUI with all the information to start off the investor 
	 * stock exchange program.
	 */
	public void mainWindow(){
		MainWindow window = new MianWindow(mLog, mPortfolios, mCompanies);
	}
	
	/**
	 * Displays the log of all the user's activity during this session
	 */
	public void exit(){
		ReportWindow successfulTrans = new SuccessReportWindow(mLog);
		this.wait(WAIT_TIME);
		successfulTrans.dispose();
		
		ReportWindow failedTrans = new FailedReportWindow(mLog);
		this.wait(WAIT_TIME);
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
