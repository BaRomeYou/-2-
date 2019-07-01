package com.encore.coffee.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.encore.coffee.view.CardView;
import com.encore.coffee.view.CashView;
import com.encore.coffee.view.CoffeeJoin;
import com.encore.coffee.view.FindID;
import com.encore.coffee.view.LookID;
import com.encore.coffee.view.LookPass;
import com.encore.coffee.view.MemberUp;
import com.encore.coffee.view.MyDrawing;
import com.encore.coffee.view.coffeeloginView;
import com.encore.coffee.view.order;
import com.encore.coffee.view.sell;
import com.encore.coffee.view.sell_g;
import com.encore.coffee.view.update;

public class Controller implements ActionListener {
	
	CoffeeJoin joinView;
	coffeeloginView loginView;
	MemberUp memberUp;
	sell_g sell_g;
	sell sell;
	update adminUp;
	order odr;
	CardView cardView;
	CashView cashView;
	FindID findId;
	LookID lookId;
	LookPass lookPass;
	MyDrawing md;
	
	
	
	public Controller() {
		 
		joinView = new CoffeeJoin();
		loginView = new coffeeloginView();
		memberUp = new MemberUp();
		sell_g = new sell_g();
		sell = new sell();
		adminUp = new update();
		odr = new order();
		cardView = new CardView();
		md=cardView.md;
		cashView = new CashView();
		findId = new FindID();
		lookId = new LookID();
		lookPass = new LookPass();
		eventUp();
	}
	
	public void eventUp() {
		//CoffeeJoin
		joinView.bt_checkid.addActionListener(this);
		joinView.bt_reset.addActionListener(this);
		joinView.bt_submit.addActionListener(this);
		
		//coffeeloginView
		loginView.bt_findID.addActionListener(this);
		loginView.bt_join.addActionListener(this);
		loginView.bt_login.addActionListener(this);
		
		//MemberUp();
		memberUp.bt_checkid.addActionListener(this);
		memberUp.bt_reset.addActionListener(this);
		memberUp.bt_submit.addActionListener(this);
		
		//sell_g
		
		//sell
		System.out.println("sell>>>"+sell);
		System.out.println("btn>>>"+sell.btn);
		//sell.btn.addActionListener(this);
		sell.btnDw.addActionListener(this);
		sell.btnGr.addActionListener(this);
		
		//update
		adminUp.btnNewButton.addActionListener(this);
		adminUp.btnNewButton_1.addActionListener(this);
		
		//order
		
		odr.btnNewButton_2.addActionListener(this);
		odr.btnNewButton_3.addActionListener(this);
		odr.button.addActionListener(this);
		odr.button_1.addActionListener(this);
		odr.button_10.addActionListener(this);
		odr.button_11.addActionListener(this);
		odr.button_12.addActionListener(this);
		odr.button_13.addActionListener(this);
		odr.button_14.addActionListener(this);
		odr.button_15.addActionListener(this);
		odr.button_16.addActionListener(this);
		odr.button_17.addActionListener(this);
		odr.button_18.addActionListener(this);
		odr.button_19.addActionListener(this);
		odr.button_20.addActionListener(this);
		odr.button_2.addActionListener(this);
		odr.button_21.addActionListener(this);
		odr.button_22.addActionListener(this);
		odr.button_23.addActionListener(this);
		odr.button_3.addActionListener(this);
		odr.button_4.addActionListener(this);
		odr.button_5.addActionListener(this);
		odr.button_6.addActionListener(this);
		odr.button_7.addActionListener(this);
		odr.button_8.addActionListener(this);
		odr.button_9.addActionListener(this);
		
		//cardView
		cardView.btn_cancel.addActionListener(this);
		cardView.btn_confirm.addActionListener(this);
		
		//cashView
		cashView.btn_cancel.addActionListener(this);
		cashView.btn_confirm.addActionListener(this);
		
		//findId
		findId.bt_close.addActionListener(this);
		findId.bt_findID.addActionListener(this);
		findId.bt_findPass.addActionListener(this);
		
		//lookId
		lookId.bt_back.addActionListener(this);
		lookPass.bt_find.addActionListener(this);
		
		//lookPass
		lookPass.bt_back.addActionListener(this);
		lookPass.bt_find.addActionListener(this);
	}
	
