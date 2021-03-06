

package com.dspit.stockExchange.data;

import java.math.BigDecimal;

import com.dspit.stockExchange.Exception.NoSharesException;
import com.dspit.stockExchange.Exception.PriceOutOfRangeException;
import com.dspit.stockExchange.Exception.QuantityOutOfRangeException;

/**
 * A transaction which represents a sale. 
 *
 * @see {@link Transaction}
 *
 * @author David Boivin (Spit)
 */
public final class SellTransaction extends Transaction {

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
	public SellTransaction(Portfolio portfolio, Company company, BigDecimal price) {
		super(portfolio, company, portfolio.getTotalShares(company), price);
		
		
		mTransType = SELL_TRANSACTION_NAME;
		
	}

// Overrides -------------------------------------------------------------- //
	
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
	public String getTransType() throws NoSharesException {
		if(shareQuantity == 0)
			throw new NoSharesException();
		else
			return mTransType;
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
	public int getShareQuantity() throws QuantityOutOfRangeException 
	{
			return shareQuantity;
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
		if(sharePrice.compareTo(TransactionInterface.minSharePrice) == -1 || 
				sharePrice.compareTo(TransactionInterface.maxSharePrice) == 1)
			throw new PriceOutOfRangeException();
		
		else
			return sharePrice;
	}
	
	/** 
	 * See {@link Transaction#getTotal()} for more information.
	 * 
	 * @see Transaction#getTotal()
	 */
	@Override
	public BigDecimal getTotal() {
		mTotal = sharePrice.multiply(new BigDecimal(shareQuantity));
		return mTotal;
	}

	
	/**
	 * See {@link Transaction#checkValid()} for more information.
	 * 
	 * @see Transaction#checkValid(int, BigDecimal)
	 */
	@Override
	protected boolean checkValid() {
		
		if(mPortfolio.getTotalShares(mCompany, this) <= 0){
			return false;
		}else if(sharePrice.compareTo(minSharePrice) == -1 || 
				sharePrice.compareTo(maxSharePrice) == 1){
			System.out.println("Price porblem");
			return false;
		}else
			return true;
	}
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Returns the total profit of this transaction.
	 *
	 * @return The profit (+/-) of this transaction. 
	 * @throws NoSharesException If there are no shares for this company to sell.
	 */
	public BigDecimal getProfit() throws NoSharesException{
		
		BigDecimal totalCost = mPortfolio.getTotalCost(mCompany, this);
		
		if(totalCost.doubleValue() > 0){
			return totalCost.subtract(sharePrice.multiply(new BigDecimal(shareQuantity)));
		}else{
			throw new NoSharesException();
		}
		

	}

}
