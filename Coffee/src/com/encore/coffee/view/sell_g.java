package com.encore.coffee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorListener;

import com.encore.coffee.model.CoffeeDAO;

import javax.swing.event.AncestorEvent;

/*
 class gg extends JPanel{
      int ham[]=new int[6];
       
       public void paint(Graphics g) {
         
            g.clearRect(0, 0, getWidth(), getHeight());
            g.drawLine(50, 250, 700, 250);
            for (int i = 0;  i< ham.length; i++) {
               g.drawString(i*10+"", 25,255-30*i);
               g.drawLine(50, 250-30*i, 700, 250-30*i);
            }
            g.drawLine(50, 70, 50, 250);
         }
}

*/
public class sell_g  {
   public CoffeeDAO dao;
   public   JButton button;
   public   JFrame frame;
   public  DrawingPanel drawingPanel;
   
   public Container contentPane;
   public JPanel controlPanel;
   sell sell;
   public int ame,espre,  cara, capu, latte, dolche;
  
   
   public sell_g() {
      frame = new JFrame("�Ǹ� ��ǰ ���� ��Ʈ");
        frame.setLocation(500,200);
        frame.setPreferredSize(new Dimension(1200,350));
         contentPane = frame.getContentPane();
        
         drawingPanel = new DrawingPanel();
        contentPane.add(drawingPanel, BorderLayout.CENTER);
        //�׷����� �׸� �г�
        
           controlPanel = new JPanel();
         dao=new CoffeeDAO();
         ArrayList<Integer> list = dao.coffee_g();
        
         this.ame=list.get(0);
         this.espre=list.get(1);
         this.cara=list.get(2);
         this.capu=list.get(3);
         this.latte=list.get(4);
         //this.dolche=arr[5];
         
         
         
        
         button = new JButton("Ŀ���Ѱ� �׸���");
        sell=new sell();
        
    //    arr=new int[6];
        
        
        controlPanel.add(button);
        contentPane.add(controlPanel, BorderLayout.SOUTH);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
           
           @Override
         public void windowClosing(WindowEvent e) {
            frame.setVisible(true);
            sell.setVisible(true);
         }
        });
        
      
        
        
        
        
        button.addActionListener(new DrawActionListener(ame,espre,  cara, capu, latte, dolche,drawingPanel));//�Ķ���ͷ� Ŀ���Ѱ踦 ��������
        //"�׷��� �׸���" ��ư�� �������� �۵� �� �����͵��                         // ���� int ame, int espre, int cara,int capu,int latte,int dolche ���� 
      
     
        frame.pack();
        frame.setVisible(false);
   }
   
   
   public static void main(String args[])
    {
      new sell_g();
      
      
    }

   
   public void threwItem(int ame2, int espre2, int cara2, int capu2, int latte2, int dolche2) {
      this.ame=ame2;
        this.espre=espre2;
        this.cara=cara2; 
        this.capu=capu2; 
       this.latte=latte2;  
       this.dolche=dolche2;
      
   }
}
   /**
    * Create the frame.
    */
   /*
   public sell_g() {
      

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 746, 429);
      contentPane = new JPanel();
      contentPane.setBorder(null);
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      
      JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
      tabbedPane.setBounds(14, 24, 710, 325);
      tabbedPane.add("����",p2=new gg());
      
      p2.setLayout(null);
      
      
      tabbedPane.add("����Ŀ��",p3=new gg());
      contentPane.add(tabbedPane);
      tabbedPane.add("Ŀ��",pannel =new gg());
      pannel.setToolTipText("");
      pannel.setLayout(null);
      
      
   }
   
   */
   
   
   
   class DrawingPanel extends JPanel
   {
   int ame,  espre, cara, capu, latte, dolche ;
    public void paint(Graphics g){
    g.clearRect(0,0,getWidth(),getHeight());
    g.drawLine(50,250,1100,250);
    for(int cnt = 1 ;cnt<11;cnt++)
    {
     g.drawString(cnt *10 +"",25,255-20*cnt);
     g.drawLine(50, 250-20*cnt, 1100,250-20*cnt);
    }
    g.drawLine(50,20,50,250);
    g.drawString("�Ƹ޸�ī��",100,270);
    g.drawString("����������",200,270);
    g.drawString("ī����",300,270);
    g.drawString("īǪġ��", 400, 270);
    g.drawString("ī���", 500, 270);
    g.drawString("��ü��", 600, 270);
    g.setColor(Color.RED);
    if (ame>0)
     g.fillRect(110,250-ame*2,10,ame*2);
    if(espre>0)
     g.fillRect(210,250-espre*2,10,espre*2);
    if(cara>0)
     g.fillRect(310,250-cara*2,10,cara*2);
    if(capu>0)
    g.fillRect(410,250-capu*2,10,capu*2);
    if(latte>0)
    g.fillRect(510,250-latte*2,10,latte*2);
    if(dolche>0)
    g.fillRect(610,250-dolche*2,10,dolche*2);
    }
    void setScores(int ame, int espre, int cara,int capu,int latte,int dolche)
    {
       this.ame=ame;
        this.espre=espre;
        this.cara=cara;
        this.capu=capu;
        this.latte=latte;
        this.dolche=dolche;
    }
    
    
    
    
    
    
    
   }
   
   
   class DrawActionListener implements ActionListener
   {
    int ame,espre,cara,capu,latte,dolche;
    DrawingPanel drawingPanel;
    DrawActionListener(int ame, int espre, int cara,int capu ,int latte,int dolche ,DrawingPanel drawingPanel)
    {
     this.ame=ame;
     this.espre=espre;
     this.cara=cara;
     this.capu=capu;
     this.latte=latte;
     this.dolche=dolche;
     
     this.drawingPanel = drawingPanel;
    }
    public void actionPerformed(ActionEvent e)
    {
     try
     {
   
      drawingPanel.setScores(ame, espre, cara,capu,latte,dolche);
      drawingPanel.repaint();
     }
     catch (NumberFormatException nfe){
      //JOptionPane.showMessageDialog(drawingPanel,"�߸��� ���� �Է��Դϴ�","�����޽���",JOptionPane.ERROR_MESSAGE);
     }
    }
   }
   
   
   
