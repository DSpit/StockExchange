

package com.dspit.stockExchange.data;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A portfolio for each investor the user is managing. Contains all
 * data pertinent to a single investor.
 *	
 * @author David Boivin (Spit)
 */
final public class Portfolio 
{

	private String mName = null;
	protected ArrayList<Transaction> mPortfolio = new ArrayList<Transaction>();
	
	protected int reportIterator = 0;

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
	
	protected void add(Transaction trans, int index)
	{
		mPortfolio.add(index, trans);
	}
	
	
//	QUESTION : This method assumes that SellTransactions are not actually added to a portfolio,
//			and that their purpose is solely to "sell" a corresponding stock. Maybe its better
//			to actually add the SellTransaction to the portfolio? 
	/**
	 * Sell Method. Returns true and adds SellTransaction portfolio if corresponding BuyTransaction
	 * was found
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
					add(sTrans, i);
					return true;
				}
			}
		return false;
	}
	
//	/**
//	 * Method that searches if a portfolio contains company stocks pertaining to the particular
//	 * SellTransaction. Returns index of transaction if found and -1 if not found.
//	 * 
//	 * @param sTrans : The SellTransaction argument
//	 * @return : index of Transaction object in portfolio or -1 if none is found
//	 */
//	protected int search(SellTransaction sTrans)
//	{
//		for(int i = 0; i < mPortfolio.size(); i++)
//		{
//			if((mPortfolio.get(i).mCompany.getCompanyId() == sTrans.mCompany.getCompanyId())
//					&& (mPortfolio.get(i) instanceof BuyTransaction))
//			{
//				return i;
//			}
//		}
//		return -1;
//	}
	
	/**
	 * Method that gets the total cost of all the shares owned by this portfolio
	 * of a particular company.
	 * 
	 * @param company The company to search up shares for. 
	 * 
	 * @return The total cost of all the shares of one company or -1 if none is found
	 */
	public BigDecimal getTotalCost(Company company){
		BigDecimal total = new BigDecimal(0);
		
		for(Transaction t : mPortfolio){
			if(t.isValid() && t.getCompanyName().equals(company.getName())){
				total.add(t.getTotal());
			}
		}
		
		return total;
	}
	

	/**
	 * Returns portfolio name
	 * @return mName
	 */
	public String getName()
	{
		return mName;
	}
	
	/**
	 * Returns the string representation of this class.
	 * 
	 * @return The name of the portfolio.
	 */
	@Override
	public String toString(){
		return mName;
	}
}
