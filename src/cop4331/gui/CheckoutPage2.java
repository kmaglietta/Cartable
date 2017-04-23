package cop4331.gui;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class CheckoutPage2 extends Page {

	public CheckoutPage2(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	@Override
	public void display(){
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		this.add(new JLabel("Thank you for your purchase!"));
		this.updateUI();
	}

}
