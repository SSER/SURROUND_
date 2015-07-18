package Tools;
import java.io.*;
import java.net.*;
import Common.*;

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
//				System.out.println("LinkServer 3");
				System.out.println("read message from server "+m.getSender()+" to "+m.getReceiver()+" :   "+m.getCon());
			}
			catch(Exception e) {System.out.println("LinkServer Thread Error!");}
		}
	}
}
