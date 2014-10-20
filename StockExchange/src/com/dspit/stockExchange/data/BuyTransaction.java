

package com.dspit.stockExchange.data;

import java.math.BigDecimal;

import com.dspit.stockExchange.Exception.NoSharesException;
import com.dspit.stockExchange.Exception.PriceOutOfRangeException;
import com.dspit.stockExchange.Exception.QuantityOutOfRangeException;

/**
 * A transaction which represents a buy. 
 * 
 * @author David Boivin (Spit)
 */
public class BuyTransaction extends Transaction {

	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which initializes all members with the given data. 
	 * This method must deduce whether the transaction is valid or not.
	 *
	 * @param portfolio The portfolio which is associated with this transaction.
	 * @param company The company which is associated with this transaction.
	 * @param shares The number of shares which is either bought or sold in this transaction.
	 * @param price The price of each individual shares for this transaction.
	 */
	public BuyTransaction(Portfolio portfolio, Company company, int shares, BigDecimal price) {
		super(portfolio, company, shares, price);
		// TODO don't forget to check validity
	}

// Override ---------------------------------------------------------------- //
	
	/**
	 * See {@link Transaction#getTransType()} for more information. <br><br>
	 * 
	 * Will throw a {@link NoSharesException} if the transaction is invalid because
	 * 	the portfolio doesn't have any shares for that particular company.
	 * 
	 * @throws NoSharesException
	 * 
	 * @see Transaction#getTransType()
	 */
	@Override
	public String getTransType() throws NoSharesException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * See {@link Transaction#getShareQuantity()} for more information. <br><br>
	 * 
	 * Will throw a {@link QuantityOutOfRangeException} if the transaction is invalid because
	 * 	the quantity of of shares is out of the defined range.
	 * 
	 * @throws QuantityOutOfRangeException
	 * 
	 * @see Transaction#getShareQuantity()
	 */
	@Override
	public int getShareQuantity() throws QuantityOutOfRangeException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * See {@link Transaction#getUnitPrice()} for more information. <br><br>
	 * 
	 * Will throw a {@link PriceOutOfRangeException} if the transaction is invalid because
	 * 	the price of of shares is out of the defined range.
	 * 
	 * @throws PriceOutOfRangeException
	 * 
	 * @see Transaction#getUnitPrice()
	 */
	@Override
	public BigDecimal getUnitPrice() throws PriceOutOfRangeException {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * See {@link Transaction#getTotal()} for more information.
	 * 
	 * @see Transaction#getTotal()
	 */
	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * See {@link Transaction#checkValid()} for more information.
	 * 
	 * @see Transaction#checkValid(int, BigDecimal)
	 */
	@Override
	protected boolean checkValid() {
		// TODO Auto-generated method stub
		return false;
	}

}