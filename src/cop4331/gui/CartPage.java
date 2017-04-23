package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

import cop4331.data.Product;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

/**<h1>CartPage</h1>
 * Displays all the items currently in the users cart
 * @author */
@SuppressWarnings("serial")
public class CartPage extends Page{
	
	/**<h1>Constructor</h1>
	 * Uses the constructor form Page
	 * @author */
	public CartPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	/**<h1>display</h1>
	 * Displays the actual elements in the page
	 * @author */
	@Override
	public void display(){
		
		//Create the grid bag layout
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Product prod = null;
		
		//Add the customer nav-bar
		con.gridy = 0;
		con.gridx = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		
		//For each product in the cart create and add a ProductPanel and remove-from-cart button
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
		
		//Display the total
		con.gridy++;
		con.gridx = 0;
		con.fill = GridBagConstraints.HORIZONTAL;
		this.add(new JLabel("Total: $" + String.valueOf(Session.getInstance().getCart().getTotal())),con);
		
		//Create and add the checkout button
		con.gridx = 1;
		this.add(createCheckOutButton(),con);
		this.updateUI();
	}

	/**<h1>createCheckOutButton</h1>
	 * Generates a button used for checking out
	 * @author */
	private Component createCheckOutButton() {
		JButton check = new JButton("Checkout");
		
		check.addActionListener(new ActionListener(){
			//Show the first checkout page
			@Override
			public void actionPerformed(ActionEvent e) {
				showPanel("Checkout1");
			}
			
		});
		return check;
	}
	/**<h1>createDeleteButton</h1>
	 * Generates a button used for removing the product from cart
	 * @author */
	private Component createDeleteButton(Product prod) {
		JButton delete = new JButton("Delete");
		
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//If product was succesfuly removed from the cart
				if(Session.getInstance().getCart().remove(prod))
					//Update the cart table
						DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				//Refresh
				refresh();
			}
			
		});
		return delete;
	}
	
	
}
