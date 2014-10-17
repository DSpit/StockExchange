

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
public class SuccessfulTransactionReport extends ReportWindow {
	
// Constants --------------------------------------------------------------- //
	
	public final static String TITLE = "Successful Transaction Report";

// Constructors ------------------------------------------------------------ //
	
	public SuccessfulTransactionReport(ArrayList<Transaction> log){
		super(log, TITLE, true);
	}
	
// Overrides --------------------------------------------------------------- //
	
	/**
	 * The transaction is considered valid if the transaction was 
	 * successful.
	 * 
	 * @see Transaction#isSuccessful()
	 * @see ReprotWindow#isValid(Transaction)
	 */
	@Override
	protected boolean isValid(Transaction trans) {
		
		if(trans.isSuccessful()){
			return true;
		}
		
		return false;
	}

}
