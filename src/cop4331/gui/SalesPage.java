package cop4331.gui;

import java.awt.*;

import javax.swing.*;

import cop4331.singles.Session;

@SuppressWarnings("serial")
/**<h1>SalesPage</h1>
 * Displays the informaton to the sellers sales
 * @author */
public class SalesPage extends Page {
	
	/**<h1>constructor</h1>
	 * Uses the constructor from Page
	 * @author */
	public SalesPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
	/**<h1>display</h1>
	 * Displays the actual elements in the page
	 * @author */
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 0;
		this.add(new SellerNavPanel(super.getCardLayout(), super.getCards()),con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Costs"), con);
		con.gridx = 1;
		this.add(new JLabel("$"+String.valueOf(Session.getInstance().getOrder().getCost())), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Sales"), con);
		con.gridx = 1;
		this.add(new JLabel("$"+String.valueOf(Session.getInstance().getOrder().getTotal())), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Revenue"), con);
		con.gridx = 1;
		this.add(new JLabel("$"+String.valueOf(Session.getInstance().getOrder().getRevenue())), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("# Products Sold"), con);
		con.gridx = 1;
		this.add(new JLabel(String.valueOf(Session.getInstance().getOrder().getNumProd())), con);
		
		this.updateUI();
	}

}
