package cop4331.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**<h1>Application</h1>
 * The application main
 * @author */
public class Application {

	static CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		//Create the fame to hold everything
		JFrame frame = new JFrame("Cartable");
		frame.setLayout(new BorderLayout());
		JPanel cards = new JPanel(cardLayout);
		cards.setSize(500,500);
		
		//Add all the pages with labels
		cards.add(new SignInPage(cardLayout, cards), "SignIn");
		cards.add(new SignUpPage(cardLayout, cards), "SignUp");
		cards.add(new AvailableProdcutsPage(cardLayout, cards), "Available");
		cards.add(new ProductPage(cardLayout, cards), "Product");
		cards.add(new CartPage(cardLayout,cards), "Cart");
		cards.add(new CheckoutPage1(cardLayout,cards), "Checkout1");
		cards.add(new CheckoutPage2(cardLayout,cards), "Checkout2");
		cards.add(new InventoryPage(cardLayout,cards), "Inventory");
		cards.add(new SalesPage(cardLayout,cards), "Sales");
		cards.add(new AddProductPage(cardLayout,cards),"Add");
		
		//App's Initial view starts at the SignIn screen
		cardLayout.show(cards, "SignIn");
		
		//Wrap everything up!
		frame.add(cards, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
