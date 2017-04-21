package cop4331;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Iterator;

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
				reset();
			}
			
		});
	}
	private void reset() {
		this.removeAll();
		
	}

	private void display() {
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		Iterator<Product> it = DatabaseInterface.getInstance().getProducts();	

		con.gridx = 2;
		con.gridy = 0;
		this.add(new CustomerNavPanel(cardLayout, cards), con);
		
		con.gridy = 2;
		
		con.gridx = 0;
		this.add(new JLabel("Product"),con);
		con.gridx = 1;
		this.add(new JLabel("Description"),con);
		con.gridx = 2;
		this.add(new JLabel("Cost"),con);
		con.gridx = 0;
		while(it.hasNext()){
			con.gridy ++;
			con.ipady = 40;
			con.fill = con.HORIZONTAL;
			con.gridwidth = 3;
			this.add(new ProductPanel(it.next(), cardLayout, cards), con);
		}
		this.updateUI();	
			
	}
	
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
		
		
}
