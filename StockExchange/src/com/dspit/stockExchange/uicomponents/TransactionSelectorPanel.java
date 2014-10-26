package com.dspit.stockExchange.uicomponents;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
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
	
	
// Members ----------------------------------------------------------------- //
	
	private SelectorPanel<Portfolio> mPortfoliosPanel;
	private SelectorPanel<Company> mCompaniesPanel;
	
// GUI COmponents ---------------------------------------------------------- //
	
	private ControlPanel mControlPanel;
	
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
		
		
		this.add(selectorContainer);
		this.add(mControlPanel = new ControlPanel(ControlPanel.CONTROL_BUTTON_NEXT, controlListener));
		
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
	 * Method used for easy access to control panel
	 * 
	 * @return The control panel of this panel.
	 */
	public ControlPanel getControlPanel(){
		return mControlPanel;
	}

}
