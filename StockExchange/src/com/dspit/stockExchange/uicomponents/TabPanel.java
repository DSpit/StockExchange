

package com.dspit.stockExchange.uicomponents;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
import com.dspit.stockExchange.data.TransactionBuilder;
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
	 * Return the entire panel inputs in a condensed and organized way.
	 *
	 * @return A HashMap of portfolios attached to a list of vectors
	 * 	which contain the information needed to create a transaction.
	 */
	public HashMap<Portfolio, ArrayList<TransactionBuilder>> getValues() throws IllegalArgumentException{
		try{
			return mEditPanel.getAllValues();
		}catch(IllegalArgumentException e){
			throw e;
		}
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
			
			//set layout
			this.setLayout(new GridLayout(1, tabList.size()));
			
			//create logical components 
			ButtonGroup bGroup = new ButtonGroup();
			ActionListener tabListener = new TabListener(cardPanel);
			
			//iterate through the list of portfolios to create a tab of each
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
			
			/**
			 * Constructor which assigns the controlled surface to
			 * the TabListener.
			 *
			 * @param cardPanel The Layout assigned to this TabArea.
			 */
			public TabListener(EditPanel cardPanel){
				mEditPanel = cardPanel;
			}
			
			/**
			 * Changes the controlled surface display to the 
			 * selected panel.
			 */
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
			
			/**
			 * The constructor which assigns the this button
			 * to the name parameter and sets this Tabs index to
			 * the given index
			 *
			 * @param name The text assigned to the button.
			 * @param index The index of the Tab.
			 */
			public Tab(String name, int index){
				super(name);
				mIndex = index;
			}
			
			/**
			 * Returns the index of the Tab.
			 *
			 * @return The Index of the tab.
			 */
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
		
		/**
		 * The Controlled surface where the user decides what to do.
		 *
		 * @param portfolios The list of selected portfolios.
		 * @param companies The list of selected companies.
		 */
		public EditPanel(ArrayList<Portfolio> portfolios, ArrayList<Company> companies){
			super();
			
			//set the layout.
			this.setLayout(new CardLayout());
			
			//Create a panel for each Portfolio
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
		public HashMap<Portfolio, ArrayList<TransactionBuilder>> getAllValues() throws IllegalArgumentException{
			HashMap<Portfolio, ArrayList<TransactionBuilder>> output = new HashMap<Portfolio, ArrayList<TransactionBuilder>>();
			
			//creates a container list for all the portfolio panels
			Component[] subPanelList = this.getComponents();
			
			//iterates through the panels and gets the information
			for(int i = 0; i < subPanelList.length; ++i){
				OptionPanel panel = (OptionPanel)subPanelList[i];
				
				
				//gets the transaction information supplied by this panel 
				try{
					ArrayList<TransactionBuilder> v = panel.getValues();
					
					//adds information to the retrieved information
					output.put(panel.getPortfolio(), v);
					
				}catch(IllegalArgumentException e){
					throw e;
				}
			}
			
			//returns this new set of information
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
			
			/**
			 * Creates a panel so the user can edit the wanted portfolios
			 * with the wanted companies.
			 *
			 * @param p The portfolios associated with this panel.
			 * @param companies The companies which the user wants to edit
			 */
			public OptionPanel(Portfolio p, ArrayList<Company> companies){
				super();
				
				//Initializes the members
				mPortfolio = p;
				mCompanyPanels = new ArrayList<SelectorPanel>(companies.size());
				
				//set the look of the panel
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				this.setBorder(BorderFactory.createTitledBorder(p.getName()));
				
				//Adds each company as a panel Main panel
				for(Company c : companies){
					SelectorPanel sPanel = new SelectorPanel(c);	//creates a panel for the company
					
					//adds the panel to the logical and the graphic UI
					mCompanyPanels.add(sPanel);
					this.add(sPanel);
				}
				
			}
			
			/**
			 * Returns the portfolio assigned to this panel.
			 *
			 * @return The portfolio of this instance.
			 */
			public Portfolio getPortfolio(){
				return mPortfolio;
			}
			
			/**
			 * Returns the values set of inputs that are located in 
			 * this panel.
			 *
			 * @return The input values.
			 * 
			 * @throws IllgealArgumentException if the builder decides that the values are
			 * not considered valid.
			 */
			public ArrayList<TransactionBuilder> getValues() throws IllegalArgumentException{
				//return list
				ArrayList<TransactionBuilder> list = new ArrayList<TransactionBuilder>();
				
				//iterate through the input surfaces
				for(SelectorPanel panel : mCompanyPanels){
					TransactionBuilder builder = new TransactionBuilder();
					builder.setPortrfolio(mPortfolio);
					
					try{
						panel.getInputValues(builder);
					}catch(IllegalArgumentException e){
						throw new IllegalArgumentException(e.getMessage() + " in " + mPortfolio.getName());
					}
					
					list.add(builder);
				}
				
				
				//return the values
				return list;
			}
			
			/**
			 * The panel where the user selects all the input variables.
			 * It contains the graphic interfaces for the user
			 *
			 * @author David Boivin (Spit)
			 */
			private class SelectorPanel extends JPanel{
				
				private final String TOGGLE_BUTTON_BUY = "Buy";
				private final String TOGGLE_BUTTON_SELL = "Sell";
				private final String QUANTITY_INPUT_LABEL = "Quantity: ";
				private final String PRICE_INPUT_LABEL = "Price: ";
				
				private Company mCompany;
				private JToggleButton mBuySellToggle;
				private JTextField mQuantity;
				private JTextField mPrice;
				
				/**
				 * Constructor that builds and initializes all the input interfaces.
				 *
				 * @param company The company to make this Panel represent.
				 */
				public SelectorPanel(Company company){
					super();
					
					//set up layout
					this.setLayout(new GridLayout(1, 3));
					this.setBorder(BorderFactory.createTitledBorder(company.getName()));
					
					mCompany = company;
					
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
					
					//add input interfaces
					this.add(togglePanel);
					this.add(quantityPanel);
					this.add(pricePanel);
				}
				
				/**
				 * Return the values of the input interfaces.
				 *
				 * @return The set of values for this interface
				 * 
				 * @throws IllegalArgumentException If the value in the input interfaces are not valid.
				 */
				public void getInputValues(TransactionBuilder builder) throws IllegalArgumentException{
					
					try{
						builder.setCompany(mCompany);
						builder.setTransactionType(mBuySellToggle.getText());
						
						if(mBuySellToggle.getText().equals(TransactionInterface.BUY_TRANSACTION_NAME)){
							builder.setShareQuantity(mQuantity.getText());
						}
						
						builder.setSharePrice(mPrice.getText());
						
					}catch(IllegalArgumentException e){
						throw new IllegalArgumentException("Error with: " + mCompany.getName());
					}
				}
				
				/**
				 * Listener for the toggle button. Which will enable or disable the
				 * quantity field, based on if it is Selling or buying.
				 *
				 * @author David Boivin (Spit)
				 */
				private class ToggleListener implements ActionListener{
					
					private JTextField mQuantityInput; 
					
					/**
					 * Constructor which input interface for quantity to 
					 * a member variable.
					 *
					 * @param quantityInput The quantity input interface.
					 */
					public ToggleListener(JTextField quantityInput){
						mQuantityInput = quantityInput;
					}
					
					/**
					 * Switches the text on the ToggleButton and sets the quantity
					 * input interface enabled or disables accordingly.
					 */
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
