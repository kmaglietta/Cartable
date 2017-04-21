package cop4331;

import java.awt.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProductPage extends Page {
	
	public ProductPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void display(){
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		//Display product info
		//display add to cart button
		System.out.println("Product");
		this.updateUI();
	}
}
