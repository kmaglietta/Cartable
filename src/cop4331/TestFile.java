package cop4331;

import java.awt.CardLayout;

import javax.swing.*;

public class TestFile {
	static CardLayout cardLayout = new CardLayout();
	
	public static void main (String[] args) {
		//DatabaseInterface.getInstance();
		JFrame frame = new JFrame();
		JPanel cards = new JPanel(cardLayout);
		cards.add(new SignInPage(cardLayout, cards), "Sign In");
		cards.add(new SignUpPage(cardLayout, cards), "Sign Up");
		cardLayout.show(cards, "Sign In");
		frame.add(cards);
		frame.pack();
		frame.setVisible(true);
	}
}
