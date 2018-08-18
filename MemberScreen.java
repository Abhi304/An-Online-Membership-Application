package database;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//public class MemberScreen extends javax.swing.JFrame {
public class MemberScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtMuid;
	private JTextField txtMname;
	private JTextField txtMsname;
	private JTextField txtMuname;
	private JTextField txtMpwd;
	private JTextField txtMemail;
	public JTextField txtU;
	private String  user;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 MemberScreen frame = new MemberScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
    private JLabel lblNewLabel_6;
    private JTable table;
    private JLabel lblWelcome;
    private JButton btnLogOut;
    private JTextField textFieldSearch;
    
    
    
    public void refreshTable(){
    	try {
			String query="Select User_ID,Name,Surname,Email,Username from GroupStudy";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * Create the frame.
	 */
    public void setUser(String user){
		this.txtMuname.setText(user);
		
	}
	public String getUser(){
		return this.txtMuname.getText();
	}
	public MemberScreen() throws Exception {
		
		conn=Main.getConnection();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				 try {
					   
				        String query = "SELECT * From GroupStudy WHERE Username=? ";
				        PreparedStatement pst=conn.prepareStatement(query);
				        pst.setString(1,(String)txtMuname.getText());
				        
						ResultSet rs=pst.executeQuery();
						
						while(rs.next()){
							txtMuid.setText(rs.getString("User_ID"));
							txtMname.setText(rs.getString("Name"));
							txtMsname.setText(rs.getString("Surname"));
							txtMuname.setText(rs.getString("Username"));
							txtMpwd.setText(rs.getString("Password"));
							txtMemail.setText(rs.getString("Email"));
						}
						
					
						
						pst.close();
						
						
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(1500, 700, 800, 600);
		setBounds(1000, 600, 1800, 1200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtMuid = new JTextField();
		txtMuid.setEditable(false);
		txtMuid.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMuid.setBounds(252, 167, 236, 39);
		contentPane.add(txtMuid);
		txtMuid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User_ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel.setBounds(11, 170, 161, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(11, 265, 115, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_2.setBounds(11, 345, 161, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_3.setBounds(11, 423, 179, 33);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_4.setBounds(11, 499, 161, 33);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblNewLabel_5.setBounds(11, 574, 115, 33);
		contentPane.add(lblNewLabel_5);
		
		txtMname = new JTextField();
		txtMname.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMname.setBounds(252, 262, 236, 39);
		contentPane.add(txtMname);
		txtMname.setColumns(10);
		
		txtMsname = new JTextField();
		txtMsname.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMsname.setBounds(252, 342, 236, 39);
		contentPane.add(txtMsname);
		txtMsname.setColumns(10);
		
		txtMuname = new JTextField();
		txtMuname.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMuname.setBounds(252, 420, 236, 39);
		contentPane.add(txtMuname);
		txtMuname.setColumns(10);
		
		txtMpwd = new JTextField();
		txtMpwd.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMpwd.setBounds(252, 496, 236, 39);
		contentPane.add(txtMpwd);
		txtMpwd.setColumns(10);
		
		txtMemail = new JTextField();
		txtMemail.setFont(new Font("Tahoma", Font.PLAIN, 34));
		txtMemail.setBounds(252, 571, 236, 39);
		contentPane.add(txtMemail);
		txtMemail.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="Update GroupStudy set User_ID='"+txtMuid.getText()+"',Name='"+txtMname.getText()+"',Surname='"+txtMsname.getText()+"',Username='"+txtMuname.getText()+"',Password='"+txtMpwd.getText()+"',Email='"+txtMemail.getText()+"' where User_ID='"+txtMuid.getText()+"'    ";
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnNewButton.setBounds(149, 780, 171, 50);
		contentPane.add(btnNewButton);
		
		lblNewLabel_6 = new JLabel("Bible Study Members");
		lblNewLabel_6.setFont(new Font("French Script MT", Font.BOLD, 55));
		lblNewLabel_6.setBounds(879, 84, 470, 49);
		contentPane.add(lblNewLabel_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(524, 136, 1172, 783);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setEnabled(false);
		/*table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});*/
		
		//table.getColumnModel().getColumn(0).setResizable(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 34));
		scrollPane.setViewportView(table);
		//table = new JTable();
		table.setRowHeight(45);
		
		lblWelcome = new JLabel("Welcome,");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblWelcome.setBounds(5, 20, 185, 45);
		contentPane.add(lblWelcome);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dispose();
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception ennn) {
					ennn.printStackTrace();
				}
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnLogOut.setBounds(149, 901, 171, 50);
		contentPane.add(btnLogOut);
		
		JComboBox comboBoxSearch = new JComboBox();
		comboBoxSearch.setFont(new Font("Tahoma", Font.PLAIN, 34));
		comboBoxSearch.setModel(new DefaultComboBoxModel(new String[] {"Search..", "User_ID", "Name", "Surname", "Username"}));
		comboBoxSearch.setBounds(524, 26, 183, 39);
		contentPane.add(comboBoxSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.setFont(new Font("Tahoma", Font.PLAIN, 34));
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					String selection=(String)comboBoxSearch.getSelectedItem();
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
		textFieldSearch.setBounds(733, 28, 236, 39);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		table.getTableHeader().setPreferredSize(new Dimension(30,100));
		table.getTableHeader().setFont(new Font("Arial",Font.ITALIC,35));
	}
	
	public MemberScreen(String loginUser){
	    
	  
		lblNewLabel_6.setText(loginUser);
		
	}
}
