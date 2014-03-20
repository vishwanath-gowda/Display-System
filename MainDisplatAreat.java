package rvce.Display.com;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JPanel;

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
		try{
		for(;;){
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
		}
		}catch(InterruptedException ie){
			System.out.println("closing myself");
			return;
		}catch(Exception e){
			System.out.println(e.getStackTrace());
		}
	}




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

						TimeUnit.SECONDS.sleep(2);
					}else if(Ext.equalsIgnoreCase(".html")){
						System.out.println("In html case and setting page");
						textpanel.setVisible(false);
						imagepanel.setVisible(false);
						MainWindow.htmlpane.setPage("file:///"+dirName+"\\"+files[i]);
						htmlpanel.setVisible(true);
						TimeUnit.SECONDS.sleep(2);
					
						
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





















}
