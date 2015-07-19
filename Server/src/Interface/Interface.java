package Interface;
import java.awt.*;
import java.awt.event.*; //!!!
import javax.swing.*;
import Pattern.*;
import Common.*;

public class Interface extends JFrame implements ActionListener {
	
	JButton button1 = null, button2 = null;
	JPanel jp;
	
	public static void main(String[] args) {
		new Interface();
	}
	
	public Interface() {
		button1 = new JButton("START SERVER");
		button1.addActionListener(this);
		button1.setActionCommand("command_start");
		button2 = new JButton("STOP SERVER");
		button2.addActionListener(this);
		button2.setActionCommand("command_stop");
		
		jp = new JPanel();
//		jp.setLayout(new GridLayout(2,1,200,20));
//		this.add(button1,BorderLayout.NORTH);
//		this.add(button2,BorderLayout.SOUTH);
		jp.add(button1);
		jp.add(button2);
		this.add(jp);
		
		this.setSize(300,100);
		this.setLocation(400, 400);
		this.setTitle("QQServer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("command_start")) {
			new Serve();
		}
		else if (e.getActionCommand().equals("command_stop")) {
			this.dispose();
		}
	}
}
