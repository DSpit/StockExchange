

package com.dspit.stockExchange.data;

import java.math.BigDecimal;

import com.dspit.stockExchange.Exception.NoSharesException;
import com.dspit.stockExchange.Exception.PriceOutOfRangeException;
import com.dspit.stockExchange.Exception.QuantityOutOfRangeException;

/**
 * A general log entry of what the user has done with different 
 * portfolios.
 *
 * @author David Boivin (Spit)
 */
public abstract class Transaction implements TransactionInterface
{
	
// Static Counter ---------------------------------------------------------- //
	
	protected static int sTransNumber = 0;
	
// Members ----------------------------------------------------------------- //
	
	protected int mTransNumber;
	protected String mTransType;
	protected Portfolio mPortfolio;
	protected Company mCompany;
	protected int shareQuantity;
	protected BigDecimal sharePrice;
	protected BigDecimal mTotal;		 
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which initializes all members with the given data. 
	 * This method must deduce whether the transaction is valid or not.
	 *
	 * @param portfolio The portfolio which is associated with this transaction.
	 * @param company The company which is associated with this transaction.
	 * @param shares The number of shares which is either bought or sold in this transaction.
	 * @param price The price of each individual shares for this transaction.
	 */
	public Transaction(Portfolio portfolio, Company company, int shares, BigDecimal price) {
		mPortfolio = portfolio;
		mCompany = company;
		shareQuantity = shares;
		sharePrice = price;
		mTotal = price.multiply(new BigDecimal(shares));
		
		mTransNumber = ++sTransNumber;
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Method which defines if the Transaction is considered valid.
	 *
	 * @return <b>true</b> if the transaction is considered valid and
	 * 	<b>false</b> otherwise.
	 */
	public boolean isValid(){
		return false;	//TODO
	}
	
	/**
	 * Method which returns the unique transaction number of the instance
	 * which is calling it.
	 *
	 * @return The unique identifier of the instance of this transaction.
	 */
	public int getTransNum(){
		return mTransNumber;
	}
	
	/**
	 * Returns the name of the owner of the portfolio which is associated
	 * with this transaction.
	 *
	 * @return The name of the portfolio which is associated with this 
	 * 	transaction.
	 */
	public String getPortfolioName(){
		return mPortfolio.getName();
	}
	
	/**
	 * Returns the name of the company which is associate with this 
	 * transaction.
	 *
	 * @return The name of the company which is associated with this
	 * 	transaction
	 */
	public String getCompanyName(){
		return ""; //TODO
	}
	
// Abstract Methods -------------------------------------------------------- //
	
	/**
	 * Returns the type of transaction which this transaction 
	 * represents.
	 *
	 * @return {@link TransactionInterface#BUY_TRANSACTION_NAME} if the
	 * 	transaction is of type <b>Buy</b> and 
	 * 	{@link TransactionInterface#SELL_TRANSACTION_NAME} if the 
	 * 	transaction is of type <b>Sell</b>. 
	 * 
	 * @throws NoSharesException 
	 * 	
	 */
	public abstract String getTransType() throws NoSharesException;
	
	/**
	 * The total number of shares which have either been 
	 * bought or sold.
	 *
	 * @return The number of shares in this transaction.
	 */
	public abstract int getShareQuantity() throws QuantityOutOfRangeException;
	
	/**
	 * Returns the price associated with a single share
	 * in this transaction.
	 *
	 * @return The price of a single share (whether a Buy or Sell Transaction).
	 */
	public abstract BigDecimal getUnitPrice() throws PriceOutOfRangeException;
	
	/**
	 * Returns the total worth of this transaction.
	 * 
	 * @return The total worth of this transaction
	 * 	i.e. Total = Quantity * Unit Price
	 */
	public abstract BigDecimal getTotal();
	
	/**
	 * This method checks if the price is considered valid and
	 * if the price if considered valid based on the rules:
	 * 
	 * <ul>
	 * 	<li>The quantity of shares of any transaction matches the one in the portfolio. </li>
	 * 	<li>The quantity of shares of any transaction is in the range from 10 to 100 (including them)</li>
	 *	<li>The unit price per share of any transaction is in the range from 1.05 to 10.05 (including them)</li>
	 * </ul>
	 * 
	 * @return <b>true</b> if the price and the shares are 
	 * 		considered valid and <b>false</b> otherwise.
	 */
	protected abstract boolean checkValid();
	
}
