package com.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWin {

	private JFrame frame;
	public static String secret_key;//I need it bcoz it works as Primary Key.
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin window = new MainWin();
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
	public MainWin() {
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
		
		JButton btnCreateAccount = new JButton("CREATE ACCOUNT");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MakeAccount ma = new MakeAccount();
				ma.setVisible(true);//account created and secret_key copied to global secret_key
				
			}
		});
		btnCreateAccount.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnCreateAccount.setForeground(Color.RED);
		btnCreateAccount.setBackground(Color.GREEN);
		btnCreateAccount.setBounds(133, 72, 190, 25);
		frame.getContentPane().add(btnCreateAccount);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginWin lw = new LoginWin();
				frame.dispose();
			}
		});
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnLogin.setForeground(Color.RED);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setBounds(167, 151, 117, 25);
		frame.getContentPane().add(btnLogin);
	}
}
