package Trip_Manage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login {
	private JTextField userName = new JTextField(15);
	private JTextArea ta = new JTextArea(4, 10);
	private JPasswordField password = new JPasswordField(15);
	private JLabel label1 = new JLabel("用户名");
	private JLabel label2 = new JLabel("密  码");
	private JLabel label3 = new JLabel("");
	private JLabel label4 = new JLabel("");
	private Visited_SQL sql = new Visited_SQL();

	public Login() {

		JFrame f = new JFrame();
		JButton Login_Button = new JButton("登陆");
		JButton Register_Button = new JButton("注册");
		JRadioButton User_Select = new JRadioButton("用户");
		JRadioButton Administrator_Select = new JRadioButton("管理员");
		User_Select.setSelected(true);
		ButtonGroup a = new ButtonGroup();

		a.add(Administrator_Select);
		a.add(User_Select);
		User_Select.setBounds(400, 310, 60, 25);
		Administrator_Select.setBounds(460, 310, 70, 25);
		Login_Button.setBounds(400, 350, 60, 25);
		Register_Button.setBounds(470, 350, 60, 25);
		userName.setBounds(400, 230, 130, 25);
		password.setBounds(400, 280, 130, 25);
		label1.setBounds(400, 210, 100, 20);
		label2.setBounds(400, 260, 100, 20);
		label3.setBounds(535, 230, 130, 25);
		label4.setBounds(535, 280, 130, 25);
		f.setLayout(null);
		String name = userName.getText();
		System.out.println(name);
		Register_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();// 点击按钮时frame1销毁,new一个frame2
				new Register();
			}
		});
		Login_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = userName.getText();
				String pas = password.getText();
				boolean select;
				if (User_Select.isSelected() == true)
					select = false;
				else
					select = true;
				ResultSet user_check = sql
						.Reading("select user,password,user_type from personal_information where user=\""+ name + "\";");
				try {
					if (!user_check.next())
						label3.setText("用户名不存在");
					else if (user_check.getString(2).compareTo(pas) == 0) {
						System.out.println(select + user_check.getString(3));
						if (select == true && user_check.getString(3).compareTo("用户")==0) {
							JOptionPane.showMessageDialog(f, "登录失败，未授权");
						} else {
							// label3.setText("登陆成功");
							f.dispose();// 点击按钮时frame1销毁,new一个frame2
							new Index04(select, name);
						}

					} else {
						label3.setText("密码不正确");
						// System.out.println(user_check.getString(2));
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String temp = label3.getText();
			}

		});

		f.add(label1);
		f.add(userName);
		f.add(label2);
		f.add(label3);
		f.add(label4);
		f.add(password);
		f.add(Login_Button);
		f.add(Register_Button);
		f.add(User_Select);
		f.add(Administrator_Select);
		f.setVisible(true);
		f.setSize(1000, 650);
		f.setLocation(160, 30);
		f.setBackground(Color.blue);
		f.setTitle("Login");
		f.setResizable(false);
	}
}
