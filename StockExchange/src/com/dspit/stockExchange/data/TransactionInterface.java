

package com.dspit.stockExchange.data;

import java.math.BigDecimal;


/**
 * Interface for the general transactions
 * 
 * @author dSpit
 */
public interface TransactionInterface {
	
//Constants ---------------------------------------------------------------- //
	
	public final static String[] SUCCESS_REPORT_COLUMNS = {"Transaction Number",
								"Investor", 
								"Company", 
								"Transaction Type", 
								"Price", 
								"Quantity",
								"Total", 
								"Profit"};
	public final static String[] FAILED_REPORT_COLUMNS = {"Transaction Number",
								"Investor", 
								"Company", 
								"Transaction Type", 
								"Price", 
								"Quantity",
								"Message",};
	
	public final static String BUY_TRANSACTION_NAME = "Buy";
	public final static String SELL_TRANSACTION_NAME = "Sell";
	
	public final static BigDecimal minSharePrice = new BigDecimal(1.05);
	public final static BigDecimal maxSharePrice = new BigDecimal(10.05);
	
	public final static int minShareTransQuantity = 10;
	public final static int maxShareTransQuantity = 100;
}
