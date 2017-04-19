package cop4331;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanelSwitch {
	static CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
