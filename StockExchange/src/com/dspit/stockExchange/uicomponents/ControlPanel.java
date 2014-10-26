

package com.dspit.stockExchange.uicomponents;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Basic format control scheme implementations.
 * 
 * @author David Boivin (Spit)
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel {
	
// Constants --------------------------------------------------------------- //
	
	public static final String CONTROL_BUTTON_DONE = "Done";
	public static final String CONTROL_BUTTON_NEXT = "Next";
	
// UI Components  ---------------------------------------------------------- //
	
	JButton mControlButton;
	JLabel mErrorLabel;
	
// Constructor ------------------------------------------------------------- //
	
	protected ControlPanel(String cButtonText, ActionListener controlListener){
		super();
		
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		mControlButton = new JButton(cButtonText);
		mControlButton.addActionListener(controlListener);
		
		//initialize the error label
		mErrorLabel = new JLabel("");
		mErrorLabel.setFont(new Font(null, Font.PLAIN, 12));
		mErrorLabel.setForeground(Color.RED);
		
		//add components to panel
		this.add(mErrorLabel);
		this.add(mControlButton);
	}
	
// Public Method ----------------------------------------------------------- //
	
	/**
	 * A method which sets an error for the user to see.
	 *
	 * @param message Error Message to display the user
	 */
	public void setErrorMessage(String message){
		mErrorLabel.setText(message);
	}
	
	/**
	 * Method which removes all text from the error message.
	 */
	public void removeErrorMessage(){
		mErrorLabel.setText("");
	}

}
