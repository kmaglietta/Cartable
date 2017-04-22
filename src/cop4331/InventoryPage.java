package cop4331;

import java.awt.CardLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InventoryPage extends Page {

	public InventoryPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	public void display(){
		this.add(new SellerNavPanel(super.getCardLayout(), super.getCards()));
		this.updateUI();
	}

}
