package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import cop4331.data.*;
import cop4331.singles.*;

@SuppressWarnings("serial")
public class UpdateProductPage extends Page {
	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	
	public UpdateProductPage(CardLayout cardLayout, JPanel cards){
		super(cardLayout, cards);
	}
	
	
	/**<h1>display</h1>
	 * Displays the actual elements in the page
	 * @author */
	@Override
	public void display(){
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		//Create an array of text fields
		for (int i = 0; i < 4; i++){
			fields.add(new TextField(10));
		}
		if(Session.getInstance().getUser() != null){
			fields.get(0).setText(Session.getInstance().getProduct().getName());
			fields.get(1).setText(Session.getInstance().getProduct().getDescription());
			fields.get(2).setText(String.valueOf(Session.getInstance().getProduct().getCost()));
			fields.get(3).setText(String.valueOf(Session.getInstance().getProduct().getSellPrice()));
		}
		
		//Add nav-bar
		con.gridx = 0;
		con.gridy = 0;
		this.add(new SellerNavPanel(super.getCardLayout(), super.getCards()));
		
		//Generate the form with pre-filled fields
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Name"), con);
		con.gridx = 1;
		this.add(fields.get(0), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Description (No ',' of new lines)"), con);
		con.gridx = 1;
		this.add(fields.get(1), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Cost"), con);
		con.gridx = 1;
		this.add(fields.get(2), con);
		con.gridy++;
		con.gridx = 0;
		this.add(new JLabel("Sell Price"), con);
		con.gridx = 1;
		this.add(fields.get(3), con);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy++;
		con.gridy++;
		this.add(createUpdateButton(),con);
		con.gridx = 1;
		this.add(createDeleteButton(),con);
		this.updateUI();
	}
	
	/**<h1>reset</h1>
	 * Removes all elements
	 * @author */
	@Override
	public void reset(){
		this.fieldText.clear();
		this.fields.clear();
		this.removeAll();
	}
	
	public JButton createUpdateButton(){
		JButton update = new JButton("Update");
		
		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Product prod = Session.getInstance().getProduct();
				boolean valid = true;
				String error = "";
				getText();
				
				for(String s : fieldText){
					if(s.equals("")){
						valid = false;
						error = "empty";
					}
				}
				
				if(valid){
					try{
						prod.setName(fieldText.get(0));
						prod.setDescription(fieldText.get(1));
						prod.setCost(Double.valueOf(fieldText.get(2)));
						prod.setSellPrice(Double.valueOf(fieldText.get(3)));
						DatabaseInterface.getInstance().updateProduct(prod);
						showPanel("Inventory");
					}
					catch (NumberFormatException ex){
						valid = false;
						error = "double";
					}
				}
				
				if(!valid){
					if(error.equals("empty")) generatePopUp("All feilds required");
					else if(error.equals("double")) generatePopUp("Please enter a numacical value.");
				}
				
			}
			
		});
		
		return update;
	}
	
	public JButton createDeleteButton(){
		JButton delete = new JButton("Delete");
		
		delete.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DatabaseInterface.getInstance().deleteProduct(Session.getInstance().getProduct());
				showPanel("Inventory");
			}
			
		});
		
		return delete;
	}
	
	/**<h1>getText</h1>
	 * Get the text from all the TextFeilds
	 * @author */
	private void getText() {
		fieldText.clear();
		for(TextField tf : fields) {
			fieldText.add(tf.getText());
		}
	}
}
