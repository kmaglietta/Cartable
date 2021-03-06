package cop4331.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cop4331.data.Product;
import cop4331.singles.Session;

@SuppressWarnings("serial")
/**<h1>ProductPanel</h1>
 * A nice little panel that other Components can add to show a product
 * @author */
public class ProductPanel extends JPanel{
	Product prod;
	
	/**<h1>constructor</h1>
	 * Sets the card layout,cards,product<br>
	 * Adds a mouse listener the on-click shows the productpage of the product!
	 * @author */
	public ProductPanel(Product prod, CardLayout cardLayout, JPanel cards){
		this.prod = prod;
		
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		

		//con.weighty = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel(prod.getName()),con);
		con.gridx = 1;
		this.add(new JLabel("$"+String.valueOf(prod.getSellPrice())),con);
		con.gridx = 0;
		con.gridy = 1;
		con.gridwidth = GridBagConstraints.HORIZONTAL;
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
