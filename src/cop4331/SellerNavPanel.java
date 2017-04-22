package cop4331;

import java.awt.CardLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SellerNavPanel extends NavPanel {

	public SellerNavPanel(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	@Override
	public void display(){
		this.add(createShowButton("Inventory"));
		this.add(createShowButton("Sales"));
		this.add(createSignOutButton());
		this.updateUI();

		System.out.println("Inventory Nav");
	}
}
