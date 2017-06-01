package Trip_Manage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Operate {
	private Visited_SQL sql = new Visited_SQL();

	public Operate(JFrame f, String menu_index, int operate_index,
			boolean use_type, String user_name) {
		// menu ��ʾ��ǰ�����ڵĲ˵�
		// operate_index ��ʾ��ǰ�����������±�
		if (menu_index.compareTo("��ѯ����") == 0) {
			int number = 0;
			ResultSet rs = sql.Reading("select *from ticket_information");
			String ticket_id = null;
			try {
				while (rs.next()) {
					number++;
					if (number == operate_index) {
						ticket_id = rs.getString(2);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (operate_index <= number && operate_index >= 1) {

				if (use_type) {
					rs = sql.Reading("select count(scenery_id) from order_information where scenery_id='"
							+ ticket_id + "'");
					try {
						rs.next();
						if (rs.getInt(1) == 0) {
							sql.writing("delete from ticket_information where scenery_id='"
									+ ticket_id + "';");
						} else {
							JOptionPane.showMessageDialog(f, "��ɾ���ľ����Ѿ����ο����˶���");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					rs = sql.Reading("select user_id from personal_information where user='"
							+ user_name + "'");
					String user_id = null;
					try {
						rs.next();
						user_id = rs.getString(1);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rs = sql.Reading("select order_id from order_information order by order_id");
					int order_index = 0;
					try {
						while (rs.next()) {
							if (rs.getInt(1) == order_index)
								order_index++;
							else
								break;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String time_input = JOptionPane
							.showInputDialog("��������Ҫ�ι۵�ʱ�䣨xx:xx��");
					String patterStr1 = "[0-9][0-9]:[0-9][0-9]";
					boolean result = Pattern.matches(patterStr1, time_input);
					if(result)
					{
						if (time_input != null) {
							sql.writing("insert into order_information value('"
									+ order_index + "','" + user_id + "','"
									+ ticket_id + "','" + time_input + "')");
							sql.writing("update ticket_information set visitors_number=visitors_number+1 where scenery_id='"
									+ ticket_id + "'");
						}
						else
						{
							JOptionPane.showMessageDialog(f,"��������Ϊ��"); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f,"�������ڸ�ʽ����ȷ"); 
					}
				}
			}
			if(operate_index==number+1 && use_type)
			{
				new Add_Scenery();
			}
		}

		if (menu_index.compareTo("��Ʊ") == 0) {
			int number = 0;
			int order_id = 0;
			int scenery_id = 0;
			ResultSet rs = sql.Reading("select *from order_information");
			String ticket_id = null;
			try {
				while (rs.next()) {
					number++;
					if (number == operate_index) {
						scenery_id = rs.getInt(3);
						order_id = rs.getInt(1);
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(order_id);
			sql.writing("delete from order_information where order_id='"
					+ order_id + "';");
			sql.writing("update ticket_information set visitors_number=visitors_number-1 where scenery_id='"
					+ scenery_id + "'");
		}

		
		
		
		if (menu_index.compareTo("������Ϣ") == 0) {
			if (use_type) {

			} else {

			}
		}

		if (menu_index.compareTo("�鿴����") == 0) {
			if (use_type) {

			} else {

			}
		}
	}
}
