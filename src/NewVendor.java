import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class NewVendor extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JTextField shopField;
	Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewVendor frame = new NewVendor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewVendor() {
		setType(Type.UTILITY);
		con = DbConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Name");
		lblNewLabel_1_4.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(23, 30, 109, 14);
		contentPane.add(lblNewLabel_1_4);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(23, 55, 190, 30);
		contentPane.add(nameField);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Phone");
		lblNewLabel_1_4_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_1.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(295, 30, 109, 14);
		contentPane.add(lblNewLabel_1_4_1);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(295, 55, 190, 30);
		contentPane.add(phoneField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(23, 147, 190, 30);
		contentPane.add(addressField);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Address");
		lblNewLabel_1_4_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_2.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_2.setBounds(23, 120, 109, 14);
		contentPane.add(lblNewLabel_1_4_2);
		
		JLabel lblNewLabel_1_4_3 = new JLabel("Shop");
		lblNewLabel_1_4_3.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_3.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_3.setBounds(295, 120, 109, 14);
		contentPane.add(lblNewLabel_1_4_3);
		
		shopField = new JTextField();
		shopField.setColumns(10);
		shopField.setBounds(295, 147, 190, 30);
		contentPane.add(shopField);
		
		JButton btnAddVendor = new JButton("add vendor");
		btnAddVendor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO vendor(name, phone, address, shop) VALUES(?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, nameField.getText());
					ps.setInt(2, Integer.valueOf(phoneField.getText()));
					ps.setString(3, addressField.getText());
					ps.setString(4, shopField.getText());
					
					
					boolean res = ps.execute();
					if(res != true) {
						nameField.setText("");
						phoneField.setText("");
						addressField.setText("");
						shopField.setText("");
						JOptionPane.showMessageDialog(null, "Vendor added successfully");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnAddVendor.setForeground(new Color(72, 61, 139));
		btnAddVendor.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnAddVendor.setBorder(null);
		btnAddVendor.setBackground(SystemColor.menu);
		btnAddVendor.setBounds(255, 225, 120, 30);
		contentPane.add(btnAddVendor);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setForeground(new Color(72, 61, 139));
		btnBack.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnBack.setBorder(null);
		btnBack.setBackground(SystemColor.menu);
		btnBack.setBounds(385, 225, 100, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewVendor = new JLabel("New Vendor");
		lblNewVendor.setForeground(new Color(72, 61, 139));
		lblNewVendor.setFont(new Font("Poppins", Font.BOLD, 14));
		lblNewVendor.setBounds(23, 233, 108, 14);
		contentPane.add(lblNewVendor);
	}

}
