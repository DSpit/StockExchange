

package com.dspit.stockExchange.uicomponents;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import com.dspit.stockExchange.data.Company;
import com.dspit.stockExchange.data.Portfolio;
import com.dspit.stockExchange.data.TransactionInterface;

/**
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class TabPanel extends JPanel {

// Members ----------------------------------------------------------------- //
	
	
	
// UI Components ----------------------------------------------------------- //
	
	private EditPanel mEditPanel;
	
// Constructors ------------------------------------------------------------ //
	
	/**
	 * Constructor which builds the window and tabs, as well as create 
	 * the link between each sub-panel and it's appropriate tab.
	 *  
	 * @param selectedPortfolios A list of Portfolios the user 
	 * 	has selected.
	 * @param selectedCompanies A list of Companies the user has
	 * 	selected.
	 */
	public TabPanel(ArrayList<Portfolio> selectedPortfolios,
			ArrayList<Company> selectedCompanies) {
		super();
		
		//set the main layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//initialize main components
		mEditPanel = new EditPanel(selectedPortfolios, selectedCompanies);
		SelectorPanel tabPanel = new SelectorPanel(selectedPortfolios, mEditPanel);
		
		this.add(mEditPanel);
		this.add(tabPanel);
	}

// Public Methods ---------------------------------------------------------- //
	
	/**
	 *
	 * @return
	 */
	public HashMap<Portfolio, ArrayList<Vector<String>>> getValues() {
		return mEditPanel.getAllValues();
	}
	
