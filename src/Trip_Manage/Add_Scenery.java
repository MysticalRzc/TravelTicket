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


public class Add_Scenery {
	private JTextField scenery = new JTextField(100);
	private JTextField scenery_address = new JTextField(100);
	private JTextField ticket_price = new JTextField(100);
	private JTextField ticket_price_half = new JTextField(100);
	private JTextField maximum_capacity = new JTextField(100);
	private JTextField opening_time = new JTextField(100);
	private JTextField artificial_ticket_time = new JTextField(100);
	private JLabel label1 = new JLabel("景点名称");
	private JLabel label2 = new JLabel("景点地址");
	private JLabel label3 = new JLabel("票价");
	private JLabel label4 = new JLabel("优惠票票价");
	private JLabel label5 = new JLabel("最大客容量");
	private JLabel label6 = new JLabel("开放时间");
	private JLabel label7 = new JLabel("人工售票时间");
	JButton Login_Button = new JButton("添加");
	JButton Register_Button = new JButton("取消");
	private Visited_SQL sql = new Visited_SQL();

	public Add_Scenery() {

		JFrame f = new JFrame();
		int a,b,c,d;
		a=50;
		b=30;
		c=100;
		d=25;
		label1.setBounds(a, b, c, d);
		scenery.setBounds(a+100,b,c,d);
		b+=33;
		label2.setBounds(a, b, c, d);
		scenery_address.setBounds(a+100, b, c, d);
		b+=33;
		label3.setBounds(a, b, c, d);
		ticket_price.setBounds(a+100, b, c, d);
		b+=33;
		label4.setBounds(a, b, c, d);
		ticket_price_half.setBounds(a+100, b, c, d);
		b+=33;
		label5.setBounds(a, b, c, d);
		maximum_capacity.setBounds(a+100, b, c, d);
		b+=33;
		label6.setBounds(a, b, c, d);
		opening_time.setBounds(a+100, b, c, d);
		b+=33;
		label7.setBounds(a, b, c, d);
		artificial_ticket_time.setBounds(a+100, b, c, d);
		
		b+=33;
		Login_Button.setBounds(a, b, c, d);
		Register_Button.setBounds(a+100, b, c, d);
		
		

	
		
		f.add(Login_Button);
		f.add(Register_Button);
		f.add(scenery);
		f.add(scenery_address);
		f.add(ticket_price);
		f.add(ticket_price_half);
		f.add(maximum_capacity);
		f.add(opening_time);
		f.add(artificial_ticket_time);
		f.add(label1);
		f.add(label2);
		f.add(label3);
		f.add(label4);
		f.add(label5);
		f.add(label6);
		f.add(label7);
		
		
		f.setLayout(null);
		
		
		Register_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();// 点击按钮时frame1销毁,new一个frame2
			}
		});
		Login_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s1 = scenery.getText();
				String s2 = scenery_address.getText();
				String s3 = ticket_price.getText();
				String s4 = ticket_price_half.getText();
				String s5 = maximum_capacity.getText();
				String s6 = opening_time.getText();
				String s7 = artificial_ticket_time.getText();
				ResultSet rs = sql.Reading("select scenery_id from ticket_information order by scenery_id");
				int scenery_id = 0;
				try {
					while (rs.next()) {
						if (rs.getInt(1) == scenery_id)
							scenery_id++;
						else
							break;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String writing = "insert into ticket_information values('"+s1+"','"+scenery_id+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+0+"','"+s6+"','"+s7+"');";
				sql.writing(writing);
				JOptionPane.showMessageDialog(f,"添加成功"); 
			}

		});
		f.setVisible(true);
		f.setSize(300, 400);
		f.setLocation(520, 150);
		f.setBackground(Color.blue);
		f.setTitle("添加景点");
		f.setResizable(false);
	}
}
