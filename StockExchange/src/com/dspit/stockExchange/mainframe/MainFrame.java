

package com.dspit.stockExchange.mainframe;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;

/**
 * Main Stock Exchange SubSystem. Runs all GUI and 
 * computational processes
 *
 * @author David Boivin (Spit)
 */
public class MainFrame{
	
	
// Members ----------------------------------------------------------------- //
	
	private CompanyList mCompanies;
	private PortfolioList mPortfolios;
	
// Constructors ------------------------------------------------------------ //
	
	public MainFrame(){
		mCompanies = new CompanyList();
		mPortfolios = new PortfolioList();
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Displays a GUI with all the information to start off the investor 
	 * stock exchange program.
	 */
	public void start(){
		
	}
	
	/**
	 * Displays the log of all the user's activity during this session
	 */
	public void exit(){
		
	}
	
// MAIN -------------------------------------------------------------------- //
	
	public static void main(String[] args){
		
		MainFrame exchange = new MainFrame();
		
		exchange.start();
		exchange.exit();
		
	}
}
