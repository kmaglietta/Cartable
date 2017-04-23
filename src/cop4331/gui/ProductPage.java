package cop4331.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cop4331.data.Product;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class ProductPage extends Page {
	
	public ProductPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		Product prod = Session.getInstance().getProduct();
		this.setLayout(new GridBagLayout());
		//nav bar
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		//page contnets
		con.gridy = 1;
		con.gridx = 0;
		this.add(new JLabel(prod.getName()), con);
		con.gridy = 1;
		con.gridx = 1;
		this.add(new JLabel("$"+String.valueOf(prod.getSellPrice())), con);
		con.gridy = 2;
		con.gridx = 0;
		con.gridwidth = 2;
		this.add(new JLabel(prod.getDescription()), con);
		this.add(createAddCartButton(prod));
		this.updateUI();
	}
	
	private Component createAddCartButton(Product prod) {
		JButton add = new JButton("Add To Cart");
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Session.getInstance().getCart().add(prod);
				DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				showPanel("Cart");
			}
			
		});
		
		return add;
	}
}
