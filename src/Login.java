import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// database connection variable
	Connection con=null;
	/**
	 * Create the frame.
	 */
	public Login() {
		setName("loginFrame");
		
		con = DbConnection.dbConnection();
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 605);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(72, 61, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Poppins SemiBold", Font.PLAIN, 24));
		lblNewLabel.setBounds(369, 56, 75, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(311, 154, 86, 30);
		contentPane.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setBorder(null);
		usernameField.setFont(new Font("Poppins", Font.PLAIN, 11));
		usernameField.setBackground(new Color(248, 248, 255));
		usernameField.setBounds(311, 182, 205, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM user WHERE username=? AND password=?";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, usernameField.getText());
					ps.setString(2, passwordField.getText());
					
					ResultSet res = ps.executeQuery();
					
					if(res.next()) {
						JOptionPane.showMessageDialog(btnLogin, "Login successful");
						setVisible(false);
						Dashboard dashboard = new Dashboard();
						dashboard.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(btnLogin, "Invalid username or password");
					}
					ps.close();
					con.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnLogin.setBorder(null);
		btnLogin.setForeground(new Color(248, 248, 255));
		btnLogin.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnLogin.setBackground(new Color(65, 105, 225));
		btnLogin.setBounds(311, 358, 205, 35);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(311, 224, 86, 30);
		contentPane.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Poppins", Font.PLAIN, 11));
		passwordField.setBounds(311, 252, 205, 30);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show password");
		chckbxNewCheckBox.setIconTextGap(6);
		chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBorder(null);
		chckbxNewCheckBox.setBackground(new Color(72, 61, 139));
		chckbxNewCheckBox.setFont(new Font("Poppins", Font.PLAIN, 11));
		chckbxNewCheckBox.setBounds(409, 303, 124, 23);
		contentPane.add(chckbxNewCheckBox);
	}
}
