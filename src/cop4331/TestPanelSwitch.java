package cop4331;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestPanelSwitch {
	static CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("Cartable");
		frame.setLayout(new BorderLayout());
		JPanel cards = new JPanel(cardLayout);
		cards.setSize(500,500);
		
		cards.add(new SignInPage(cardLayout, cards), "SignIn");
		cards.add(new SignUpPage(cardLayout, cards), "SignUp");
		cards.add(new AvailableProdcutsPage(cardLayout, cards), "Available");
		cards.add(new ProductPage(cardLayout, cards), "Product");
		cards.add(new CartPage(cardLayout,cards), "Cart");
		cards.add(new CheckoutPage1(cardLayout,cards), "Checkout1");
		cards.add(new CheckoutPage2(cardLayout,cards), "Checkout2");
		cards.add(new InventoryPage(cardLayout,cards), "Inventory");
		cards.add(new SalesPage(cardLayout,cards), "Sales");
		
		cardLayout.show(cards, "SignIn");
		
		frame.add(cards, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

}
