package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox comboBoxName;
	private JComboBox comboBoxSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JScrollPane scrollPane;
	private JTextField txtUid;
	private JTextField txtName;
	private JTextField txtSname;
	private JTextField txtUname;
	private JTextField txtPwd;
	private JTextField txtEmail;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JTextField textFieldSearch;
	private final JLabel lblWelcomeAdmin = new JLabel("Welcome, Admin");
	private JButton btnNewButton_1;
	
	public void refreshTable()
	{
		try {
			String query="Select User_ID,Name,Surname,Email,Username from GroupStudy";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(0).setMinWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(50);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void fillComboBox(){
		try{
			String query="Select * from GroupStudy";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				comboBoxName.addItem(rs.getString("User_ID"));
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public UserInfo() throws Exception {
		conn=Main.getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 300, 2000, 1500);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLoad = new JButton("Members_Data");
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Select User_ID,Name,Surname,Email,Username from GroupStudy";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnLoad.setBounds(1055, 29, 327, 41);
		contentPane.add(btnLoad);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(581, 110, 1323, 998);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(45);
		table.getTableHeader().setPreferredSize(new Dimension(30,100));
		table.getTableHeader().setFont(new Font("Arial",Font.ITALIC,35));
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row =table.getSelectedRow();
					String User_ID=(table.getModel().getValueAt(row, 0)).toString();
					
					String query="Select * from GroupStudy where User_ID='"+User_ID+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()){
						txtUid.setText(rs.getString("User_ID"));
						txtName.setText(rs.getString("Name"));
						txtSname.setText(rs.getString("Surname"));
						txtUname.setText(rs.getString("Username"));
						txtPwd.setText(rs.getString("Password"));
						txtEmail.setText(rs.getString("Email"));
					}
					
				
					
					pst.close();
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setFont(new Font("Arial", Font.PLAIN, 30));
		
		JLabel lblNewLabel = new JLabel("User_ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setBounds(9, 287, 132, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(9, 382, 132, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_2.setBounds(15, 472, 143, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_3.setBounds(15, 567, 151, 33);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_4.setBounds(15, 652, 143, 33);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_5.setBounds(26, 745, 115, 33);
		contentPane.add(lblNewLabel_5);
		
		txtUid = new JTextField();
		txtUid.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtUid.setBounds(202, 268, 236, 52);
		contentPane.add(txtUid);
		txtUid.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtName.setBounds(202, 360, 236, 52);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSname = new JTextField();
		txtSname.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtSname.setBounds(202, 463, 236, 52);
		contentPane.add(txtSname);
		txtSname.setColumns(10);
		
		txtUname = new JTextField();
		txtUname.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtUname.setBounds(202, 558, 236, 52);
		contentPane.add(txtUname);
		txtUname.setColumns(10);
		
		txtPwd = new JTextField();
		txtPwd.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtPwd.setBounds(202, 643, 236, 52);
		contentPane.add(txtPwd);
		txtPwd.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtEmail.setBounds(202, 747, 236, 52);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="Insert into GroupStudy(User_ID,Name,Surname,Username,Password,Email) values (?,?,?,?,?,?)";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, txtUid.getText() );
					pst.setString(2, txtName.getText() );
					pst.setString(3, txtSname.getText() );
					pst.setString(4, txtUname.getText() );
					pst.setString(5, txtPwd.getText() );
					pst.setString(6, txtEmail.getText() );
					
					pst.execute();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
					          "Arial", Font.PLAIN, 30))); 
					JOptionPane.showMessageDialog(null, "Data Saved");
					
					pst.close();
					
					refreshTable();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnSave.setBounds(145, 1012, 211, 52);
		contentPane.add(btnSave);
		
		btnNewButton = new JButton("UPDATE");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		try{
			String query="Update GroupStudy set User_ID='"+txtUid.getText()+"',Name='"+txtName.getText()+"',Surname='"+txtSname.getText()+"',Username='"+txtUname.getText()+"',Password='"+txtPwd.getText()+"',Email='"+txtEmail.getText()+"' where User_ID='"+txtUid.getText()+"'    ";
			PreparedStatement pst=conn.prepareStatement(query);
			
			
			pst.execute();
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
			          "Arial", Font.PLAIN, 30))); 
			JOptionPane.showMessageDialog(null, "Data Updated");
			
			pst.close();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
				
		refreshTable();
				
				
			}
		});
		btnNewButton.setBounds(145, 1235, 206, 52);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="Delete from GroupStudy Where User_ID='"+txtUid.getText()+"'   ";
					PreparedStatement pst=conn.prepareStatement(query);
					
					
					pst.execute();
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
					          "Arial", Font.PLAIN, 30))); 
					JOptionPane.showMessageDialog(null, "Data Deleted");
					
					pst.close();
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				refreshTable();
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnDelete.setBounds(145, 1123, 211, 52);
		contentPane.add(btnDelete);
		
	    comboBoxName = new JComboBox();
	    comboBoxName.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent arg0) {
	    	}
	    });
	    comboBoxName.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try{
					String query="Select * from GroupStudy where User_ID=?  ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,(String)comboBoxName.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()){
						txtUid.setText(rs.getString("User_ID"));
						txtName.setText(rs.getString("Name"));
						txtSname.setText(rs.getString("Surname"));
						txtUname.setText(rs.getString("Username"));
						txtPwd.setText(rs.getString("Password"));
						txtEmail.setText(rs.getString("Email"));
					}
					
				
					
					pst.close();
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
	    		
	    		
	    	}
	    });
		comboBoxName.setFont(new Font("Tahoma", Font.PLAIN, 34));
		comboBoxName.setBounds(118, 50, 171, 43);
		contentPane.add(comboBoxName);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 34));
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String selection=(String)comboBoxSelection.getSelectedItem();
					String query="Select * from GroupStudy where "+selection+"=?  ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,textFieldSearch.getText());
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					
					
					
					
				
					
					pst.close();
					
					
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
				
				
			}
		});
		textFieldSearch.setBounds(730, 33, 236, 45);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
	    comboBoxSelection = new JComboBox();
	    comboBoxSelection.setFont(new Font("Tahoma", Font.PLAIN, 34));
		comboBoxSelection.setModel(new DefaultComboBoxModel(new String[] {"Search..", "User_ID", "Name", "Surname", "Username"}));
		comboBoxSelection.setBounds(490, 33, 222, 45);
		contentPane.add(comboBoxSelection);
		lblWelcomeAdmin.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblWelcomeAdmin.setBounds(71, 141, 367, 41);
		contentPane.add(lblWelcomeAdmin);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUid.setText("");
				txtName.setText("");
				txtSname.setText("");
				txtUname.setText("");
				txtPwd.setText("");
				txtEmail.setText("");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnReset.setBounds(145, 888, 211, 52);
		contentPane.add(btnReset);
		
		btnNewButton_1 = new JButton("Log out");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dispose();
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception ennn) {
					ennn.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		btnNewButton_1.setBounds(1651, 1230, 225, 52);
		contentPane.add(btnNewButton_1);
		refreshTable();
		fillComboBox();
	}
}
