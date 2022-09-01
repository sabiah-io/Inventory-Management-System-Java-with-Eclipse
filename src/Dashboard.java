import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class Dashboard extends JFrame {

	private JPanel contentPane;
	private JTable goodsTable;
	static HashMap<String, String> vendors = new HashMap<String, String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con = null;
	private JTable vendorsTable;
	private JTable issuedGoodsTable;
	private JTable categoriesTable;
	
	public static void loadGoods(Connection con, JTable table) {
		try {
			Statement st = con.createStatement();
			String query = "select * from product";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String id, name, quantity, price, category;
			while(rs.next()) {
				id=rs.getString(1);
				name=rs.getString(2);
				quantity=rs.getString(3);
				price=rs.getString(4);
				category=rs.getString(5);
				String[] row = {id, name, quantity, price, category};
				model.addRow(row);
			}
			
			st.close();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, ex);
		}
		
	}
	
	public static void loadVendors(Connection con, JTable table) {
		try {
			Statement st = con.createStatement();
			String query = "select * from vendor";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String id, name, phone, address, shop;
			while(rs.next()) {
				vendors.put("name", rs.getString(2));
				vendors.put("phone", rs.getString(3));
				vendors.put("address", rs.getString(4));
				vendors.put("shop", rs.getString(5));
				id=rs.getString(1);
				name=rs.getString(2);
				phone=rs.getString(3);
				address=rs.getString(4);
				shop=rs.getString(5);
				String[] row = {id, name, phone, address, shop};
				model.addRow(row);
			}
			
			st.close();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, ex);
		}
		
	}
	
	public static void loadIssuedGoods(Connection con, JTable table) {
		try {
			Statement st = con.createStatement();
			String query = "select * from issued_goods";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String id, name, vendor, buying_price, selling_price, quantity, gross_price, date;
			while(rs.next()) {
				id=rs.getString(1);
				name=rs.getString(2);
				vendor=rs.getString(3);
				buying_price=rs.getString(4);
				selling_price=rs.getString(5);
				quantity=rs.getString(6);
				gross_price=rs.getString(7);
				date=rs.getString(8);
				String[] row = {id, name, vendor, buying_price, selling_price, quantity, gross_price, date};
				model.addRow(row);
			}
			
			st.close();
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, ex);
		}
		
	}
	
	public static void loadCategories(Connection con, JTable table) {
		try {
			Statement st = con.createStatement();
			String query = "select * from categories";
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			DefaultTableModel model = (DefaultTableModel) table.getModel();	
			
			int cols = rsmd.getColumnCount();
			String[] colName = new String[cols];
			for (int i=0; i<cols; i++) {
				colName[i] = rsmd.getColumnName(i+1);
			}
			model.setColumnIdentifiers(colName);
			
			String id, name;
			while(rs.next()) {
				id=rs.getString(1);
				name=rs.getString(2);
				String[] row = {id, name};
				model.addRow(row);
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
	public Dashboard() {
		setTitle("Inventory System");
		con = DbConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(177, -56, 667, 622);
		contentPane.add(tabbedPane);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBorder(null);
		sidePanel.setBackground(new Color(72, 61, 139));
		sidePanel.setBounds(0, 0, 177, 566);
		contentPane.add(sidePanel);
		sidePanel.setLayout(null);
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(new Color(248, 248, 255));
		lblDashboard.setBounds(35, 22, 99, 22);
		lblDashboard.setFont(new Font("Poppins SemiBold", Font.PLAIN, 16));
		sidePanel.add(lblDashboard);
		
		
		
		JButton btnGoods = new JButton("Goods");
		btnGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnGoods.setHorizontalAlignment(SwingConstants.LEADING);
		btnGoods.setBorder(null);
		btnGoods.setBackground(new Color(72, 61, 139));
		btnGoods.setIconTextGap(20);
		btnGoods.setForeground(new Color(100, 149, 237));
		btnGoods.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/shopping-cart-free-icon-font.png")));
		btnGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnGoods.setBounds(35, 79, 142, 31);
		sidePanel.add(btnGoods);
		
		JButton btnAddGoods = new JButton("Add Goods");
		btnAddGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				tabbedPane.setSelectedIndex(1);
				AddGood addGood = new AddGood();
				addGood.setVisible(true);
			}
		});
		btnAddGoods.setHorizontalAlignment(SwingConstants.LEADING);
		btnAddGoods.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/shopping-cart-add-free-icon-font.png")));
		btnAddGoods.setIconTextGap(20);
		btnAddGoods.setForeground(new Color(100, 149, 237));
		btnAddGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnAddGoods.setBorder(null);
		btnAddGoods.setBackground(new Color(72, 61, 139));
		btnAddGoods.setBounds(35, 121, 142, 31);
		sidePanel.add(btnAddGoods);
		
		JButton btnIssuedGoods = new JButton("Issued Goods");
		btnIssuedGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(3);
			}
		});
		btnIssuedGoods.setHorizontalAlignment(SwingConstants.LEADING);
		btnIssuedGoods.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/ticket-free-icon-font.png")));
		btnIssuedGoods.setIconTextGap(20);
		btnIssuedGoods.setForeground(new Color(100, 149, 237));
		btnIssuedGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnIssuedGoods.setBorder(null);
		btnIssuedGoods.setBackground(new Color(72, 61, 139));
		btnIssuedGoods.setBounds(35, 163, 142, 31);
		sidePanel.add(btnIssuedGoods);
		
		JButton btnCategories = new JButton("Categories");
		btnCategories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(4);
			}
		});
		btnCategories.setHorizontalAlignment(SwingConstants.LEADING);
		btnCategories.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/apps-add-free-icon-font.png")));
		btnCategories.setIconTextGap(20);
		btnCategories.setForeground(new Color(100, 149, 237));
		btnCategories.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnCategories.setBorder(null);
		btnCategories.setBackground(new Color(72, 61, 139));
		btnCategories.setBounds(35, 205, 142, 31);
		sidePanel.add(btnCategories);
		
		JButton btnVendors = new JButton("Vendors");
		btnVendors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(5);
