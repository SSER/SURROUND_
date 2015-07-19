package Tools;
import java.util.*;
import Interface.*;

public class ManageChat {
	
	public static HashMap<String, Chat> hm = new HashMap<String, Chat>();
    public static HashMap<String,String> chatBuffer = new HashMap<String, String>();
    
    public static void addChatBuffer(String key,String Content){
    	if (chatBuffer.containsKey(key)){
    		String val = chatBuffer.get(key);
    		val = val + "13078061085akabboy" + Content;	
    		chatBuffer.put(key,val);
    		System.out.println("In buffer key is:"+key+"content is:"+val);
    	}
    	else {
    		String val = Content;
    		chatBuffer.put(key, val);
    	}
    }
    public static String[] getChatBuffer(String key){
    	String record = chatBuffer.get(key);
    	String[] contents = record.split("13078061085akabboy");
    	System.out.println("In get chat buffer content is :"+record);
    	return contents;
    }
	public static void addChat(String ownerIDAndfriendID, Chat c) {
		hm.put(ownerIDAndfriendID, c);
	}
	
	public static Chat getChat(String ownerIDAndfriendID) {
		return (Chat)hm.get(ownerIDAndfriendID);
	}
}
