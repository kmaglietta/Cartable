package cop4331;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CustomerNavPanel extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;

	public CustomerNavPanel(CardLayout cardLayout, JPanel cards){
		this.cardLayout = cardLayout;
		this.cards = cards;
		this.add(new JLabel("Welcome "+Session.getInstance().getUser().getUsername()));
		this.add(createSignOutButton());
	}
	
	private JButton createSignOutButton(){
		JButton signout = new JButton("SignOut");
		
		signout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Session.getInstance().clear();
				showPanel("SignIn");
			}
			
		});
		return signout;
	}
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
}