//				loadVendors(con, vendorsTable);
			}
		});
		btnVendors.setHorizontalAlignment(SwingConstants.LEADING);
		btnVendors.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/users-alt-free-icon-font.png")));
		btnVendors.setIconTextGap(20);
		btnVendors.setForeground(new Color(100, 149, 237));
		btnVendors.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnVendors.setBorder(null);
		btnVendors.setBackground(new Color(72, 61, 139));
		btnVendors.setBounds(35, 247, 142, 31);
		sidePanel.add(btnVendors);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnLogout.setHorizontalAlignment(SwingConstants.LEADING);
		btnLogout.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/log-out-free-icon-font.png")));
		btnLogout.setIconTextGap(20);
		btnLogout.setForeground(new Color(100, 149, 237));
		btnLogout.setFont(new Font("Poppins Medium", Font.PLAIN, 12));
		btnLogout.setBorder(null);
		btnLogout.setBackground(new Color(72, 61, 139));
		btnLogout.setBounds(35, 484, 142, 31);
		sidePanel.add(btnLogout);
		
		
		
		JPanel goodsPane = new JPanel();
		goodsPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadGoods(con, goodsTable);
			}
			@Override
			public void componentHidden(ComponentEvent e) {
				goodsTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
			}
		});
		goodsPane.setBackground(SystemColor.control);
		tabbedPane.addTab("New tab", null, goodsPane, null);
		goodsPane.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(72, 61, 139));
		panel_6.setBounds(0, 31, 662, 49);
		goodsPane.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Goods");
		lblNewLabel.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(42, 11, 72, 27);
		panel_6.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(0, 79, 662, 463);
		goodsPane.add(scrollPane);
		
		goodsTable = new JTable();
		goodsTable.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		goodsTable.setBackground(SystemColor.control);
		goodsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		
		scrollPane.setViewportView(goodsTable);
		
		JPanel addgoodsPane = new JPanel();
		addgoodsPane.setBackground(SystemColor.control);
		tabbedPane.addTab("New tab", null, addgoodsPane, null);
		addgoodsPane.setLayout(null);
		
		JPanel panel_6_5 = new JPanel();
		panel_6_5.setLayout(null);
		panel_6_5.setBackground(new Color(72, 61, 139));
		panel_6_5.setBounds(0, 30, 662, 49);
		addgoodsPane.add(panel_6_5);
		
		JLabel lblAddGoods = new JLabel("Add Goods");
		lblAddGoods.setForeground(Color.WHITE);
		lblAddGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblAddGoods.setBounds(42, 11, 98, 27);
		panel_6_5.add(lblAddGoods);
		
		JComboBox vendorComboBox = new JComboBox();
		vendorComboBox.setFont(new Font("Poppins", Font.PLAIN, 11));
		vendorComboBox.setBounds(22, 116, 166, 22);
		addgoodsPane.add(vendorComboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Vendor");
		lblNewLabel_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(24, 91, 46, 14);
		addgoodsPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date");
		lblNewLabel_1_1.setForeground(new Color(72, 61, 139));
		lblNewLabel_1_1.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(278, 91, 46, 14);
		addgoodsPane.add(lblNewLabel_1_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(274, 116, 166, 22);
		addgoodsPane.add(dateChooser);
		
		JButton btnAddGood = new JButton("add good");
		btnAddGood.setBorder(null);
		btnAddGood.setBackground(SystemColor.control);
		btnAddGood.setForeground(new Color(72, 61, 139));
		btnAddGood.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/shopping-bag-add-free-icon-font.png")));
		btnAddGood.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnAddGood.setBounds(22, 177, 120, 30);
		addgoodsPane.add(btnAddGood);
		
		JButton btnRemoveGood = new JButton("remove good");
		btnRemoveGood.setBackground(SystemColor.control);
		btnRemoveGood.setBorder(null);
		btnRemoveGood.setForeground(new Color(72, 61, 139));
		btnRemoveGood.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/cross-circle-free-icon-font.png")));
		btnRemoveGood.setFont(new Font("Poppins Medium", Font.ITALIC, 11));
		btnRemoveGood.setBounds(152, 177, 130, 30);
		addgoodsPane.add(btnRemoveGood);
		
		JButton btnSaveGoods = new JButton("Save");
		btnSaveGoods.setBorder(null);
		btnSaveGoods.setBackground(new Color(72, 61, 139));
		btnSaveGoods.setForeground(new Color(255, 255, 255));
		btnSaveGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		btnSaveGoods.setBounds(546, 536, 89, 30);
		addgoodsPane.add(btnSaveGoods);
		
		JPanel billsPane = new JPanel();
		billsPane.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, billsPane, null);
		billsPane.setLayout(null);
		
		JPanel panel_6_4 = new JPanel();
		panel_6_4.setLayout(null);
		panel_6_4.setBackground(new Color(72, 61, 139));
		panel_6_4.setBounds(0, 31, 662, 49);
		billsPane.add(panel_6_4);
		
		JLabel lblBills = new JLabel("Bills");
		lblBills.setForeground(Color.WHITE);
		lblBills.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblBills.setBounds(42, 11, 42, 27);
		panel_6_4.add(lblBills);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBackground(Color.WHITE);
		scrollPane_4.setBounds(0, 80, 662, 514);
		billsPane.add(scrollPane_4);
		
		JPanel issuedgoodsPane = new JPanel();
		issuedgoodsPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				issuedGoodsTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
			}
			@Override
			public void componentShown(ComponentEvent e) {
				loadIssuedGoods(con, issuedGoodsTable);
			}
		});
		issuedgoodsPane.setBackground(new Color(255, 255, 255));
		tabbedPane.addTab("New tab", null, issuedgoodsPane, null);
		issuedgoodsPane.setLayout(null);
		
		JPanel panel_6_2 = new JPanel();
		panel_6_2.setLayout(null);
		panel_6_2.setBackground(new Color(72, 61, 139));
		panel_6_2.setBounds(0, 31, 662, 49);
		issuedgoodsPane.add(panel_6_2);
		
		JLabel lblIssuedGoods = new JLabel("Issued Goods");
		lblIssuedGoods.setForeground(Color.WHITE);
		lblIssuedGoods.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblIssuedGoods.setBounds(42, 11, 109, 27);
		panel_6_2.add(lblIssuedGoods);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBackground(Color.WHITE);
		scrollPane_2.setBounds(0, 80, 662, 514);
		issuedgoodsPane.add(scrollPane_2);
		
		issuedGoodsTable = new JTable();
		issuedGoodsTable.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		issuedGoodsTable.setBackground(SystemColor.controlHighlight);
		scrollPane_2.setViewportView(issuedGoodsTable);
		
		JPanel categoriesPane = new JPanel();
		categoriesPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				categoriesTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
			}
			@Override
			public void componentShown(ComponentEvent e) {
				loadCategories(con, categoriesTable);
			}
		});
		categoriesPane.setBackground(SystemColor.control);
		tabbedPane.addTab("New tab", null, categoriesPane, null);
		categoriesPane.setLayout(null);
		
		JPanel panel_6_3 = new JPanel();
		panel_6_3.setLayout(null);
		panel_6_3.setBackground(new Color(72, 61, 139));
		panel_6_3.setBounds(0, 31, 662, 49);
		categoriesPane.add(panel_6_3);
		
		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setForeground(Color.WHITE);
		lblCategories.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblCategories.setBounds(42, 11, 89, 27);
		panel_6_3.add(lblCategories);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBackground(Color.WHITE);
		scrollPane_3.setBounds(0, 80, 662, 456);
		categoriesPane.add(scrollPane_3);
		
		categoriesTable = new JTable();
		categoriesTable.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		categoriesTable.setBackground(SystemColor.control);
		scrollPane_3.setViewportView(categoriesTable);
		
		JPanel vendorsPane = new JPanel();
		vendorsPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadVendors(con, vendorsTable);
			}
			@Override
			public void componentHidden(ComponentEvent e) {
				vendorsTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
			}
		});
		vendorsPane.setBackground(SystemColor.control);
		tabbedPane.addTab("New tab", null, vendorsPane, null);
		vendorsPane.setLayout(null);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setLayout(null);
		panel_6_1.setBackground(new Color(72, 61, 139));
		panel_6_1.setBounds(0, 31, 662, 49);
		vendorsPane.add(panel_6_1);
		
		JLabel lblVendors = new JLabel("Vendors");
		lblVendors.setForeground(Color.WHITE);
		lblVendors.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblVendors.setBounds(42, 11, 72, 27);
		panel_6_1.add(lblVendors);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(0, 80, 662, 455);
		vendorsPane.add(scrollPane_1);
		
		vendorsTable = new JTable();
		vendorsTable.setFont(new Font("Poppins Medium", Font.PLAIN, 11));
		vendorsTable.setBackground(SystemColor.control);
		vendorsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane_1.setViewportView(vendorsTable);
		
	
		
		JButton btnNewButton_2 = new JButton("New");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGood good = new NewGood();
				good.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/shopping-cart-add-free-icon-font.png")));
		btnNewButton_2.setIconTextGap(10);
		btnNewButton_2.setForeground(new Color(100, 149, 237));
		btnNewButton_2.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(new Color(72, 61, 139));
		btnNewButton_2.setBounds(552, 553, 100, 30);
		goodsPane.add(btnNewButton_2);
		
		JButton btnGoodsLoad = new JButton("Load");
		btnGoodsLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodsTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
				loadGoods(con, goodsTable);
			}
		});
		btnGoodsLoad.setIconTextGap(10);
		btnGoodsLoad.setForeground(new Color(100, 149, 237));
		btnGoodsLoad.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnGoodsLoad.setBorder(null);
		btnGoodsLoad.setBackground(new Color(72, 61, 139));
		btnGoodsLoad.setBounds(438, 553, 100, 30);
		goodsPane.add(btnGoodsLoad);

		
		JButton btnNewButton_1 = new JButton("New");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewVendor vendor = new NewVendor();
				vendor.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/user-add-free-icon-font.png")));
		btnNewButton_1.setIconTextGap(10);
		btnNewButton_1.setForeground(new Color(100, 149, 237));
		btnNewButton_1.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(72, 61, 139));
		btnNewButton_1.setBounds(552, 546, 100, 30);
		vendorsPane.add(btnNewButton_1);
		
		JButton btnVendorLoad = new JButton("Load");
		btnVendorLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {vendorsTable.setModel(new DefaultTableModel(
					new Object[][] {
					},
					new String[] {
					}
				));
			loadVendors(con, vendorsTable);
			}
		});
		btnVendorLoad.setIconTextGap(10);
		btnVendorLoad.setForeground(new Color(100, 149, 237));
		btnVendorLoad.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnVendorLoad.setBorder(null);
		btnVendorLoad.setBackground(new Color(72, 61, 139));
		btnVendorLoad.setBounds(442, 546, 100, 30);
		vendorsPane.add(btnVendorLoad);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewCategory category = new NewCategory();
				category.setVisible(true);
			}
		});
		btnNewButton.setIconTextGap(10);
		btnNewButton.setIcon(new ImageIcon(Dashboard.class.getResource("/assets/apps-add-free-icon-font.png")));
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(100, 149, 237));
		btnNewButton.setBackground(new Color(72, 61, 139));
		btnNewButton.setBounds(552, 547, 100, 30);
		categoriesPane.add(btnNewButton);
		
		JButton btnCatLoad = new JButton("Load");
		btnCatLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoriesTable.setModel(new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
						}
					));
				loadCategories(con, categoriesTable);
			}
		});
		btnCatLoad.setIconTextGap(10);
		btnCatLoad.setForeground(new Color(100, 149, 237));
		btnCatLoad.setFont(new Font("Poppins Medium", Font.PLAIN, 14));
		btnCatLoad.setBorder(null);
		btnCatLoad.setBackground(new Color(72, 61, 139));
		btnCatLoad.setBounds(442, 547, 100, 30);
		categoriesPane.add(btnCatLoad);
	}
}
