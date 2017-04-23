package cop4331.gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import cop4331.singles.Session;

/**<h1>NavPanel</h1>
 * A super class for all nav-panels
 * @author */
@SuppressWarnings("serial")
public class NavPanel extends JPanel {

	private CardLayout cardLayout;
	private JPanel cards;
	
	/**<h1>Constructor</h1>
	 * Sets the cardLayout and cards 
	 * @author */
	public NavPanel(CardLayout cardLayout, JPanel cards){
		setCardLayout(cardLayout);
		setCards(cards);
		display();
	}
	
	/**<h1>display</h1>
	 * Overwritten by sub classes
	 * @author */
	protected void display() {
		
	}
	
	//auto-generated
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
	}
	
	/**<h1>CreateShowButton</h1>
	 * @return A button that shows the panel matching the passed string
	 * @author */
	protected JButton createShowButton(String string) {
		// TODO Auto-generated method stub
		JButton show = new JButton(string);
		show.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showPanel(string);
			}
			
		});
		
		return show;
	}
	
	/**<h1>CreateSignOutButton</h1>
	 * @return A button that clears the session and redirects back to the sign
	 * @author */
	protected JButton createSignOutButton(){
		JButton signout = new JButton("SignOut");
		
		signout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Session.getInstance().clear();
				showPanel("SignIn");
			}
			
		});
		return signout;
	}
	
	/**<h1>showPanel</h1>
	 * @return Shows that panel of passed string
	 * @author */
	protected void showPanel(String name) {
		cardLayout.show(cards, name);
	}
}
