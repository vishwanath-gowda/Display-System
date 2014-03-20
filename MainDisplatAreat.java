package rvce.Display.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Notes;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.SlideShow;

public class MainDisplatAreat implements Runnable{

	JPanel imagepanel=MainWindow.imagepanel;
	JPanel textpanel=MainWindow.textpanel;
	JPanel htmlpanel=MainWindow.htmlpanel;
	JEditorPane htmlpane=MainWindow.htmlpane;

	String dirName="f:\\Priority1";
	Scanner s=null;
	File dir=null;
	String files[];
	File openFile=null;
	FileInputStream fis=null;
	byte[] data;
	Timer t=null;
	String Ext;
	
	@Override
	public void run() {
		System.out.println("it came to main thread");
		int i;
		
		for(;;){
			try{
			for(i=0;i<10;i++){
				if(i<3){
					dirName="f:\\Priority1";
					displayFolderContents();
					dirName="f:\\Priority2";
					displayFolderContents();
					dirName="f:\\Priority3";
					displayFolderContents();
				}else if(i>=3&&i<7){

					dirName="f:\\Priority1";
					displayFolderContents();
					dirName="f:\\Priority2";
					displayFolderContents();


				}else{
					dirName="f:\\Priority1";
					displayFolderContents();

				}

			}
		
		}catch(InterruptedException ie){
			System.out.println("closing myself");
			System.exit(0);
			
			
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}}




	public void displayFolderContents() throws InterruptedException,Exception {
		dir = new File(dirName);
		if (dir.isDirectory() == false) {
			if (dir.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		}
		else {
			
				files = dir.list();
				files=getUnExpiredContentOnly();
				for (int i = 0; i < files.length; i++){
					Ext=files[i].substring(files[i].lastIndexOf("."), files[i].length());
					//System.out.println(files[i]+" has extension "+Ext);
					if(Ext.equalsIgnoreCase(".txt")){
						//System.out.println(".txt comparision worked");
						openFile=new File(dirName+"\\"+files[i]);
						fis = new FileInputStream(openFile);
						data = new byte[(int)openFile.length()];
						fis.read(data);
						fis.close();
						//System.out.println("in file");
						String s = new String(data, "UTF-8");
						//System.out.println("Ffile read and its content is "+s);

						textpanel.setVisible(true);
						imagepanel.setVisible(false);
						htmlpanel.setVisible(false);
						MainWindow.textArea.setText(s);

						TimeUnit.SECONDS.sleep(5);
					}else if(Ext.equalsIgnoreCase(".html")){
						System.out.println("In html case and setting page");
						textpanel.setVisible(false);
						imagepanel.setVisible(false);
						MainWindow.htmlpane.setPage("file:///"+dirName+"\\"+files[i]);
						htmlpanel.setVisible(true);
						TimeUnit.SECONDS.sleep(2);
					
						
					}else if(Ext.equalsIgnoreCase(".ppt")){
						Display(dirName+"/"+files[i]);
						System.out.println("comparision successfull");
					}
					else{
						//System.out.println(".jpg comparision worked");
						MainWindow.lblRvce.setIcon(new ImageIcon(dirName+"\\"+files[i]));
						textpanel.setVisible(false);
						imagepanel.setVisible(true);
						htmlpanel.setVisible(false);
						
						TimeUnit.SECONDS.sleep(2);
					}
				}
				//MainWindow.textArea.setText("");

			}		
			
		}
	

	public String[] getUnExpiredContentOnly() {
		//System.out.println("in unexp");
		//System.out.println("in getUnExpiredContentOnly");
		String from=null,to=null,cur=null;
		String ss[];
		String[] finalFiles=new String[files.length];
		int finalFilescount=-1;
		String withoutDot;

		GetCurrentTimeStamp gt= new GetCurrentTimeStamp();
		cur=gt.getDate();
		//System.out.println("cur time="+cur);
		//System.out.println("files.length="+files.length);

		for(int i=0; i<files.length;i++){
			//StringTokenizer st=new StringTokenizer(files[i]);
			//System.out.println("\n"+files[i]);
			ss=files[i].split("\\.");
			/*System.out.println("\n"+ss.length);
		for (String string : ss) {
			System.out.println("\n"+string);
		}*/

			withoutDot=ss[0];

			ss=withoutDot.split("_");
			from=ss[ss.length-2];
			//System.out.println("\n from "+from);
			to=ss[ss.length-1];
			//System.out.println("\n to "+to);


			cur=gt.getDate();
			//System.out.println("current time="+cur );
			if(gt.isGreater(cur, from)){
				//System.out.println(cur +" >"+from+" for file"+files[i]);

				if(gt.isGreater(to, cur)){
					//System.out.println(cur +" <"+to+" for file"+files[i]);
					//System.out.println("adding"+ files[i]);
					finalFiles[++finalFilescount]=files[i];
				}
			}
		}
		String send[]=new String[++finalFilescount];
		//System.out.println(finalFilescount);
		System.arraycopy(finalFiles, 0, send, 0, finalFilescount);
		/** for (String string1 : send) {
			 System.out.println("\n"+string1);
		}*/


		return send;
	}



	public void Display(String source) 
	{ 
		System.out.println("in display method");
	try { 
	// Create a slideshow object; this creates an underlying POIFSFileSystem object for us 
		System.out.println("No prob");
		SlideShow ppt = new SlideShow(new HSLFSlideShow(source)); 
	
		System.out.println("No prob");
	// Get all of the slides from the PPT file 
	Slide[] slides = ppt.getSlides(); 
	Dimension pgsize = ppt.getPageSize(); 
	//pgsize.width=2000;
	//pgsize.height=600;
	 
	//String temp=""; 
	//lblPage.setText(currentPage+" / "+all); 
for(int i=0;i<slides.length;i++){
	
	
	BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB); 
	//System.out.println(pgsize.width+" "+pgsize.height);
	Graphics2D graphics = img.createGraphics(); 
	//clear the drawing area 
	graphics.setPaint(Color.white); 
	//graphics.setBackground(Color.red); 
	graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height)); 

	//render 
	slides[i].draw(graphics); 
	//save the output 
	/*FileOutputStream out = new FileOutputStream("slide-" + (i + 1) + ".png"); 
	javax.imageio.ImageIO.write(img, "png", out); 
	out.close(); 
	//ImageIcon icon = new ImageIcon("slide-" + (i + 1) + ".png");*/ 
	ImageIcon icon = new ImageIcon(img); 
	MainWindow.slidelabel.setIcon(icon); 
	// Obtain metrics about the slide: its number and name 
	/*int number = slides[currentPage-1].getSlideNumber(); 
	String title = slides[currentPage-1].getTitle(); 

	// Obtain the embedded text in the slide 
	TextRun[] textRuns = slides[currentPage-1].getTextRuns(); 
	System.out.println("Slide " + number + ": " + title); 
	System.out.println("\tText Runs"); 
	txtArea.setText("Slide : " + number + " Title : " + title + "\n"); 
	for (int j = 0; j < textRuns.length; j++) { 
	// Display each of the text runs present on the slide 
	System.out.println("\t\t" + j + ": " + textRuns[j].getText()); 
	temp=txtArea.getText(); 
	txtArea.setText(temp+"\t\t" + textRuns[j].getText() + "\n"); 
	} 

	// Obtain the notes for this slide 
	System.out.println("\tNotes: "); 
	Notes notes = slides[currentPage-1].getNotesSheet(); 
	if (notes != null) { 
	// Notes are comprised of an array of text runs 
	TextRun[] notesTextRuns = notes.getTextRuns(); 
	for (int j = 0; j < notesTextRuns.length; j++) { 
	System.out.println("\t\t" + notesTextRuns[j].getText()); 
	} 
	} 

	*/
	TimeUnit.SECONDS.sleep(2);
}
	} catch (Exception e) { 
	e.printStackTrace(); 
	} 
	
	} 

}
