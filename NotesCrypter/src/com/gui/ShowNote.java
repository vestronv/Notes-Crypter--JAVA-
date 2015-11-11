package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.ScrollPane;
import java.awt.Panel;
import java.sql.*;
import com.sql.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.crypter.*;

public class ShowNote extends JFrame {

	private JPanel contentPane;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ShowNote frame = new ShowNote();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowNote(String timeStamp) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String query = "SELECT `TOPIC_TEXT` FROM `notes` WHERE KEY='"+variables.secret_key+"' AND `TOPIC_DATE_TIME`='"+
		timeStamp+"';";
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBackground(Color.DARK_GRAY);
		editorPane.setForeground(Color.GREEN);
		JScrollPane scrollPane = new JScrollPane(editorPane);scrollPane.setPreferredSize(new Dimension(500,450));
		try{
			Connection connection;
			connection = SQLConnector.dbConnector();
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			String cipher = "";
			while(rs.next()){
				cipher += rs.getObject(1).toString();
			}
			editorPane.setText(EnDecryptMsg.decrypt(cipher));
		}
		catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
		
		Panel panel = new Panel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 57, 600, 520);
		panel.add(scrollPane);
		
		frame = new JFrame();
		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.setSize(650, 600);
	}
}
