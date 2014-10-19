

package com.dspit.stockExchange.uicomponents;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
	
// Members ----------------------------------------------------------------- //
	
	private JTable mTable;
	private boolean mIsSuccessful;
	
// Constructors ------------------------------------------------------------ //
	
	public ReportPanel(boolean isSuccessful){
		super(new BorderLayout(PADDING, PADDING));
		
		mIsSuccessful = isSuccessful;
		mTable = new JTable(5, 10);
		DefaultTableModel tModel = new DefaultTableModel();
		
		tModel.setColumnIdentifiers((mIsSuccessful)? TransactionInterface.SUCCESS_REPORT_COLUMNS
				: TransactionInterface.FAILED_REPORT_COLUMNS);
		
		mTable.setModel(tModel);
		
		this.add(mTable);
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	public void include(Transaction trans){
		
	}
}
