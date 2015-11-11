package com.gui;

import java.awt.EventQueue;
import java.sql.*;
import com.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginWin {

	private JFrame frame;
	private JTextField usernameTF;
	private JPasswordField passwordTF;
	private JButton btnLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWin window = new LoginWin();
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
	public LoginWin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.desktop);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(true);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblUsername.setForeground(Color.RED);
		lblUsername.setBounds(162, 44, 120, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblPassword.setForeground(Color.RED);
		lblPassword.setBounds(172, 100, 110, 15);
		frame.getContentPane().add(lblPassword);
		
		usernameTF = new JTextField();
		usernameTF.setBounds(300, 42, 114, 19);
		frame.getContentPane().add(usernameTF);
		usernameTF.setColumns(10);
		
		
		passwordTF = new JPasswordField();
		passwordTF.setBounds(311, 98, 103, 20);
		frame.getContentPane().add(passwordTF);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection connection;
					connection = SQLConnector.dbConnector();
					if(usernameTF.getText().length()>1 && passwordTF.getPassword().length > 1){
						String uname,pass;
						uname = usernameTF.getText();
						pass = String.valueOf(passwordTF.getPassword());
						String query = "SELECT * FROM `user_info` WHERE NAME='"+uname+"' AND PASSWORD='"+pass+"';";
						
						PreparedStatement pst = connection.prepareStatement(query);
						ResultSet rs = pst.executeQuery();
						if(!rs.next()){
							JOptionPane.showMessageDialog(null, uname+pass+"Invalid Username and/or Password");
						}else{
							JOptionPane.showMessageDialog(null, "Logged In");
							/////// SET SECRET_KEY
							try{
							String query1 = "SELECT KEY FROM `user_info` WHERE NAME='"+uname+"' AND PASSWORD='"+pass+"' ;";
							//JOptionPane.showMessageDialog(null, query1);
							PreparedStatement pst1 = connection.prepareStatement(query1);
							ResultSet rs1 = pst1.executeQuery();
							ResultSetMetaData rsmd1 = rs1.getMetaData();
							String secret_key_copy;
							int columnsNumber1 = rsmd1.getColumnCount();
							secret_key_copy = rs1.getObject(1).toString();
							variables.secret_key = secret_key_copy;
							frame.dispose();
							UIWin uiw = new UIWin();
							}
							catch(Exception ex){
								JOptionPane.showMessageDialog(null, ex);
							}
						}
						rs.close();pst.close();connection.close();
					}
				}
				catch(Exception ex){
						
				}
			}
		});
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setForeground(Color.RED);
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnLogin.setBounds(214, 196, 117, 25);
		frame.getContentPane().add(btnLogin);
	}
}
