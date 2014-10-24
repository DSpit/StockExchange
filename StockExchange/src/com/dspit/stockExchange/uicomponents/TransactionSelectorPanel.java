package com.dspit.stockExchange.uicomponents;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;

@SuppressWarnings("serial")
public class TransactionSelectorPanel extends JPanel {
	
	private final String NEXT_BUTTON = "Next";
	
	public TransactionSelectorPanel(PortfolioList portfolios, CompanyList companies){
		super();
		
		LayoutManager lm = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(lm);
		
		this.add(new SelectorPanel(portfolios));
		this.add(new SelectorPanel(companies));
		this.add(this.getGUIControls());
	}
	
	private JPanel getGUIControls(){
		
		JPanel controlPanel = new JPanel();
		
		
		
		JButton next = new JButton(NEXT_BUTTON);
		next.setActionListener(new NextListener());
		
		
		return null; //TODO
	}

}
