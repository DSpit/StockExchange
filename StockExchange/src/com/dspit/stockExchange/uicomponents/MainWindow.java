package com.dspit.stockExchange.uicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.Company;
import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.Portfolio;
import com.dspit.stockExchange.data.PortfolioList;
import com.dspit.stockExchange.data.Transaction;
import com.dspit.stockExchange.data.TransactionBuilder;

/**
 * The main Graphical interface of this system. This is where
 * the user will select which portfolios and companies it wants to
 * edit stocks for and once the user is ready, the next page will appear
 * for the user can create transactions for each portfolio.
 * 
 * @author David Boivin (dSpit)
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	
// Constants --------------------------------------------------------------- //
	
	private final static String TITLE = "Stock Exchange";
	private final int PADDING = 5;
	
// Members ----------------------------------------------------------------- //
	
	private ArrayList<Transaction> mLog;
	private PortfolioList mPortfolios;
	private CompanyList mCompanies;
	
// UI Components ----------------------------------------------------------- //
	
	private JPanel mMainPanel;
	
// Constructor ------------------------------------------------------------- //
	
	/**
	 * Constructor which initializes the title of the JFrame to 
	 * {@link MainWindow#TITLE} and sets the panel to {@link TransactionSelectorPanel}.
	 *
	 * @param log The transaction log of the main system which this GUI plugs into.
	 * @param portfolios All the possible portfolios which the user can select.
	 * @param companies All the possible companies which the user can select.
	 */
	public MainWindow(ArrayList<Transaction> log, PortfolioList portfolios, CompanyList companies){
		super(TITLE);
		
		//basic framework for the frame
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout(PADDING, PADDING));
		this.setPreferredSize(new Dimension(300, 400));
		
		//initializes the members of this class
		mLog = log;
		mPortfolios = portfolios;
		mCompanies = companies;
		
		//sets the main panel
		mMainPanel = new TransactionSelectorPanel(mPortfolios, mCompanies, 
				new ControlButtonListener());
		
		//adds the main panel to the frame
		this.add(mMainPanel, BorderLayout.CENTER);
		
		//makes sure the frame is visible
		this.setVisible(true);
	}
	
// Listeners --------------------------------------------------------------- //
	
	/**
	 * Listener which dictates what is done after the user has decided
	 * that they have selected all the portfolios and companies it wants
	 * to use.
	 *
	 * @author David Boivin (Spit)
	 */
	private class ControlButtonListener implements ActionListener{

		private final String USER_ERROR_MESSAGE = "There must be at least one selection of both Companies and Portfolios";
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//makes life easier
			TransactionSelectorPanel panel = (TransactionSelectorPanel) mMainPanel;
			
			//get selected objects
			ArrayList<Portfolio> selectedPortfolios = 
					panel.getSelectedPortfolios();
			ArrayList<Company> selectedCompanies = 
					panel.getSelectedCompanies();
			
			//checks if the lists are valid e.i. the lists aren't empty
			if(selectedPortfolios.isEmpty() || selectedCompanies.isEmpty()){
				panel.getControlPanel().setErrorMessage(USER_ERROR_MESSAGE);
				mMainPanel = (JPanel) panel;
				return;
			}else{
				panel.getControlPanel().removeErrorMessage();
			}
			
			//change the panel to the next one
			mMainPanel.removeAll();
			mMainPanel.add(new TransactionInputPanel(selectedPortfolios, selectedCompanies, mLog, new CloseListener()));
			
			//repaints the main panel to its new form
			mMainPanel.revalidate();
			mMainPanel.repaint();
			
		}
	}
	
	/**
	 * Listener which dictates what happens when the user is done with the TransactionInputPanel
	 *
	 * @author David Boivin (Spit)
	 */
	private class CloseListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//makes life easier
			TransactionInputPanel panel = (TransactionInputPanel) mMainPanel.getComponent(0);
			
			HashMap<Portfolio, ArrayList<TransactionBuilder>> pendingTransactions;
			
			try{
				pendingTransactions = panel.getInputValues();
				
				//iterate through the portfolios
				for(Portfolio key : pendingTransactions.keySet()){
					//iterate through each pending transaction for the portfolio
					for(TransactionBuilder builder : pendingTransactions.get(key)){
						Transaction t = builder.createTransaction();
						
						//add to the internal log and the portfolio log
						mLog.add(t);
						key.add(t);
					}
				}

				
				//set start report sequence
				
				//display successful transactions
				AbsTransactionReport successfulTrans = new SuccessfulTransactionReport(mLog);
				Timer t = new Timer(); 
				
				t.schedule(new ReportTask(successfulTrans),10000);
				
				dispose();		//dispose of the transaction panel
				
				/* 			NOTE:					*/
				/*  add portfolio & company saving code here if you so wish	*/
				/*								*/
				
			}catch(IllegalArgumentException ex){
				panel.getControlPanel().setErrorMessage(ex.getMessage());
			}
		}
		
		private class ReportTask extends TimerTask{
			private AbsTransactionReport mSuccessTrans;
			
			public ReportTask(AbsTransactionReport successfulTrans){
				mSuccessTrans = successfulTrans;
			}
			@Override
			public void run() {
				mSuccessTrans.dispose();	//dispose of the window
				
				//display failed transactions
				 AbsTransactionReport failedTrans = new FailedTransactionReport(mLog);
				 
				 Timer t = new Timer();
				 
				 t.schedule(new CloseTask(failedTrans), 10000);
				 
				 this.cancel();
				 
				 
			}
			
			private class CloseTask extends TimerTask{
				private AbsTransactionReport mFailedTrans;
				
				public CloseTask(AbsTransactionReport failedTrans){
					mFailedTrans = failedTrans;
				}

				@Override
				public void run() {
					mFailedTrans.dispose();
					
					this.cancel();
				}
				
			}
		}
		
	}
}
