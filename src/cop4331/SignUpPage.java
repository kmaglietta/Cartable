package cop4331;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignUpPage extends JPanel{
	public SignUpPage(CardLayout cardLayout, JPanel cards) {
		JButton button = new JButton("Sign Up");
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cards, "Sign In");
				
			}
			
		});
		
		this.add(button);
		
		this.setVisible(true);
	}
}
