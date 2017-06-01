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
		// 第几行，就是列表中的第几个UserInfo对象
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

	// 界面数据有变化时，模型对象的这个方法会被调用，暂时弹出说明框
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//		String info = rowIndex + "行" + columnIndex + "列的值改变: "
//				+ aValue.toString();
//		javax.swing.JOptionPane.showMessageDialog(null, info);
		System.out.println(aValue);
		data.x=rowIndex;
	}
	
	// 指定某单元格是否可编辑:第一列不可编缉，第一列是ID，是每个对象的唯一识别号
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex != 0) {
			return true;
		}
		return false;
	}

	// 取每一列的列名
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
			return "出错!";
		
	}

	// 添加和移除监听器的方法暂不用，写为空的
	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}
}
