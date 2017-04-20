package cop4331;

import java.awt.CardLayout;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class AvailableProdcutsPage extends JPanel{
	public AvailableProdcutsPage(CardLayout cardLayout, JPanel cards) {
		
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
		this.add(new JLabel("hello"));
	}
	
	private void display() {
				this.add(new JLabel(Session.getInstance().getUser().getUsername()));
				this.updateUI();	
			
	}
}
