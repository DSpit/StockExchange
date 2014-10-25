

package com.dspit.stockExchange.uicomponents;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * This class represents a panel which displays a single column of 
 * elements and each of those elements are selectable.
 * 
 * @author David Boivin (Spit)
 * @param <E> an Elements which will be used by calling E.toString()
 * 	so as to assign text to the check boxes
 */
@SuppressWarnings("serial")
public class SelectorPanel<E> extends JPanel {
	
// Constants --------------------------------------------------------------- //
	
	private final int PADDING = 5;
	
// Members ----------------------------------------------------------------- //
	
	private ArrayList<ElementCheckBox> options;
	
// Constructor ------------------------------------------------------------- //
	
	public SelectorPanel(ArrayList<E> elements){
		super();
		
		//set panel layout
		this.setLayout(new GridLayout(elements.size(), 1, PADDING, PADDING));
		
		options = new ArrayList<ElementCheckBox>();
		
		//sets each element in the element array 
		for(E element : elements){
			//new CheckBox
			ElementCheckBox cBox = new ElementCheckBox(element);		
			
			//add to logical list
			options.add(cBox);
			
			//add to panel
			this.add(cBox, elements.indexOf(element));
		}
	}
	
// Public Methods ---------------------------------------------------------- //
	
	public ArrayList<E> getSelected(){
		
		ArrayList<E> output = new ArrayList<>();
		
		for(ElementCheckBox i : options){
			if(i.isSelected()){
				output.add(i.getElement());
			}
		}
		
		return output;
	}
	
//Private inner classes ---------------------------------------------------- //
	
	/**
	 * The same as a {@link JCheckBox} except for the fact that it holds
	 * an element and allows access to it later. Although, you can't modify which
	 * element is inside a particular instance of this class (by design).
	 * 
	 * @author David Boivin (Spit)
	 */
	public class ElementCheckBox extends JCheckBox {
		
	// Members ----------------------------------------------------------------- //
		
		private E mElement;
		
	// Constructor ------------------------------------------------------------- //
		
		/**
		 * Constructor which assigns the text to this JCheckBox
		 *
		 * @param element
		 */
		public ElementCheckBox(E element){
			super(element.toString());
			
			mElement = element;
		}
		
	// Public Methods ---------------------------------------------------------- //
		
		/**
		 * Returns the object which is held within this class.
		 *
		 * @return The Element of this instance.
		 */
		public E getElement(){
			return mElement;
		}
	}


}
