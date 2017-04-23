package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPanel;

import cop4331.data.Product;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class InventoryPage extends Page {

	public InventoryPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Product prod = null;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new SellerNavPanel(super.getCardLayout(), super.getCards()),con);
		Iterator<Product> it = DatabaseInterface.getInstance().getProducts(Session.getInstance().getUid());
		while(it.hasNext()){
			prod = it.next();
			con.gridy++;
			con.gridx = 0;
			this.add(new ProductPanel(prod,super.getCardLayout(),super.getCards()),con);
			con.gridx = 1;
			this.add(createEditButton(prod),con);
		}
		this.updateUI();
	}

	private Component createEditButton(Product prod) {
		JButton edit = new JButton("Edit");
		
		edit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Edit Product: "+prod.getName());
			}
			
		});
		return edit;
	}

}
