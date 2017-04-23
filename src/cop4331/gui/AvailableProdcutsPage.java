package cop4331.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import cop4331.data.Product;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class AvailableProdcutsPage extends Page{
	public AvailableProdcutsPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display() {
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Iterator<Product> it = DatabaseInterface.getInstance().getProducts();	
		Product prod = null;
		
		con.gridx = 0;
		con.gridy = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()), con);
		while(it.hasNext()){
			prod = it.next();
			con.gridy++;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.gridx = 0;
			this.add(new ProductPanel(prod,super.getCardLayout(), super.getCards()),con);
			con.gridx = 1;
			this.add(createAddCartButton(prod), con);
		}
		this.updateUI();	
	}
	
	private Component createAddCartButton(Product prod) {
		JButton add = new JButton("Add To Cart");
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Session.getInstance().getCart().add(prod);
				DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				refresh();
			}
			
		});
		
		return add;
	}
}
