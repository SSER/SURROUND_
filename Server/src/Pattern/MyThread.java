package Pattern;
import java.net.*;
import java.io.*;

import Common.*;

public class MyThread extends Thread{
	
	Socket s;
	String uid;
	
	public MyThread(Socket s, String uid) {
		this.s = s;
		this.uid = uid;
	}
	
	public void run() {
		
		if (!ThreadFlag.hm.containsKey(uid))
			//ThreadFlag.addThreadFlag(m.getReceiver(), this);
			ThreadFlag.addThreadFlag(uid, this);
		System.out.println("a new Server Thread start!");
		while (true) {
			try {
				
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				System.out.println("I;m in MyThread 22");
				
				String context = m.getCon();
				//System.out.println(m.getSender() + " 对 " + m.getReceiver() + "说 :" + m.getCon());
				
				if (!ThreadFlag.hm.containsKey(m.getSender()))
					//ThreadFlag.addThreadFlag(m.getReceiver(), this);
					ThreadFlag.addThreadFlag(m.getSender(), this);
				
				if (m.getMsType().equals("message_common_message")) {
					System.out.println(m.getSender() + " 对 " + m.getReceiver() + "说 :" + m.getCon());
					if (ThreadFlag.hm.containsKey(m.getReceiver())){
					    MyThread mt = ThreadFlag.getThread(m.getReceiver());
					    ObjectOutputStream oos = new ObjectOutputStream(mt.s.getOutputStream());
					    oos.writeObject(m);
					}
				}
				else if (m.getMsType().equals(MessageType.message_get_online_friend)) {
					String res = ThreadFlag.getOnlineUser();
					Message m2 = new Message();
					m2.setMsType(MessageType.message_ret_online_friend);
					m2.setCon(res);
					m2.setReceiver(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
				
//				System.out.println("Server receiver succesfully");
//				System.out.println(m.getSender()+m.getReceiver()+m.getCon()+m.getSendTime());
//				System.out.println("read message from client "+m.getSender()+" to "+m.getReceiver()+" "+m.getCon());			
			}
			catch (Exception e) {/*System.out.println("server thread receive fail 2");*/}
		}
	}
}
