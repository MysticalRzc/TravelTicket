package Trip_Manage;

import java.util.List;

import javax.swing.JButton;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class Container_deal implements TableModel {
	private List<Container> userList; 
	private int ListNumber;
	public data da;
	public Container_deal(List<Container> userList,int ListNumber) {
		this.userList = userList;
		this.ListNumber=ListNumber;
	}

		public int getRowCount() {
		return userList.size();
	}

	
	public int getColumnCount() {
		return ListNumber;
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	JButton button = new JButton("hello");
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		// �ڼ��У������б��еĵڼ���UserInfo����
		Container user = userList.get(rowIndex);
		switch(columnIndex)
		{
			case 0:
				return user.getList1();
			case 1:
				return user.getList2();
			case 2:
				return user.getList3();
			case 3:
				return user.getList4();
			case 4:
				return user.getList5();
			case 5:
				return user.getList6();
		}
		return "";
	}

	// ���������б仯ʱ��ģ�Ͷ������������ᱻ���ã���ʱ����˵����
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		String info = rowIndex + "��" + columnIndex + "�е�ֵ�ı�: "
//				+ aValue.toString();
//		javax.swing.JOptionPane.showMessageDialog(null, info);
		System.out.println(aValue);
		data.x=rowIndex;
	}
	
	// ָ��ĳ��Ԫ���Ƿ�ɱ༭:��һ�в��ɱ༩����һ����ID����ÿ�������Ψһʶ���
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex != 0) {
			return true;
		}
		return false;
	}

	// ȡÿһ�е�����
	public String getColumnName(int columnIndex) {
		
			switch(columnIndex)
			{
				case 0:
					return "List1";
				case 1:
					return "List2";
				case 2:
					return "List3";
				case 3:
					return "List4";
				case 4:
					return "List5";
				case 5:
					return "List6";
			}
			return "����!";
		
	}

	// ��Ӻ��Ƴ��������ķ����ݲ��ã�дΪ�յ�
	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}
}
