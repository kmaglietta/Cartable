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
		this.add(createSignOutButton(),con);
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel("Welcome " + Session.getInstance().getUser().getUsername()),con);
		
		con.gridy = 2;
		
		con.gridx = 0;
		this.add(new JLabel("Product"),con);
		con.gridx = 1;
		this.add(new JLabel("Description"),con);
		con.gridx = 2;
		this.add(new JLabel("Cost"),con);
		
		while(it.hasNext()){
			con.gridy++;
			Product pro = it.next();con.gridx = 0;
			this.add(new JLabel(pro.getName()),con);
			con.gridx = 1;
			this.add(new JLabel(pro.getDescription()),con);
			con.gridx = 2;
			this.add(new JLabel(String.valueOf(pro.getCost())),con);
		}
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
