package cop4331;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignInPage extends JPanel{
	public SignInPage(CardLayout cardLayout, JPanel cards) {

		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		//con.fill = GridBagConstraints.VERTICAL;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel("Username"), con);
		con.gridx = 1;
		con.gridy = 0;
		this.add(new TextField("Username"), con);
		con.gridx = 0;
		con.gridy = 1;
		this.add(new JLabel("Password"), con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(new TextField("Password"), con);
		con.gridx = 0;
		con.gridy = 2;
		con.gridwidth = 2;
		this.add(createSignUpButton(cardLayout,cards), con);
		
		this.setVisible(true);
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

