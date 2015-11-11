package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import java.awt.SystemColor;
import javax.swing.JTable;
import java.sql.*;
import com.sql.*;
import java.util.*;
import javax.swing.*;
import java.awt.Font;

public class UserInfo extends JFrame {

	private JPanel contentPane;
	private JPanel contentPane_1;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfo frame = new UserInfo();
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
	public UserInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		try{
			Connection connection;
			connection = SQLConnector.dbConnector();
			//JOptionPane.showMessageDialog(null, variables.secret_key);
			
			String query1 = "SELECT * FROM `user_info` WHERE KEY='"+variables.secret_key+"' "+";";
			String query2 = "SELECT `SUBJECT` FROM `user_subjects` WHERE KEY='"+variables.secret_key+"' "+";";
			
			PreparedStatement pst1 = connection.prepareStatement(query1);
			PreparedStatement pst2 = connection.prepareStatement(query2);
			
			ResultSet rs1 = pst1.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
			
			ResultSetMetaData rsmd1 = rs1.getMetaData();
			ResultSetMetaData rsmd2 = rs2.getMetaData();
			
			Vector info = new Vector();
			Vector subjects = new Vector();
			
			Vector columnNames1 = new Vector();
			Vector columnNames2 = new Vector();
			
			int columnNumber1 = rsmd1.getColumnCount();
			for(int i=1; i<=columnNumber1; i++) {columnNames1.addElement(rsmd1.getColumnName(i));}
			int columnNumber2 = rsmd2.getColumnCount();
			for(int i=1; i<=columnNumber2; i++) {columnNames2.addElement(rsmd2.getColumnName(i));}
			
			while(rs1.next()){
				Vector row = new Vector(columnNumber1);
				for(int i=1; i<=columnNumber1; i++){
					row.addElement(rs1.getObject(i));
				}
				info.addElement(row);
			}
			
			while(rs2.next()){
				Vector row = new Vector(columnNumber2);
				for(int i=1; i<=columnNumber2; i++){
					row.addElement(rs2.getObject(i));
				}
				subjects.addElement(row);
			}
			rs1.close();rs2.close();pst1.close();pst2.close();connection.close();
			
			//for(int i=0;i<subjects.size();i++)System.out.println("->"+subjects.get(i));
			
			
			JTable table1 = new JTable(info,columnNames1);
			table1.setEnabled(false);
			table1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			table1.setForeground(Color.GREEN);
			table1.setBackground(Color.BLACK);
			table1.setRowHeight(20);
			
			JTable table2 = new JTable(subjects,columnNames2);
			table2.setEnabled(false);
			table2.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			table2.setForeground(Color.GREEN);
			table2.setBackground(Color.BLACK);
			table2.setRowHeight(20);
			
			TableColumn column=new TableColumn();
			
			for(int i=0; i<table1.getColumnCount(); i++){
				column = table1.getColumnModel().getColumn(i);
				column.setWidth(200);
			}
			for(int i=0; i<table2.getColumnCount(); i++){
				column = table2.getColumnModel().getColumn(i);
				column.setWidth(200);
			}
			column.setResizable(true);
			JScrollPane scrollPane1 = new JScrollPane(table1);scrollPane1.setPreferredSize(new Dimension(450,50));
			scrollPane1.setBackground(Color.BLACK);
			JScrollPane scrollPane2 = new JScrollPane(table2);scrollPane2.setPreferredSize(new Dimension(450,150));
			scrollPane2.setBackground(Color.BLACK);
			contentPane_1 = new JPanel();
			contentPane_1.setForeground(Color.GREEN);
			contentPane_1.setBackground(Color.BLUE);
			contentPane_1.add(scrollPane1);
			contentPane_1.add(scrollPane2);
			frame = new JFrame();
			frame.getContentPane().add(contentPane_1);
			frame.setSize(500, 350);
			frame.setVisible(true);
			frame.setTitle("USER INFO");
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
