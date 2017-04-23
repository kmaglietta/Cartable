package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import cop4331.data.Product;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class CartPage extends Page{
	
	public CartPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Product prod = null;
		
		con.gridy = 0;
		con.gridx = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		
		Iterator<Product> prods = Session.getInstance().getCart().getProducts();
		while(prods.hasNext()){
			prod = prods.next();
			con.gridy++;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.gridx = 0;
			this.add(new ProductPanel(prod,super.getCardLayout(), super.getCards()),con);
			con.gridx = 1;
			this.add(createDeleteButton(prod), con);
		}
		con.gridy++;
		con.gridx = 0;
		con.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Total: $" + String.valueOf(Session.getInstance().getCart().getTotal())),con);
		con.gridx = 1;
		this.add(createCheckOutButton(),con);
		this.updateUI();
	}

	private Component createCheckOutButton() {
		JButton check = new JButton("Checkout");
		
		check.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showPanel("Checkout1");
			}
			
		});
		return check;
	}

	private Component createDeleteButton(Product prod) {
		JButton delete = new JButton("Delete");
		
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Session.getInstance().getCart().remove(prod))
						DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				refresh();
			}
			
		});
		return delete;
	}
	
	
}
