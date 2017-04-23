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
/**<h1>AvailableProductsPage</h1>
 * Displays all the products available to buy
 * @author */
public class AvailableProdcutsPage extends Page{
	/**<h1>constructor</h1>
	 * Uses the constructor from Page
	 * @author */
	public AvailableProdcutsPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	/**<h1>display</h1>
	 * Displays the actual elements in the page
	 * @author */
	@Override
	public void display() {
		
		//Create a gridBagLayout
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		//Get the available products iterator
		Iterator<Product> it = DatabaseInterface.getInstance().getProducts();	
		Product prod = null;
		
		//Add the customer nav-bar
		con.gridx = 0;
		con.gridy = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()), con);
		
		//For each available product construct and add a ProductPanel and add-to-cart button
		while(it.hasNext()){
			prod = it.next();
			con.gridy++;
			con.fill = GridBagConstraints.HORIZONTAL;
			con.gridx = 0;
			this.add(new ProductPanel(prod,super.getCardLayout(), super.getCards()),con);
			con.gridx = 1;
			this.add(createAddCartButton(prod), con);
		}
		
		//Refresh the ui to show 
		this.updateUI();	
	}
	
	//Creates a button that adds the passed product to the users cart
	private Component createAddCartButton(Product prod) {
		JButton add = new JButton("Add To Cart");
		
		//Atach an action listener
		add.addActionListener(new ActionListener(){

			@Override
			//Add the product to the cart
			//Update the cart database
			//refresh the page
			public void actionPerformed(ActionEvent e) {
				Session.getInstance().getCart().add(prod);
				DatabaseInterface.getInstance().updateCartTabel(Session.getInstance().getUid(), Session.getInstance().getCart());
				refresh();
			}
			
		});
		
		return add;
	}
}
