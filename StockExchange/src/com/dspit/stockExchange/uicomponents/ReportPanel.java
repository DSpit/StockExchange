

package com.dspit.stockExchange.uicomponents;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	
	private JTable mTable;
	private boolean mIsSuccessful;
	private String[] mColumns;
	
// Constructors ------------------------------------------------------------ //
	
	public ReportPanel(boolean isSuccessful){
		super(new BorderLayout(PADDING, PADDING));
		
		mColumns = (mIsSuccessful)? TransactionInterface.SUCCESS_REPORT_COLUMNS
				: TransactionInterface.FAILED_REPORT_COLUMNS;
		
		mIsSuccessful = isSuccessful;
		mTable = new JTable(0, mColumns.length);
		
		DefaultTableModel tModel = new DefaultTableModel();
		tModel.setColumnIdentifiers(mColumns);
		mTable.setModel(tModel);
		
		this.add(mTable);
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	public void add(Transaction trans){
		
		//set the column values of the transactions inside a vector object
		
		Vector<String> row = new Vector<String>();
		row.add(0, String.toString(trans.getTransNum()));	//add the Transaction Number
		row.add(1, trans.getPortfolioName());			//add investor
		row.add(2, trans.getCompanyName());			//add company
		row.add(3, trans.getTransType());			//add transaction type (Sell or Buy)
		row.add(4, String.toString(trans.getNumShares()));	//add the total number of shares
		row.add(5, String.toString(trans.getUnitPrice()));	//add the price of the individual share
		
		if(mIsSuccessful){
			row.add(6, String.toString(trans.getTotal()));	//add is total
			
			if(trans instanceof BuyTransaction){
				row.add(7, BLANK);
			}else{
				//TODO figure out how to get the total profit/loss
			}
		}else{
			//TODO add Invalid report list
		}
		//append row to the table
		((DefaultTableModel) mTable.getModel()).addRow(row);
	}
}
