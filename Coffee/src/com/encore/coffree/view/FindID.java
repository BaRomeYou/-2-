package com.encore.coffree.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;

public class FindID extends JFrame{

	public JButton bt_findID, bt_findPass, bt_close;

	public FindID() {
		getContentPane().setBackground(Color.WHITE);
		setTitle("���̵���ã��");

		bt_findPass = new JButton("��й�ȣã��");
		bt_findPass.setForeground(Color.BLACK);
		bt_findPass.setFont(new Font("����", Font.BOLD, 15));
		bt_findPass.setBackground(Color.MAGENTA);
		
		bt_findID = new JButton("���̵�ã��");
		bt_findID.setForeground(Color.BLACK);
		bt_findID.setFont(new Font("����", Font.BOLD, 15));
		bt_findID.setBackground(Color.MAGENTA);

		bt_findPass.setBounds(91,116,154,69);
		bt_findID.setBounds(308,116,163,69);
		setBounds(400,300,559,354);

		getContentPane().setLayout(null);
		
		getContentPane().add(bt_findPass);
		getContentPane().add(bt_findID);
		
		JButton bt_close = new JButton("�ݱ�");
		bt_close.setFont(new Font("����", Font.PLAIN, 15));
		bt_close.setBackground(Color.RED);
		bt_close.setForeground(Color.WHITE);
		bt_close.setBounds(452, 266, 75, 27);
		getContentPane().add(bt_close);

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}// ������
	 
	 public static void main(String[] args) {
		new FindID();
	}
}// FindID

