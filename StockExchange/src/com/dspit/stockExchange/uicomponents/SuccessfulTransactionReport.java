

package com.dspit.stockExchange.uicomponents;

import java.util.ArrayList;

import com.dspit.stockExchange.data.Transaction;

/**
 * The Basic GUI that displays the successful transactions completed during
 * the lifetime of this program.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class SuccessfulTransactionReport extends TransactionReport {
	
// Constants --------------------------------------------------------------- //
	
	public final static String TITLE = "Successful Transaction Report";

// Constructors ------------------------------------------------------------ //
	
	/**
	 * Basically does exactly what {@link TransactionReport#TransactionReport(ArrayList, String, boolean)}
	 * does with the the success variable set to true because this is the
	 * Successful transaction report.
	 * 
	 * @param log The list of transactions
	 */
	public SuccessfulTransactionReport(ArrayList<Transaction> log){
		super(log, TITLE, true);
	}
	
// Overrides --------------------------------------------------------------- //
	
	/**
	 * The transaction is considered valid if the transaction was 
	 * successful.
	 * 
	 * @see Transaction#isValid()
	 * @see ReprotWindow#isValid(Transaction)
	 */
	@Override
	protected boolean isValid(Transaction trans) {
		
		if(trans.isValid()){
			return true;
		}
		
		return false;
	}

}
