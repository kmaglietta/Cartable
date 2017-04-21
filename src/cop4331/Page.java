package cop4331;

import java.awt.CardLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Page extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;
	public Page(CardLayout cardLayout, JPanel cards) {
		this.setCardLayout(cardLayout);
		this.setCards(cards);
		
		this.addComponentListener(new ComponentListener(){

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				display();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				reset();
			}
			
		});
	}
	public void display(){
		this.add(new JLabel("Page"));
	}
	public void reset(){
		this.removeAll();
	}
	public void showPanel(String name) {
		cardLayout.show(cards, name);
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
}
