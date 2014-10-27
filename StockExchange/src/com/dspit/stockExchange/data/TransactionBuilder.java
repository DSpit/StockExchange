

package com.dspit.stockExchange.data;

import java.math.BigDecimal;

import javax.lang.model.type.NullType;

/**
 * A class used to build a transaction from the ground up. Basically
 * this is just a way of storing the values needed to create a transaction
 * until all the values can be rounded up and check.
 *
 * @author David Boivin (Spit)
 */
public class TransactionBuilder {
	
// Constants --------------------------------------------------------------- //
	
	private final String DEFAULT_STRING_VALUE = null;
	private final Portfolio DEFAULT_PORTFOLIO_VALUE = null;
	private final Company DEFAULT_COMPANY_VALUE = null;
	private final BigDecimal DEFAULT_BIG_DECIMAL_VALUE = null;
	private final int DEFAULT_INT_VALUE = -1;
	
// Members ----------------------------------------------------------------- //
	
	private String mTransType;
	private Portfolio mPortfolio;
	private Company mCompany;
	private int shareQuantity;
	private BigDecimal sharePrice;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which sets the members to their default values.
	 */
	public TransactionBuilder(){
		mTransType = DEFAULT_STRING_VALUE;
		mPortfolio = DEFAULT_PORTFOLIO_VALUE;
		mCompany = DEFAULT_COMPANY_VALUE;
		shareQuantity = DEFAULT_INT_VALUE;
		sharePrice = DEFAULT_BIG_DECIMAL_VALUE;
	}
	
// Getters ----------------------------------------------------------------- //
	
	/**
	 * Get Transaction Type. Can only be <b>null</b>, {@link TransactionInterface#BUY_TRANSACTION_NAME} or
	 * {@link TransactionInterface#SELL_TRANSACTION_NAME}.
	 *
	 * @return The transaction type.
	 */
	public String getTransactionType(){
		return mTransType;
	}
	
	/**
	 * The portfolio to which this transaction is about.
	 *
	 * @return The portfolio which is the subject of transaction.
	 */
	public Portfolio getPortfolio(){
		return mPortfolio;
	}
	
	/**
	 * The Company which is participating in the transaction.
	 *
	 * @return The company which is subject to this transaction.
	 */
	public Company getCompany(){
		return mCompany;
	}
	
	/**
	 * The total number of shares of this transaction.
	 *
	 * @return The quantity of shares which is part of this transaction.
	 */
	public int getShareQuantity(){
		return shareQuantity;
	}
	
	/**
	 * The price of each share in this transaction.
	 *
	 * @return The price of a single share.
	 */
	public BigDecimal getSharePrice(){
		return sharePrice;
	}
	
// Setters ----------------------------------------------------------------- //
	
	/**
	 * Sets the transaction Type for this pending transaction.
	 *
	 * @param transactionType The Type of pending transaction.
	 * 
	 * @return itself, which allows for chaining.
	 * 
	 * @throws IllegalArgumentException is thrown if the transaction type
	 * 	is not either {@link TransactionInterface#BUY_TRANSACTION_NAME} or 
	 * 	{@link TransactionInterface#SELL_TRANSACTION_NAME}.
	 */
	public TransactionBuilder setTransactionType(String transactionType) throws IllegalArgumentException{
		
		if(transactionType.equals(TransactionInterface.BUY_TRANSACTION_NAME) ||
				transactionType.equals(TransactionInterface.SELL_TRANSACTION_NAME)){
			mTransType = transactionType;
			return this;
			
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Sets the portfolio to to the pending transaction
	 * 
	 * @return itself, which allows for chaining.
	 *
	 * @param portfolio The portfolio associated with this pending transaction.
	 */
	public TransactionBuilder setPortrfolio(Portfolio portfolio){
		mPortfolio = portfolio;
		return this;
	}
	
	/**
	 * Sets the company to the pending transaction.
	 *
	 * @return itself, which allows for chaining.
	 * 
	 * @param company The company associated with this pending transaction.
	 */
	public TransactionBuilder setCompany(Company company){
		mCompany = company;
		return this;
	}
	
	/**
	 * Sets the quantity for this pending transaction.
	 *
	 * @param quantity The quantity of shares assicatiated with this pending transactions.
	 * 
	 * @return itself, which allows for chaining.
	 * 
	 * @throws IllegalArgumentException If the string from of the quantity of shares
	 * cannot be parsed into a proper integer.
	 */
	public TransactionBuilder setShareQuantity(String quantity) throws IllegalArgumentException{
		
		try{
			shareQuantity = Integer.parseInt(quantity);
			return this;
			
		}catch(NumberFormatException e){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * sets the price for this pending transaction.
	 *
	 * @param price The price for each share associated to this transaction.
	 * 
	 * @return itself, which allows for chaining.
	 * 
	 * @throws IllegalArgumentException if the String form of the price cannot be parsed
	 * 	into a double and then assigned to a BigDecimal. 
	 */
	public TransactionBuilder setSharePrice(String price) throws IllegalArgumentException{
		try{
			sharePrice = new BigDecimal(Double.parseDouble(price));
			return this;
			
		}catch(Exception e){
			throw new IllegalArgumentException();
		}
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Method which creates the transaction from the information stored
	 * within this class.
	 *
	 * @return A new Transaction.
	 * @throws NullPointerException If any of the values are at default value.
	 */
	public Transaction createTransaction() throws NullPointerException{
		
		if(this.checkDefaultValues()){
			
			return (mTransType.equals(TransactionInterface.BUY_TRANSACTION_NAME))?
					new BuyTransaction(mPortfolio, mCompany, shareQuantity, sharePrice):
					new SellTransaction(mPortfolio, mCompany, shareQuantity, sharePrice);
		}else{
			throw new NullPointerException();
		}
	}
	
// Private Methods --------------------------------------------------------- //
	
	/**
	 * Method which checks if any of the parameters are still at their default
	 * value, in which case the value hasn't been set.
	 * 
	 * @return <b>true</b> if the values are now default and  <b>false</b> if 
	 * at least on value is at default.
	 */
	private boolean checkDefaultValues(){
		
		//check the transaction type
		if(mTransType == DEFAULT_STRING_VALUE){
			return false;
		}
		
		//check the portfolio
		if(mPortfolio == DEFAULT_PORTFOLIO_VALUE){
			return false;
		}
		
		//check the company
		if(mCompany == DEFAULT_COMPANY_VALUE){
			return false;
		}
		
		//check the quantity
		if(shareQuantity == DEFAULT_INT_VALUE){
			return false;
		}
		
		//check the price
		if(sharePrice == DEFAULT_BIG_DECIMAL_VALUE){
			return false;
		}
		
		//if it got here everything checks out.
		return true;
	}
	
}
