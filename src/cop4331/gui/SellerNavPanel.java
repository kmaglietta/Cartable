package cop4331.gui;

import java.awt.CardLayout;

import javax.swing.JPanel;
/**<h1>SellerNavPanel</h1>
 * Displays the options the seller has
 * @author */
@SuppressWarnings("serial")
public class SellerNavPanel extends NavPanel {
	
	/**<h1>constructor</h1>
	 * Uses the constructor from NavPanel
	 * @author */
	public SellerNavPanel(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	/**<h1>display</h1>
	 * Creates and adds the nessaccary buttons
	 * @author */
	@Override
	public void display(){
		this.add(createShowButton("Inventory"));
		this.add(createShowButton("Add"));
		this.add(createShowButton("Sales"));
		this.add(createSignOutButton());
		this.updateUI();
	}
}
