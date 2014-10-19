

package com.dspit.stockExchange.uicomponents;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.dspit.stockExchange.data.Transaction;

/**
 * Class made to generally describe what a report window will look like.
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public abstract class ReportWindow extends JFrame {

// Constants --------------------------------------------------------------- //
	
	
	
// Members ----------------------------------------------------------------- //
	
	protected ArrayList<Transaction> mLog;
	
// GUI Components ---------------------------------------------------------- //
	
	private ReportPanel mMainPanel;
	
// Constructor ------------------------------------------------------------- //
	
	public ReportWindow(ArrayList<Transaction> log, String title, boolean isSuccessful){
		super(title);
		
		mLog = log;
		
		//set usual frame info
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//build main panel
		mMainPanel = new ReportPanel(isSuccessful);
		this.populateReport();
		
		//add main panel to frame
		this.add(mMainPanel);
		
		//finish frame setup
		this.pack();
		this.setVisible(true);
		
		
	}
	
// Protected Methods ------------------------------------------------------- //
	
	/**
	 * A method which defines what is considered a valid transaction
	 * which should be placed within the report.
	 *
	 * @param trans A Transaction to evaluate.
	 * 
	 * @return <b>true</b> if the transaction is considered valid by the
	 * implementation of this class and <b>false</b> otherwise.
	 */
	protected abstract boolean isValid(Transaction trans);
	
// Private Methods --------------------------------------------------------- //
	
	/**
	 * Adds  the proper transactions to the report, based on the
	 * {@link #isValid(Transaction))} which is defined my the implementation 
	 * of this class.
	 */
	private void populateReport(){
		
		for(Transaction trans : mLog){
			if(this.isValid(trans)){
				mMainPanel.include(trans);
			}
		}
	}
	
}
