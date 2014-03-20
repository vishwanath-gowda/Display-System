package rvce.Display.com;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JEditorPane;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public Image image;
	public static JPanel imagepanel=null;
public JPanel panel_3 ;
public InputStream    fis;
public BufferedReader br;
public String         line;
public JPanel panel_1;
//public Scroll text;
public Thread imageThread=null;
public Thread textThread=null;
public static JLabel lblRvce=null;
Dimension screenSize=null;
double width;
double height;
private Thread scrollThread;
static JLabel scrollLabel=null;
static JLabel title=null;
double titlepanel_width,titlepanel_height;
double sidepanel_x,sidepanel_y;
double sidepanel_width,sidepanel_height;
double imagepanel_x,imagepanel_y;
double imagepanel_width,imagepanel_height;
double scrollpanel_x,scrollpanel_y;
double scrollpanel_width,scrollpanel_height;
public ImageIcon imageIcon;
public JLabel lblIcon=null;
public static JPanel htmlpanel = null;
JLabel lblTime;
Scanner s=null;
File dir=null;
String files[];
JPanel scrollPanel=null;
//String dirName="\\ScrollText";
File openFile=null;
FileInputStream file=null;
byte[] data;
public static JPanel textpanel=null;
public static JTextArea textArea=null;
public static JEditorPane htmlpane=null;
public Thread MainDisplayArea_t_o=null;
double scrollLabel_x;
double scrollLabel_y;
double scrollLabel_width;
double scrollLabel_height;
double lblRvce_x;
double lblRvce_y;
double lblRvce_width;
double lblRvce_height;
double notice_x;
double notice_y;
double notice_width;
double notice_height;
double evtitle_x;
double evtitle_y;
double evtitle_width;
double evtitle_height;
double icon_x;
double icon_y;
double icon_width;
double icon_height;
double titlepanel_x;
double titlepanel_y;
double title_x;
double title_y;
double title_width;
double title_height;
double datepanel_x;
double datepanel_y;
double datepanel_width;
double datepanel_height;
double lblDate_x;
double lblDate_y;
double lblDate_width;
double lblDate_height;
double lblDay_x;
double lblDay_y;
double lblDay_width;
double lblDay_height;
double lblTime_x;
double lblTime_y;
double lblTime_width;
double lblTime_height;
/**
 * 
 */
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		//setTitle("DisplayBoard");
		setBackground(new Color(255, 160, 122));
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("Resources/logo4.PNG")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		width=screenSize.getWidth();
		height=screenSize.getHeight();
		setBounds(0,0, (int)width,(int)height);
				
		 Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
 
		
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(UIManager.getBorder("InternalFrame.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		scrollPanel = new JPanel();
		scrollPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPanel.setBackground(SystemColor.window);
		scrollpanel_x=width*0.0065885798;
		scrollpanel_y=height*0.9114583333;
		scrollpanel_width= width*0.7869692533;
		scrollpanel_height=height*0.078125;
		scrollPanel.setBounds((int)scrollpanel_x, (int)scrollpanel_y,(int)scrollpanel_width , (int)scrollpanel_height);
		//scrollPanel.setBounds(9, 700,1075 , 56);
		contentPane.add(scrollPanel);
		scrollPanel.setLayout(null);
		
				
		scrollLabel = new JLabel("New label");
		scrollLabel.setForeground(Color.BLACK);
		scrollLabel.setBackground(new Color(253, 245, 230));
		scrollLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		scrollLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollLabel_x=scrollpanel_x*0.92279;
		scrollLabel_y=scrollpanel_y*0.017216;
		scrollLabel_width=scrollpanel_width*0.97484;
		scrollLabel_height=scrollpanel_height*0.616667;
		scrollLabel.setBounds((int)scrollLabel_x, (int)scrollLabel_y,(int)scrollLabel_width , (int)scrollLabel_height);
		//scrollLabel.setBounds(12, 12, 1046, 37);
		scrollPanel.add(scrollLabel);
		scrollThread=new Thread(new ScrollClass());
		scrollThread.start();	
				
		
		imagepanel = new JPanel();
		imagepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		imagepanel.setBackground(SystemColor.window);
		imagepanel_x=width*0.0073206442;
		imagepanel_y=height*0.109375;
		imagepanel_width=width*0.7862371889;
		imagepanel_height=height*0.7916666667;
		imagepanel.setBounds((int)imagepanel_x, (int)imagepanel_y,(int)imagepanel_width , (int)imagepanel_height);
		//imagepanel.setBounds(10, 84, 1074, 607);
		contentPane.add(imagepanel);
		imagepanel.setLayout(null);
		
				
		lblRvce = new JLabel("");
		lblRvce_x=imagepanel_x*0.76982;
		lblRvce_y=imagepanel_y*0.119;
		lblRvce_width=imagepanel_width*0.981;
	    lblRvce_height=imagepanel_height*0.96524;
		lblRvce.setBounds((int)lblRvce_x,(int)lblRvce_y, (int)lblRvce_width, (int)lblRvce_height);
		//lblRvce.setBounds(10,10, 1053, 582);
		imagepanel.add(lblRvce);
		lblRvce.setBackground(SystemColor.window);
		lblRvce.setOpaque(true);
		lblRvce.setIcon(new ImageIcon("\\Images\\1.jpg"));
		lblRvce.setHorizontalAlignment(SwingConstants.CENTER);
		lblRvce.setBorder(null);
		imageThread=new Thread(new ImageRotator());
		//imageThread.start();
		
		
		JPanel sidepanel = new JPanel();
		sidepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		sidepanel.setBackground(SystemColor.window);
		sidepanel_x=width*0.7994143485;
		sidepanel_y=height*0.109375;
		sidepanel_width=width*0.1972650073;
		sidepanel_height=height*0.8800020833;
		//sidepanel.setBounds(1092,84,264, 673);
		sidepanel.setBounds((int)sidepanel_x, (int)sidepanel_y,(int)sidepanel_width , (int)sidepanel_height);
		contentPane.add(sidepanel);
		sidepanel.setLayout(null);
				
							
		JLabel lblNotice = new JLabel("Today's Notice");
		lblNotice.setBackground(Color.WHITE);
		notice_x=sidepanel_x*0.00731;
		notice_y=sidepanel_y*0.130952381;
		notice_width=sidepanel_width*0.924246;
		notice_height=sidepanel_height*0.044576;
		lblNotice.setBounds((int)notice_x,(int)notice_y,(int)notice_width, (int)notice_height);
		//lblNotice.setBounds(8,11,244, 30);
		sidepanel.add(lblNotice);
		lblNotice.setForeground(Color.BLACK);
		lblNotice.setFont(new Font("Arial Black", Font.BOLD, 24));
		lblNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotice.setBorder(new MatteBorder(2, 2, 2, 2, (Color) null));
		
			
		JTextArea eventlist = new JTextArea();
		eventlist.setForeground(Color.BLACK);
		eventlist.setFont(new Font("Times New Roman", Font.BOLD, 15));
		eventlist.setText("admin will update theis column");
		evtitle_x=sidepanel_x*0.008234;
		evtitle_y=sidepanel_y*0.619;
		evtitle_width=sidepanel_width*0.928;
		evtitle_height=sidepanel_height*0.90739;
		eventlist.setBounds((int)evtitle_x,(int)evtitle_y,(int)evtitle_width, (int)evtitle_height);
		sidepanel.add(eventlist);
				
		
		JPanel titlepanel = new JPanel();
		titlepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		titlepanel.setBackground(SystemColor.window);
		titlepanel_x=width*0.0073206442;
		titlepanel_y=height*0.0143229167;
		titlepanel_width=0.7869692533* width;
		titlepanel_height=0.0846354167*height;
		titlepanel.setBounds((int)titlepanel_x,(int)titlepanel_y,(int)titlepanel_width, (int)titlepanel_height);
		//titlepanel.setBounds(10, 11, 1075,65);
		contentPane.add(titlepanel);
		titlepanel.setLayout(null);
		
				
		imageIcon = new ImageIcon("\\Icon\\logo4.png");
        lblIcon = new MyJLabel(imageIcon);
		lblIcon.setBorder(border);
		//lblIcon.setBounds(10, 11, 46, 47);
		lblIcon.setBackground(Color.WHITE);
		icon_x=titlepanel_x*0.8;
		icon_y=titlepanel_y*0.99999976;
		icon_width=titlepanel_width*0.04372;
		icon_height=titlepanel_height*0.6615384615;
		lblIcon.setBounds((int)icon_x,  (int)icon_y, (int)icon_width,(int)icon_height );
		//lblIcon.setBounds(8,  11, 47,43 );
		lblIcon.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		titlepanel.add(lblIcon);
		
		title = new JLabel("Welcome to RVCE");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 20));
		title.setBackground(Color.WHITE);
		title_x=titlepanel_x*6.5;
		title_y=titlepanel_y*0.9999999977;
		title_width=titlepanel_width*0.9302325581;
		title_height=titlepanel_height*0.6615384615;
		title.setBounds((int)title_x,  (int)title_y, (int)title_width,(int)title_height );
		//title.setBounds(65, 11, 1000, 43);
		titlepanel.add(title);
		
				
		JPanel datepanel = new JPanel();
		datepanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		datepanel.setBackground(Color.WHITE);
		datepanel_x=width*0.7994143485;
		datepanel_y=height*0.0143229167;
		datepanel_width=width*0.193265;
		datepanel_height=height*0.0846354167;
		datepanel.setBounds((int)datepanel_x,  (int)datepanel_y, (int)datepanel_width,(int)datepanel_height );
		//datepanel.setBounds(1092, 11, 264, 65);
		contentPane.add(datepanel);
		datepanel.setLayout(null);
		
		System.out.println(datepanel_x+"tp"+datepanel_y+"vv"+datepanel_width+"vv"+datepanel_height);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        java.util.Date date = new java.util.Date();
        String dateTime = dateFormat.format(date);
		
		JLabel lblDate = new JLabel(dateTime);
		lblDate_x=datepanel_x*0;
		lblDate_y=datepanel_y*1.9090909091;
		lblDate_width=datepanel_width*0.2992424276;
		lblDate_height=datepanel_height*0.4;
		lblDate.setBounds((int)lblDate_x,  (int)lblDate_y, (int)lblDate_width,(int)lblDate_height );
		//lblDate.setBounds(0, 21, 79, 26);
		datepanel.add(lblDate);
		lblDate.setBackground(Color.BLACK);
		lblDate.setForeground(Color.BLACK);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDate.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(64, 64, 64)));
		
		DateFormat d= new SimpleDateFormat("EEEE");
        java.util.Date dt = new java.util.Date();
        String day = d.format(dt);
				
		JLabel lblDay = new JLabel(day);
		lblDay_x=datepanel_x*0.07967;
		lblDay_y=datepanel_y*1.9090909091;
		lblDay_width=datepanel_width*0.3674242842;
		lblDay_height=datepanel_height*0.4;
		lblDay.setBounds((int)lblDay_x,  (int)lblDay_y, (int)lblDay_width,(int)lblDay_height );
		//lblDay.setBounds(87, 21, 97, 26);
		datepanel.add(lblDay);
		lblDay.setBackground(Color.BLACK);
		lblDay.setForeground(Color.BLACK);
		lblDay.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(64, 64, 64)));
		
		lblTime = new JLabel("");
		lblTime_x=datepanel_x*0.1694139;
		lblTime_y=datepanel_y*1.9090909091;
		lblTime_width=datepanel_width*0.2992424276;
		lblTime_height=datepanel_height*0.4;
		lblTime.setBounds((int)lblTime_x,  (int)lblTime_y, (int)lblTime_width,(int)lblTime_height );
		//lblTime.setBounds(185, 21, 79, 26);
		datepanel.add(lblTime);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");  
        ActionListener timerListener = new ActionListener()  
        {  
            public void actionPerformed(ActionEvent e)  
            {  
                Date date = new Date();  
                String time = timeFormat.format(date);  
                lblTime.setText(time);  
            }  
        };  
        Timer timer = new Timer(1000, timerListener);  
        // to make sure it doesn't wait one second at the start  
        timer.setInitialDelay(0);  
        timer.start();
		
		textpanel = new JPanel();
		textpanel.setBounds((int)imagepanel_x, (int)imagepanel_y,(int)imagepanel_width , (int)imagepanel_height);
		contentPane.add(textpanel);
		textpanel.setLayout(null);
		
		
		/*openFile=new File("\\ScrollText\\1_20140320_20140325.txt");
		file = new FileInputStream(openFile);
		data = new byte[(int)openFile.length()];
		file.read(data);
		file.close();
		
		String s = new String(data, "UTF-8");
		//System.out.println("file read and its content is "+s);*/
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
		textArea.setEditable(false);
		textArea.setBounds((int)lblRvce_x,(int)lblRvce_y, (int)lblRvce_width, (int)lblRvce_height);
		textpanel.add(textArea);
		textArea.setText("display");
		textThread=new Thread(new Display());
		//textThread.start();
		//textArea.setCaretPosition(0);
		//System.out.println("came at");
		
		htmlpanel = new JPanel();
		htmlpanel.setBounds((int)imagepanel_x, (int)imagepanel_y,(int)imagepanel_width , (int)imagepanel_height);
		contentPane.add(htmlpanel);
		htmlpanel.setLayout(null);
		
		htmlpane = new JEditorPane();
		htmlpane.setEditable(false);
		htmlpane.setVisible(true);
		htmlpane.setBounds((int)lblRvce_x,(int)lblRvce_y, (int)lblRvce_width, (int)lblRvce_height);
		htmlpane.setPage("file:///f:\\Priority1\\abc_20140311000000_20150313000000.html");
		htmlpanel.add(htmlpane);
		
		  lblIcon.repaint();
		  setUndecorated(true);
		  MainDisplayArea_t_o=new Thread(new MainDisplatAreat());
		 MainDisplayArea_t_o.start();
		textpanel.setVisible(false);
		imagepanel.setVisible(false);
		htmlpanel.setVisible(false);
	}
}

class MyJLabel extends JLabel
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon imageIcon;
    public MyJLabel(ImageIcon icon)
    {
        super();
        this.imageIcon = icon;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(imageIcon.getImage(),0,0,getWidth(),getHeight(),this);
    }
}