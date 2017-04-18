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
	
	public SignUpPage(CardLayout cardLayout, JPanel cards) {
		
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
		this.add(createSignInButton(cardLayout,cards), con);
		con.gridx = 1;
		con.gridy = 4;
		this.add(createSignUpButton(cardLayout,cards), con);
		
		this.setVisible(true);
	}
	
	
	
	private JButton createSignInButton(CardLayout cardLayout, JPanel cards) {

		JButton button = new JButton("Sign In");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cards, "Sign In");
			}
			
		});
		
		return button;
	}
	
	private JButton createSignUpButton(CardLayout cardLayout, JPanel cards) {
JButton button = new JButton("Sign Up");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cards, "Sign Up");
			}
			
		});
		
		return button;
	}
}
