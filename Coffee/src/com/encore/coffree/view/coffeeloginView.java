package com.encore.coffree.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class coffeeloginView extends JFrame{
	public JTextField tf_id;
	public JPasswordField tf_pass;
	public JLabel la_id, la_pass;
	
	public JButton bt_login, bt_join, bt_findID;

	public coffeeloginView() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("�α�����");

		tf_id = new JTextField();
		tf_pass = new JPasswordField();
		bt_login = new JButton("�α���");
		bt_login.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		bt_login.setBackground(new Color(255, 255, 255));
		bt_join = new JButton("ȸ������");
		bt_join.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		bt_join.setBackground(Color.WHITE);
		bt_findID = new JButton("���̵�/���ã��");
		bt_findID.setBackground(Color.WHITE);
		bt_findID.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));

		la_id = new JLabel("I D");
		la_id.setForeground(Color.RED);
		la_id.setHorizontalAlignment(SwingConstants.CENTER);
		la_id.setFont(new Font("����", Font.BOLD, 20));
		la_pass = new JLabel("Password");
		la_pass.setForeground(Color.RED);
		la_pass.setHorizontalAlignment(SwingConstants.CENTER);
		la_pass.setFont(new Font("����", Font.BOLD, 20));
		
		tf_id.setBounds(278,42,218,77);
		tf_pass.setBounds(278,131,218,72);
		bt_login.setBounds(62,236,104,49);
		bt_join.setBounds(186,236,126,49);
		bt_findID.setBounds(335,236,169,49);
		la_id.setBounds(61,47,181,67);
		la_pass.setBounds(61,136,172,67);
		
		
		setBounds(400,300,559,354);

		getContentPane().setLayout(null);
		getContentPane().add(tf_id);
		getContentPane().add(tf_pass);
		getContentPane().add(la_id);
		getContentPane().add(la_pass);
		getContentPane().add(bt_login);
		getContentPane().add(bt_join);
		getContentPane().add(bt_findID);

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}// ������

	 public void showMsg(String msg) {
		   JOptionPane.showMessageDialog(this, msg);
	    }//showMsg

	 public void initText() {
	    	tf_id.setText("");
	    	tf_pass.setText("");
	    	tf_id.requestFocus();
	    }
	 
	 public static void main(String[] args) {
		new coffeeloginView();
	}
}// LoginForm

