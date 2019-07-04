package com.encore.coffee.control;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

import com.encore.coffee.model.CoffeeDAO;
import com.encore.coffee.model.memberVO;
import com.encore.coffee.model.orderVO;
import com.encore.coffee.model.productVO;
import com.encore.coffee.view.CardView;
import com.encore.coffee.view.CashView;
import com.encore.coffee.view.CoffeeJoin;
import com.encore.coffee.view.FindID;
import com.encore.coffee.view.ForUpdate;
import com.encore.coffee.view.LookID;
import com.encore.coffee.view.LookPass;
import com.encore.coffee.view.MemberUp;
import com.encore.coffee.view.MyDrawing;
import com.encore.coffee.view.coffeeloginView;
import com.encore.coffee.view.order;
import com.encore.coffee.view.sell;
import com.encore.coffee.view.sell_g;
import com.encore.coffee.view.update;

public class Controller implements ActionListener, MouseListener {

	CoffeeJoin joinView;
	coffeeloginView loginView;
	MemberUp memberUp;
	sell_g sell_g;
	sell sell;
	update adminUp;
	public order odr;
	// order
	CashView csv;
	CardView crdv;
	int listNum = 1; // order�信�� �ǿ��ʿ� ��ġ�� ��ȣ
	int selectNum = 1; // ����=0;
	int orderMoney = 0;

	public HashMap<String, productVO> prdList;// �� import ��Ű���� �����ؾ��ԡ�
	Object selecItem;

	FindID findId;
	LookID lookId;
	LookPass lookPass;
	private String item, item2;
	CoffeeDAO dao;
	MyDrawing md;

	ForUpdate upLogin;

	public Controller() {

		joinView = new CoffeeJoin();
		loginView = new coffeeloginView();
		memberUp = new MemberUp();
		sell_g = new sell_g();
		sell = new sell();
		adminUp = new update();
		odr = new order();
		prdList = new HashMap<String, productVO>();
		crdv = new CardView();
		csv = new CashView();
		findId = new FindID();
		lookId = new LookID();
		lookPass = new LookPass();
		dao = new CoffeeDAO();
		md = crdv.md;
		Pop pp = new Pop();
		pp.start();
		upLogin = new ForUpdate();
		eventUp();
	}

