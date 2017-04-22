package cop4331;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class CustomerNavPanel extends NavPanel{

	public CustomerNavPanel(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	@Override
	public void display(){
		this.add(new JLabel("Welcome "+Session.getInstance().getUser().getUsername()));
		this.add(createShowButton("Available"));
		this.add(createShowButton("Cart"));
		this.add(new JLabel("$"+String.valueOf(Session.getInstance().getCart().getTotal())));
		this.add(createSignOutButton());
	}
	
}
