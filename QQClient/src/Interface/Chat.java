package Interface;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Date;
import java.io.*;
import java.net.*;
import Common.*;
import Pattern.*;
import Tools.*;
public class Chat extends JFrame implements ActionListener {
	
	JPanel jp;
	JTextArea texta = null;
	JScrollPane jsp = null;
	JTextField textf = null;
	JButton button = null;
	String tempOwnerID, tempFriendID;
	
	public Chat(String ownerID, String friendID) {
		
		tempOwnerID = ownerID;
		tempFriendID = friendID;
		
		texta = new JTextArea();
		jsp = new JScrollPane(texta);
		textf = new JTextField(10);
		button = new JButton("send");
		button.addActionListener(this);
		button.setActionCommand("command_send");
		jp = new JPanel();
		jp.add(textf); jp.add(button);
		
		this.add(jsp);
		this.add(jp,BorderLayout.SOUTH);
		
		this.setSize(400,300);
		this.setLocation(400, 400);
		this.setTitle(ownerID+" is chating with "+friendID);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("command_send")) {
			texta.append(tempOwnerID+" :   "+textf.getText()+"\r\n");
			Message m = new Message();
			m.setSender(tempOwnerID);
			m.setReceiver(tempFriendID);
			m.setCon(textf.getText());
			m.setSendTime(new Date().toString()); // remember this !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
	//		System.out.println("chat is sending message¡£¡£¡£");
			try {
	//			Socket s = new Socket("127.0.0.1",8888);
				ObjectOutputStream oos = new ObjectOutputStream((ManageThread.getLinkServer(tempOwnerID)).getS().getOutputStream());
				// try to put this thing up  it means just one object
				oos.writeObject(m);
	//			Connection c = new Connection();
	//			System.out.println("ok");
	//			c.send(m);
	//			System.out.println("ok");
	//			System.out.println("chat send message success!");
			}
			catch (Exception e1) {System.out.println("chat window send message fail");}
			textf.setText("");
		}
	}
	
//	public void run() {
//		while(true) {
//			try {
//				System.out.println("chat receiver thread start");
//				ObjectInputStream ois = new ObjectInputStream(Connection.s.getInputStream());
//				Message m = (Message)ois.readObject();
//				this.texta.append(m.getSender()+" :   "+m.getCon()+"\r\n");
//			}
//			catch (Exception e) {/*System.out.println("chat receiver fail");*/}
//		}
//	}
}
