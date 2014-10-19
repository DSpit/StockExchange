

package com.dspit.stockExchange.data;

/**
 * A general log entry of what the user has done with different 
 * portfolios.
 *
 * @author David Boivin (Spit)
 */
public class Transaction implements TransactionInterface{
	
// Static Counter ---------------------------------------------------------- //
	
	protected static int sTransNumber = 0;
	
// Constructor ------------------------------------------------------------- //
	
	public Transaction(){
		++sTransNumber;
	}
	
	public boolean isSuccessful(){
		return false;	//TODO
	}

}
