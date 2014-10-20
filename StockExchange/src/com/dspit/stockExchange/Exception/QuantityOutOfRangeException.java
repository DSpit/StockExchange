

package com.dspit.stockExchange.Exception;

/**
 * An exception representing an error when a quantity of shares is out of defined
 * range.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class QuantityOutOfRangeException extends Exception {
	
// Constants --------------------------------------------------------------- //

	public final String MARKER = "*2";
	
	private static final String MESSAGE = "Quantity of shares out of range";
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Builds a new Exception with a proper message for this
	 * exception.
	 */
	public QuantityOutOfRangeException(){
		super(MESSAGE);
	}

}
