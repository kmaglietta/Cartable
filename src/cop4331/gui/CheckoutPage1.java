package cop4331.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class CheckoutPage1 extends Page {

	public CheckoutPage1(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.weightx = 0.0;
		con.gridwidth = 2;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		con.gridy++;
		this.add(new JLabel("Payment Information"),con);
		
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Full Name"), con);
		con.gridx = 1;
		this.add(new TextField("Full Name"), con);
		
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Card Number"), con);
		con.gridx = 1;
		this.add(new TextField("12314814689"), con);
		
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Are you a robot"), con);
		con.gridx = 1;
		this.add(new JCheckBox(), con);
		
		con.gridy++;
		con.fill = GridBagConstraints.HORIZONTAL;

		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridwidth = 2;
		con.gridx = 0;
		con.gridy++;
		con.gridy++;
		this.add(createCheckoutButton(),con);
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