	public void eventUp() {
		// CoffeeJoin
		joinView.bt_checkid.addActionListener(this);
		joinView.bt_reset.addActionListener(this);
		joinView.bt_submit.addActionListener(this);

		// coffeeloginView
		loginView.bt_findID.addActionListener(this);
		loginView.bt_join.addActionListener(this);
		loginView.bt_login.addActionListener(this);
		loginView.bt_perInfo.addActionListener(this);

		// MemberUp();
		memberUp.bt_checkid.addActionListener(this);
		memberUp.bt_reset.addActionListener(this);
		memberUp.bt_submit.addActionListener(this);
		upLogin.bt_login.addActionListener(this);

		// sell_g

		// sell

		sell.btnDw.addActionListener(this);
		sell.btnGr.addActionListener(this);
		sell.bt_mem.addActionListener(this);

		// update
		adminUp.btnNewButton.addActionListener(this);
		adminUp.btnNewButton_1.addActionListener(this);

		// order
		odr.btnNewButton_1.addActionListener(this);
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
		odr.button_24.addActionListener(this);
		odr.button_3.addActionListener(this);
		odr.button_4.addActionListener(this);
		odr.button_5.addActionListener(this);
		odr.button_6.addActionListener(this);
		odr.button_7.addActionListener(this);
		odr.button_8.addActionListener(this);
		odr.button_9.addActionListener(this);

		odr.table.addMouseListener(this);

		JComponent comp = odr.spinner.getEditor(); // order���ִ� �����������ϸ� HashMap�� ��������(selectNum)�� �ٲ��
		JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		formatter.setCommitsOnValidEdit(true);

		odr.spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				System.out.println(odr.spinner.getValue());

				if (selecItem.equals("Americano")) {

					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Americano", new productVO(listNum + "", "Americano", 2500, selectNum));
					int quantity = prdList.get("Americano").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);

					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);// ��������� ���̺� �����۶���

					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);// ī������� ���̺� �����۶���

				} else if (selecItem.equals("Espresso")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Espresso", new productVO(listNum + "", "Espresso", 2000, selectNum));
					int quantity = prdList.get("Espresso").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Cramel")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Cramel", new productVO(listNum + "", "Cramel", 3500, selectNum));
					int quantity = prdList.get("Cramel").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Capuccino")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Capuccino", new productVO(listNum + "", "Capuccino", 3500, selectNum));
					int quantity = prdList.get("Capuccino").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Latte")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Latte", new productVO(listNum + "", "Latte", 3000, selectNum));
					int quantity = prdList.get("Latte").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Dolche")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Dolche", new productVO(listNum + "", "Dolche", 4000, selectNum));
					int quantity = prdList.get("Dolche").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("BlackTea")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("BlackTea", new productVO(listNum + "", "BlackTea", 4000, selectNum));
					int quantity = prdList.get("BlackTea").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Green")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Green", new productVO(listNum + "", "Green", 4500, selectNum));
					int quantity = prdList.get("Green").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Cool")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Cool", new productVO(listNum + "", "Cool", 4000, selectNum));
					int quantity = prdList.get("Cool").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Vanila")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Vanila", new productVO(listNum + "", "Vanila", 4500, selectNum));
					int quantity = prdList.get("Vanila").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Pink")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Pink", new productVO(listNum + "", "Pink", 4000, selectNum));
					int quantity = prdList.get("Pink").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("ChocoChip")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("ChocoChip", new productVO(listNum + "", "ChocoChip", 2000, selectNum));
					int quantity = prdList.get("ChocoChip").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Blue")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Blue", new productVO(listNum + "", "Blue", 3000, selectNum));
					int quantity = prdList.get("Blue").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Banana")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Banana", new productVO(listNum + "", "Banana", 4000, selectNum));
					int quantity = prdList.get("Banana").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Chocos")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Chocos", new productVO(listNum + "", "Chocos", 3000, selectNum));
					int quantity = prdList.get("Chocos").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("RedVelvet")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("RedVelvet", new productVO(listNum + "", "RedVelvet", 4000, selectNum));
					int quantity = prdList.get("RedVelvet").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Greens")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Greens", new productVO(listNum + "", "Greens", 3000, selectNum));
					int quantity = prdList.get("Greens").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Crape")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Crape", new productVO(listNum + "", "Crape", 4500, selectNum));
					int quantity = prdList.get("Crape").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Carameld")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Carameld", new productVO(listNum + "", "Carameld", 1000, selectNum));
					int quantity = prdList.get("Carameld").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("JavaCh")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("JavaCh", new productVO(listNum + "", "JavaCh", 500, selectNum));
					int quantity = prdList.get("JavaCh").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Heizz")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Heizz", new productVO(listNum + "", "Heizz", 1000, selectNum));
					int quantity = prdList.get("Heizz").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				} else if (selecItem.equals("Whip")) {
					selectNum = (Integer) odr.spinner.getValue();
					prdList.put("Whip", new productVO(listNum + "", "Whip", 500, selectNum));
					int quantity = prdList.get("Whip").getQuantity();
					odr.table.setValueAt(quantity, odr.table.getSelectedRow(), 3);
					csv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);
					crdv.table.setValueAt(quantity, odr.table.getSelectedRow(), 2);

				}

			}
		}); // odr�信�� jspinner�����̺�Ʈ

		// cardView
		crdv.btn_cancel.addActionListener(this);
		crdv.btn_confirm.addActionListener(this);

		// cashView
		csv.btn_cancel.addActionListener(this);
		csv.btn_confirm.addActionListener(this);

		// findId
		findId.bt_close.addActionListener(this);
		findId.bt_findID.addActionListener(this);
		findId.bt_findPass.addActionListener(this);

		// lookId
		lookId.bt_back.addActionListener(this);
		lookId.bt_find.addActionListener(this);

		// lookPass
		lookPass.bt_back.addActionListener(this);
		lookPass.bt_find.addActionListener(this);
	}

	// �α��� ��ư������ ȸ�������� Ȯ�εǾ order�䰡 �㶧 popâ�� �����Ǿ��� Ŭ����
	class Pop extends Thread {
		public void run() {
			int num = 1;
			int P = 0;
			Calendar cal = Calendar.getInstance();
			String sec = null;
			String[] picture = { "event1", "event2", "event3" };

			while (true) {

				try {
					sec = (cal.get(Calendar.SECOND) < 10 ? "0" : "") + cal.get(Calendar.SECOND);

					sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				num++;

				if (num % 5 == 0) {
					if (P < 3) {
						P++;

						if (P == 3) {
							P = 0;

						}

						odr.lblNewLabel_1
								.setIcon(new ImageIcon("C:\\Users\\Playdata\\git\\java-chocochip\\Coffee\\src\\images\\"
										+ picture[P] + ".jpg"));
					}

				}

			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == loginView.bt_join) {// ȸ������ ��ư Ŭ��
			loginView.setVisible(false);
			joinView.setVisible(true);

		} else if (obj == loginView.bt_login) {// �α��� ��ư Ŭ��
			String id = loginView.tf_id.getText();
			String pass = new String(loginView.tf_pass.getPassword());
			CoffeeDAO dao = new CoffeeDAO();
			if (id.equals("") || pass.equals("")) {
				JOptionPane.showMessageDialog(loginView, "���̵� �Ǵ� ��й�ȣ�� �Է��ϼ���");
				loginView.tf_id.requestFocus();
				return;
			} else if (dao.findLogin(id, pass)) {
				odr.setVisible(true);
				odr.loginid = id;
				loginView.setVisible(false);
				loginView.initText();
			} else {
				loginView.showMsg("�α��ο� �����Ͽ����ϴ�.");
			}

		} else if (obj == loginView.bt_findID) {// ���̵�, ��й�ȣ ã��
			findId.setVisible(true);
			loginView.setVisible(false);

		} else if (obj == loginView.bt_perInfo) {
			loginView.setVisible(false);
			upLogin.setVisible(true);

		} else if (obj == upLogin.bt_login) {
			String id = upLogin.tf_id.getText();
			String pass = new String(upLogin.tf_pass.getPassword());

			CoffeeDAO dao = new CoffeeDAO();

			if (dao.findLogin(id, pass)) {
				upLogin.setVisible(false);
				memberUp.setVisible(true);
				upLogin.loginId = id;
				memberVO vo = dao.findById(id);
//	              System.out.println("����vo>>>" + vo);
				memberUp.initText(vo);

			} else {
				upLogin.showMsg("�α��ο� �����Ͽ����ϴ�.");
			}

			upLogin.initText();

		} else if (obj == joinView.bt_checkid) {// �ߺ�Ȯ��
			String id = joinView.tf_id.getText();
			CoffeeDAO dao = new CoffeeDAO();

			if (dao.check_id(id) == 1) {
				JOptionPane.showMessageDialog(joinView, "���̵� �����մϴ�");
			} else
				JOptionPane.showMessageDialog(joinView, "��밡���� ���̵��Դϴ�");

		} else if (obj == joinView.bt_reset) {// ���
			joinView.setVisible(false);
			loginView.setVisible(true);

		} else if (obj == joinView.bt_submit) {// ȸ�����Ե��

			joinView.setVisible(true);
			String id = joinView.tf_id.getText();
			String pass = new String(joinView.tf_pass.getPassword());
			String pass2 = new String(joinView.tf_pass2.getPassword());
			String name = joinView.tf_name.getText();
			String birth = joinView.cb_year.getSelectedItem().toString() + "-"
					+ joinView.cb_month.getSelectedItem().toString() + "-"
					+ joinView.cb_date.getSelectedItem().toString();
			String phone = joinView.tf_phone1.getText() + "-" + joinView.tf_phone2.getText() + "-"
					+ joinView.tf_phone3.getText();
			String[] phone2 = phone.split("-");
			String email = "";
			String self = joinView.cb_email.getSelectedItem().toString();

			if (self.equals("�����Է�")) {
				email = joinView.tf_email.getText();
				if (!email.matches("^[a-zA-Z]*$")) {
					JOptionPane.showMessageDialog(joinView, "�̸����� Ȯ�����ּ���");
					joinView.tf_email.setText("");
					joinView.tf_email.requestFocus();
					return;
				}
			} else {
				email = joinView.tf_email.getText() + self;
				if (!joinView.tf_email.getText().matches("^[a-zA-Z]*$")) {
					JOptionPane.showMessageDialog(joinView, "�̸����� Ȯ�����ּ���");
					joinView.tf_email.setText("");
					joinView.tf_email.requestFocus();
					return;
				}
			}
			String gender = joinView.cb_gender.getSelectedItem().toString();

			// ��ȿ�� �˻�
			if (id.equals("") || pass.equals("") || name.equals("") || email.equals("")) {
				JOptionPane.showMessageDialog(joinView, "�� ĭ�� �Է����ּ���");
				return;
			} else if (id.length() < 4 || id.length() > 11) {
				JOptionPane.showMessageDialog(joinView, "ID�� 4~10�ڸ��� ���ڷ� �Է����ּ���");
				joinView.tf_id.requestFocus();
				return;
			} else if (pass.length() < 3 || pass.length() > 12) {
				JOptionPane.showMessageDialog(joinView, "��й�ȣ�� 4~11�ڸ��� �ۼ����ּ���");
				joinView.tf_pass.requestFocus();
				return;
			} else if (!pass.equals(pass2)) {
				JOptionPane.showMessageDialog(joinView, "��й�ȣ�� Ȯ���Ͽ��ֽʽÿ�");
				joinView.tf_pass2.requestFocus();
				joinView.tf_pass2.setText("");
				return;

			} else if (id.equals(pass)) {
				JOptionPane.showMessageDialog(joinView, "���̵�� ��й�ȣ�� ������ �� �����ϴ�");
				joinView.tf_pass.setText("");
				joinView.tf_pass.requestFocus();
				return;

			} else if (!id.matches("^[a-zA-Z0-9]*$")) {
				JOptionPane.showMessageDialog(joinView, "���̵�� ���� Ȥ�� ���� ��,�ҹ��ڸ� �����մϴ�.");
				joinView.tf_id.setText("");
				joinView.tf_id.requestFocus();
				return;
			} else if (!pass.matches("^[a-zA-Z0-9]*$")) {
				JOptionPane.showMessageDialog(joinView, "��й�ȣ�� ���� Ȥ�� ���� ��,�ҹ��ڸ� �����մϴ�.");
				joinView.tf_pass.setText("");
				joinView.tf_pass.requestFocus();
				return;
			} else if (!(phone2[0].matches("^[0-9]*$") || phone2[1].matches("^[0-9]*$")
					|| phone2[2].matches("^[0-9]*$"))) {
				JOptionPane.showMessageDialog(joinView, "��ȭ��ȣ�� Ȯ�����ּ���");
				return;

			} else if (self.equals("==����==")) {
				JOptionPane.showMessageDialog(joinView, "�̸����� �������ּ���");
				joinView.tf_email.requestFocus();
				return;
			}

			memberVO vo = new memberVO(id, pass, name, birth, phone, email, gender);
			CoffeeDAO dao = new CoffeeDAO();
			if (dao.create(vo)) {
				joinView.showMSG("ȸ�����ԵǾ����ϴ�");
				joinView.setVisible(false);
				loginView.setVisible(true);
			} else {
				joinView.showMSG("ȸ�����Կ� �����Ͽ����ϴ�.");
				joinView.setVisible(false);
				loginView.setVisible(true);
			}

		

		} else if (obj == memberUp.bt_reset) {// ȸ����������(���)
			
			memberUp.setVisible(false);
	         loginView.setVisible(true);
		} else if (obj == memberUp.bt_submit) {// ȸ����������(���)
	             String phone = memberUp.tf_phone1.getText() + "-" + memberUp.tf_phone2.getText() + "-"
	                   + memberUp.tf_phone3.getText();
	             String birth = memberUp.cb_year.getSelectedItem().toString() + "-"
	                   + memberUp.cb_month.getSelectedItem().toString() + "-"
	                   + memberUp.cb_date.getSelectedItem().toString();

	             String mails = "gmail.comnaver.comdaum.net";
	             String email = memberUp.tf_email.getText();
	             String upmail;
	             if (email.contains(mails)) {
	                upmail = memberUp.tf_email.getText()+"@"+ memberUp.cb_email.getSelectedItem();
	             } else {
	                upmail = email;
	             }

	             // ������ ������ �Ѱ��� ������(vo)���� ����
	             memberVO vo = new memberVO();
	             vo.setId(memberUp.tf_id.getText());
	             vo.setPwd(new String(memberUp.tf_pass.getPassword()));
	             vo.setPhone(phone);
	             vo.setMail(upmail);
	             vo.setGender(memberUp.cb_gender.getSelectedItem().toString());
	             vo.setBirth(birth);

	             CoffeeDAO dao = new CoffeeDAO();
	             if (dao.member_up(vo)) {
	                // DAO�������� �ݿ�(���̵�, ���� ������ ����)
	                // sell.displayTable(dao.findAll());//sellâ�� ȸ������ �������� ����
	                memberUp.showMsg("�����Ϸ�!! �ٽ� �α����� �ּ���!");
	                memberUp.setVisible(false);
	                loginView.setVisible(true);// �α����Ͽ� ���� ���� Ȯ��
	             }

		} else if (obj == adminUp.btnNewButton) {// ����(Ȯ��)

		} else if (obj == adminUp.btnNewButton_1) {// ����(���)

			int row = odr.table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(odr, "������ �������� �����ϼ���");
				return;
			} else {
				DefaultTableModel model = (DefaultTableModel) odr.table.getModel();
				model.removeRow(row);
			}

		} else if (obj == odr.button) {
			prdList.put("Americano", new productVO(listNum + "", "Americano", 2500, selectNum));// set
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Americano"));
			csv.displayTable(prdList.get("Americano"));
			crdv.displayTable(prdList.get("Americano"));

			listNum++;
			odr.button.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_1) {
			prdList.put("Espresso", new productVO(listNum + "", "Espresso", 2000, selectNum));// set

			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Espresso"));
			csv.displayTable(prdList.get("Espresso"));
			crdv.displayTable(prdList.get("Espresso"));
			odr.button_1.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_2) {
			prdList.put("Cramel", new productVO(listNum + "", "Cramel", 3500, selectNum));// set

			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Cramel"));
			csv.displayTable(prdList.get("Cramel"));
			crdv.displayTable(prdList.get("Cramel"));
			odr.button_2.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����
		} else if (obj == odr.button_3) {

			prdList.put("Capuccino", new productVO(listNum + "", "Capuccino", 3500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Capuccino"));
			csv.displayTable(prdList.get("Capuccino"));
			crdv.displayTable(prdList.get("Capuccino"));
			odr.button_3.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_4) {

			prdList.put("Latte", new productVO(listNum + "", "Latte", 3000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Latte"));
			csv.displayTable(prdList.get("Latte"));
			crdv.displayTable(prdList.get("Latte"));
			odr.button_4.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_5) {

			prdList.put("Dolche", new productVO(listNum + "", "Dolche", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Dolche"));
			csv.displayTable(prdList.get("Dolche"));
			crdv.displayTable(prdList.get("Dolche"));
			odr.button_5.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_6) {

			prdList.put("BlackTea", new productVO(listNum + "", "BlackTea", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("BlackTea"));
			csv.displayTable(prdList.get("BlackTea"));
			crdv.displayTable(prdList.get("BlackTea"));
			odr.button_6.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_7) {

			prdList.put("Green", new productVO(listNum + "", "Green", 4500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Green"));
			csv.displayTable(prdList.get("Green"));
			crdv.displayTable(prdList.get("Green"));
			odr.button_7.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_8) {

			prdList.put("Cool", new productVO(listNum + "", "Cool", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Cool"));
			csv.displayTable(prdList.get("Cool"));
			crdv.displayTable(prdList.get("Cool"));
			odr.button_8.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_9) {

			prdList.put("Vanila", new productVO(listNum + "", "Vanila", 4500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Vanila"));
			csv.displayTable(prdList.get("Vanila"));
			crdv.displayTable(prdList.get("Vanila"));
			odr.button_9.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_10) {

			prdList.put("Pink", new productVO(listNum + "", "Pink", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Pink"));
			csv.displayTable(prdList.get("Pink"));
			crdv.displayTable(prdList.get("Pink"));
			odr.button_10.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_11) {

			prdList.put("ChocoChip", new productVO(listNum + "", "ChocoChip", 2000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("ChocoChip"));
			csv.displayTable(prdList.get("ChocoChip"));
			crdv.displayTable(prdList.get("ChocoChip"));
			odr.button_11.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_12) {

			prdList.put("Blue", new productVO(listNum + "", "Blue", 3000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Blue"));
			csv.displayTable(prdList.get("Blue"));
			crdv.displayTable(prdList.get("Blue"));
			odr.button_12.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_13) {

			prdList.put("Banana", new productVO(listNum + "", "Banana", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Banana"));
			csv.displayTable(prdList.get("Banana"));
			crdv.displayTable(prdList.get("Banana"));
			odr.button_13.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_14) {

			prdList.put("Chocos", new productVO(listNum + "", "Chocos", 3000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Chocos"));
			csv.displayTable(prdList.get("Chocos"));
			crdv.displayTable(prdList.get("Chocos"));
			odr.button_14.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_15) {

			prdList.put("RedVelvet", new productVO(listNum + "", "RedVelvet", 4000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("RedVelvet"));
			csv.displayTable(prdList.get("RedVelvet"));
			crdv.displayTable(prdList.get("RedVelvet"));
			odr.button_15.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_16) {

			prdList.put("Greens", new productVO(listNum + "", "Greens", 3000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Greens"));
			csv.displayTable(prdList.get("Greens"));
			crdv.displayTable(prdList.get("Greens"));
			odr.button_16.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_17) {

			prdList.put("Crape", new productVO(listNum + "", "Crape", 4500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Crape"));
			csv.displayTable(prdList.get("Crape"));
			crdv.displayTable(prdList.get("Crape"));
			odr.button_17.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_18) { // ��ü���
			DefaultTableModel model = (DefaultTableModel) odr.table.getModel();
			model.setNumRows(0);

			crdv.dtm.setNumRows(0);
			csv.dtm.setNumRows(0);

			prdList.clear();

			odr.buttonSet(item2, 2);

			// System.out.println(prdList.get("Americano"));
			System.out.println(prdList.keySet());

		} else if (obj == odr.button_19) {
			csv.frame.setVisible(true);
			csv.textField.setText(csv.sumPrice() + "");
			odr.setVisible(false);

		} else if (obj == odr.button_20) {
			crdv.frame.setVisible(true);
			crdv.textField.setText(crdv.sumPrice() + "");
			odr.setVisible(false);
		} else if (obj == odr.btnNewButton_2) {
			prdList.put("Carameld", new productVO(listNum + "", "Carameld", 1000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Carameld"));
			csv.displayTable(prdList.get("Carameld"));
			crdv.displayTable(prdList.get("Carameld"));
			odr.btnNewButton_2.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_21) {

			prdList.put("JavaCh", new productVO(listNum + "", "JavaCh", 500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("JavaCh"));
			csv.displayTable(prdList.get("JavaCh"));
			crdv.displayTable(prdList.get("JavaCh"));
			odr.button_21.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_22) {

			prdList.put("Heizz", new productVO(listNum + "", "Heizz", 1000, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Heizz"));
			csv.displayTable(prdList.get("Heizz"));
			crdv.displayTable(prdList.get("Heizz"));
			odr.button_22.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.button_23) {

			prdList.put("Whip", new productVO(listNum + "", "Whip", 500, selectNum));// set
			listNum++;
			// odr�� �ִ� jtable�� Ŭ���� ��ư�� �´°� ����ϱ�
			odr.displayTable(prdList.get("Whip"));
			csv.displayTable(prdList.get("Whip"));
			crdv.displayTable(prdList.get("Whip"));
			odr.button_23.setEnabled(false); // �������, ��ü��ҹ�ư Ŭ���� true�� ���� , �����ϴ��� spinner�� ���ؼ��� selectNum����

		} else if (obj == odr.btnNewButton_1) {// �������=

			item2 = (String) selecItem;
			odr.buttonSet(item2, 1);
			int row = odr.table.getSelectedRow();

			if (odr.table.getSelectedRow() != -1) {
				odr.model.removeRow(odr.table.getSelectedRow());

			}

			prdList.remove(item);

		} else if (obj == crdv.btn_confirm) {

		} else if (obj == crdv.btn_cancel) {
			md.my.clearCanvas();
			crdv.frame.setVisible(false);

		} else if (obj == csv.btn_confirm) {// ���ݰ��� Ȯ�ι�ư

			if (csv.table.getModel().getRowCount() != -1) {
				// totalPrice=csv.sumPrice();
				// System.out.println("===============");

				ArrayList<orderVO> list = csv.sumName();
				String userId = odr.loginid;
				System.out.println("userId>>>"+ userId);
				int pId = dao.insertMenu(userId);
				dao.insertStock(list, pId);
			} else
				JOptionPane.showMessageDialog(csv.frame, "�����Ҿ������̾���!!");

		} else if (obj == csv.btn_cancel) {// ���ݰ��� ��ҹ�ư
			DefaultTableModel model = (DefaultTableModel) csv.table.getModel();
			model.setNumRows(0);

			csv.frame.setVisible(false);
			odr.setVisible(true);

		} else if (obj == findId.bt_findID) {// ���̵��й�ȣ ã��(���̵��ư)
			findId.setVisible(false);
			lookId.setVisible(true);

		} else if (obj == findId.bt_findPass) {// ���̵��й�ȣ ã��(��й�ȣ��ư)
			findId.setVisible(false);
			lookPass.setVisible(true);

		} else if (obj == findId.bt_close) {// ���̵��й�ȣ ã��(�ݱ�)
			findId.setVisible(false);
			loginView.setVisible(true);

		} else if (obj == lookId.bt_find) {// ���̵�� ã��(ã���ư)
			String email = lookId.tf_email.getText();
			CoffeeDAO dao = new CoffeeDAO();
			if (dao.find_id(email).equals("")) {
				JOptionPane.showMessageDialog(lookId, "��ġ�ϴ� ���̵� �����ϴ�");
				return;
			} else
				JOptionPane.showMessageDialog(lookId, "���̵�� " + dao.find_id(email) + "�Դϴ�.");

		} else if (obj == lookId.bt_back) {// ���̵�� ã��(�ǵ��ư���)
			lookId.setVisible(false);
			loginView.setVisible(true);

		} else if (obj == lookPass.bt_find) {// ��й�ȣ�� ã��(ã���ư)
			String id = lookPass.tf_id.getText();
			String email = lookPass.tf_email.getText();

			CoffeeDAO dao = new CoffeeDAO();
			String pass = dao.find_pass(id, email);

			if (pass.equals("")) {
				JOptionPane.showMessageDialog(lookPass, "��ġ�ϴ� ������ �����ϴ�");

				return;
			} else
				JOptionPane.showMessageDialog(lookPass, "��й�ȣ��" + pass + "�Դϴ�");
			// System.out.println(dao.find_pass(id, email));

		} else if (obj == lookPass.bt_back) {// ��й�ȣ�� ã��(�ǵ��ư���)
			lookPass.setVisible(false);
			loginView.setVisible(true);

		} else if (obj == sell.bt_mem) { // �˻���ư Ŭ����

			Map<String, String> map = sell.memberSearch();

			CoffeeDAO dao = new CoffeeDAO();
			ArrayList<memberVO> list = dao.findSearch(map);
			sell.displayTable(list);
		}

		else if (obj == sell.btnDw) {
			
			
			
			Map<String, String> map = sell.productSearch();
			CoffeeDAO dao = new CoffeeDAO();
			ArrayList<productVO> list = dao.findProductSearch(map);
			sell.displayProductTable(list);
		} else if (obj == odr.button_24) { // ������ �α��� ��ư
			
			ArrayList <productVO> list2 = dao.searchProductAll();
			sell.displayProductAll(list2);
			
		
			
			if (odr.loginid.equals("admin")) {
				JOptionPane.showMessageDialog(odr, "������ ���� �Ǿ����ϴ�");
				odr.auth = true;
				sell.setVisible(true);
				ArrayList<productVO> list = dao.searchProductAll();
				sell.displayProductAll(list);
			} else {
				JOptionPane.showMessageDialog(odr, "������ ������ �ʿ��մϴ�");
				return;
			}

		} else if (obj == odr.btnNewButton_3) {
			dao.disconnect();
			JOptionPane.showMessageDialog(odr, "�α׾ƿ� �Ǿ����ϴ�");
			odr.setVisible(false);
			loginView.setVisible(true);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		int row = odr.table.getSelectedRow();// odr.table.getSelectedRow();
		int column = 1;
		selecItem = odr.table.getValueAt(row, column);

		odr.spinner.setValue(1);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Controller();
	}

}