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
		// menu 表示当前所处于的菜单
		// operate_index 表示当前操作的数据下表
		if (menu_index.compareTo("查询景点") == 0) {
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
							JOptionPane.showMessageDialog(f, "您删除的景点已经有游客下了订单");
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
							.showInputDialog("请输入您要参观的时间（xx:xx）");
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
							JOptionPane.showMessageDialog(f,"输入日期为空"); 
						}
					}
					else
					{
						JOptionPane.showMessageDialog(f,"输入日期格式不正确"); 
					}
				}
			}
			if(operate_index==number+1 && use_type)
			{
				new Add_Scenery();
			}
		}

		if (menu_index.compareTo("退票") == 0) {
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

		
		
		
		if (menu_index.compareTo("个人信息") == 0) {
			if (use_type) {

			} else {

			}
		}

		if (menu_index.compareTo("查看订单") == 0) {
			if (use_type) {

			} else {

			}
		}
	}
}
