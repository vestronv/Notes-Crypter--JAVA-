package com.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import java.sql.*;
import java.util.Vector;

import com.sql.*;
import javax.swing.*;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListNotes extends JFrame {
	
	//################## THIS CLALSS USED TO LET USER SELECT JUST ONE ITEM OF JTABLE :D ##############################
	public class ForcedListSelectionModel extends DefaultListSelectionModel {
		
	    public ForcedListSelectionModel () {
	        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    }
	    
	    @Override
	    public void clearSelection() {
	    }
	    
	    @Override
	    public void removeSelectionInterval(int index0, int index1) {
	    }
	    
	}
	//################################################################################################################
	private JPanel contentPane;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListNotes frame = new ListNotes();
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
	public ListNotes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String query = "SELECT `TOPIC_NAME`, `TOPIC_MOOD`, `TOPIC_SUBJECT`, `TOPIC_DATE_TIME` FROM `notes` WHERE KEY='"
				+variables.secret_key+ "' ORDER BY `TOPIC_DATE_TIME` DESC;";
		Connection connection;
		try{
			connection = SQLConnector.dbConnector();
			PreparedStatement pst = connection.prepareStatement(query);
			
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			Vector info = new Vector();
			Vector columnNames = new Vector();
			int columnNumber = rsmd.getColumnCount();
			for(int i=1; i<=columnNumber; i++) {columnNames.addElement(rsmd.getColumnName(i));}
			
			while(rs.next()){
				Vector row = new Vector(columnNumber);
				for(int i=1; i<=columnNumber; i++){
					row.addElement(rs.getObject(i));
				}
				info.addElement(row);
			}
			rs.close();pst.close();
			
			final JTable table = new JTable(info,columnNames);
			table.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
			table.setForeground(Color.GREEN);
			table.setBackground(Color.DARK_GRAY);
			table.setRowHeight(20);
			
			table.setSelectionModel(new ForcedListSelectionModel());
			
			TableColumn column=new TableColumn();
			for(int i=0; i<table.getColumnCount(); i++){
				column = table.getColumnModel().getColumn(i);
				column.setWidth(300);
			}
			column.setResizable(true);
			JScrollPane scrollPane = new JScrollPane(table);//scrollPane.setPreferredSize(new Dimension(500,450));
			scrollPane.setBounds(20,50,620,500);
			scrollPane.setBackground(Color.DARK_GRAY);
			
			frame = new JFrame();
			frame.getContentPane().setBackground(Color.BLACK);
			Panel panel = new Panel();
			panel.setBackground(Color.BLACK);
			panel.setBounds(10, 40, 650, 550);
			panel.add(scrollPane);
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(panel);
			frame.setTitle("MY NOTES");
			frame.setSize(700, 600);
			frame.setVisible(true);
			
			JButton btnVNote = new JButton("VIEW");
			btnVNote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row_num = table.getSelectedRow();
					if(row_num<0)JOptionPane.showInternalMessageDialog(null, "Select a note.");
					else{
						String timeStamp = table.getValueAt(row_num, 3).toString();
						ShowNote sn = new ShowNote(timeStamp);
					}
				}
			});
			btnVNote.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
			btnVNote.setForeground(Color.RED);
			btnVNote.setBackground(Color.GREEN);
			btnVNote.setBounds(550, 550, 25, 25);
			panel.add(btnVNote);
			
			
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, ex);
		}
	}
}
