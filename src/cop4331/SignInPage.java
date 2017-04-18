package cop4331;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignInPage extends JPanel{
	public SignInPage(CardLayout cardLayout, JPanel cards) {
		JButton button = new JButton("Sign In");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cards, "Sign Up");
				
			}
			
		});
		
		this.add(button);
		
		this.setVisible(true);
	}
}
