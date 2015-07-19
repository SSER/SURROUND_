package Tools;
import java.io.*;
import java.net.*;
import Common.*;
import Interface.Chat;

public class LinkServer extends Thread{

	private Socket s;
	
	public LinkServer(Socket s) {
		this.s = s;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
	public void run() {
		System.out.println("LinkServer Thread Start!");
		while(true) {
			try {
//				System.out.println("LinkServer 1");
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
//				System.out.println("LinkServer 2");
				Message m = (Message)ois.readObject();
				//≤‚ ‘”√
				
				System.out.println("important!!" + " " + m.getSender()+ " " + m.getReceiver() + " " + m.getCon());
				
				System.out.println("keyy is !: " + m.getSender() + " " + m.getReceiver());
				if (m.getMsType().equals("message_common_message")){
				    if (ManageChat.hm.containsKey(m.getReceiver() + " " + m.getSender())){
					    Chat c = ManageChat.getChat(m.getReceiver() + " " + m.getSender());
				        c.AddMessage(m);
				   
				    }
				    else {
				    	ManageChat.addChatBuffer(m.getSender(), m.getCon());
				        System.out.println("I'm in linkserver 40 with buffer configure The sender id is : " + m.getSender());
				        
				    }
				}

				System.out.println("LinkServer 3");
				System.out.println("I'm In Linkserver 31");
				System.out.println("important!!" + " " + m.getSender()+ " " + m.getReceiver() + " " + m.getCon());
				System.out.println("read message from server "+m.getSender()+" to "+m.getReceiver()+" :   "+m.getCon());
			}
			catch(Exception e) {System.out.println("LinkServer Thread Error!");}
		}
	}
}
