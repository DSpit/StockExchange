

package com.dspit.stockExchange.uicomponents;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dspit.stockExchange.Exception.NoSharesException;
import com.dspit.stockExchange.Exception.PriceOutOfRangeException;
import com.dspit.stockExchange.Exception.QuantityOutOfRangeException;
import com.dspit.stockExchange.data.SellTransaction;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.data.TransactionInterface;

/**
 * The main GUI look and feel of the report section of this program.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class ReportPanel extends JPanel {
	
// Constants --------------------------------------------------------------- //
	
	private static final int PADDING = 5;
	private final String  BLANK = "(blank)";
	
// Members ----------------------------------------------------------------- //
	
	private boolean mIsSuccessful;
	private String[] mColumns;

	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which builds a table to display to the user all the necessary
	 * information of the report.
	 *
	 * @param isSuccessful <b>true</b> if the panel must display successful 
	 * reports and <b>false</b> if the panel must display failed reports.
	 */
	public ReportPanel(boolean isSuccessful){
		super();
		
		mIsSuccessful = isSuccessful;
		
		mColumns = (mIsSuccessful)? TransactionInterface.SUCCESS_REPORT_COLUMNS
				: TransactionInterface.FAILED_REPORT_COLUMNS;
		
		this.setLayout(new GridLayout(0, mColumns.length, PADDING, PADDING));
		
		for(int i = 0; i < mColumns.length; ++i){
			this.add(new JLabel(mColumns[i]));
		}
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Method which add this {@link Transaction} to the table of 
	 * transactions held by this panel.
	 *
	 * @param trans The {@link Transaction} to add to this panel.
	 */
	public void add(Transaction trans){
		
		//checks if the transactions is in the right report
		if(mIsSuccessful != trans.isValid()){
			return;		//abort adding the transaction to the report
		}
		
		//set the column values of the transactions inside a vector object
		Vector<String> row = new Vector<String>(6, 1);
		row.add(0, String.valueOf(trans.getTransNum()));	//add the Transaction Number
		row.add(1, trans.getPortfolioName());			//add investor
		row.add(2, trans.getCompanyName());			//add company
		
		/*
		 * This is just a fancy ass way of either entering the right information depending on 
		 * if the transaction is valid and what the error cause it to be invalid.
		 */
		try{							//add transaction type (Sell or Buy)
			row.add(3, trans.getTransType());
			
			try{							//add the total number of shares
				row.add(4, String.valueOf(trans.getShareQuantity()));
				
				try{						//add the price of the individual share
					row.add(5, String.valueOf(trans.getUnitPrice()));
					
				}catch(PriceOutOfRangeException e){
					row.add(5, e.MARKER);
					row.add(6, e.getMessage());
				}
				
			}catch(QuantityOutOfRangeException e){
				row.add(4, e.MARKER);
				row.add(5, e.MARKER);
				row.add(6, e.getMessage());
			}	
			
		}catch(NoSharesException e){
			row.add(3, TransactionInterface.SELL_TRANSACTION_NAME);
			row.add(4, e.MARKER);
			row.add(5, e.MARKER);
			row.add(6, e.getMessage());
		}
		
		/*
		 * checks if the report is a successful report to as to set the necessary
		 * values in the table
		 */
		if(mIsSuccessful){
			row.add(6, String.valueOf(trans.getTotal()));	//add the total
			
			if(row.get(3).equals(TransactionInterface.BUY_TRANSACTION_NAME)){
				row.add(7, BLANK);
			}else{
				try{
					row.add(7, String.valueOf(((SellTransaction) trans).getProfit()));
				}catch(NoSharesException e){
					System.out.println("Should Never Get HEre");
				}
			}
		}
		
		for(String v : row){
			this.add(new JLabel(v));
		}

	}
}
