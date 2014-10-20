

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
public class FailedTransactionReport extends TransactionReport {
	
// Constants --------------------------------------------------------------- //
	
	public static final String TITLE= "Failed Transaction Report";

// Constructors ------------------------------------------------------------ //
	
	/**
	 * Basically does exactly what {@link TransactionReport#TransactionReport(ArrayList, String, boolean)}
	 * does with the the success variable set to true because this is the
	 * Successful transaction report.
	 * 
	 * @param log The list of transactions
	 */
	public FailedTransactionReport(ArrayList<Transaction> log) {
		super(log, TITLE, false);
	}

// Overrides --------------------------------------------------------------- //
	
	/**
	 * The transaction is considered valid if the transaction was 
	 * unsuccessful.
	 * 
	 * @see Transaction#isValid()
	 * @see ReprotWindow#isValid(Transaction)
	 */
	@Override
	protected boolean isValid(Transaction trans) {
		
		if(trans.isValid()){
			return false;
		}
		
		return true;
	}

}
