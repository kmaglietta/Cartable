package cop4331;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignUpPage extends JPanel{

	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	private JCheckBox sellerFlag = new JCheckBox();
	private CardLayout cardLayout;
	private JPanel cards;
	
	public SignUpPage(CardLayout cardLayout, JPanel cards) {
		this.cardLayout = cardLayout;
		this.cards = cards;
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		for (int i = 0; i < 3; i++){
			fields.add(new TextField(10));
		}
		
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
	
	public void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	//pass string of panel name
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
	
	private JButton createSubmitButton(){
		JButton submit = new JButton("Submit");
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean valid = true;
				String error = "";
				User user = null;
				getText();
				
				for(String s : fieldText){
					if(s.equals("")) {
						valid = false;
						error = "empty";
					}
				}
				
				if(fieldText.get(1).equals(fieldText.get(2))) {
					user = DatabaseInterface.getInstance().addUser(fieldText.get(0), fieldText.get(1), booltoInt(sellerFlag.isSelected()));
					if (user == null){
						valid = false;
						error = "name";
					}
				}else{
					valid = false;
					error ="match";
				}
				
				if(!valid){
					if(error.equals("empty")) generatePopUp("All feilds required!");
					else if(error.equals("match")) generatePopUp("Passwords do not match");
					else if(error.equals("name")) generatePopUp("Username exists");
				}
				else {
					Session.getInstance().setUser(user);
					if(sellerFlag.isSelected()) showPanel("Invnetory");
					else showPanel("Available");
				}
			}
			
		});
		
		return submit;
	}
	
	public void generatePopUp(String mes) {
		JOptionPane.showMessageDialog(this, mes);
	}
	
	public int booltoInt(Boolean bool){
		if(bool) return 1;
		return 0;
	}
	
	private void getText() {
		fieldText.clear();
		for(TextField tf : fields) {
			fieldText.add(tf.getText());
		}
	}
}
