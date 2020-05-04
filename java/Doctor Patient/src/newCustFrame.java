import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class newCustFrame extends JFrame {
	private JPanel top;
	private JPanel bot;
	
	private JLabel userLabel = new JLabel("UserName: ");
	private JTextField userText = new JTextField(15);
	private JLabel passLabel = new JLabel("Password: ");
	private JTextField passText = new JTextField(15);
	
	private JLabel nameLabel = new JLabel("Name: ");
	private JTextField nameField = new JTextField(10);
	private JLabel addLabel = new JLabel("Adress: ");
	private JTextField addField = new JTextField(10);
	private JLabel phoneLabel = new JLabel("Phone: ");
	private JTextField phoneField = new JTextField(10);
	private JLabel docLabel = new JLabel("Doctor: ");
	private JComboBox docIDComboBox;
	private JButton addButton= new JButton("Add");
	private JButton logButton= new JButton ("Login Page");
	
	private Doctor doctor;
	private Customer customer;
	
	public newCustFrame(){
		super("New Patient");
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		top = new JPanel();
		top.setLayout(new GridLayout(6, 2));
		top.add(userLabel);
		top.add(userText);
		top.add(passLabel);
		top.add(passText);
		top.add(nameLabel);
		top.add(nameField);
		top.add(addLabel);
		top.add(addField);
		top.add(phoneLabel);
		top.add(phoneField);
		top.add(docLabel);
		docIDComboBox = getDoctID();
		top.add(docIDComboBox);
		docIDComboBox.setEnabled(false);
		
		bot = new JPanel();
		bot.setLayout(new GridLayout(2, 1));
		bot.add(addButton);
		bot.add(logButton);
		
		
		add(top, BorderLayout.CENTER);
		add(bot, BorderLayout.SOUTH);
		
		AddButtonListener addButtonListener = new AddButtonListener();
		addButton.addActionListener(addButtonListener);
		
		LogButtonListener logButtonListener = new LogButtonListener();
		logButton.addActionListener(logButtonListener);
	}
	private JComboBox getDoctID(){
		DoctorDB doctors = new DoctorDB();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(0);
		for (int i = 0; doctors.getDoctors() != null && i < doctors.getDoctors().size(); i++){
			if (doctors.getDoctors().get(i) != null)
				comboBox.addItem(doctors.getDoctors().get(i).getUsername());
		}
		return comboBox;
	}
	private class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (!docIDComboBox.isEnabled()){
				if (userText.getText().trim().length() > 0 && passText.getText().trim().length() > 0 &&
						nameField.getText().trim().length() > 0 &&
						addField.getText().trim().length() > 0 &&
						phoneField.getText().trim().length() > 0 ){
					
						customer = new Customer(userText.getText().trim(), passText.getText().trim(), 
							nameField.getText().trim(), addField.getText().trim(), phoneField.getText().trim());
						
					JOptionPane.showMessageDialog(newCustFrame.this, "New Patient correctly added. Now select Your doctor", "Error", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(newCustFrame.this, "Please type in correct info", "Error", JOptionPane.ERROR_MESSAGE);
				}
				docIDComboBox.setEnabled(true);
				addButton.setText("Add Doctor");

			} else if (docIDComboBox.isEnabled()) {
				if (!(docIDComboBox.getSelectedItem().toString()).equals(0)) {
					
					int docId =0;
					DoctorDB doctors = new DoctorDB();
					doctor = doctors.getDoctors().get(docIDComboBox.getSelectedIndex() -1);
					docId=doctor.getId();
					
					CustomerDB customers = new CustomerDB();
					customers.addCustomer1(customer,docId);
					
					JOptionPane.showMessageDialog(newCustFrame.this, "You have added a Doctor", "Error", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(newCustFrame.this, "Please type in correct info", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	private class LogButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			LoginFrame frame = new LoginFrame();
			frame.setVisible(true);
		}
	}
	
}
