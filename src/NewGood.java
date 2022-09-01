import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class NewGood extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField quantityField;
	private JTextField priceField;
	Connection con = null;
	Stack<Object> productStack = new Stack<Object>(); 
	Queue<Object> productQueue = new ArrayDeque<Object>();
	List<Object> productList = new ArrayList<Object>(); 
	ArrayList<String> stackList  = new ArrayList<String>(Arrays.asList("Beverages", "Bakery", "Canned", "Diary"));
	ArrayList<String> queueList  = new ArrayList<String>(Arrays.asList("Baking", "Frozen", "Meat"));
	ArrayList<String> list  = new ArrayList<String>(Arrays.asList("Produce", "Cleaners", "Paper", "Personal"));
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGood frame = new NewGood();
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
	
	@SuppressWarnings("unchecked")
	public static void addStackToDb(Connection con, Stack<Object> stack) {
		while(stack.isEmpty() == false) {
			try {
				Object productObject = stack.pop();
				String name = ((HashMap<String,String>) productObject).get("name");
				String quantity = ((HashMap<String,String>) productObject).get("quantity");
				String price = ((HashMap<String,String>) productObject).get("price");
				String category = ((HashMap<String,String>) productObject).get("category");
				
				String query = "INSERT INTO product(name, quantity, price, category) VALUES(?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, name);
				ps.setInt(2, Integer.valueOf(quantity));
				ps.setInt(3, Integer.valueOf(price));
				ps.setString(4, category);
				
				ps.execute();
				ps.close();
			} catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void addQueueToDb(Connection con, Queue<Object> queue) {
		while(queue.isEmpty() == false) {
			try {
				Object productObject = queue.poll();
				String name = ((HashMap<String,String>) productObject).get("name");
				String quantity = ((HashMap<String,String>) productObject).get("quantity");
				String price = ((HashMap<String,String>) productObject).get("price");
				String category = ((HashMap<String,String>) productObject).get("category");
				
				String query = "INSERT INTO product(name, quantity, price, category) VALUES(?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, name);
				ps.setInt(2, Integer.valueOf(quantity));
				ps.setInt(3, Integer.valueOf(price));
				ps.setString(4, category);
				
				ps.execute();
				ps.close();
			} catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void addListToDb(Connection con, List<Object> list) {
		for (int i = 0; i < list.size(); i++) {
			try {
				Object productObject = list.get(i);
				String name = ((HashMap<String,String>) productObject).get("name");
				String quantity = ((HashMap<String,String>) productObject).get("quantity");
				String price = ((HashMap<String,String>) productObject).get("price");
				String category = ((HashMap<String,String>) productObject).get("category");
				
				String query = "INSERT INTO product(name, quantity, price, category) VALUES(?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, name);
				ps.setInt(2, Integer.valueOf(quantity));
				ps.setInt(3, Integer.valueOf(price));
				ps.setString(4, category);
				
				ps.execute();
				ps.close();
			} catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex);
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public NewGood() {
		setType(Type.UTILITY);
		con = DbConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 343);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1_4 = new JLabel("Name");
		lblNewLabel_1_4.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(28, 26, 109, 14);
		contentPane.add(lblNewLabel_1_4);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Poppins", Font.PLAIN, 12));
		nameField.setColumns(10);
		nameField.setBounds(28, 51, 190, 30);
		contentPane.add(nameField);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Quantity");
		lblNewLabel_1_4_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_1.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(280, 26, 109, 14);
		contentPane.add(lblNewLabel_1_4_1);
		
		quantityField = new JTextField();
		quantityField.setFont(new Font("Poppins", Font.PLAIN, 12));
		quantityField.setColumns(10);
		quantityField.setBounds(280, 51, 190, 30);
		contentPane.add(quantityField);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Price");
		lblNewLabel_1_4_2.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_2.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_2.setBounds(28, 112, 109, 14);
		contentPane.add(lblNewLabel_1_4_2);
		
		priceField = new JTextField();
		priceField.setFont(new Font("Poppins", Font.PLAIN, 12));
		priceField.setColumns(10);
		priceField.setBounds(28, 137, 190, 30);
		contentPane.add(priceField);
		
		JLabel lblNewLabel_1_4_3 = new JLabel("Category");
		lblNewLabel_1_4_3.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_4_3.setFont(new Font("Poppins Medium", Font.PLAIN, 13));
		lblNewLabel_1_4_3.setBounds(280, 112, 109, 14);
		contentPane.add(lblNewLabel_1_4_3);
		JComboBox catComboBox = new JComboBox();
		catComboBox.setBounds(280, 137, 190, 30);
		contentPane.add(catComboBox);
		
		JButton btnAdd = new JButton("add ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// HashMap initialization
					// This will be us used to store added products and ready to be pushed to
					// the database
					HashMap<String, String> products = new HashMap<String, String>();
					products.put("name", nameField.getText());
					products.put("quantity", quantityField.getText());
					products.put("price", priceField.getText());
					products.put("category", catComboBox.getSelectedItem().toString());
					
					if(stackList.contains(catComboBox.getSelectedItem().toString())) {
						// Here we populate our stack
						productStack.push(products);
						JOptionPane.showMessageDialog(null, "Product added successfully using Stack implementation");
						
					} else if(queueList.contains(catComboBox.getSelectedItem().toString())) {
						// Here we populate our queue
						productQueue.add(products);
						JOptionPane.showMessageDialog(null, "Product added successfully using Queue implementation");
					} else if(list.contains(catComboBox.getSelectedItem().toString())) {
						// Here we populate our list
						productList.add(products);
						JOptionPane.showMessageDialog(null, "Product added successfully using List implementation");
					}
					nameField.setText("");
					quantityField.setText("0");
					priceField.setText("0");
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
			}
		});
		btnAdd.setForeground(new Color(72, 61, 139));
		btnAdd.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnAdd.setBorder(null);
		btnAdd.setBackground(SystemColor.menu);
		btnAdd.setBounds(280, 229, 100, 30);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStackToDb(con, productStack);
				addQueueToDb(con, productQueue);
				addListToDb(con, productList);
				setVisible(false);
				
			}
		});
		btnBack.setForeground(new Color(72, 61, 139));
		btnBack.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnBack.setBorder(null);
		btnBack.setBackground(SystemColor.menu);
		btnBack.setBounds(390, 229, 100, 30);
		contentPane.add(btnBack);
		
		JLabel lblNewGood = new JLabel("New Good");
		lblNewGood.setForeground(new Color(72, 61, 139));
		lblNewGood.setFont(new Font("Poppins", Font.BOLD, 14));
		lblNewGood.setBounds(28, 237, 74, 14);
		contentPane.add(lblNewGood);
		
		
		loadCombo(con, catComboBox, "select * from categories", 2);
	}
}
