package cop4331.gui;

import java.awt.CardLayout;
import java.awt.event.*;

import javax.swing.*;

/**<h1>Page</h1>
 * The base page for all pages to extend except for form pages.<br>
 * There seems to be something odd about textfilds using this.
 * @author */
@SuppressWarnings("serial")
public class Page extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;
	
	/**<h1>CConstructor</h1>
	 * Sets the cardLayout and cards for sub classes to use<br>
	 * Contains a component listener to see when the page is shown/hidden 
	 * @author */
	public Page(CardLayout cardLayout, JPanel cards) {
		this.setCardLayout(cardLayout);
		this.setCards(cards);
		
		//Listen for if this page is visible or hiden
		this.addComponentListener(new ComponentListener(){

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				//On visible display content
				display();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				//On hid remove content
				reset();
			}
			
		});
	}
	
	/**<h1>display</h1>
	 * @return overwritten by sub-classes
	 * @author */
	public void display(){
		this.add(new JLabel("Page"));
	}
	
	/**<h1>reset</h1>
	 * Removes all elements
	 * @author */
	public void reset(){
		this.removeAll();
	}
	
	/**<h1>refresh</h1>
	 * Refreshes the same page to update any changed elements
	 * @author */
	public void refresh(){
		this.setVisible(false);
		this.setVisible(true);
	}
	
	/**<h1>showPanel</h1>
	 * Show the panel matching the string passed
	 * @author */
	public void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	/**<h1>generatePopUp</h1>
	 * Display a message dialog with the passed string
	 * @author */
	public void generatePopUp(String mes) {
		JOptionPane.showMessageDialog(this, mes);
	}
	
	//Auto generated
	public CardLayout getCardLayout() {
		return cardLayout;
	}
	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}
	public JPanel getCards() {
		return cards;
	}
	public void setCards(JPanel cards) {
		this.cards = cards;
	}
}
