package com.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import com.sql.*;
import javax.swing.JLabel;
import com.crypter.*;

public class CreateNote extends JFrame {

	private JPanel contentPane;
	private JTextField topic_nameTF;
	private JFrame frame;
	String topic_name,mood="ROBOT",topic_subject="mixed";
	private JTextField subjectTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNote frame = new CreateNote();
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
	public CreateNote() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		//JOptionPane.showMessageDialog(null, System.currentTimeMillis());
		frame = new JFrame();
		frame.getContentPane().add(contentPane);
		frame.setSize(650, 450);
		frame.setVisible(true);

		String[] moods = new String[]{"ROBOT","GOOD","AWESOME","BAD","F*UP"};
		final JComboBox moodCB = new JComboBox(moods);
		moodCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mood = moodCB.getSelectedItem().toString();
			}
		});
		moodCB.setBounds(191, 2, 68, 24);
		contentPane.add(moodCB);
		
		topic_nameTF = new JTextField();
		topic_nameTF.setBounds(28, 2, 114, 22);
		contentPane.add(topic_nameTF);
		topic_nameTF.setColumns(10);
		
		JButton btnAddImage = new JButton("ADD IMAGE");
		btnAddImage.setBounds(50, 38, 117, 25);
		contentPane.add(btnAddImage);
		
		subjectTF = new JTextField();
		subjectTF.setBounds(354, 41, 84, 19);
		contentPane.add(subjectTF);
		subjectTF.setColumns(10);
		
		JLabel lblSubject = new JLabel("SUBJECT");
		lblSubject.setBounds(260, 58, 70, 15);
		contentPane.add(lblSubject);
		
		final JEditorPane editorPane = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane(editorPane);
		editorPane.setContentType("text/html");
		scrollPane.setBounds(50, 100, 400, 150);
		contentPane.add(scrollPane);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topic_name = topic_nameTF.getText();
				mood = moodCB.getSelectedItem().toString();
				//JOptionPane.showMessageDialog(null, topic_name+" "+editorPane.getDocument().getLength()+" "
				//+variables.secret_key);
				if(topic_name.length()>1 && editorPane.getDocument().getLength()>1 && variables.secret_key.length()>1){
					String topic_text="";
					try{
						topic_text = editorPane.getDocument().getText(0, editorPane.getDocument().getLength());
						topic_text = EnDecryptMsg.crypt(topic_text);
					}
					catch(Exception brr){
						JOptionPane.showMessageDialog(null, "Something FISHY sorry for trouble.");
					}
					try{
					Connection  connection;
					connection = SQLConnector.dbConnector();
					String query = "INSERT INTO `notes` values (?, ?, ?, ?, ?, ?);";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, variables.secret_key);
					pst.setString(2, topic_name);
					pst.setString(3, topic_text);
					String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
					pst.setString(4, timeStamp);
					pst.setString(5, mood);
					try{
						if(subjectTF.getText().length()<2)topic_subject = "mixed";
						else {
							topic_subject = subjectTF.getText();
							String query1 = "INSERT INTO `user_subjects` VALUES(?, ?);";
							PreparedStatement pst1 = connection.prepareStatement(query1);
							pst1.setString(1, variables.secret_key);
							pst1.setString(2, topic_subject);
							pst1.executeUpdate();
						}
					}
					catch(Exception bekaar){
						//JOptionPane.showMessageDialog(null, "Subject length Should be > 1.");
					}
					pst.setString(6, topic_subject);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Note Saved.");
					frame.dispose();
					}
					catch(Exception ex){
						JOptionPane.showMessageDialog(null, "Some DB Error."+ex);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "TOPIC NAME AND TEXT LENGTH SHOULD BE > 1");
				}
			}
		});
		btnSave.setBounds(328, 2, 84, 25);
		contentPane.add(btnSave);
		
		//String[] subjects = new String[]{};
		Vector subjects = new Vector();
		String query2 = "SELECT `SUBJECT` FROM `user_subjects` WHERE KEY='"+variables.secret_key+"' "+";";
		try{
			Connection connection;
			connection = SQLConnector.dbConnector();
			PreparedStatement pst2 = connection.prepareStatement(query2);
			ResultSet rs2 = pst2.executeQuery();
			ResultSetMetaData rsmd2 = rs2.getMetaData();
			int columnNumber2 = rsmd2.getColumnCount();
			while(rs2.next()){
				Vector row = new Vector(columnNumber2);
				for(int i=1; i<=columnNumber2; i++){
					row.addElement(rs2.getObject(i));
				}
				subjects.addElement(row);
			}
			
		}
		catch(Exception subjectss){JOptionPane.showMessageDialog(null, subjectss);}
		final JComboBox subjectsCB = new JComboBox(subjects);
		subjectsCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topic_subject = subjectsCB.getSelectedItem().toString();
				int len = topic_subject.length();
				topic_subject = topic_subject.substring(1, len-1);
				subjectTF.setText(topic_subject);
			}
		});
		subjectsCB.setBounds(476, 65, 68, 24);
		contentPane.add(subjectsCB);
	
		
	}
}
