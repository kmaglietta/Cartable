package cop4331.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import cop4331.data.User;
import cop4331.singles.DatabaseInterface;
import cop4331.singles.Session;

@SuppressWarnings("serial")
/**<h1>SignInPage</h1>
 * Contains the sign in form
 * @author */
public class SignInPage extends JPanel{
	
	private CardLayout cardLayout;
	private JPanel cards;
	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	
	/**<h1>Constructor</h1>
	 * Generates the page view with all required fields and buttons
	 * */
	public SignInPage(CardLayout cardLayout, JPanel cards) {
		
		this.cardLayout = cardLayout;
		this.cards = cards;
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		//Create an array of text fields
		for (int i = 0; i < 2; i++){
			fields.add(new TextField(10));
		}
		
		//Generate the form
		con.gridx = 0;
		con.gridy = 0;
		this.add(new JLabel("Username"), con);
		con.gridx = 1;
		con.gridy = 0;
		this.add(fields.get(0), con);
		con.gridx = 0;
		con.gridy = 1;
		this.add(new JLabel("Password"), con);
		con.gridx = 1;
		con.gridy = 1;
		this.add(fields.get(1), con);
		con.gridx = 0;
		con.gridy = 2;
		this.add(createShowButton("SignUp"), con);
		con.gridx = 1;
		con.gridy = 2;
		this.add(createSubmitButton(), con);
		
		this.setVisible(true);
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
						System.out.println(s);
						valid = false;
						error = "empty";
					}
				}
				
				//If all are filled attempt login
				if(valid) {
					//Attempt to get the user with matching credentials
					user = DatabaseInterface.getInstance().attemptLogin(fieldText.get(0),fieldText.get(1));
					//i guess you guessed the right password or looked in db/users.txt
					if(user != null){
						Session.getInstance().setUser(user);
					}
					//WOPS! That user or password don't match
					else{
						valid = false;
						error ="fail";
					}
				}
				//Uh-oh there were erros
				if(!valid){
					//Say what exactly happened
					if(error.equals("empty")) generatePopUp("All feilds required!");
					else if(error.equals("fail")) generatePopUp("Username or password are incorect");
				}
				else {
					//Where we gonna go?
					//Seller?
					if(user.isSeller()){
						//Get your sales and proceed to your inventory
						Session.getInstance().setOrder(DatabaseInterface.getInstance().getSales(user.getId()));
						showPanel("Inventory");
					}
					//Customer?
					else {
						//Spend some hard earned non-existent money
						showPanel("Available");
					}

					for(TextField tf : fields){
						tf.setText("");
					}
				}
			}
			
		});
		
		return submit;
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
}