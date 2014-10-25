package com.dspit.stockExchange.uicomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.Company;
import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.Portfolio;
import com.dspit.stockExchange.data.PortfolioList;

/**
 * The Selector panel where the user can select which portfolios and companies
 * it wishes to edit.
 *
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial") 
public class TransactionSelectorPanel extends JPanel {
	
// Constants --------------------------------------------------------------- //
	
	private final String NEXT_BUTTON = "Next";
	
// GUI COmponents ---------------------------------------------------------- //
	
	private SelectorPanel<Portfolio> mPortfoliosPanel;
	private SelectorPanel<Company> mCompaniesPanel;
	private JButton mControlButton;
	private JLabel mErrorMessage;
	
// Constructors ------------------------------------------------------------ //
	
	public TransactionSelectorPanel(PortfolioList portfolios, CompanyList companies,
			ActionListener controlListener){
		super();
		
		//set panel layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//setup the selector panels
		JPanel selectorContainer = new JPanel();
		selectorContainer.setLayout(new BoxLayout(selectorContainer, BoxLayout.X_AXIS));
		mPortfoliosPanel = new SelectorPanel<Portfolio>(portfolios);
		mCompaniesPanel = new SelectorPanel<Company>(companies);
		selectorContainer.add(mPortfoliosPanel);
		selectorContainer.add(mCompaniesPanel);
		
		//initialize the control button
		mControlButton = new JButton(NEXT_BUTTON);
		mControlButton.addActionListener(controlListener);
		
		//initialize the error label
		mErrorMessage = new JLabel("");
		mErrorMessage.setFont(new Font(null, Font.PLAIN, 12));
		mErrorMessage.setForeground(Color.RED);
		
		this.add(selectorContainer);
		this.add(this.setupGUIControls());
		
	}
	
// Public Methods ---------------------------------------------------------- //
	
	/**
	 * Method which returns the selected portfolios.
	 *
	 * @return An array of Portfolio which the user has selected.
	 */
	public ArrayList<Portfolio> getSelectedPortfolios(){
		return mPortfoliosPanel.getSelected();
	}
	
	/**
	 * Method which returns an array of selected companies.
	 *
	 * @return An array Company which the user has selected.
	 */
	public ArrayList<Company> getSelectedCompanies(){
		return mCompaniesPanel.getSelected();
	}
	
	/**
	 * A method which sets an error for the user to see.
	 *
	 * @param message Error Message to display the user
	 */
	public void displayUserError(String message){
		mErrorMessage.setText(message);
	}
	
	/**
	 * Method which removes all text from the error message.
	 */
	public void removeUserError(){
		mErrorMessage.setText("");
	}
	
// Private Methods --------------------------------------------------------- //
	
	/**
	 * Method which is simply for properly setting up the control panel area
	 * of this panel
	 *
	 * @return A {@JPanel} which contains all the controls for this panel.
	 */
	private JPanel setupGUIControls(){
		
		//basic panel set up
		JPanel controlPanel = new JPanel();
		LayoutManager lm = new BoxLayout(controlPanel, BoxLayout.Y_AXIS);
		controlPanel.setLayout(lm);
		controlPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		//add components to panel
		controlPanel.add(mErrorMessage);
		controlPanel.add(mControlButton);
		
		return controlPanel;
	}

}
