package cop4331;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class SalesPage extends Page {

	public SalesPage(CardLayout cardLayout, JPanel cards) {
		super(cardLayout, cards);
	}
	
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
