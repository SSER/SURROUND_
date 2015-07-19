package Tools;
import java.util.*;
public class ManageThread {
	
	public static HashMap<String, LinkServer> hm = new HashMap<String, LinkServer>();
	
	public static void addThread(String senderID, LinkServer ls) {
		hm.put(senderID, ls);
	}
	
	public static LinkServer getLinkServer(String senderID) {
		return (LinkServer)hm.get(senderID);
	}
}
