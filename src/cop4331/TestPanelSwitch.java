package cop4331;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanelSwitch {
	static CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Cartable");
		JPanel cards = new JPanel(cardLayout);
		cards.setSize(500,500);
		
		cards.add(new SignInPage(cardLayout, cards), "SignIn");
		cards.add(new SignUpPage(cardLayout, cards), "SignUp");
		cards.add(new AvailableProdcutsPage(cardLayout, cards), "Available");
		cards.add(new ProductPage(cardLayout, cards), "Product");
		
		cardLayout.show(cards, "Sign In");
		
		frame.add(cards);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

}
