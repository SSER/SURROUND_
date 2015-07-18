package Pattern;
import java.io.*;
import java.net.*;

import Common.*;
import Dbop.SqliteJBDC;

public class Serve {
	
	public Serve() {
		SqliteJBDC dbop = new SqliteJBDC();
		try {
			ServerSocket ss = new ServerSocket(8888);
			while (true){
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				System.out.println(u.getId()+"    "+u.getKey());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String userName = u.getId();
				String password = u.getKey();
				String type = u.getType();
		    	System.out.println("in server 24 : ");
				if (type.equals("login")){
				    boolean isIn = dbop.query(userName, password);
				    if (u.getKey().equals("123456") || isIn == true) {
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
				
				else if (type.equals("register")){
			    	System.out.println("in server 42 : "); 
					boolean isSuccess = dbop.insert(userName, password);
					 if (isSuccess){
						 m.setMsType("6");
						 oos.writeObject(m);
					 } 
					 else {
						 m.setMsType("7");
						 oos.writeObject(m);
					 }
				}
			}
		}
		catch (Exception e) {/*System.out.println("server thread receive fail 1");*/}
	}
}
