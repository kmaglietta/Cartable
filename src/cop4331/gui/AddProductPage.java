package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
public class AddProductPage extends Panel {
	private CardLayout cardLayout;
	private JPanel cards;
	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	
	public AddProductPage(CardLayout cardLayout, JPanel cards){
		this.cardLayout = cardLayout;
		this.cards = cards;
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		//Create an array of text fields
		for (int i = 0; i < 4; i++){
			fields.add(new TextField(10));
		}
		
		//Add nav-bar
		con.gridx = 0;
		con.gridy = 0;
		this.add(new SellerNavPanel(this.cardLayout, this.cards));
		
		//Generate the form
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
		con.gridwidth = 2;
		con.gridx = 0;
		con.gridy++;
		con.gridy++;
		this.add(createAddButton(),con);
	}
	
	/**<h1>createCheckoutButton</h1>
	 * @return JButton for checking out
	 * @author */
	private Component createAddButton() {
		JButton add = new JButton("Add");
		add.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
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
						DatabaseInterface.getInstance().addProduct(
								Session.getInstance().getUid(), 
								fieldText.get(0), 
								fieldText.get(1), 
								Double.valueOf(fieldText.get(3)), 
								Double.valueOf(fieldText.get(2)));
						clearFields();
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
		
		return add;
	}
	
	/**<h1>showPanel</h1>
	 * Makes switching Pages simpler by using a stirring to determine the required page
	 * */
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	/**<h1>generatePopUp</h1>
	 * Generates a message dialog using the passed message
	 * @author */
	private void generatePopUp(String mes) {
		JOptionPane.showMessageDialog(this, mes);
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
	
	private void clearFields(){
		for(TextField f : fields){
			f.setText("");
		}
	}
}
