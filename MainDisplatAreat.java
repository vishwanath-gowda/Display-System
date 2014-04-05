package rvce.Display.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


public class MainDisplatAreat implements Runnable{

	JPanel imagepanel=MainWindow.imagepanel;
	JPanel textpanel=MainWindow.textpanel;
	JPanel htmlpanel=MainWindow.htmlpanel;
	JEditorPane htmlpane=MainWindow.htmlpane;
	JLabel slidelabel=MainWindow.slidelabel;

	String dirName="f:\\Priority1";
	//Scanner s=null;
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
					slidelabel.setVisible(false);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					//					MainWindow.textArea.setText(s);
					displayText(s);

					TimeUnit.SECONDS.sleep(2);
				}else if(Ext.equalsIgnoreCase(".html")){
					System.out.println("In html case and setting page");
					textpanel.setVisible(false);
					imagepanel.setVisible(false);
					slidelabel.setVisible(false);
					MainWindow.htmlpane.setPage("file:///"+dirName+"\\"+files[i]);
					htmlpanel.setVisible(true);
					TimeUnit.SECONDS.sleep(2);


				}else if(Ext.equalsIgnoreCase(".ppt")){
					textpanel.setVisible(false);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					slidelabel.setVisible(true);
					DisplayPpt(dirName+"/"+files[i]);

				}else if(Ext.equalsIgnoreCase(".pptx")){
					textpanel.setVisible(false);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					slidelabel.setVisible(true);
					DisplayPptx(dirName+"/"+files[i]);
					System.out.println("pptx comparision successfull");
				}
				else if(Ext.equalsIgnoreCase(".doc")){
					textpanel.setVisible(true);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					slidelabel.setVisible(false);
					DisplayDoc(dirName+"/"+files[i]);
					System.out.println("pptx comparision successfull");
				}
				else if(Ext.equalsIgnoreCase(".docx")){
					textpanel.setVisible(true);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					slidelabel.setVisible(false);
					DisplayDocx(dirName+"/"+files[i]);
					System.out.println("pptx comparision successfull");
				}
				else if(Ext.equalsIgnoreCase(".pdf")){
					textpanel.setVisible(true);
					imagepanel.setVisible(false);
					htmlpanel.setVisible(false);
					slidelabel.setVisible(false);
					//DisplayPdf(dirName+"/"+files[i]);
					DisplayPdf(dirName+"/"+files[i]);
					System.out.println("pptx comparision successfull");
				}
				else{
					//System.out.println(".jpg comparision worked");
					
						ImageIcon ic=getResizedImageIcon(dirName+"\\"+files[i]);
					//MainWindow.lblRvce.setIcon(new ImageIcon(dirName+"\\"+files[i]));
						MainWindow.lblRvce.setIcon(ic);
					imagepanel.setVisible(true);
					textpanel.setVisible(false);
					htmlpanel.setVisible(false);

					TimeUnit.SECONDS.sleep(2);
				}
			}
			//MainWindow.textArea.setText("");

		}		

	}










	




	private String[] getUnExpiredContentOnly() {
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



	private void DisplayPpt(String source) 
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
			double zoom = 1; // magnify it by 2
			AffineTransform at = new AffineTransform();
			at.setToScale(zoom, zoom);

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
				graphics.setTransform(at);

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



	private void DisplayPptx(String source) throws IOException, InterruptedException{
		FileInputStream is = new FileInputStream(source);
		XMLSlideShow ppt = new XMLSlideShow(is);
		is.close();

		double zoom = 1; // magnify it by 2
		AffineTransform at = new AffineTransform();
		at.setToScale(zoom, zoom);

		Dimension pgsize = ppt.getPageSize();

		XSLFSlide[] slide = ppt.getSlides();
		BufferedImage icons[]= new BufferedImage[slide.length];
		for (int i = 0; i < slide.length; i++) {
			icons[i]= new BufferedImage((int)Math.ceil(pgsize.width*zoom), (int)Math.ceil(pgsize.height*zoom), BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = icons[i].createGraphics();
			graphics.setTransform(at);

			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
			slide[i].draw(graphics);
			ImageIcon icon = new ImageIcon(icons[i]); 
			MainWindow.slidelabel.setIcon(icon); 
			TimeUnit.SECONDS.sleep(2);
		}
	}


	private void DisplayDocx(String source) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(new File(source));
		XWPFDocument doc = new XWPFDocument(fis);
		XWPFWordExtractor extract = new XWPFWordExtractor(doc);
		// System.out.println(extract.getText());
		displayText(extract.getText());
		//MainWindow.textArea.setText(extract.getText());
		TimeUnit.SECONDS.sleep(2);


	}
	private void DisplayDoc(String source) throws IOException, InterruptedException {

		FileInputStream fis = new FileInputStream(new File(source));
		HWPFDocument doc = new HWPFDocument(fis);
		WordExtractor extractor = new WordExtractor(doc);
		// System.out.println(extractor.getText());
		displayText(extractor.getText());
		//MainWindow.textArea.setText(extractor.getText());

		TimeUnit.SECONDS.sleep(5);

	}
	private void DisplayPdf(String source) throws IOException, InterruptedException {
		// TODO Auto-generated method stub


		String pageText;
		PdfReader reader = new PdfReader(source);
		int n = reader.getNumberOfPages();
		for(int i=1;i<=n;i++){
			pageText=PdfTextExtractor.getTextFromPage(reader, i);
			displayText(pageText);
			//MainWindow.textArea.setText(pageText);
			TimeUnit.SECONDS.sleep(2);
		}


	}
	private void DisplayPdfWithImage(String source) throws IOException, InterruptedException {

	}


	private void displayText(String text) throws InterruptedException{
		
		Rectangle r=MainWindow.textArea.getBounds();
		float exnochars=20;
		float exSize=500;
		String curText=null;
		boolean isTextRemaining=true;
		
		int nooflines=r.height/60;
		if(nooflines==0){
			nooflines=1;
		}
		
		int textsize= (int)((exnochars/exSize)*(r.width));
		textsize*=nooflines;
		
		
		System.out.println(textsize);
	
		//System.out.println(r.width+" "+r.height+" "+r.x+" "+r.y);
		
		
		
		int length=text.length();
		int start=0,end=-1;
		
		
		
		while(isTextRemaining){
			
			
			start=end+1;
			end=end+textsize;
			if(end>=(length-1)){
				end=length-1;
				isTextRemaining=false;
				curText=text.substring(start, end);
				MainWindow.textArea.setText(curText);
				TimeUnit.SECONDS.sleep(5);
				continue;
			}
			
			while(text.charAt(end)!=' '){
				end--;
			}
			
			curText=text.substring(start, end);
				
				MainWindow.textArea.setText(curText);
				TimeUnit.SECONDS.sleep(5);
			}

			//MainWindow.textArea.setText(text);
		}
	
	private ImageIcon getResizedImageIcon(String string) {
		 ImageIcon icon = new ImageIcon(string);
		 icon = new ImageIcon(icon.getImage().getScaledInstance((int)MainWindow.lblRvce_width, (int)MainWindow.lblRvce_height, BufferedImage.SCALE_SMOOTH));
		 
		return icon;
	}

}
