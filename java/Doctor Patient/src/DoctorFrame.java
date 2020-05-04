import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ListIterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class DoctorFrame extends JFrame {
	private BorderLayout borderLayout;
	
	private JPanel topPanel;
	private JPanel currentPanel;
	private JLabel patLabel  = new JLabel ("Patients: ");
	private JComboBox custIDComboBox;
	private JLabel name1Label  = new JLabel ("Name: ");
	private JLabel name3Label  = new JLabel (" ");
	private JLabel add1Label  = new JLabel ("Address: ");
	private JLabel add3Label  = new JLabel (" ");
	private JLabel phone1Label = new JLabel ("Phone: ");
	private JLabel phone3Label  = new JLabel (" ");
	
	private JPanel diagPanel1;
	private JPanel diagPanel;
	private JTextField diagField = new JTextField(25);
	private JButton add1Button= new JButton("Add");;
	private JButton clear1Button= new JButton("Clear");
	
	private JPanel presPanel1;
	private JPanel presPanel;
	private JTextField presField = new JTextField(25);
	private JButton add2Button= new JButton("Add");;
	private JButton clear2Button= new JButton("Clear");
	
	private JPanel botPanel;
	private JPanel diagnoisPanel;
	private JLabel diagLabel = new JLabel ("");
	private JButton remove1Button = new JButton ("Remove");
	
	private JPanel prescriptionPanel;
	private JLabel prescLabel = new JLabel ("");
	private JButton remove2Button = new JButton ("Remove");
	private Doctor doctor;
	private Customer customer;
	private int id;
	
	public DoctorFrame(int id){
		super("Doctor");
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(600, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		this.id=id;
		System.out.println(id);
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3));
		topPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		
		currentPanel = new JPanel();
		currentPanel.setLayout(new GridLayout(4, 2));
		currentPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Info"));
		currentPanel.add(patLabel);
		custIDComboBox = getCustID(id);
		currentPanel.add(custIDComboBox);
		currentPanel.add(name1Label);
		currentPanel.add(name3Label);
		currentPanel.add(add1Label);
		currentPanel.add(add3Label);
		currentPanel.add(phone1Label);
		currentPanel.add(phone3Label);
		
		diagPanel1 = new JPanel();
		diagPanel1.add(add1Button);
		diagPanel1.add(clear1Button);
		
		diagPanel = new JPanel();
		diagPanel.setLayout(new GridLayout(2, 1));
		diagPanel.setBorder(new TitledBorder(new EtchedBorder(), "Add Diagnosis"));
		diagPanel.add(diagField);

		diagPanel.add(diagPanel1);
		
		presPanel1 = new JPanel();
		presPanel1.add(add2Button);
		presPanel1.add(clear2Button);
		
		presPanel = new JPanel();
		presPanel.setLayout(new GridLayout(2, 2));
		presPanel.setBorder(new TitledBorder(new EtchedBorder(), "Add Prescription"));
		presPanel.add(presField);
		presPanel.add(presPanel1);
		
		topPanel.add(currentPanel);
		topPanel.add(diagPanel);
		topPanel.add(presPanel);
		
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(1, 2));
		botPanel.setBorder(new TitledBorder(new EtchedBorder(),""));
		
		diagnoisPanel = new JPanel();
		diagnoisPanel.setLayout(new GridLayout(2, 1));
		diagnoisPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Diagnosis"));
		diagnoisPanel.add(diagLabel);
		diagnoisPanel.add(remove1Button); 
		
		prescriptionPanel = new JPanel();
		prescriptionPanel.setLayout(new GridLayout(2, 1));
		prescriptionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Prescription"));
		prescriptionPanel.add(prescLabel);
		prescriptionPanel.add(remove2Button); 
		
		botPanel.add(diagnoisPanel);
		botPanel.add(prescriptionPanel);
		

		add(topPanel, BorderLayout.NORTH);
		add(botPanel, BorderLayout.CENTER);
		
		AddButtonListener addButtonListener = new AddButtonListener();
		add1Button.addActionListener(addButtonListener);
		add2Button.addActionListener(addButtonListener);
		
		ClearButtonListener clearButtonListener = new ClearButtonListener();
		clear1Button.addActionListener(clearButtonListener);
		clear2Button.addActionListener(clearButtonListener);
		
		RemoveButtonListener removeButtonListener = new RemoveButtonListener();
		remove1Button.addActionListener(removeButtonListener);
		remove2Button.addActionListener(removeButtonListener);
		
		CustomerIDItemListener customerIDItemListener = new CustomerIDItemListener();
		custIDComboBox.addItemListener(customerIDItemListener);
	}
	private class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == clear1Button){
				diagField.setText("");
			}
			else if (e.getSource() == clear2Button){
				presField.setText("");
			}
		}
	}
	private class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (!custIDComboBox.getSelectedItem().toString().equals(0)){
				CustomerDB customers = new CustomerDB();
				customer = customers.getCustomers1(id).get(custIDComboBox.getSelectedIndex() - 1);
				
					if(e.getSource() == add1Button){
						customer.setDiagnoses(diagField.getText());
						customers.updateDiagnosis(customer);
						diagLabel.setText(diagField.getText());
					}
					else if (e.getSource() == add2Button){
						customer.setPrescription(presField.getText());
						customers.updatePrescription(customer);
						prescLabel.setText(presField.getText());
					}
			}
		}
	}
	private class RemoveButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (!custIDComboBox.getSelectedItem().toString().equals(0)){
				CustomerDB customers = new CustomerDB();
				customer = customers.getCustomers1(id).get(custIDComboBox.getSelectedIndex() - 1);
			
					if(e.getSource() == remove1Button){
						customer.setDiagnoses("");
						customers.deleteDiagnosis(customer);
						diagLabel.setText("");
					}
					else if (e.getSource() == remove2Button){
						customer.setPrescription("");
						customers.deletePrescription(customer);
						prescLabel.setText("");
					}
			}
		}
	}
	private JComboBox getCustID(int id){
		CustomerDB customers = new CustomerDB();
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(0);
		for (int i = 0; customers.getCustomers1(id) != null && i < customers.getCustomers1(id).size(); i++){
			if (customers.getCustomers1(id).get(i) != null)
				comboBox.addItem(customers.getCustomers1(id).get(i).getName());
		}
		return comboBox;
		
	}
	private void updateCustID(CustomerDB customers){
		DefaultComboBoxModel model = new DefaultComboBoxModel();
	    ListIterator<Customer> iter = customers.getCustomers1(id).listIterator();  
	    model.addElement(0);
	    while (iter.hasNext()) {  
	      model.addElement(((Customer)iter.next()).getName());  
	    }  
	    custIDComboBox.setModel(model);
	}
	private class CustomerIDItemListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if (!custIDComboBox.getSelectedItem().toString().equals(0)){
				CustomerDB customers = new CustomerDB();
				customer = customers.getCustomers1(id).get(custIDComboBox.getSelectedIndex() - 1);
				
				name3Label.setText(customer.getName());
				add3Label.setText(customer.getAddress());
				phone3Label.setText(customer.getPhone());
				diagLabel.setText(customer.getDiagnoses());
				prescLabel.setText(customer.getPrescription());
				
			}
		}
	}
}