// Private Inner Classes --------------------------------------------------- //
	
	/**
	 * This private inner class represents the tab portion of the TabPanel.
	 *
	 * @author David Boivin (Spit)
	 */
	private class SelectorPanel extends JPanel{
		
		/**
		 * Constructor which creates the panel that holds all the different tabs
		 * of this instance of TabPanel.
		 *
		 * @param tabList A list of Portfolios which are considered a pane for each tab.
		 * @param cardPanel The panel containing all the actual panes for the tabs
		 */
		public SelectorPanel(ArrayList<Portfolio> tabList, EditPanel cardPanel){
			super();
			
			this.setLayout(new GridLayout(1, tabList.size()));
			
			ButtonGroup bGroup = new ButtonGroup();
			
			ActionListener tabListener = new TabListener(cardPanel);
			
			for(int i = 0; i < tabList.size(); ++i){
				Tab b = new Tab(tabList.get(i).getName(), i);	//create new button
				
				//add listener
				b.addActionListener(tabListener);
				
				//add this button to the list of buttons, both graphically and logically.
				bGroup.add(b);
				this.add(b);
			}
		}
		
		/**
		 * Listener which listens for clicks on the tab buttons.
		 * Will change the current panel on the EditPanel based on what
		 * button is clicked.
		 *
		 * @author David Boivin (Spit)
		 */
		private class TabListener implements ActionListener{

			private EditPanel mEditPanel;
			
			public TabListener(EditPanel cardPanel){
				mEditPanel = cardPanel;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//change the pane which is currently being shown by mEditPanel
				mEditPanel.showPanel(((Tab) e.getSource()).getIndex());
				
			}
			
		}
		
		/**
		 * An extended version of {@link JButton} which, on top of
		 * the regular JButton stuff, holds the index of the panel
		 * which is is responsible for.
		 *
		 * @author David Boivin (Spit)
		 */
		private class Tab extends JButton{
			
			private int mIndex;
			
			public Tab(String name, int index){
				super(name);
				mIndex = index;
			}
			
			public int getIndex(){
				return mIndex;
			}
		}
		
	}
	
	/**
	 * Panel which holds all of the option panels.
	 *
	 * @author David Boivin (Spit)
	 */
	private class EditPanel extends JPanel{
		
		public EditPanel(ArrayList<Portfolio> portfolios, ArrayList<Company> companies){
			super();
			
			this.setLayout(new CardLayout());
			
			for(int i = 0; i < portfolios.size(); ++i){
				this.add(new OptionPanel(portfolios.get(i), companies), String.valueOf(i));
			}
			
		}
		
		/**
		 * Displays the panel at the given index
		 *
		 * @param index The index of the panel.
		 */
		public void showPanel(int index){
			((CardLayout) this.getLayout()).show(this, String.valueOf(index));
		}
		
		/**
		 * Returns a list of all the values which represent the variables the user has entered.
		 *
		 * @return The values the user has entered.
		 */
		public HashMap<Portfolio, ArrayList<Vector<String>>> getAllValues(){
			HashMap<Portfolio, ArrayList<Vector<String>>> output = new HashMap<Portfolio, ArrayList<Vector<String>>>();
			
			Component[] subPanelList = this.getComponents();
			
			for(int i = 0; i < subPanelList.length; ++i){
				OptionPanel panel = (OptionPanel)subPanelList[i];
				
				ArrayList<Vector<String>> v = panel.getValues();
				
				output.put(panel.getPortfolio(), v);
			}
			
			return output;
		}
		
		/**
		 * This panel is what the user will select values from.
		 *
		 * @author David Boivin (Spit)
		 */
		private class OptionPanel extends JPanel{
			
			private Portfolio mPortfolio;
			
			private ArrayList<SelectorPanel> mCompanyPanels;
			
			public OptionPanel(Portfolio p, ArrayList<Company> companies){
				super();
				
				mPortfolio = p;
				mCompanyPanels = new ArrayList<SelectorPanel>(companies.size());
				
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				this.setBorder(BorderFactory.createTitledBorder(p.getName()));
				
				for(Company c : companies){
					SelectorPanel sPanel = new SelectorPanel(c);
					
					mCompanyPanels.add(sPanel);
					this.add(sPanel);
				}
				
			}
			
			public Portfolio getPortfolio(){
				return mPortfolio;
			}
			
			public ArrayList<Vector<String>> getValues(){
				ArrayList<Vector<String>> list = new ArrayList<Vector<String>>();
				
				for(SelectorPanel panel : mCompanyPanels){
					list.add(panel.getInputValues());
				}
				
				
				return list;
			}
			
			private class SelectorPanel extends JPanel{
				
				private final String TOGGLE_BUTTON_BUY = "Buy";
				private final String TOGGLE_BUTTON_SELL = "Sell";
				private final String QUANTITY_INPUT_LABEL = "Quantity: ";
				private final String PRICE_INPUT_LABEL = "Price: ";
				
				private JToggleButton mBuySellToggle;
				private JTextField mQuantity;
				private JTextField mPrice;
				
				public SelectorPanel(Company company){
					super();
					
					this.setLayout(new GridLayout(1, 3));
					this.setBorder(BorderFactory.createTitledBorder(company.getName()));
					
					//QuantitlyPanel
					JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					
					mQuantity = new JTextField(5);
					
					quantityPanel.add(new JLabel(QUANTITY_INPUT_LABEL));
					quantityPanel.add(mQuantity);
					
					//PricePanel
					JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					
					mPrice = new JTextField(5);
					
					pricePanel.add(new JLabel(PRICE_INPUT_LABEL));
					pricePanel.add(mPrice);
					
					//Toggle panel
					JPanel togglePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					
					mBuySellToggle = new JToggleButton(TOGGLE_BUTTON_BUY);
					mBuySellToggle.addActionListener(new ToggleListener(mQuantity));
					
					togglePanel.add(new JLabel(TOGGLE_BUTTON_BUY + " or " +
								TOGGLE_BUTTON_SELL));
					togglePanel.add(mBuySellToggle);
					
					this.add(togglePanel);
					this.add(quantityPanel);
					this.add(pricePanel);
				}
				
				/**
				 * 
				 *
				 * @return
				 */
				public Vector<String> getInputValues(){
					Vector<String> v = new Vector<String>();
					
					v.add(0, (mBuySellToggle.isSelected()? 
							TransactionInterface.BUY_TRANSACTION_NAME:
							TransactionInterface.SELL_TRANSACTION_NAME));	//Buy or Sell
					v.add(1, ).
					
					return v;
				}
				
				private class ToggleListener implements ActionListener{
					
					private JTextField mQuantityInput; 
					
					public ToggleListener(JTextField quantityInput){
						mQuantityInput = quantityInput;
					}

					@Override
					public void actionPerformed(ActionEvent e) {
						JToggleButton b = (JToggleButton) e.getSource();
						
						if(b.getText().equals(TOGGLE_BUTTON_BUY)){
							b.setText(TOGGLE_BUTTON_SELL);
							mQuantityInput.setEnabled(false);
							
						}else{
							b.setText(TOGGLE_BUTTON_BUY);
							mQuantityInput.setEnabled(true);
						}
					}
					
				}
			}
		}
	}

}
