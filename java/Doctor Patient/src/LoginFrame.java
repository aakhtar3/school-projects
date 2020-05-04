import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
public class LoginFrame extends JFrame {
	private JLabel greetLabel1 = new JLabel("Please select account type and type in your username and password");
	private JButton logButton = new JButton("Log in");
	private JRadioButton docButton = new JRadioButton("Docotor");
	private JRadioButton patButton = new JRadioButton("Patient");
	private JButton newButton = new JButton("New user");
	private JButton canButton = new JButton("Cancel");
	private ButtonGroup buttonGroup;
	private JLabel userLabel = new JLabel("Username:");
	private JTextField userField = new JTextField(15);
	private JLabel passLabel = new JLabel("Password:");
	private JPasswordField passField = new JPasswordField(15);
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel greetPanel;
	private JPanel radioPanel;
	private JPanel typePanel;
	private JPanel buttonPanel;
	private JPanel botPanel;
	

	public LoginFrame(){
		setSize(450,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLayout(new BorderLayout(5, 5));
		
		greetPanel = new JPanel();
		greetPanel.setLayout(new GridLayout(1, 1));
		greetPanel.add(greetLabel1);
		
		radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(1, 3));
		radioPanel.add(docButton);
		radioPanel.add(patButton);
		radioPanel.add(newButton);
		

		buttonGroup = new ButtonGroup();
		buttonGroup.add(docButton);
		buttonGroup.add(patButton);
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));
		topPanel.add(greetPanel);
		topPanel.add(radioPanel);
		
		typePanel = new JPanel();
		typePanel.setLayout(new GridLayout(2, 2));
		typePanel.add(userLabel);
		typePanel.add(userField);
		typePanel.add(passLabel);
		typePanel.add(passField);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(logButton);
		buttonPanel.add(canButton);
		
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(2, 1));
		botPanel.add(typePanel);
		botPanel.add(buttonPanel);
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		mainPanel.add(topPanel);
		mainPanel.add(botPanel);
		
		add(mainPanel, BorderLayout.CENTER);
		
		LoginButtonListener loginButtonListener = new LoginButtonListener();
		logButton.addActionListener(loginButtonListener);
		
		ClearButtonListener clearButtonListener = new ClearButtonListener();
		canButton.addActionListener(clearButtonListener);
		
		NewButtonListener newButtonListener = new NewButtonListener();
		newButton.addActionListener(newButtonListener);
	}
	
	private class LoginButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(docButton.isSelected()) {
				LoginDB doctor = new LoginDB();
				Login current_user = new Login( userField.getText(),passField.getText());
				
				boolean found = false;
				int id =0;
				
				ArrayList<Login> doc_list =  doctor.getDoctors();
				//for loop to find the right user
				for(int i= 0; i<doc_list.size()&& !found; i++){
					if(current_user.getUsername().equals(doc_list.get(i).getUsername())&&current_user.getPassword().equals(doc_list.get(i).getPassword())){
					found = true;
					id = doc_list.get(i).getId();
					}	
				}

				if(found){
					setVisible(false);
					DoctorFrame frame1 = new DoctorFrame(id);
					frame1.setVisible(true);
				} else if(!found)
					JOptionPane.showMessageDialog(LoginFrame.this, "Username and password mismatch", "Error", JOptionPane.ERROR_MESSAGE);
			} else if(patButton.isSelected()) {
				LoginDB patient = new LoginDB();
				Login current_user = new Login( userField.getText(),passField.getText());
				
				boolean found = false;
				int id =0;
				ArrayList<Login> pat_list =  patient.getPatients();
				
				for(int i= 0; i<pat_list.size()&& !found; i++){
					if(current_user.getUsername().equals(pat_list.get(i).getUsername())&&current_user.getPassword().equals(pat_list.get(i).getPassword())){
					found = true;
					id = pat_list.get(i).getId();
					}	
				}
				
				if(found){
				setVisible(false);
				CustomerFrame frame2 = new CustomerFrame(id);
				frame2.setVisible(true);
				} else if(!found)
					JOptionPane.showMessageDialog(LoginFrame.this, "Username and password mismatch", "Error", JOptionPane.ERROR_MESSAGE);
				
			} else {
				JOptionPane.showMessageDialog(LoginFrame.this, "Please select user type", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	private class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			userField.setText("");
			passField.setText("");
			if(docButton.isEnabled()||patButton.isEnabled()){
				docButton.setSelected(false);
				patButton.setSelected(false);
			}
			
		}
	}
	private class NewButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(docButton.isSelected()){
				setVisible(false);
				newDocFrame frame3 = new newDocFrame();
				frame3.setVisible(true);
			} else if(patButton.isSelected()){
				setVisible(false);
				newCustFrame frame4 = new newCustFrame();
				frame4.setVisible(true);
			} else{
				JOptionPane.showMessageDialog(LoginFrame.this, "Please select user type", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
