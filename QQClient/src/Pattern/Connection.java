package Pattern; 
import java.io.*;
import java.net.*;
import Common.*;
import Tools.*;

public class Connection {
	
	public Socket s;
	
	public boolean send(Object o) {
		
		boolean b = false;
		try {
			s = new Socket("127.0.0.1",8888);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			if (ms.getMsType().equals("1")) {
				LinkServer ls = new LinkServer(s);
				ls.start();
				ManageThread.addThread(((User)o).getId(), ls);
				b = true;
			}	
		}
		catch(Exception e) {}
		return b;
	}
}
