package cop4331;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NavPanel extends JPanel {

	private CardLayout cardLayout;
	private JPanel cards;
	
	public NavPanel(CardLayout cardLayout, JPanel cards){
		setCardLayout(cardLayout);
		setCards(cards);
		display();
	}

	protected void display() {
		
	}

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
	protected void showPanel(String name) {
		cardLayout.show(cards, name);
	}
}
