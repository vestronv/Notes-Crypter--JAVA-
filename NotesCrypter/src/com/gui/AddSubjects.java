package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import com.sql.*;

public class AddSubjects extends JFrame {

	private JPanel contentPane;
	private JTextField subjectTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSubjects frame = new AddSubjects();
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
	public AddSubjects() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddYourNotes = new JLabel("ADD YOUR NOTES SUBJECTS");
		lblAddYourNotes.setBounds(91, 12, 263, 15);
		contentPane.add(lblAddYourNotes);
		
		JEditorPane dtrpnNddnf = new JEditorPane();
		dtrpnNddnf.setEditable(false);
		dtrpnNddnf.setText("nddnf"
				+ "sdfjsjfs\n\nflkfl"
				+ "jdkfsdnfjnsdjkfnsfsnk");
		dtrpnNddnf.setBounds(52, 39, 302, 109);
		contentPane.add(dtrpnNddnf);
		
		JLabel lblSubject = new JLabel("SUBJECT NAME : ");
		lblSubject.setBounds(52, 187, 113, 15);
		contentPane.add(lblSubject);
		
		subjectTF = new JTextField();
		subjectTF.setBounds(195, 185, 114, 19);
		contentPane.add(subjectTF);
		subjectTF.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(subjectTF.getText().length()>1){
					try{
						Connection connection;
						connection = SQLConnector.dbConnector();
						String query = "INSERT INTO `user_subjects` values (?, ?);";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, variables.secret_key);
						pst.setString(2, subjectTF.getText());
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Subject "+subjectTF.getText()+" added.");
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, ex);
					}
				}
			}
		});
		btnAdd.setBounds(321, 182, 77, 25);
		contentPane.add(btnAdd);
	}
}