	//로그인 버튼눌러서 회원정보가 확인되어서 order뷰가 뜰때 pop창에 생성되야할 클래스
	class Pop extends Thread{
		 public void run() {
			 int num=1;
			 int P=0;
			 Calendar cal=Calendar.getInstance();
			 String sec=null;
			 String[] picture= {"event1","event2","event3"};
			 
			 while(true) {
				 
				 try {
					sec=(cal.get(Calendar.SECOND)<10?"0":"")+cal.get(Calendar.SECOND);
					 
					 sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 num++;
				 
				 if(num%5==0) {
					 if(P<5) {
						 P++;
						 
						 if(P==5) {
							 P=0;
							 
						 }
						 
						 odr.lblNewLabel_1.setIcon(new ImageIcon(order.class.getResource("../images/"+picture[P]+".jpg")));
					 }
					 
				 }
				 
				 
			 }
			 
		 }
		
		
		
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
	      if(obj==loginView.bt_join) {//회원가입 버튼 클릭
	         
	         
	      }else if(obj==loginView.bt_login) {//로그인 버튼 클릭
	         String id = loginView.tf_id.getText();
	         String pass = new String(loginView.tf_pass.getPassword());
	      }else if(obj==loginView.bt_findID) {//아이디, 비밀번호 찾기
	         
	      }else if(obj==joinView.bt_checkid) {//중복확인
	         
	      }else if(obj==joinView.bt_reset) {//취소
	         
	      }else if(obj==joinView.bt_submit) {//회원가입등록
	         
	      }else if(obj==memberUp.bt_checkid) {//회원정보수정(중복확인)
	         
	      }else if(obj==memberUp.bt_reset) {//회원정보수정(취소)
	         
	      }else if(obj==memberUp.bt_submit) {//회원정보수정(등록)
	         
	      }else if(obj==adminUp.btnNewButton) {//수정(확인)
	         
	      }else if(obj==adminUp.btnNewButton_1) {//수정(취소)
	    	
	    	  int row = odr.table.getSelectedRow();
	    	  if(row==-1) { 
	    		  JOptionPane.showMessageDialog(odr, "삭제할 아이템을 선택하세요" );
	    		  return; 
	    		  } else {
	    	  DefaultTableModel model = (DefaultTableModel) odr.table.getModel();
	    	  model.removeRow(row);
	    		  }
	    	  
	         
	      }else if(obj==odr.button) {
	         
	      }else if(obj==odr.button_1) {
	         
	      }else if(obj==odr.button_2) {
	         
	      }else if(obj==odr.button_3) {
	         
	      }else if(obj==odr.button_4) {
	         
	      }else if(obj==odr.button_5) {
	         
	      }else if(obj==odr.button_6) {
	         
	      }else if(obj==odr.button_7) {
	         
	      }else if(obj==odr.button_8) {
	         
	      }else if(obj==odr.button_9) {
	         
	      }else if(obj==odr.button_10) {
	         
	      }else if(obj==odr.button_11) {
	         
	      }else if(obj==odr.button_12) {
	         
	      }else if(obj==odr.button_13) {
	         
	      }else if(obj==odr.button_14) {
	         
	      }else if(obj==odr.button_15) {
	         
	      }else if(obj==odr.button_16) {
	         
	      }else if(obj==odr.button_17) {
	         
	      }else if(obj==odr.button_18) {
	    	  JOptionPane.showConfirmDialog(odr, "모두 취소하시겠습니까?");
	    	  DefaultTableModel model = (DefaultTableModel) odr.table.getModel();
	    	  model.setRowCount(0);
	         
	      }else if(obj==odr.button_19) {
	         
	      }else if(obj==odr.button_20) {
	         
	      }else if(obj==odr.btnNewButton_2) {
	         
	      }else if(obj==odr.button_21) {
	         
	      }else if(obj==odr.button_22) {
	         
	      }else if(obj==odr.button_23) {
	         
	      }else if(obj==cardView.btn_confirm) {
	    	 
	    	  md.my.checkSign();
	    	  
	    	 
	    	  
	    	 
	    	 
	      }else if(obj==cardView.btn_cancel) {
	         
	      }else if(obj==cashView.btn_confirm) {//현금결제 확인버튼
	    	  
	      }else if(obj==cashView.btn_cancel) {//현금결제 취소버튼
	    	  
	      }else if(obj==findId.bt_findID) {//아이디비밀번호 찾기(아이디버튼)
	    	  
	      }else if(obj==findId.bt_findPass) {//아이디비밀번호 찾기(비밀번호버튼)
	    	  
	      }else if(obj==findId.bt_close) {//아이디비밀번호 찾기(닫기)
	    	  
	      }else if(obj==lookId.bt_find) {//아이디로 찾기(찾기버튼)
	    	  
	      }else if(obj==lookId.bt_back) {//아이디로 찾기(되돌아가기)
	    	  
	      }else if(obj==lookPass.bt_find) {//비밀번호로 찾기(찾기버튼)
	    	  
	      }else if(obj==lookPass.bt_back) {//비밀번호로 찾기(되돌아가기)
	    	  
	      }

		
		
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	
}
