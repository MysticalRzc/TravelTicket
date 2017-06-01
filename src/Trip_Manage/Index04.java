package Trip_Manage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Index04 {
	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private Visited_SQL sql = new Visited_SQL();
	private String[] button = null;

	public Index04(Boolean type, String user_name) {
		String User = "��Ȩ���У���1404 20142203605 ���ٳ�   20142203638 ��¶¶\t\t\t\t\t��ǰ�û�:"+ user_name;
		frame = new JFrame();
		JTextField Void = new JTextField();
		Void.setVisible(true);
		Void.setText(" ��ӭ�������ι�Ʊ����ϵͳ!^0^");
		Void.setEditable(false);
		Void.setFont(new Font("��Բ", Font.BOLD, 60));
		Void.setBounds(54, 26, 940, 574);
		frame.add(Void);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		table = new JTable();
		if (type)
			table.setEnabled(true);
		else
			table.setEnabled(false);
		// ��������ʵ�ֵ�TableModel����,����ʱҪ�����û��б����

		String[] menu = { "��ҳ", "��ѯ����", "��Ʊ", "������Ϣ", "�鿴����","ע��"};
		JList menu_list = new JList(menu);
		String[] button = new String[1000];
		JList opertion_list = new JList(button);

		System.out.println(menu_list.getSelectedIndex());

		menu_list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				String station = menu_list.getSelectedValue().toString();
				System.out.println(station);
				if(station.compareTo("ע��")==0)
				{
					Object[] options = {};
					frame.dispose();//�����ťʱframe1����,newһ��frame2
					new Login();
				}
				else if (event.getValueIsAdjusting()) {
				//	System.out.println(station);
					new Message_Paint(station, frame, table, Void, type,
							user_name, button);
					opertion_list.setFont(new Font("��Բ", Font.TYPE1_FONT, 12));
					opertion_list.setListData(button);
				}
			}
		});

		opertion_list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting()) {
					int station_operate = opertion_list.getSelectedIndex();
					System.out.println(station_operate);
					if(station_operate!=-1)
					{
						String station_menu = menu_list.getSelectedValue().toString();
					//	System.out.println(">>>");
						new Operate(frame,station_menu,station_operate,type,user_name);
						new Message_Paint(station_menu, frame, table, Void, type,user_name, button);
						opertion_list.setFont(new Font("��Բ", Font.TYPE1_FONT, 12));
						opertion_list.setListData(button);
					}
				}
			}
		});

		frame.getContentPane().add(menu_list, BorderLayout.WEST);
		frame.getContentPane().add(opertion_list, BorderLayout.EAST);
		frame.getContentPane().add(table, BorderLayout.CENTER);

		textField = new JTextField("\t\t\t\t     \t���ι�Ʊ����ϵͳ");
		textField.setFont(new Font("�����п�", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setBackground(Color.white);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		textField_1 = new JTextField(User);
		textField_1.setEnabled(false);
		frame.getContentPane().add(textField_1, BorderLayout.SOUTH);
		textField_1.setColumns(10);
		frame.setSize(1000, 650);
		frame.setBackground(Color.white);
		frame.setLocation(160, 30);
		frame.setTitle("������Ϣ����ϵͳ");
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
