package cop4331;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ProductPanel extends JPanel{
	Product prod;
	public ProductPanel(Product prod, CardLayout cardLayout, JPanel cards){
		this.prod = prod;
		
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		

		con.weighty = con.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel(prod.getName()),con);
		con.gridx = 2;
		this.add(new JLabel("$"+String.valueOf(prod.getCost())),con);
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = 3;
		this.add(new JLabel(prod.getDescription()),con);
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				Session.getInstance().setProduct(prod);
				cardLayout.show(cards, "Product");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
	}
}
