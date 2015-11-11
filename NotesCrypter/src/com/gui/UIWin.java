package com.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import com.sql.*;

public class UIWin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIWin window = new UIWin();
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
	public UIWin() {
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
		
		JButton btnSeeDetails = new JButton("SEE DETAILS");
		btnSeeDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfo ui = new UserInfo();
				
			}
		});
		btnSeeDetails.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnSeeDetails.setForeground(Color.RED);
		btnSeeDetails.setBackground(Color.GREEN);
		btnSeeDetails.setBounds(141, 59, 144, 25);
		frame.getContentPane().add(btnSeeDetails);
		
		JButton btnStartTopic = new JButton("START TOPIC");
		btnStartTopic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateNote cn = new CreateNote();
			}
		});
		btnStartTopic.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnStartTopic.setForeground(Color.RED);
		btnStartTopic.setBackground(Color.GREEN);
		btnStartTopic.setBounds(141, 138, 144, 25);
		frame.getContentPane().add(btnStartTopic);
		
		JButton btnListAllTopics = new JButton("LIST ALL TOPICS");
		btnListAllTopics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListNotes ln = new ListNotes();
			}
		});
		btnListAllTopics.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnListAllTopics.setBackground(Color.GREEN);
		btnListAllTopics.setForeground(Color.RED);
		btnListAllTopics.setBounds(130, 194, 178, 25);
		frame.getContentPane().add(btnListAllTopics);
		
		
	}

}
