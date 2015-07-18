package Pattern;
import java.io.*;
import java.net.*;

import Common.*;

public class Serve {
	
	public Serve() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true){
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				System.out.println(u.getId()+"    "+u.getKey());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if (u.getKey().equals("123456")) {
					m.setMsType("1");
					oos.writeObject(m);
					MyThread mt = new MyThread(s);
					mt.start();
				}
				else {
					m.setMsType("2");
					oos.writeObject(m);
					s.close();
				}
			}
		}
		catch (Exception e) {/*System.out.println("server thread receive fail 1");*/}
	}
}
