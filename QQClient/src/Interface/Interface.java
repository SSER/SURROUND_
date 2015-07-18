package Interface;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
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
	
	public static void main(String[] args) {
		new Interface();
	}
	
	public Interface() {
		button1 = new JButton(new ImageIcon("image/clear.gif"));
		button1.addActionListener(this);
		button1.setActionCommand("command_clear");
		button2 = new JButton(new ImageIcon("image/denglu.gif"));
		button2.addActionListener(this);
		button2.setActionCommand("command_login");
		button3 = new JButton(new ImageIcon("image/quxiao.gif"));
		button3.addActionListener(this);
		button3.setActionCommand("command_cancel");
		button4 = new JButton(new ImageIcon("image/xiangdao.gif"));
		button4.addActionListener(this);
		button4.setActionCommand("command_register");
		
		label1 = new JLabel("ID",JLabel.CENTER);
		label2 = new JLabel("Key",JLabel.CENTER);
		label3 = new JLabel("forget password",JLabel.CENTER);
		label3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 鼠标放在这时会变成手掌
		label3.setForeground(Color.BLUE);
		label4 = new JLabel("key protection",JLabel.CENTER);
		label4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
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
		
		jp2.setLayout(new GridLayout(3,3));
		jp2.add(label1); jp2.add(text1); jp2.add(button1);
		jp2.add(label2); jp2.add(text2); jp2.add(label3);
		jp2.add(box1); jp2.add(box2); jp2.add(label4);
		
		jp3.add(button2); jp3.add(button3); jp3.add(button4);
		
		jp4.setBackground(Color.BLUE);
		jp5.setBackground(Color.BLACK);
		
		tabbedpane = new JTabbedPane();
		tabbedpane.add("common user",jp2);
		tabbedpane.add("VIP user",jp4);
		tabbedpane.add("special user",jp5);
		
		this.add(jp1,BorderLayout.NORTH);
		this.add(tabbedpane);
		this.add(jp3,BorderLayout.SOUTH);
		
		ImageIcon p1 = new ImageIcon("image/bkq.jpg");
		this.setIconImage(p1.getImage()); // not just p1!!!
		this.setSize(400,250);
		this.setLocation(400, 400);
		this.setTitle("QQClient");
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
			u.setKey(new String(text2.getPassword())); // remember this way!!!
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
		
		else if (e.getActionCommand().equals("command_register")) {
			
		}
	}
}
