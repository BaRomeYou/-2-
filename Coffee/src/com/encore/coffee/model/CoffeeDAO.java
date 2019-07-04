package com.encore.coffee.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


public class CoffeeDAO {//ȸ�� ���� �α��� 

   Connection conn;
   // Statement stmt;
   PreparedStatement stmt;
   ResultSet rs;

   Properties pro; // DB���Ӱ��� ���� ���� ��ü
   
   ArrayList<Object> list;

   public CoffeeDAO() {
      try {
         pro = new Properties(); // �Ӽ� ��� ��ü
         pro.load(new FileReader("conn/conn.properties"));
         // �Ӽ� driver, url,user,password
         Class.forName(pro.getProperty("driver"));
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   } // ������

   private void connect() {
      try {
         conn = DriverManager.getConnection(pro.getProperty("url"), pro);
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void disconnect() {
      try {
         if (conn != null)
            conn.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public boolean findLogin(String id, String pass) { //�α���
         connect();

      try {
         String sql = "select count(*) from member where id=? and pwd=?";
         stmt = conn.prepareStatement(sql); // DB�� sql�� ����
         stmt.setString(1, id);
         stmt.setString(2, pass); // ����ǥ ����ŭ ����
         // rs = stmt.executeQuery(sql);
         
         rs = stmt.executeQuery(); // �̹� ������ sql �� ����, �ߺ� ���� X
         if (rs.next()) { // ������ 1�� �����ϱ� ������ ���������� if�ȿ� �־��ִ� ���� ����
            // rs.getInt("count(*)") ==> Error �Լ����� �÷������� ������� ����
            // rs.getInt("cnt") ==> O <- ���� ���� ����
            memberVO vo = new memberVO();
            int cnt = rs.getInt(1);
            if (cnt > 0)
               return true;
               
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         disconnect();
      }
      
   return false;
   
}
   public Boolean create(memberVO vo) { //ȸ������  
      
		connect();
		
		
		try {
			String sql = "insert into member (id,no, name, pwd, birth, gender, phone, email) values(?,member_seq.nextval,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,vo.getId());
			stmt.setString(2,vo.getName());
			stmt.setString(3,vo.getPwd());
			stmt.setString(4,vo.getBirth());
			stmt.setString(5,vo.getGender());
			stmt.setString(6,vo.getPhone());
			stmt.setString(7,vo.getMail());
			stmt.executeUpdate();
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
			return false;
   }
   

   public int check_id (String id) { //id�ߺ�Ȯ�� 
      connect();
      int cnt=0;
      
      try {
         String sql = "select count(*) cnt from member where id=?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, id);
         rs = stmt.executeQuery();
         if(rs.next()) {
            cnt = rs.getInt("cnt");
         
         }
                  
         
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         disconnect();
      }
      
      return cnt;
   }
   
   public String find_id(String email) { //���̵� ã�� 
      connect();
      
      String id = "";
      
      try {
         String sql = "select id from member where email=?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, email);
         rs = stmt.executeQuery();
         if(rs.next()) {
             id = rs.getString("id");
                   
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         disconnect();
      }
      return id;
      
   } 
   
   public String find_pass(String id, String email) { // �н����� ã��  
      connect();
      String pass="";
   
      
      try {
         String sql = "select pwd from member where id=? and email=?";
         stmt = conn.prepareStatement(sql);
         stmt.setString(1,id);
         stmt.setString(2, email);
         rs = stmt.executeQuery();
         if(rs.next()) {
            pass = rs.getString("pwd");
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         disconnect();
      }
         
      return pass;
   }

   public boolean cancel_all(Collection c) {
       boolean modified = false;
       Iterator it = list.iterator();
       while (it.hasNext()) {
           if (c.contains(it.next())) {
               it.remove();
               modified = true;
           }
       }
       return modified;
   }//cancel_all

   
   public boolean member_up(memberVO vo) { //ȸ�� �������� 
	      connect();
	      
	      try {
	         String sql = "update member set pwd=?, birth=?, phone=?, email=?, gender=?"
	                       + "where id=?";
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, vo.getPwd());
	         stmt.setString(2, vo.getBirth());
	         stmt.setString(3, vo.getPhone());         
	         stmt.setString(4, vo.getMail());
	         stmt.setString(5, vo.getGender());
	         stmt.setString(6, vo.getId());
	         int t = stmt.executeUpdate();
	         
	         if(t==1)
	            return true;
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      
	      return false;       
	         
	   }

   
   public ArrayList<memberVO> findSearch(Map<String, String> map){ //������ â -> ȸ������ �������� (�޺��ڽ� ����) 
	      connect();
	     
	      ArrayList<memberVO> list = new ArrayList<memberVO>();
	      memberVO vo = null; 
	      try {
	      String sql = "select id, no, name, pwd, birth, gender, phone, email, freq from member ";
	      
	      String title = map.get("title");
	      String keyword = map.get("keyword");
	      
	      
	      if(title.equals("��ȣ"))
	         sql += "where no like ?";
	      else if(title.equals("�̸�"))
	         sql += "where name like ?";
	      else if (title.equals("���̵�"))
	         sql += "where id like ?";
	      
	      else if (title.equals("��й�ȣ"))
	         sql += "where pwd like ?";
	      else if (title.equals("����"))
	         sql += "where birth like ?";
	      else if (title.equals("����"))
	            sql += "where gender like ?";
	      else if (title.equals("��ȭ��ȣ"))
	         sql += "where phone like ?";
	      else if (title.equals("����"))
	         sql += "where email like ?";
	   
	         stmt = conn.prepareStatement(sql);
	         stmt.setString(1, "%"+keyword+"%");
	         rs = stmt.executeQuery();
	         if(rs.next()) {
	            vo =  new memberVO();
	            vo.setId(rs.getString("id"));
	            vo.setNo(rs.getInt("no"));
	            vo.setName(rs.getString("name"));
	            vo.setPwd(rs.getString("pwd"));
	            vo.setBirth(rs.getString("birth"));
	            vo.setGender(rs.getString("gender"));
	            vo.setPhone(rs.getString("phone"));
	            vo.setMail(rs.getString("email"));
	            vo.setFreq(rs.getInt("freq"));
	             list.add(vo);
	         }
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } finally {
	         disconnect();
	      }
	      return list; 
	   }

   
   public ArrayList<productVO> findProductSearch(Map<String,String> map) {
         connect();
   
         ArrayList<productVO> list = new ArrayList<productVO>();
         productVO vo = null;
         try {      
         String sql = "select menuId, menu, price from menu ";
         String title = map.get("title");
         String keyword = map.get("keyword");
         
         
         if(title.equals("Ŀ��"))
            sql += "where menuId like ?";  
         else if (title.equals("����"))
            sql += "where menuId like ?";     
         else if (title.equals("����Ŀ��"))
            sql += "where menuId like ?";
                  
         stmt = conn.prepareStatement(sql);
         stmt.setString(1, keyword+"%");
         rs = stmt.executeQuery();
         if(rs.next()) {
            vo = new productVO();
            vo.setMenu_id(rs.getString("menuId"));
            vo.setMenu(rs.getString("menu"));
            vo.setPrice(rs.getInt("price"));
            list.add(vo);
         }
         
               
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         disconnect();
      }return list;
      
   }
   
   public ArrayList<memberVO> findAll() {
		connect();
		
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		try {
			String sql = "select id,no,name,birth,phone,email,gender,freq"+
		                  "from member where id<>'admin'";
			stmt = conn.prepareStatement(sql);// sql�� ����
			rs = stmt.executeQuery();// sql�� �����û(�������!!)			

			while (rs.next()) {// ����
				// �������� ���
				memberVO vo = new memberVO();
				// 7���� �����ִ� �Ӽ������͸� �����ֱ� ���� ���.
				vo.setId(rs.getString("id"));
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setBirth(rs.getString("birth"));
				vo.setFreq(rs.getInt("freq"));
				vo.setPhone(rs.getString("phone"));
				vo.setMail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));

				list.add(vo);
			} // while
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}// findAll
   
   public memberVO findById(String id) {
       connect();
       memberVO vo=null;//��ȸ�� ������� ������ ǥ��
       
       try {
       String sql="select id,pwd,name,birth,phone,email,gender from member "
                      + "where id=?";
          stmt = conn.prepareStatement(sql); //sql�� ����
            stmt.setString(1, id);
          rs = stmt.executeQuery();//����sql���� ���� ���� ��û 
          
          if(rs.next()) {//���̵� ��ġ�ϴ� ���� �����Ѵٸ�
             vo = new memberVO(rs.getString("id"), rs.getString("pwd"),
                             rs.getString("name"), rs.getString("birth"), rs.getString("phone"),
                             rs.getString("email"), rs.getString("gender"));
          }
        } catch (SQLException e) {
           e.printStackTrace();
        }finally {
           disconnect();
        }
       return vo;
       
    }//findById


   // ĳ����,ī��� ����Ȯ�ι�ư Ŭ����
   public int insertMenu(String id) {
      //System.out.println(">>>>>>>>>>>>>  1��: insertMenu");
      connect();
      int pId=0;
      try {
         String sql = "INSERT INTO purchase (purchaseid, id, buydate) " 
                  + "VALUES(purchase_seq.nextval,?,SYSDATE)";

         stmt = conn.prepareStatement(sql);

         stmt.setString(1, id);// id
          System.out.println("�߰�::"+stmt.executeUpdate());

          sql="select purchase_seq.currval from dual";
          
          stmt  = conn.prepareStatement(sql);
         rs = stmt.executeQuery();
         rs.next(); 
         pId= rs.getInt(1);
         //System.out.println("�߰��� �ֹ���ȣ>>>"+ rs.getInt(1));
          
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         disconnect();
      }
      return pId;
   } //insertMenu

   // ĳ����,ī��� ����Ȯ�ι�ư Ŭ����
   public void insertStock(ArrayList<orderVO>menu, int pId) {
      //System.out.println(">>>>>>>>>>>>>  2��: insertStock");
      connect();

      try {
         
         
         String sql2 = "INSERT INTO stock (purchaseId, menu, quan)" 
               + "VALUES(?,?,?)";
         stmt = conn.prepareStatement(sql2);
         for (int i = 0; i < menu.size(); i++) {
            stmt.setInt(1, pId);// id
            stmt.setString(2, menu.get(i).getMenu());// id
            stmt.setInt(3, menu.get(i).getCnt());// id
            stmt.executeUpdate();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         disconnect();
      }
   } //insertStock 


   
}