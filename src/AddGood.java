import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;

import com.mysql.cj.xdevapi.Result;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AddGood extends JFrame {

	private JPanel contentPane;
	private JTextField buyField;
	private JTextField sellField;
	private JTextField grossField;

	Connection con = null;
	int i = 0;
	int total = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGood frame = new AddGood();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void loadCombo(Connection con, JComboBox<String> comboBox, String query, int pos) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				comboBox.addItem(rs.getString(pos));
			}
			
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public AddGood() {
		setType(Type.UTILITY);
		con = DbConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_6 = new JLabel("Total goods added:");
		lblNewLabel_1_6.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_6.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_6.setBounds(35, 11, 110, 14);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel totalGoods = new JLabel("0");
		totalGoods.setForeground(new Color(72, 61, 139));
		totalGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		totalGoods.setBounds(153, 11, 46, 14);
		contentPane.add(totalGoods);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Total amount:");
		lblNewLabel_1_6_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_6_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_6_1.setBounds(285, 11, 84, 14);
		contentPane.add(lblNewLabel_1_6_1);
		
		JLabel totalAmount = new JLabel("0");
		totalAmount.setForeground(new Color(72, 61, 139));
		totalAmount.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		totalAmount.setBounds(366, 11, 126, 14);
		contentPane.add(totalAmount);
		
		JLabel lblNewLabel_1 = new JLabel("Vendor");
		lblNewLabel_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(37, 58, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox vendorComboBox = new JComboBox();
		vendorComboBox.setFont(new Font("Poppins", Font.PLAIN, 11));
		vendorComboBox.setBounds(35, 83, 166, 25);
		contentPane.add(vendorComboBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date");
		lblNewLabel_1_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(285, 58, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(285, 83, 166, 25);
		contentPane.add(dateChooser);
		
		JLabel lblNewLabel_1_2 = new JLabel("Good");
		lblNewLabel_1_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_2.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(37, 140, 74, 14);
		contentPane.add(lblNewLabel_1_2);
		
		JComboBox goodComboBox = new JComboBox();
		goodComboBox.setFont(new Font("Poppins", Font.PLAIN, 11));
		goodComboBox.setBounds(35, 165, 166, 25);
		contentPane.add(goodComboBox);
		
		JLabel lblNewLabel_1_3 = new JLabel("Quantity");
		lblNewLabel_1_3.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_3.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_3.setBounds(289, 139, 62, 14);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Buying price/item");
		lblNewLabel_1_4.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_4.setBounds(37, 215, 108, 14);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Selling price/item");
		lblNewLabel_1_5.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_5.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_5.setBounds(289, 215, 100, 14);
		contentPane.add(lblNewLabel_1_5);
		
		sellField = new JTextField();
		sellField.setColumns(10);
		sellField.setBounds(285, 240, 166, 25);
		contentPane.add(sellField);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Gross price");
		lblNewLabel_1_4_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_4_1.setBounds(33, 292, 108, 14);
		contentPane.add(lblNewLabel_1_4_1);
		
		grossField = new JTextField();
		grossField.setColumns(10);
		grossField.setBounds(33, 317, 166, 25);
		contentPane.add(grossField);
		
		buyField = new JTextField();
		buyField.setColumns(10);
		buyField.setBounds(35, 240, 166, 25);
		contentPane.add(buyField);
		
		JSpinner quantitySpinner = new JSpinner();
		quantitySpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int grossTotal = Integer.valueOf(buyField.getText()) * Integer.valueOf(quantitySpinner.getValue().toString());
				grossField.setText(String.valueOf(grossTotal));
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Add Goods...");
		lblNewLabel.setForeground(new Color(72, 61, 139));
		lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 14));
		lblNewLabel.setBounds(35, 393, 108, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnAddGood = new JButton("add good");
		btnAddGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO issued_goods(name, vendor, buying_price, selling_price, quantity, gross_price, date) VALUES(?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, goodComboBox.getSelectedItem().toString());
					ps.setString(2, vendorComboBox.getSelectedItem().toString());
					ps.setInt(3, Integer.valueOf(buyField.getText()));
					ps.setInt(4, Integer.valueOf(sellField.getText()));
					ps.setInt(5, Integer.valueOf(quantitySpinner.getValue().toString()));
					ps.setInt(6, Integer.valueOf(grossField.getText()));
					String BD = (new java.text.SimpleDateFormat("MM/dd/yyyy")).format(dateChooser.getDate());
					ps.setString(7, BD.toString());
					
					
					boolean res = ps.execute();
					if(res != true) {
						i++;
						total += Integer.valueOf(grossField.getText());
						totalGoods.setText(String.valueOf(i));
						totalAmount.setText(String.valueOf(total));
						buyField.setText("");
						sellField.setText("");
						grossField.setText("");
						JOptionPane.showMessageDialog(null, "Good added successfully");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
		});
		btnAddGood.setIcon(new ImageIcon(AddGood.class.getResource("/assets/shopping-bag-add-free-icon-font.png")));
		btnAddGood.setForeground(new Color(72, 61, 139));
		btnAddGood.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnAddGood.setBorder(null);
		btnAddGood.setBackground(SystemColor.menu);
		btnAddGood.setBounds(294, 356, 120, 30);
		contentPane.add(btnAddGood);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(AddGood.class.getResource("/assets/cross-circle-free-icon-font.png")));
		btnBack.setForeground(new Color(72, 61, 139));
		btnBack.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnBack.setBorder(null);
		btnBack.setBackground(SystemColor.menu);
		btnBack.setBounds(424, 356, 100, 30);
		contentPane.add(btnBack);
		
		loadCombo(con, vendorComboBox, "select * from vendor", 2);
		loadCombo(con, goodComboBox, "select * from product", 2);
		
		
		quantitySpinner.setBounds(285, 165, 166, 25);
		contentPane.add(quantitySpinner);
	}
}
