package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import Chart.*;
public class ChartList extends JFrame implements ActionListener{
	private static final long serialVersionUID = 8813576290868649260L;
	//定义菜单属性,把在程序要要用到的菜单都定义成属性
    private JMenuBar mb=new JMenuBar();//菜单栏
    private JMenu file=new JMenu("文件");//文件菜单
    private JMenuItem feeling1=new JMenuItem("好感度");//“文件”菜单下的“新建”菜单项
    private JMenuItem Weight=new JMenuItem("好友比重");
    private JMenuItem APPERATIME=new JMenuItem("好友出没");
    
    private JMenu edit=new JMenu("编辑");
    private JRadioButtonMenuItem editLeft=new JRadioButtonMenuItem("左对齐");
    private JRadioButtonMenuItem editRight=new JRadioButtonMenuItem("右对齐");
    
    private JCheckBoxMenuItem editBold=new JCheckBoxMenuItem("加粗");
    private JCheckBoxMenuItem editItalic=new JCheckBoxMenuItem("斜体");
    
    private JMenu help=new JMenu("帮助");
    private JMenuItem showHelp=new JMenuItem("说明");
    //定义一个JPanel对象pCenter，放在中间部分，备用
    private JPanel pCenter=new JPanel();
    //下边是弹出菜单
    private JPopupMenu jpm=new JPopupMenu("弹出菜单");
    private JMenuItem popItem1=new JMenuItem("弹出菜单项一");
    private JMenuItem popItem2=new JMenuItem("弹出菜单项二");
    private void init(){
    	//将菜单加入到窗口上，一般把初始化窗口的代码放到自定义的init方法中，在构造
    	//方法中将调用init方法。
    	this.setTitle("绘制好友信息图形");
    	mb.add(file);
    	file.add(feeling1);file.add(Weight);
    	file.addSeparator();file.add(APPERATIME);
    	
    	//*为柱状图添加监听
    	feeling1.addActionListener(this);
    	feeling1.setActionCommand("bartchart");
    	//*饼状图
    	Weight.addActionListener(this);
    	Weight.setActionCommand("piechart");
    	//*另外一种柱状图
    	APPERATIME.addActionListener(this);
    	APPERATIME.setActionCommand("bartchart1");
    	
    	
    	mb.add(edit);edit.add(editLeft);edit.add(editRight);
    	ButtonGroup bg=new ButtonGroup();
    	bg.add(editLeft);bg.add(editRight);
    	edit.addSeparator();//添加一个菜单分隔符，便于将菜单按功能分隔开，效果好看
    	edit.add(editBold);edit.add(editItalic);
    	
     	mb.add(help);help.add(showHelp);
    	this.add(mb,BorderLayout.NORTH);
    	//上面把菜单栏的菜单都定义安装完毕
    	//下面把弹出菜单项添加到弹出菜单jpm上
    	jpm.add(popItem1);
		jpm.add(popItem2);
    	//设置窗口的关闭按钮动作
    	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    	
//    	this.add(pCenter,BorderLayout.CENTER);//这个是将来主窗口区备用的，本程序不用
    	//下边添加鼠标右键事件，目的显示弹出菜单
    	this.addMouseListener(new MouseAdapter(){
    		public void mouseClicked(MouseEvent me){
    			if(me.getButton()==MouseEvent.BUTTON3){
    				//在鼠标单击的组件及鼠标单击的坐标位置显示弹出菜单
    				jpm.show(me.getComponent(), me.getX(), me.getY());
    				//JOptionPane.showMessageDialog(null,"你的位置是("+me.getX()+","+me.getY()+")","",JOptionPane.OK_OPTION);
    			}
    		}
    	});
    	//设置窗口大小和显示状态
    	this.setSize(500,400);
    	this.setVisible(true);
    }
    public ChartList(){
    	init();
    }
    
    public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("bartchart")){
		    System.out.println("I'M in chartlist listener bartchart 103");
			if(e.getActionCommand().equals("bartchart")){
			    System.out.println("I'M in chart listener 106");
				JFrame frame = new BarChart1().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
			}
		}
		if(e.getActionCommand().equals("piechart")){
			 System.out.println("I'M in chart listener 110");
				JFrame frame = new PieChart().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
		}
		if(e.getActionCommand().equals("bartchart1")){
			 System.out.println("I'M in chart listener 120");
				JFrame frame = new BarChart().getChartFrame();
				frame.setVisible(true);
				frame.setSize(300, 300);
		}
	}

}
