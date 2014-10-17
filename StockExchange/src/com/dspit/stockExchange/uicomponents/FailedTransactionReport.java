

package com.dspit.stockExchange.uicomponents;

import java.util.ArrayList;

import com.dspit.stockExchange.data.Transaction;

/**
 * The Basic GUI that displays the failed transactions completed during
 * the lifetime of this program.
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class FailedTransactionReport extends ReportWindow {
	
// Constants --------------------------------------------------------------- //
	
	public static final String TITLE= "Failed Transaction Report";

// Constructors ------------------------------------------------------------ //
	
	public FailedTransactionReport(ArrayList<Transaction> log) {
		super(log, TITLE, false);
	}

// Overrides --------------------------------------------------------------- //
	
	/**
	 * The transaction is considered valid if the transaction was 
	 * unsuccessful.
	 * 
	 * @see Transaction#isSuccessful()
	 * @see ReprotWindow#isValid(Transaction)
	 */
	@Override
	protected boolean isValid(Transaction trans) {
		
		if(trans.isSuccessful()){
			return false;
		}
		
		return true;
	}

}
