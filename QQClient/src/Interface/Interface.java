package Interface;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import Common.*;
import Pattern.*;
import Tools.ManageThread;
import Tools.ManageUserList;

public class Interface extends JFrame implements ActionListener{
	
	JPanel jp1 = null, jp2 = null, jp3 = null;
	JPanel jp4, jp5;
	JButton button1 = null, button2 = null, button3 = null, button4 = null;
	JLabel label1 = null, label2 = null, label3 = null, label4 = null, labelpic;
	JCheckBox box1 = null, box2 = null;
	JTextField text1 = null;
	JPasswordField text2;
	JTabbedPane tabbedpane = null;
	
	JLabel label11;
	JLabel label21;
	JTextField text21;
	JPasswordField text22;
	JButton button11;
	JButton button22;
	
	public static void main(String[] args) throws IOException {
		new Interface();
	}
	
	public Interface() throws IOException {
		BufferedImage im = ImageIO.read(new File("image/clear.gif"));
		ImageIcon imgicon = new ImageIcon("image/clear.gif");
		button1 = new JButton("删除号码");
		button1.addActionListener(this);
		button1.setActionCommand("command_clear");
		button2 = new JButton("登陆");
		button2.addActionListener(this);
		button2.setActionCommand("command_login");
		button3 = new JButton("取消");
		button3.addActionListener(this);
		button3.setActionCommand("command_cancel");
		button4 = new JButton("向导");
		button4.addActionListener(this);
		button4.setActionCommand("guide");
		
		label1 = new JLabel("用户名",JLabel.CENTER);
		label2 = new JLabel("密码",JLabel.CENTER);
		label3 = new JLabel("forget password",JLabel.CENTER);
		label3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 鼠标放在这时会变成手掌
		label3.setForeground(Color.BLUE);
		label4 = new JLabel("key protection",JLabel.CENTER);
		label4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		label11 = new JLabel("用户名",JLabel.CENTER);
		label21 = new JLabel("密码",JLabel.CENTER);
		text21 = new JTextField(10);
		text22 = new JPasswordField(20);

		button11 = new JButton("重新输入");
		button11.addActionListener(this);
		button11.setActionCommand("reset");
		
		button22 = new JButton("注册");
		button22.addActionListener(this);
		button22.setActionCommand("register");
		//JButton button21 = new JButton("forget password",JLabel.CENTER);
		
		box1 = new JCheckBox("hiding login");
		box2 = new JCheckBox("remember password");
		
		text1 = new JTextField(10);
		text2 = new JPasswordField(20);
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		
		labelpic = new JLabel(new ImageIcon("image/tou.gif"));
		jp1.add(labelpic);
		System.out.println(jp1.getBorder());
		jp2.setLayout(new GridLayout(3,3));
		jp2.add(label1); 
		jp2.add(text1); 
		jp2.add(button1);
		jp2.add(label2); 
		jp2.add(text2); 
		jp2.add(label3);
		jp2.add(box1); 
		jp2.add(box2); 
		jp2.add(label4);
		
		jp3.add(button2); jp3.add(button3); jp3.add(button4);
		jp4.setLayout(new GridLayout(2,3));
		//jp4.setBackground(Color.WHITE);
		jp4.add(label11);
		jp4.add(text21);
		jp4.add(button11);
		jp4.add(label21);
		jp4.add(text22);
		jp4.add(button22);
		
		//jp4.add()
		//jp4.add(label1);jp4.add(text1);jp4.add(button1);
		
		jp5.setBackground(Color.BLACK);
		
		tabbedpane = new JTabbedPane();
		tabbedpane.add("common user",jp2);
		tabbedpane.add("注册",jp4);
		//tabbedpane.add("special user",jp5);
		
		this.add(jp1,BorderLayout.NORTH);
		
		this.add(tabbedpane);
		this.add(jp3,BorderLayout.SOUTH);
		
		ImageIcon p1 = new ImageIcon("image/bkq.jpg");
		this.setIconImage(p1.getImage()); // not just p1!!!
		this.setSize(400,250);
		this.setLocation(400, 400);
		this.setTitle("登陆");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("command_clear")) {
			
		}
		
		else if (e.getActionCommand().equals("command_login")) {
			
			Verify v = new Verify();
			User u = new User();
			u.setId(text1.getText());
			System.out.println("In interface 147 id is :" + u.getId());
			u.setKey(new String(text2.getPassword())); // remember this way!!!
			u.setType("login");
			// getPassword return char[]   it can't convert to String
			// but String have the constructor String(char[] c)
			
			if (v.checkUser(u)) {
				
				UserList ul = new UserList(u.getId());
				ManageUserList.addUserList(u.getId(), ul);
				
				try {
					ObjectOutputStream oos = new ObjectOutputStream(ManageThread.getLinkServer(u.getId()).getS().getOutputStream());
					Message m = new Message();
					m.setMsType(MessageType.message_get_online_friend);
					m.setSender(u.getId());
					oos.writeObject(m);
				}
				catch (Exception e2) {}
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "incorrect username or password!");
			}
		}
		
		else if (e.getActionCommand().equals("command_cancel")) {
			this.dispose();
		}
		
		else if (e.getActionCommand().equals("guide")) {
			
		}
		
		else if (e.getActionCommand().equals("register")){
			System.out.println("in client 177 : ");
			User u = new User();
			u.setId(text21.getText());
			u.setKey(new String(text22.getPassword())); // remember this way!!!
			u.setType("register");
			
			Connection C = new Connection();
			if (C.rigester(u)){
				JOptionPane.showMessageDialog(this, "register success!");
			}
			else {
				JOptionPane.showMessageDialog(this, "register fail!");
			}
			
		}
		else if (e.getActionCommand().equals("reset"));
	}
}

