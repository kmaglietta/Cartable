package cop4331;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class SignInPage extends JPanel{
	private CardLayout cardLayout;
	private JPanel cards;
	private ArrayList<String> fieldText = new ArrayList<String>();
	private ArrayList<TextField> fields = new ArrayList<TextField>();
	
	public SignInPage(CardLayout cardLayout, JPanel cards) {
		
		this.cardLayout = cardLayout;
		this.cards = cards;
		
		GridBagConstraints con = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		for (int i = 0; i < 2; i++){
			fields.add(new TextField(10));
		}
		
		//con.fill = GridBagConstraints.VERTICAL;
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
	
	
	
	//pass string of panle name
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
						System.out.println(s);
						valid = false;
						error = "empty";
					}
				}
				
				if(valid) {
					user = DatabaseInterface.getInstance().attemptLogin(fieldText.get(0),fieldText.get(1));
					if(user != null){
						Session.getInstance().setUser(user);
					}
					else{
						valid = false;
						error ="fail";
					}
				}
				if(!valid){
					if(error.equals("empty")) generatePopUp("All feilds required!");
					else if(error.equals("fail")) generatePopUp("Username or password are incorect");
				}
				else {
					if(user.isSeller()){
						Session.getInstance().setOrder(DatabaseInterface.getInstance().getSales(user.getId()));
						showPanel("Inventory");
					}
					else {
						showPanel("Available");
					}
				}
			}
			
		});
		
		return submit;
	}
	
	private void showPanel(String name) {
		cardLayout.show(cards, name);
	}
	
	private void generatePopUp(String mes) {
		JOptionPane.showMessageDialog(this, mes);
	}
	
	private void getText() {
		fieldText.clear();
		for(TextField tf : fields) {
			fieldText.add(tf.getText());
		}
	}
}