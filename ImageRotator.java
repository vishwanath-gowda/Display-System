package rvce.Display.com;

import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class ImageRotator implements Runnable {
	
	
	File dir=null;
	String files[];
	String dirName="f:\\Images";
	File openFile=null;
	FileInputStream fis=null;
	byte[] data;
	Timer t=null;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		dir = new File(dirName);
		if (dir.isDirectory() == false) {
			if (dir.exists() == false)
				System.out.println("There is no such directory!");
			else
				System.out.println("That file is not a directory.");
		}
		else {
			files = dir.list();
			//System.out.println("Files in directory \"" + dir + "\":");
			//for (int i = 0; i < files.length; i++)
				//System.out.println("   " + files[i]);
			try{
			for(;;){
				
				files = dir.list();
				files=getUnExpiredContentOnly();
				//System.out.println(files.length);
				for (int i = 0; i < files.length; i++){

					
//						//openFile=new File(dirName+"\\"+files[i]);
//						//fis = new FileInputStream(openFile);
//						data = new byte[(int)openFile.length()];
//						fis.read(data);
//						fis.close();
//						
//						String s = new String(data, "UTF-8");
//						System.out.println("file read and its content is "+s);
//						
//						Display.textArea.setText(s);
					MainWindow.lblRvce.setIcon(new ImageIcon(dirName+"\\"+files[i]));
						TimeUnit.SECONDS.sleep(2);
				

			}}}catch(Exception fnf){
				System.out.println("Exception in ImageRotator class");

				
				
			}

		}
		
	}
	public String[] getUnExpiredContentOnly() {
		// TODO Auto-generated method stub
		
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
