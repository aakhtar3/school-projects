import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CustomerFrame extends JFrame {
	private BorderLayout borderLayout;
	private int id;
	private JPanel topPanel;
	private JPanel currentPanel;
	private JLabel name1Label  = new JLabel ("Name: ");
	private JLabel name3Label  = new JLabel (" ");
	private JLabel add1Label  = new JLabel ("Address: ");
	private JLabel add3Label  = new JLabel (" ");
	private JLabel phone1Label = new JLabel ("Phone: ");
	private JLabel phone3Label  = new JLabel (" ");
	
	private JPanel newPanel;
	private JLabel name2Label = new JLabel("Name: ");
	private JTextField name2Field = new JTextField(10);
	private JLabel add2Label = new JLabel("Adress: ");
	private JTextField add2Field = new JTextField(10);
	private JLabel phone2Label = new JLabel("Phone: ");
	private JTextField phone2Field = new JTextField(10);
	
	private JButton updateButton= new JButton("Update");;
	private JButton clearButton= new JButton("Clear");
	
	private JPanel botPanel;
	private JPanel diagnoisPanel;
	private JLabel diagLabel = new JLabel ("");
	
	private JPanel prescriptionPanel;
	private JLabel prescLabel = new JLabel ("");
	private Customer customer;
	
	public CustomerFrame(int id){
		super("Customer");
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(470, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		this.id=id;
		
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2));
		topPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		
		currentPanel = new JPanel();
		currentPanel.setLayout(new GridLayout(3, 1));
		currentPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Info"));
		currentPanel.add(name1Label);
		currentPanel.add(name3Label);
		currentPanel.add(add1Label);
		currentPanel.add(add3Label);
		currentPanel.add(phone1Label);
		currentPanel.add(phone3Label);
		
		newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(4, 2));
		newPanel.setBorder(new TitledBorder(new EtchedBorder(), "New Info"));
		newPanel.add(name2Label);
		newPanel.add(name2Field);
		newPanel.add(add2Label);
		newPanel.add(add2Field);
		newPanel.add(phone2Label);
		newPanel.add(phone2Field);
		newPanel.add(updateButton);
		newPanel.add(clearButton);
		
		topPanel.add(currentPanel);
		topPanel.add(newPanel);
		
		botPanel = new JPanel();
		botPanel.setLayout(new GridLayout(1, 2));
		botPanel.setBorder(new TitledBorder(new EtchedBorder(),""));
		
		diagnoisPanel = new JPanel();
		diagnoisPanel.setLayout(new GridLayout(1, 1));
		diagnoisPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Diagnosis"));
		diagnoisPanel.add(diagLabel);
		
		prescriptionPanel = new JPanel();
		prescriptionPanel.setLayout(new GridLayout(1, 1));
		prescriptionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Current Prescription"));
		prescriptionPanel.add(prescLabel);

		botPanel.add(diagnoisPanel);
		botPanel.add(prescriptionPanel);
		
		
		add(topPanel, BorderLayout.NORTH);
		add(botPanel, BorderLayout.CENTER);
		
		CustomerDB customers = new CustomerDB();
		customers.getCustomer(id);
		customer =customers.getCustomer(id);
		name3Label.setText(customer.getName());
		add3Label.setText(customer.getAddress());
		phone3Label.setText(customer.getPhone());
		diagLabel.setText(customer.getDiagnoses());
		prescLabel.setText(customer.getPrescription());
		
		
		ClearButtonListener clearButtonListener = new ClearButtonListener();
		clearButton.addActionListener(clearButtonListener);
		
		AddButtonListener addButtonListener = new AddButtonListener();
		updateButton.addActionListener(addButtonListener);
	}

	private class ClearButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			name2Field.setText("");
			add2Field.setText("");
			phone2Field.setText("");
		}
	}

	private class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			customer.setName(name2Field.getText().toString());
			customer.setAddress(add2Field.getText().toString());
			customer.setPhone(phone2Field.getText().toString());
			
			CustomerDB customers = new CustomerDB();
			customers.updateCustomer(customer);
			
			name3Label.setText(name2Field.getText());
			add3Label.setText(add2Field.getText());
			phone3Label.setText(phone2Field.getText());		
		}		
	}
}
