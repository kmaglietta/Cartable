package cop4331;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignUpPage extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;
	
	public SignUpPage(CardLayout cardLayout, JPanel cards) {
		
		this.cardLayout = cardLayout;
		this.cards = cards;
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		//Name
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel("Name"), con);
		con.gridx = 1;
		con.gridy = 0;
		this.add(new TextField("Name", 10), con);
		
		//Username
		con.gridx = 0;
		con.gridy = 1;
		this.add(new JLabel("Username"), con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(new TextField("Username", 10), con);
		
		//Password
		con.gridx = 0;
		con.gridy = 2;
		this.add(new JLabel("Password"), con);
		con.gridx = 1;
		con.gridy = 2;
		this.add(new TextField("Password", 10), con);
		
		//Password
		con.gridx = 0;
		con.gridy = 3;
		this.add(new JLabel("Confirm Password"), con);
		con.gridx = 1;
		con.gridy = 3;
		this.add(new TextField("Confirm Password", 10), con);
		
		//Buttons
		con.gridx = 0;
		con.gridy = 4;
		this.add(createShowButton("Sign In"), con);
		con.gridx = 1;
		con.gridy = 4;
		this.add(createShowButton("Sign Up"), con);
		
		this.setVisible(true);
	}
	
	
	//pass string of panle name
	private JButton createShowButton(String name) {

		JButton button = new JButton(name);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showPanel(name);
			}
			
		});
		
		return button;
	}
	
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
}
