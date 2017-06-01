package Trip_Manage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Register {
	private JTextField userName = new JTextField(15);
	private JTextField phone = new JTextField(15);
	private JTextField ID_number = new JTextField(18);
	private JPasswordField password = new JPasswordField(15);
	private JPasswordField check_password = new JPasswordField(15);
	private JLabel label1 = new JLabel("用 户 名 :");
	private JLabel label2 = new JLabel("密        码:");
	private JLabel label9 = new JLabel("验证密码");
	private JLabel label3 = new JLabel("联系方式:");
	private JLabel label4 = new JLabel("身份证号:");
	private JLabel label5 = new JLabel("");
	private JLabel label6 = new JLabel("");
	private JLabel label7 = new JLabel("");
	private JLabel label8 = new JLabel("");
	private JLabel label10 = new JLabel("");
	private JLabel label11 = new JLabel("");
	private JLabel label12 = new JLabel("");
	private Visited_SQL sql = new Visited_SQL();
	public Register()
	{
		JFrame f = new JFrame();
		JButton Login_Button=new JButton("返回登陆");
		JButton Register_Button=new JButton("注册");
		
		JRadioButton User_Select=new JRadioButton("用户");
		JRadioButton Administrator_Select=new JRadioButton("管理员");
		ButtonGroup type = new ButtonGroup();
		type.add(Administrator_Select);
		type.add(User_Select);
		
		JRadioButton man_Select=new JRadioButton("男");
		JRadioButton woman_Select=new JRadioButton("女");
		ButtonGroup gender = new ButtonGroup();
		gender.add(man_Select);
		gender.add(woman_Select);
		
		userName.setBounds(420, 140, 130, 25);
		label1.setBounds(350, 140, 60, 25);
		label5.setBounds(560, 140, 300, 25);
		password.setBounds(420, 170, 130, 25);
		label2.setBounds(350, 170, 60, 25);
		label6.setBounds(560, 170, 300, 25);
		check_password.setBounds(420, 200, 130, 25);
		label9.setBounds(350, 200, 60, 25);
		label7.setBounds(560, 200, 300, 25);
		phone.setBounds(420, 230, 130, 25);
		label3.setBounds(350, 230, 60, 25);
		label8.setBounds(560, 230, 300, 25);
		man_Select.setBounds(420, 260, 60, 25);
		woman_Select.setBounds(490, 260, 70, 25);
		label10.setBounds(560, 260, 300, 25);
		ID_number.setBounds(420, 290, 130, 25);
		label4.setBounds(350, 290, 60, 25);
		label11.setBounds(560, 290, 300, 25);
		User_Select.setBounds(420,320,60,25);
		Administrator_Select.setBounds(480,320,70,25);
		label12.setBounds(560, 320, 300, 25);
		Login_Button.setBounds(360,350,90,25);
		Register_Button.setBounds(470,350,90,25);

		f.setLayout(null);
		String name = userName.getText();
		System.out.println(name);
		Login_Button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();//点击按钮时frame1销毁,new一个frame2
				new Login();
			}
		});
		
		Register_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String User = userName.getText();
				String Password = password.getText(); 
				String Check_password = check_password.getText();
				String Phone = phone.getText();
				String ID_Number = ID_number.getText();
				String Type_userORadmini;
				String Gender;
				if(woman_Select.isSelected()==true)
					Gender="女";
				else
					Gender="男";
				if(Administrator_Select.isSelected())
					Type_userORadmini="管理员";
				else
					Type_userORadmini="用户";
				int passCount=0;
				Check_Information che = new Check_Information();
				if(che.check_user_name(User))
				{
				//	label5.setText("用户名正确");
					passCount++;
				}
				else
					label5.setText("用户名请使用长度为3-20的大小写字母");
				label6.setText(che.check_passworld(Password));
				int passworld_length = che.check_passworld(Password).length();
				if(passworld_length==11 || passworld_length==5)
					passCount++;
				if(Check_password.compareTo(Password)==0)
				{
			//		label7.setText("密码验证通过");
					passCount++;
				}
				else
					label7.setText("密码匹配错误");
				if(che.check_phone(Phone))
				{
				//	label8.setText("正确的电话");
					passCount++;
				}
				else
					label8.setText("电话格式不正确");
				if(woman_Select.isSelected() || man_Select.isSelected())
				{
					passCount++;
					label10.setText("");
				}
				else
					label10.setText("请选择性别");
				if(che.check_ID(ID_Number))
				{
				//	label11.setText("身份证号可以使用");
					passCount++;
				}
				else
					label11.setText("身份证号码格式错误");
				if(Administrator_Select.isSelected() || User_Select.isSelected())
				{
					passCount++;
					label12.setText("");
				}
				else
					label12.setText("请选择用户类型");
				if(passCount==7)
				{
					int number=0;
					ResultSet rs = sql.Reading("select * from personal_information");
					try {
						while(rs.next())
						{
							number++;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String WritingStr;
					WritingStr="insert into personal_information values('"+User+"','"+number+"','"+Password+"','"+Type_userORadmini+"','"+Phone+"','"+Gender+"','"+ID_Number+"');";
					System.out.println(WritingStr);
					sql.writing(WritingStr);
					label5.setText("");
					label7.setText("");
					label8.setText("");
					label10.setText("");
					label11.setText("");
					label12.setText("");
					JOptionPane.showMessageDialog(f,"注册成功"); 
				}
			}
			
		}) ;
		f.add(userName);
		f.add(label1);
		f.add(label5);
		f.add(password);
		f.add(label2);
		f.add(label6);
		f.add(check_password);
		f.add(label9);
		f.add(label7);
		f.add(User_Select);
		f.add(Administrator_Select);
		f.add(label8);
		f.add(phone);
		f.add(label3);
		f.add(label10);
		f.add(man_Select);
		f.add(woman_Select);
		f.add(label11);
		f.add(ID_number);
		f.add(label4);
		f.add(label12);
		f.add(Login_Button);
		f.add(Register_Button);
		
		f.setVisible(true);
		f.setSize(1000, 650);
		f.setLocation(160,30);
		f.setTitle("Register");
		f.setResizable(false);

	}
}
