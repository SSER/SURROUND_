package Chart;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ImageProcess extends Frame{
	Image im, tmp;
	int iw, ih;
	int[] pixels;
	boolean flag_load = false;
	boolean flag_grey = false;
	String fileopen = null, filename = null;// 用于存放打开文件地址 和文件名
	
	public ImageProcess(){
		this.setTitle("直方图均衡化");
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		}
		);
	   
	    setLayout(new FlowLayout());
	    Button load = new Button("打开图像");
	    Button grey = new Button("灰度图像");
	    Button run = new Button("均衡化");
	    
	    
	    setLayout(new FlowLayout());
	    add(load);
	    add(grey);
	    add(run);
	    
	  //按钮的动作程序  打开图像
    	load.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			try {
					jLoad_ActionPerformed(e);
				} catch (IOException e1) {	
					e1.printStackTrace();
				}
    		}
    	});
    	//按钮的动作程序  灰度化
    	grey.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			try {
					jGrey_ActionPerformed(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
    		}
    	});
    	//按钮的动作程序 直方图均衡化
    	run.addActionListener(new ActionListener(){    
    		public void actionPerformed(ActionEvent e){
    			jRun_ActionPerformed(e);
    		}
    	});   
	}
	
	
	//按钮动作的实现  打开图像
    public void jLoad_ActionPerformed(ActionEvent e) throws IOException{    
    	FileDialog filedialog_open;
		
		filedialog_open = new FileDialog(this, "打开文件对话框", FileDialog.LOAD);
		filedialog_open.setFile("*.jpg");
		filedialog_open.setVisible(true);

		fileopen = filedialog_open.getDirectory();// 返回文件对话框中显示的文件所属的目录
		filename = filedialog_open.getFile();// 返回当前文件对话框中显示的文件名的字符串表

    	File inputFile = new File(fileopen+filename);
        BufferedImage input = ImageIO.read(inputFile);
        
        im = input;
		tmp = input;
        flag_load = true;
        repaint();
    }

   
    //按钮动作的实现  灰度化
    public void jGrey_ActionPerformed(ActionEvent e) throws IOException{
		if(flag_load){
		    File inputFile = new File(fileopen+filename);
  			BufferedImage input = ImageIO.read(inputFile);//ImageIO类包含一些用来查找 ImageReader 和 ImageWriter 以及执行简单编码和解码的静态便捷方法。
			
		  	iw = input.getWidth(this);
  		    ih = input.getHeight(this);
  		    pixels = new int[iw*ih];
  		    
			BufferedImage grayImage = new BufferedImage(iw, ih, BufferedImage.TYPE_BYTE_GRAY);
			for(int i=0; i<iw; i++){
  				for(int j=0; j<ih; j++){
  					int rgb = input.getRGB(i, j);  
  					int grey = (int) (0.3*((rgb&0xff0000 )>>16)+0.59*((rgb&0xff00 )>>8)+0.11*((rgb&0xff))); 
  					rgb = 255<<24|grey<<16|grey<<8|grey;
  					grayImage.setRGB(i, j, rgb);
  				}
  			}
  			tmp = grayImage;
  			try{
    			PixelGrabber pg = new PixelGrabber(tmp,0,0,iw,ih,pixels,0,iw);
    			pg.grabPixels();
    		}catch(InterruptedException e3){
    			e3.printStackTrace();
    	}
  			flag_grey = true;
  			repaint();
			} else{
				JOptionPane.showMessageDialog(null, "先点击“打开图像”！","提示：",
						JOptionPane.WARNING_MESSAGE);
			}
	}

    
    
  //按钮动作的实现 直方图均衡化 
    public void jRun_ActionPerformed(ActionEvent e){
    	if(flag_load&&flag_grey){
    		try{
    			PixelGrabber pg = new PixelGrabber(tmp,0,0,iw,ih,pixels,0,iw);
    			pg.grabPixels();
    		}catch(InterruptedException e3){
    			e3.printStackTrace();
    	}
    	BufferedImage greyImage = new BufferedImage(iw, ih, 
					BufferedImage.TYPE_BYTE_GRAY);
    	//获取图像的直方图
    	int[] histogram = new int[256];
    	for(int i=0; i<ih-1; i++){
    		for(int j=0; j<iw-1; j++){
    			int grey = pixels[i*iw+j]&0xff;
    			histogram[grey]++;
    		}
    	}
    	//直方图均衡化
    	double a = (double)255/(iw*ih);
    	double[] c = new double [256];
    	c[0] = (a*histogram[0]);
    	for(int i=1; i<256; i++){
    		c[i] = c[i-1]+(int)(a*histogram[i]);
    	}
    	for(int i=0; i<ih; i++){
    		for(int j=0; j<iw; j++){
    			int grey = pixels[i*iw+j]&0x0000ff;
    			int hist = (int)(c[grey]);
    			pixels[i*iw+j] = 255<<24|hist<<16|hist<<8|hist;
    			greyImage.setRGB(j, i, pixels[i*iw+j]);
    		}
    	}
    	tmp = greyImage;
  		flag_load = true;
  		repaint();
    	}else{
			JOptionPane.showMessageDialog(null, "先点击“打开图像”！","提示：",
					JOptionPane.WARNING_MESSAGE);
		}
    }


    //绘图函数
  	public void paint(Graphics g){

  			g.drawImage(tmp,50,50,this);
  	}
}