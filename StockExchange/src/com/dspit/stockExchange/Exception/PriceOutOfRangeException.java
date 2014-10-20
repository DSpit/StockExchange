

package com.dspit.stockExchange.Exception;

/**
 * An exception representing an error when a price is out of defined
 * range.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class PriceOutOfRangeException extends Exception {
	
// Constants --------------------------------------------------------------- //
	
	public final String MARKER = "*3";
	
	private static final String MESSAGE = "Price out of range";
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Builds a new Exception with a proper message for this
	 * exception.
	 */
	public PriceOutOfRangeException(){
		super(MESSAGE);
	}
}
