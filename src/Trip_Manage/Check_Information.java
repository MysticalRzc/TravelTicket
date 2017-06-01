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
				return "密码长度过短，请更换密码";
			else if (line.matches ("^\\d+$"))
            {
                return "密码强度弱，建议添加字母";
            }
            else if (line.matches ("^[a-zA-Z]+$"))
            {
                return "密码强度弱，建议添加数字";
            }
            else if (line.matches ("(?i)^((\\d+[\\da-z]*[a-z]+)|([a-z]+[\\da-z]*\\d+)|([a-z]+[\\da-z]*[a-z]*)|(\\d+[\\da-z]*\\d*)){8,20}"))
            {
            	return "密码强度强";
            }
            else
            {
                return "密码中等强度，建议加强";
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
