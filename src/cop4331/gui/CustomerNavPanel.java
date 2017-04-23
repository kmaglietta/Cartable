package cop4331.gui;

import java.awt.*;

import javax.swing.*;

import cop4331.singles.Session;

@SuppressWarnings("serial")
/**<h1>CustomerNavPanel</h1>
 * Displays the options the customer has
 * @author */
public class CustomerNavPanel extends NavPanel{
	
	/**<h1>constructor</h1>
	 * Uses the constructor from NavPanel
	 * @author */
	public CustomerNavPanel(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	/**<h1>display</h1>
	 * Creates and adds the nessaccary buttons
	 * @author */
	@Override
	public void display(){
		this.add(new JLabel("Welcome "+Session.getInstance().getUser().getUsername()));
		this.add(createShowButton("Available"));
		this.add(createShowButton("Cart"));
		this.add(new JLabel("$"+String.valueOf(Session.getInstance().getCart().getTotal())));
		this.add(createSignOutButton());
	}
	
}
