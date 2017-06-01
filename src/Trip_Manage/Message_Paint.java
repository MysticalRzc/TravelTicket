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


public class Message_Paint {
	private Visited_SQL sql = new Visited_SQL();
	ResultSet rs = null;
	public Message_Paint(String Select, JFrame f, JTable table,JTextField Void, boolean type, String Username,String[] button) {
		for(int i=0;i<100;i++)
			button[i]="";
		
		if (Select.compareTo("首页") == 0) {
			table.setVisible(false);
			Void.setVisible(true);
			Void.setEditable(false);;
//			opertion_list.setVisible(false);
			f.add(Void);

			// Void.setFont(new Font("楷体",Font.BOLD,60));
		}
		
		
		if (Select.compareTo("查询景点") == 0) {
			Void.setVisible(false);
			table.setVisible(true);
//			opertion_list.setVisible(true);
			int number=0;

			rs = sql.Reading("select *from ticket_information");
			List<Container> userList = new ArrayList<Container>();
			try {
				if (true) {
					Container user = new Container();
					user.setList1("景点名称");
					user.setList2("景点地点");
					user.setList3("票价");
					user.setList4("优惠票");
					user.setList5("游客量");
					user.setList6("开放时间");
					userList.add(user);
				}
				while (rs.next()) {
					Container user = new Container();
					user.setList1(rs.getString(1));
					user.setList2(rs.getString(3));
					user.setList3(rs.getString(4));
					user.setList4(rs.getString(5));
					user.setList5(rs.getString(7));
					user.setList6(rs.getString(8));
					userList.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ResultSet rs = sql.Reading("select *from ticket_information");

			try {
				while (rs.next()) {
					number++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			button[0] = " ";
			int i;
			String operate = null;
			if(type)
				operate = "删除";
			else 
				operate = "购买";
			for (i = 0; i < number; i++)
				button[i + 1] = operate;
			if(type)
				button[i+1]="添加";
			Container_deal tm = new Container_deal(userList, 6);
			table.setModel(tm);
			table.setBorder(new LineBorder(Color.orange));
			

		}
		
		
		
		
		
		
		
		if (Select.compareTo("个人信息") == 0) {
			table.setVisible(true);
			Void.setVisible(false);
			sql = new Visited_SQL();
			if (type)
				rs = sql.Reading("select *from personal_information");
			else
				rs = sql.Reading("select *from personal_information where user=\""+ Username + "\"");
			// List<Information> userList = getUserList();

			
			
			
			List<Container> userList = new ArrayList<Container>();
			try {
				if (true) {
					Container user = new Container();
					user.setList1("用户名");
					user.setList2("密码");
					user.setList3("用户类型");
					user.setList4("联系方式");
					user.setList5("性别");
					user.setList6("身份证");
					userList.add(user);
				}
				while (rs.next()) {
					Container user = new Container();
					user.setList1(rs.getString(1));
					user.setList2(rs.getString(3));
					user.setList3(rs.getString(4));
					user.setList4(rs.getString(5));
					user.setList5(rs.getString(6));
					user.setList6(rs.getString(7));
					userList.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
//			int number=0;
//			if(type)
//			{
//				rs = sql.Reading("select *from personal_information");
//			}
//			else
//			{
//				rs = sql.Reading("select *from personal_information where user='"+Username+"';");
//			}
//			try {
//				while (rs.next()) {
//					number++;
//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			button[0] = " ";
//			for (int i = 0; i < number; i++)
//				button[i + 1] = "删除";
			
			Container_deal tm = new Container_deal(userList, 6);
			table.setModel(tm);
			table.setBorder(new LineBorder(Color.orange));
		}

		
		
		
		
		
		
		if (Select.compareTo("退票") == 0) {
			table.setVisible(true);
			Void.setVisible(false);
			sql = new Visited_SQL();
			if (type == true) {
				rs = sql.Reading("select user,scenery,visiting_time from personal_information,order_information,ticket_information where order_information.user_id=personal_information.user_id and order_information.scenery_id=ticket_information.scenery_id;");
			} else {
				rs = sql.Reading("select user,scenery,visiting_time from personal_information,order_information,ticket_information where order_information.user_id=personal_information.user_id and order_information.scenery_id=ticket_information.scenery_id and user=\""
						+ Username + "\";");
			}

			List<Container> userList = new ArrayList<Container>();
			try {
				if (true) {
					Container user = new Container();
					user.setList1("用户名");
					user.setList2("景点");
					user.setList3("参观时间");
					userList.add(user);
				}
				while (rs.next()) {
					Container user = new Container();
					user.setList1(rs.getString(1));
					user.setList2(rs.getString(2));
					user.setList3(rs.getString(3));
					userList.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int number=0;
			if(type)
				rs = sql.Reading("select *from order_information");
			else
				rs = sql.Reading("select * from order_information,personal_information where order_information.user_id=personal_information.user_id and user='"+Username+"';");
			try {
				while (rs.next()) {
					number++;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			button[0] = " ";
			for (int i = 0; i < number; i++)
				button[i + 1] = "退票";
			
			Container_deal tm = new Container_deal(userList, 3);
			table.setModel(tm);
			table.setBorder(new LineBorder(Color.orange));
		}
		
		
		
		
		
		
		

		if (Select.compareTo("查看订单") == 0) {
			table.setVisible(true);
			Void.setVisible(false);
			sql = new Visited_SQL();
			if (type == true) {
				rs = sql.Reading("select user,scenery,visiting_time from personal_information,order_information,ticket_information where order_information.user_id=personal_information.user_id and order_information.scenery_id=ticket_information.scenery_id;");
			} else {
				rs = sql.Reading("select user,scenery,visiting_time from personal_information,order_information,ticket_information where order_information.user_id=personal_information.user_id and order_information.scenery_id=ticket_information.scenery_id and user=\""
						+ Username + "\";");
			}
			// List<Information> userList = getUserList();

			List<Container> userList = new ArrayList<Container>();
			try {
				if (true) {
					Container user = new Container();
					user.setList1("用户名");
					user.setList2("景点");
					user.setList3("参观时间");

					userList.add(user);
				}
				while (rs.next()) {
					Container user = new Container();
					user.setList1(rs.getString(1));
					user.setList2(rs.getString(2));
					user.setList3(rs.getString(3));
					userList.add(user);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			
//			int number=0;
//			if(type)
//				rs = sql.Reading("select * from order_information");
//			else
//				rs = sql.Reading("select * from order_information,personal_information where order_information.user_id=personal_information.user_id and user='"+Username+"';");
//
//			try {
//				while (rs.next()) {
//					number++;
//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			button[0] = " ";
//			for (int i = 0; i < number; i++)
//				button[i + 1] = "订单";
			
			Container_deal tm = new Container_deal(userList, 3);
			table.setModel(tm);
			table.setBorder(new LineBorder(Color.orange));
		}
	}
}
