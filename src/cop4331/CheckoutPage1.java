package cop4331;

import java.awt.*;
import java.awt.event.*;

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
		this.add(createCheckoutButton());
		this.updateUI();
	}

	private Component createCheckoutButton() {
		JButton checkout = new JButton("Checkout");
		checkout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseInterface.getInstance().setOrder(
						Session.getInstance().getUser().getId(),
						Session.getInstance().getCart().getProducts());
				Session.getInstance().getCart().clear();
				DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				showPanel("Checkout2");
			}
			
		});
		
		return checkout;
	}

}
