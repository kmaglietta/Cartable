package cop4331;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
/**<h1>SignUpPage</h1>
 * Contains the sign up form
 * @author */
public class SignUpPage extends JPanel{

	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	private JCheckBox sellerFlag = new JCheckBox();
	private CardLayout cardLayout;
	private JPanel cards;
	
	/**<h1>Constructor</h1>
	 * Generates the page view with all required fields and buttons
	 * */
	public SignUpPage(CardLayout cardLayout, JPanel cards) {
		this.cardLayout = cardLayout;
		this.cards = cards;
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		//Create an array of text fields
		for (int i = 0; i < 3; i++){
			fields.add(new TextField(10));
		}
		//Generate the form
		
		//Username
		con.gridx = 0;
		con.gridy = 1;
		this.add(new JLabel("Username"), con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(fields.get(0), con);
		
		//Password
		con.gridx = 0;
		con.gridy = 2;
		this.add(new JLabel("Password"), con);
		con.gridx = 1;
		con.gridy = 2;
		this.add(fields.get(1), con);
		
		//Password
		con.gridx = 0;
		con.gridy = 3;
		this.add(new JLabel("Confirm Password"), con);
		con.gridx = 1;
		con.gridy = 3;
		this.add(fields.get(2), con);
		
		//Password
		con.gridx = 0;
		con.gridy = 4;
		this.add(new JLabel("Seller"), con);
		con.gridx = 1;
		con.gridy = 4;
		this.add(sellerFlag, con);
		
		//Buttons
		con.gridx = 0;
		con.gridy = 5;
		this.add(createShowButton("SignIn"), con);
		con.gridx = 1;
		con.gridy = 5;
		this.add(createSubmitButton(), con);
	}
	
	/**<h1>showPanel</h1>
	 * Makes switching Pages simpler by using a stirring to determine the required page
	 * */
	public void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	/**<h1>createShowButton</h1>
	 * Creates a button that when clicked shows the panel matching the name passes
	 * @return JButton that on-click shows the page matching the passed string
	 * @author */
	private JButton createShowButton(String name) {

		JButton button = new JButton(name);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showPanel(name);
			}
			
		});
		
		return button;
	}
	
	/**<h1>createShowButton</h1>
	 * Creates a button that when clicked attempts to submit the form.
	 * A success will result in the user being sent to their respective pages </br>
	 * 		Customer -> AvailableProductsPage</br>
	 * 		Seller -> InventoryPage
	 * @return JButton that on-click attempts to submit the form
	 * @author */
	private JButton createSubmitButton(){
		JButton submit = new JButton("Submit");
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String error = "";
				User user = null;
				getText();
				
				//See if all fields are filled
				for(String s : fieldText){
					if(s.equals("")) {
						valid = false;
						error = "empty";
					}
				}
				
				//See if both passwords match
				if(valid && fieldText.get(1).equals(fieldText.get(2))) {
					//Attempt to add user to the database
					user = DatabaseInterface.getInstance().addUser(fieldText.get(0), fieldText.get(1), booltoInt(sellerFlag.isSelected()));
					//An unsuccessful add means that the user name exists
					if (user == null){
						valid = false;
						error = "name";
					}
				}else{
					//Both passwords did not match
					valid = false;
					error ="match";
				}
				
				//If there was an error see what it was
				if(!valid){
					
					//See what error occurred and generate a pop up alert the user to the error
					if(error.equals("empty")) generatePopUp("All feilds required!");
					else if(error.equals("match")) generatePopUp("Passwords do not match");
					else if(error.equals("name")) generatePopUp("Username exists");
				}
				
				//GO FOR LAUNCH!
				else {
					//Set the user in session
					Session.getInstance().setUser(user);
					//Does the user wish to be a seller?
					if(sellerFlag.isSelected()) {
						//Set the sales of the seller (will be empty)
						Session.getInstance().setOrder(DatabaseInterface.getInstance().getSales(user.getId()));
						//Direct the user to their new inventory!
						showPanel("Invnetory");
					}
					//Show the lame customer to the products they can buy
					else showPanel("Available");
				}
			}
			
		});
		
		//Wow! Alot happens in one click
		
		//Return the new button
		return submit;
	}
	
	/**<h1>generatePopUp</h1>
	 * Generates a message dialog using the passed message
	 * @author */
	public void generatePopUp(String mes) {
		JOptionPane.showMessageDialog(this, mes);
	}
	
	/**<h1>booltoInt</h1>
	 * How is this not a thing??</br>
	 * Integer.valueOf(boolean);</br>
	 * Returns the integer value of a boolean value</br>
	 * false -> 0, true -> 1
	 * @author */
	public int booltoInt(Boolean bool){
		if(bool) return 1;
		return 0;
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
