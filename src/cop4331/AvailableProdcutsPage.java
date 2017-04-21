package cop4331;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Iterator;

import javax.swing.*;

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

		con.gridx = 2;
		con.gridy = 0;
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()), con);
		
		con.gridy = 2;
		
		con.gridx = 0;
		this.add(new JLabel("Product"),con);
		con.gridx = 1;
		this.add(new JLabel("Description"),con);
		con.gridx = 2;
		this.add(new JLabel("Cost"),con);
		con.gridx = 0;
		while(it.hasNext()){
			con.gridy ++;
			con.ipady = 40;
			//con.fill = con.HORIZONTAL;
			con.gridwidth = 3;
			this.add(new ProductPanel(it.next(), super.getCardLayout(), super.getCards()), con);
		}
		this.updateUI();	
	}
}
