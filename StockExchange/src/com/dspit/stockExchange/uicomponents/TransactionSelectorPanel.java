package com.dspit.stockExchange.uicomponents;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.dspit.stockExchange.data.CompanyList;
import com.dspit.stockExchange.data.PortfolioList;

@SuppressWarnings("serial")
public class TransactionSelectorPanel extends JPanel {
	
	private final String NEXT_BUTTON = "Next";
	
	private JPanel
	
	public TransactionSelectorPanel(PortfolioList portfolios, CompanyList companies){
		super();
		
		LayoutManager lm = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(lm);
		
		mPortfolioPanel = new SelectorPanel(portfolios);
		
		this.add(mPortfolioPanel);
		this.add(new SelectorPanel(companies));
		this.add(this.getGUIControls());
	}
	
	private JPanel getGUIControls(){
		
		JPanel controlPanel = new JPanel();
		
		
		
		JButton next = new JButton(NEXT_BUTTON);
		next.setActionListener(new NextListener());
		
		
		return null; //TODO
	}
	
	
	private class NextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}

}
