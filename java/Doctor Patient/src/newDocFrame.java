import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class newDocFrame extends JFrame {
	private JLabel userLabel = new JLabel("UserName: ");
	private JTextField userText = new JTextField(15);
	private JLabel passLabel = new JLabel("Password: ");
	private JTextField passText = new JTextField(15);
	private JButton addButton = new JButton("Add");
	private JButton logButton= new JButton ("Login Page");
	private JPanel top;
	private JPanel bot;
	
	private Doctor doctor;
	
	public newDocFrame(){
		super("New Doctor");
		setLayout(new FlowLayout());
		setResizable(false);
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(5, 5));
		
		top = new JPanel();
		top.setLayout(new GridLayout(2, 2));
		top.add(userLabel);
		top.add(userText);
		top.add(passLabel);
		top.add(passText);
		
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
	private class AddButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (userText.getText().trim().length() > 0 && passText.getText().trim().length() > 0){
					doctor = new Doctor(userText.getText().trim(), passText.getText().trim());
					JOptionPane.showMessageDialog(newDocFrame.this, "New Doctor correctly added", "Error", JOptionPane.INFORMATION_MESSAGE);
			} else{
				JOptionPane.showMessageDialog(newDocFrame.this, "Please type in correct info", "Error", JOptionPane.ERROR_MESSAGE);
			}				
			DoctorDB doctors = new DoctorDB();
			doctors.addDoctor(doctor);
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

