package Trip_Manage;

import java.util.regex.Pattern;

public class Check_Information {
	public boolean check_user_name(String str){
//		String patterStr1 = "[a-zA-z]{3,20}$";
//		boolean result = Pattern.matches(patterStr1, str);
//		return result;
		return true;
		}
	public String check_passworld(String line){
			if(line.matches("^{0,6}"))
				return "���볤�ȹ��̣����������";
			else if (line.matches ("^\\d+$"))
            {
                return "����ǿ���������������ĸ";
            }
            else if (line.matches ("^[a-zA-Z]+$"))
            {
                return "����ǿ�����������������";
            }
            else if (line.matches ("(?i)^((\\d+[\\da-z]*[a-z]+)|([a-z]+[\\da-z]*\\d+)|([a-z]+[\\da-z]*[a-z]*)|(\\d+[\\da-z]*\\d*)){8,20}"))
            {
            	return "����ǿ��ǿ";
            }
            else
            {
                return "�����е�ǿ�ȣ������ǿ";
            }
	}
	public boolean check_phone(String line){
		if(line.matches("^1[8753][0-9]{9,9}"))
			return true;
		else
			return false;
	}
	public boolean check_ID(String line){
		if(line.matches("^[0-9]{18}"))
			return true;
		else
			return false;
	}
}
