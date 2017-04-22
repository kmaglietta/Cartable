package cop4331;

import java.awt.CardLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class CheckoutPage1 extends Page {

	public CheckoutPage1(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display(){
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		this.add(new JLabel("Checkout"));
		this.updateUI();
	}

}
