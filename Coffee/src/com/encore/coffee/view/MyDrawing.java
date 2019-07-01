package com.encore.coffee.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

 class MyCanvas extends Canvas {
	 
	 //ó���� ��� �� �������� �ϱ� ���ؼ� x,y -�� ����
	 int x=-50; int y=-50, w=7, h=7;
	 Color cr=Color.black;
	 
	 @Override
	 public void paint(Graphics g){
	  g.setColor(cr);
	  g.fillOval(x, y, w, h); // x, y ������ 70,70 ũ���� �� �׸���
	 }
	 
	 @Override
	 public void update(Graphics g){
	  paint(g);
	 }
	 
	}

 
public class MyDrawing extends JPanel {
	 
	 JPanel p1,p2;
	
	 Canvas can; // �θ�Ÿ��
	 public MyHandler my;
	 
	 public MyDrawing(){
	  //super("::MyDrawing::");
	  System.out.println("MyDrawing������");
		 
	  p1=new JPanel(); add(p1, "North");
	  p2=new JPanel(){ // �����ֱ�
	   public Insets getInsets(){
	    return new Insets(0,10,0,10);
	   }
	  }; add(p2, "Center");
	  p2.setBackground(Color.lightGray);
	  
	  can=new MyCanvas(); // ��ȭ�� ������ �ϴ� ������Ʈ MyCanvas�� can�� ��� �޴� �ڽ�->���� ���ڱ� ����
	  can.setSize(300, 300); // ��ȭ�� ũ��
	  can.setBackground(Color.white); // ��ȭ�� ���� �ֱ�
	  p2.add(can);
	  
	  //������ ���� -------------------
	   my=new MyHandler();
	  can.addMouseMotionListener(my); // ĵ���� ��ü�� ���콺��Ǹ����ʸ� �����Ѵ�.
	    
	  //�̹����� ���ο��� ���ϰ� �����ڿ��� �Ѵ�
	  setSize(500,500);
	  setVisible(true);
	 }
	 
	 
	 /*�̺�Ʈ�ҽ�: ĵ����
	  * �̺�Ʈ: MouseEvent
	  * �̺�Ʈ �ڵ鷯: MouseMotionListener�� ����
	  * */
	 
	 
	 public class MyHandler implements MouseMotionListener, ActionListener{
		ArrayList list = new ArrayList();//
	  
		public MyHandler() {
		   System.out.println("MyHandler������");
		}
		
		
	  public void mouseDragged(MouseEvent e){
	   
	   //���콺�� �巡���� ������ x��ǥ,y��ǥ�� ���ͼ� can�� x,y ��ǥ���� �����Ѵ�.
	   int xx=e.getX();  int yy=e.getY();
	   
	   ((MyCanvas)can).x=xx; ((MyCanvas)can).y=yy;
	   
	   //paint()�� JVM�� ȣ�����ִ� �޼ҵ����� ����x, repaint�� �Ἥ ��������
	   can.repaint();	   
	   list.add("sign");
	  }
	  
	  
	  public boolean checkSign() {
		  System.out.println("list size>>>"+ list.size());
		  if(list.size()==0) {
			  JOptionPane.showConfirmDialog(null, "�������ּ���!!");
		  }
		  return false;
	  }
	  
	  public void clearCanvas() {
		  list.clear();
	  }
	  
	  public void mouseMoved(MouseEvent e){
	  }
	  
	  public void actionPerformed(ActionEvent e){
	   Object o=e.getSource();
	   MyCanvas can2 = (MyCanvas)can;	 
	 }
	 }
	 
	 
	 public static void main(String[] args) {
	    new MyDrawing(); 
	 }
	
	 
	}
