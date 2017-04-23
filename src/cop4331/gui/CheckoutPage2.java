package cop4331.gui;

import java.awt.*;

import javax.swing.*;

/**<h1>CheckoutPage2</h1>
 * Displays the actual elements in the page
 * @author */
@SuppressWarnings("serial")
public class CheckoutPage2 extends Page {
	
	/**<h1>constructor</h1>
	 * Uses the constructor from Page
	 * @author */
	public CheckoutPage2(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	/**<h1>display</h1>
	 * Displays the actual elements in the page
	 * @author */
	@Override
	public void display(){
		//Customer nav
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		//confirmation
		this.add(new JLabel("Thank you for your purchase!"));
		this.updateUI();
	}

}
