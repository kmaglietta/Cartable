package cop4331;

import java.awt.CardLayout;
import java.util.Iterator;

import javax.swing.*;

@SuppressWarnings("serial")
public class CartPage extends Page{

	public CartPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void display(){
		Iterator<Product> prods = Session.getInstance().getCart().getProducts();
		this.add(new CustomerNavPanel(super.getCardLayout(), super.getCards()));
		while(prods.hasNext()){
			this.add(new ProductPanel(prods.next(),super.getCardLayout(), super.getCards()));
		}
		this.updateUI();
	}
}
