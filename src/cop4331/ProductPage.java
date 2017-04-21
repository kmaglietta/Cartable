package cop4331;

import java.awt.*;

import javax.swing.*;

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
		
		this.updateUI();
	}
}
