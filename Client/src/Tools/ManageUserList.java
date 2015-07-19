package Tools;
import java.util.*;
import Interface.*;

public class ManageUserList {
	
	public static HashMap<String, UserList> hm = new HashMap<String, UserList>();
	
	public static void addUserList(String userID, UserList ul) {
		hm.put(userID, ul);
	}
	
	public static UserList getUserList(String userID) {
		return (UserList)hm.get(userID);
	}
}
