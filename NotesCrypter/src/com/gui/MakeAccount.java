package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import com.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class MakeAccount extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JButton btnCreateAccount;
	private JPasswordField passwordPF;
	private JPasswordField secret_keyPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MakeAccount frame = new MakeAccount();
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
	public MakeAccount() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("NAME :");
		lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblName.setForeground(Color.RED);
		lblName.setBounds(35, 67, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setForeground(Color.RED);
		lblPassword.setBounds(35, 94, 114, 15);
		contentPane.add(lblPassword);
		
		JLabel lblSecretKey = new JLabel("SECRET KEY :");
		lblSecretKey.setForeground(Color.RED);
		lblSecretKey.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblSecretKey.setBounds(35, 116, 114, 15);
		contentPane.add(lblSecretKey);
		
		nameTF = new JTextField();
		nameTF.setBounds(155, 63, 114, 19);
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
		passwordPF = new JPasswordField();
		passwordPF.setBounds(175, 92, 94, 19);
		contentPane.add(passwordPF);
		
		secret_keyPF = new JPasswordField();
		secret_keyPF.setBounds(167, 115, 101, 16);
		contentPane.add(secret_keyPF);
		
		btnCreateAccount = new JButton("CREATE ACCOUNT");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection connection;
					String username,password,secret_key_copy;
					username = nameTF.getText();
					password = String.valueOf(passwordPF.getPassword());
					variables.secret_key = String.valueOf(secret_keyPF.getPassword());
					secret_key_copy = variables.secret_key;
					connection = SQLConnector.dbConnector();
					if(!password.contains("\"") && (username.length()>1)
							&& (variables.secret_key.length()>1)){
						String query = "INSERT INTO `user_info` values (?, ?, ?);";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1,username);
						pst.setString(2,password);
						pst.setString(3,secret_key_copy);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "ACCOUNT CREATED");
						dispose();
						AddSubjects aac = new AddSubjects();
						aac.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Password shouldn't contain \" and all fields length > 1 allowed.");
						passwordPF.setText("");
					}
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "SQL ERROR : " + ex);
				}
			}
		});
		btnCreateAccount.setForeground(Color.RED);
		btnCreateAccount.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnCreateAccount.setBackground(Color.GREEN);
		btnCreateAccount.setBounds(87, 200, 182, 25);
		contentPane.add(btnCreateAccount);
		
		
	}
}
