package cop4331;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignInPage extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;
	
	public SignInPage(CardLayout cardLayout, JPanel cards) {
		
		this.cardLayout = cardLayout;
		this.cards = cards;
		
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

