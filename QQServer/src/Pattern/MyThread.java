package Pattern;
import java.net.*;
import java.io.*;

import Common.*;

public class MyThread extends Thread{
	
	Socket s;
	
	public MyThread(Socket s) {
		this.s = s;
	}
	
	public void run() {
		
		System.out.println("a new Server Thread start!");
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if (!ThreadFlag.hm.containsKey(m.getReceiver()))
					ThreadFlag.addThreadFlag(m.getReceiver(), this);
				
				if (m.getMsType().equals(MessageType.message_common_message)) {
					MyThread mt = ThreadFlag.getThread(m.getReceiver());
					ObjectOutputStream oos = new ObjectOutputStream(mt.s.getOutputStream());
					oos.writeObject(m);
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
