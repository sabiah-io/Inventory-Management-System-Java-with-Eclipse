import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCategory extends JFrame {

	private JPanel contentPane;
	private JTextField categoryField;
	Connection con = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCategory frame = new NewCategory();
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
	public NewCategory() {
		setType(Type.UTILITY);
		con = DbConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddCategory = new JLabel("New Category");
		lblAddCategory.setForeground(new Color(72, 61, 139));
		lblAddCategory.setFont(new Font("Poppins", Font.BOLD, 14));
		lblAddCategory.setBounds(10, 141, 108, 14);
		contentPane.add(lblAddCategory);
		
		JButton btnAddCategory = new JButton("add category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO categories(name) VALUES(?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, categoryField.getText());
					
					
					boolean res = ps.execute();
					if(res != true) {
						categoryField.setText("");
						JOptionPane.showMessageDialog(null, "Category added successfully");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnAddCategory.setForeground(new Color(72, 61, 139));
		btnAddCategory.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnAddCategory.setBorder(null);
		btnAddCategory.setBackground(SystemColor.menu);
		btnAddCategory.setBounds(194, 125, 120, 30);
		contentPane.add(btnAddCategory);
		
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
		btnBack.setBounds(324, 125, 100, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1_4 = new JLabel("Category name");
		lblNewLabel_1_4.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(57, 46, 109, 14);
		contentPane.add(lblNewLabel_1_4);
		
		categoryField = new JTextField();
		categoryField.setFont(new Font("Poppins", Font.PLAIN, 12));
		categoryField.setColumns(10);
		categoryField.setBounds(176, 38, 190, 30);
		contentPane.add(categoryField);
	}

}
