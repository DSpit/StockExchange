

package com.dspit.stockExchange.Exception;

/**
 * An exception representing an error when the user tries to sell
 * company shares which the portfolio does not have.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class NoSharesException extends Exception {

// Constants --------------------------------------------------------------- //

	public final String MARKER = "*1";
	
	private static final String MESSAGE = "No Company Shares in the protfolio";
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Builds a new Exception with a proper message for this
	 * exception.
	 */
	public NoSharesException(){
		super(MESSAGE);
	}
}
