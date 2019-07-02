package com.encore.coffee.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.encore.coffee.model.productVO;

public class CashView {

   public JFrame frame;
   public JTextField textField;
   public JTable table;
   public DefaultTableModel dtm;
   public JScrollPane scroll_table;
   public JButton btn_confirm, btn_cancel;

   /**
    * Create the application.
    */
   public CashView() {
      initialize();
   }

   /**
    * Initialize the contents of the frame.
    */
   private void initialize() {
      Object[][] rowData = new Object[0][3];
      String[] columTitle = { "�޴���", "����", "����" };
      dtm = new DefaultTableModel(rowData, columTitle);

      table = new JTable(dtm);
      scroll_table = new JScrollPane(table);

      frame = new JFrame();
      frame.setBounds(100, 100, 450, 300);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JLabel lblNewLabel = new JLabel("�ֹ� ����Ʈ�� Ȯ�����ּ���.");
      lblNewLabel.setBounds(127, 12, 183, 18);
      frame.getContentPane().add(lblNewLabel);

      JLabel lblNewLabel_1 = new JLabel("���ֹ��ݾ�:");
      lblNewLabel_1.setBounds(60, 49, 76, 18);
      frame.getContentPane().add(lblNewLabel_1);

      textField = new JTextField();
      textField.setBounds(166, 46, 116, 24);
      textField.setEnabled(false);
      frame.getContentPane().add(textField);
      textField.setColumns(10);

      JLabel lblNewLabel_2 = new JLabel("��");
      lblNewLabel_2.setBounds(288, 49, 62, 18);
      frame.getContentPane().add(lblNewLabel_2);

      scroll_table.setBounds(31, 92, 369, 115);
      frame.getContentPane().add(scroll_table);

      JButton btn_confirm = new JButton("Ȯ��");
      btn_confirm.setBounds(95, 214, 105, 27);
      frame.getContentPane().add(btn_confirm);
      
      JButton btn_cancel = new JButton("���");
      btn_cancel.setBounds(205, 214, 105, 27);
      frame.getContentPane().add(btn_cancel);
      frame.setVisible(true);

   }

   public void displayTable(productVO pvo) {

      Object[] rowData = { pvo.getMenu(), pvo.getPrice(), pvo.getQuantity() };

      dtm.addRow(rowData);

   }

   public int sumPrice() { // ������� �� �ؽ�Ʈ�ʵ����
      
      int sumP = 0;
      for (int i = 0; i < table.getModel().getRowCount(); i++) {
         sumP += (Integer) table.getModel().getValueAt(i, 1) * (Integer) table.getModel().getValueAt(i, 2);
      }
      return sumP;
   }
}