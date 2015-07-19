package Pattern; 
import java.awt.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import Common.*;
import Tools.*;

public class Connection{
	
	public  Socket s;
	
	public Connection(){

	}
	
	public boolean login(Object o) {
		
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
	
	public boolean rigester(Object o){
		boolean b = false;
		try {
			Socket s1 = new Socket("127.0.0.1",8888);
			ObjectOutputStream oos = new ObjectOutputStream(s1.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s1.getInputStream());
			Message ms = (Message)ois.readObject();
			System.out.println("in client 39 : ");
			if (ms.getMsType().equals("6")) {
				System.out.println("register success!!");
				b = true;
			}
			else if (ms.getMsType().equals("7")) {
				System.out.println("register fail!!");
				b = false;
			}
		}
		catch(Exception e) {}
		return b;
	}
	
	public ArrayList<Object> getOnlineFriend(Object u){
		ArrayList<Object> onlines = new ArrayList<Object>();
		try {
		    ((User)u).setType("getOnline");
			System.out.println("in get connection");
		    s = new Socket("127.0.0.1",8888);
			System.out.println("in get connection");
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(u);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message)ois.readObject();
			String OnlineFriends = ms.getCon();
			System.out.println("in client 39 : " + OnlineFriends);
			String[] a = OnlineFriends.split(" ");
			
			System.out.println("I'm a0"+ a[ 0 ]);
			for (int i = 0; i < a.length; ++i){
				onlines.add(a[ i ]);
			}
		
		}  catch (Exception e) {
			System.out.println("Error in get connection");
		}
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return onlines;
	}
	
	public Socket getConnection(){
		try {
			s = new Socket("127.0.0.1",8888);
		} catch (Exception e) {}
		return s;
	}
}
