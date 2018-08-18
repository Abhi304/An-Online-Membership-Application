package database;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	public JFrame frame;
	private String loginUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn=null;
	public JTextField txtU;
	private JPasswordField txtP;
	public Login() throws Exception {
		initialize();
		conn=Main.getConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(1500, 500, 1053, 935);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel.setBounds(205, 382, 226, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblNewLabel_1.setBounds(205, 503, 215, 55);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtU = new JTextField();
		txtU.setFont(new Font("Tahoma", Font.PLAIN, 35));
		txtU.setBounds(497, 398, 236, 39);
		frame.getContentPane().add(txtU);
		txtU.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					if(txtU!=null){
						try{
					
						
						String query="SELECT * FROM Admin Where username=? and password=?";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1,txtU.getText());
						pst.setString(2,txtP.getText());
						
						ResultSet rs=pst.executeQuery();
						int count=0;
						while(rs.next()){
							count=count+1;
						}
						if(count==1)
						{
							UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
							          "Arial", Font.BOLD, 30)));       
							 JOptionPane.showMessageDialog(null,"Username and Password is correct");  

							//JOptionPane.showMessageDialog(null,"Username and Password is correct");
							frame.dispose();
							UserInfo ui=new UserInfo();
							//ui.setUser(user);
							ui.setVisible(true);
							

						}
						else if(count>1){
							JOptionPane.showMessageDialog(null,"Duplicate Username and Password");
						}
						else{
							String query1="SELECT * FROM GroupStudy Where username=? and password=?";
							PreparedStatement pst1=conn.prepareStatement(query1);
							pst1.setString(1,txtU.getText());
							pst1.setString(2,txtP.getText());
							
							ResultSet rs1=pst1.executeQuery();
							int count1=0;
							while(rs1.next()){
								
								count1=count1+1;
							}
							if(count1==1)
							{
								UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
								          "Arial", Font.BOLD, 30)));       
								 JOptionPane.showMessageDialog(null,"Welcome to Study Group");  

								//JOptionPane.showMessageDialog(null,"Username and Password is correct");
								frame.dispose();
								String user = txtU.getText();
								
								   //String msg= txtU.getText();
								 //loginUser=Login.this.txtU.getText();
								 MemberScreen mi =  new MemberScreen();
								
								 
							
								 //System.out.println(user);
								
								 mi.setUser(user);
								 mi.setVisible(true);

							}
							else if(count1>1){
								JOptionPane.showMessageDialog(null,"Duplicate Username and Password");
							}
							else{
								JOptionPane.showMessageDialog(null,"Username and password is not correct Try Again...");
							}
						}
						
						rs.close();
						pst.close();
						}
					   
					catch(Exception e){
						JOptionPane.showMessageDialog(null,e);
					}
				}
					
				
				
				
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 40));
		btnLogin.setBounds(373, 687, 171, 55);
		frame.getContentPane().add(btnLogin);
		
		txtP = new JPasswordField();
		txtP.setFont(new Font("Tahoma", Font.PLAIN, 35));
		txtP.setBounds(497, 515, 236, 39);
		frame.getContentPane().add(txtP);
		
		JLabel label = new JLabel("");
		label.setBounds(405, 0, 525, 370);
		frame.getContentPane().add(label);
		Image img=new ImageIcon(this.getClass().getResource("/bible.jpg")).getImage();
		//Image img=new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		label.setIcon(new ImageIcon(img));
		
		JLabel lblWelcomeToBible = new JLabel("Welcome to Bible study");
		lblWelcomeToBible.setFont(new Font("French Script MT", Font.BOLD, 57));
		lblWelcomeToBible.setBounds(267, 53, 609, 41);
		frame.getContentPane().add(lblWelcomeToBible);
	}
}
