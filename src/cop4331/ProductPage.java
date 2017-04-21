package cop4331;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProductPage extends JPanel {
	CardLayout cardlayout;
	JPanel cards;

	public ProductPage(CardLayout cardLayout, JPanel cards) {
		this.cardlayout = cardLayout;
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
				display();
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				reset();
			}
			
		});
	}
	
	private void display(){
		this.add(new CustomerNavPanel(cardlayout, cards));
		System.out.println("Product");
		this.updateUI();
	}
	
	private void reset(){
		this.removeAll();
	}

}
