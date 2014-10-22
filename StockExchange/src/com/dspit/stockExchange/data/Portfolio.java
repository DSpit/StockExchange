

package com.dspit.stockExchange.data;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A portfolio for each investor the user is managing. Contains all
 * data pertinent to a single investor.
 *	
 * @author David Boivin (Spit)
 */
public class Portfolio 
{

	private String mName = null;
	private ArrayList<Transaction> mPortfolio = new ArrayList<Transaction>();
//	private BigDecimal mTotal = new BigDecimal(0);
	
	// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which initializes the portfolio name to default value "George Bush"
	 */
	public Portfolio()
	{
		mName = "George Bush";
	}
	
	/**
	 * Constructor which initializes the portfolio name to specified value.
	 * 
	 * @param name : The specified name for portfolio object
	 */
	public Portfolio(String name)
	{
		mName = name;
	}
	
	/**
	 * Constructor which initializes the portfolio name to specified value and adds the transaction
	 * to mPortfolio.
	 * 
	 * @param name : The specified name for the portfolio object
	 * @param trans : The transaction to be added to the portfolio
	 */
	public Portfolio(String name, Transaction trans)
	{
		mName = name;
		add(trans);		
	}
	
	// Methods ------------------------------------------------------------ //
	
	/**
	 * Method which adds a transaction to the portfolio
	 * 
	 * @param trans : Transaction object to be added to the portfolio
	 */
	protected void add(Transaction trans)
	{
		mPortfolio.add(trans);
	}
	
	
//	QUESTION : This method assumes that SellTransactions are not actually added to a portfolio,
//			and that their purpose is solely to "sell" a corresponding stock. Maybe its better
//			to actually add the SellTransaction to the portfolio? 
	/**
	 * Sell Method. Returns true and removes corresponding BuyTransaction
	 * @param sTrans : The sell transaction
	 * @return : boolean (true if company stock to be sold was found in portfolio)
	 */
	protected boolean sell(SellTransaction sTrans)
	{	
		for(int i = 0; i < mPortfolio.size(); i++)
		{
			if((mPortfolio.get(i).mCompany.getCompanyId() == sTrans.mCompany.getCompanyId())
					&& (mPortfolio.get(i) instanceof BuyTransaction))
			{
				mPortfolio.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns portfolio name
	 * @return : mName
	 */
	protected String getPortfolioName()
	{
		return mName;
	}
}


//public void setPortfolioTotal()				Define what a profile's total is.
//{
//	for (Transaction trans : mPortfolio)
//	{
//		if(trans instanceof BuyTransaction)
//			mTotal = mTotal.add(trans.getUnitPrice());
//		
//		else
//			mTotal = mTotal
//		
//	}
//}

//public void printPortfolio()
//{	
//	System.out.println("\n" +mName + "'s Portfolio :");
//	for (Transaction trans : mPortfolio) 
//		System.out.println(trans.print());
//}
