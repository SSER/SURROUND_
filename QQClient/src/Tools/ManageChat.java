package Tools;
import java.util.*;
import Interface.*;

public class ManageChat {
	
	public static HashMap<String, Chat> hm = new HashMap<String, Chat>();
	
	public static void addChat(String ownerIDAndfriendID, Chat c) {
		hm.put(ownerIDAndfriendID, c);
	}
	
	public static Chat getChat(String ownerIDAndfriendID) {
		return (Chat)hm.get(ownerIDAndfriendID);
	}
}
