package Pattern;
import java.util.*;

public class ThreadFlag {
	
	public static HashMap<String, MyThread> hm = new HashMap<String, MyThread>();
	
	public static void addThreadFlag (String receiverID, MyThread t) {
		hm.put(receiverID, t);
	}
	
	public static MyThread getThread (String receiverID) {
		return (MyThread)hm.get(receiverID);
	}
	
	public static String getOnlineUser() {
		Iterator it = hm.keySet().iterator(); // remember this!!!
		String res = "";
		while(it.hasNext()) {
			res += it.next().toString()+" ";
		}
		return res;
	}
}
