package cop4331;

import java.awt.CardLayout;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class AvailableProdcutsPage extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;

	public AvailableProdcutsPage(CardLayout cardLayout, JPanel cards) {
		
		this.cardLayout = cardLayout;
		this.cards = cards;
		
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
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void display() {
		this.add(createSignOutButton());
		this.add(new JLabel(Session.getInstance().getUser().getUsername()));
		this.updateUI();	
			
	}
	
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	private JButton createSignOutButton(){
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
}
