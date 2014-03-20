package rvce.Display.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;

public class ScrollClass implements Runnable {
	byte[] text=null;
	public String s=null;
	public String files[];
	File dir=null;
	File file=null;
	int contentlength;
	public String contents=" ";
	public String dirName="\\ScrollText";
	public double count=0;
	
	public void run(){
		//System.out.println("came into");

		contents=preparecontent();
		
		
		
		Timer tmrCon = new Timer();  
		tmrCon.scheduleAtFixedRate(new TimerTask()  
		{  


			public void run()  
			{  
				
				contents=preparecontent();	
			
			}  
		},0,10000);
		
		Timer tmr = new Timer();  
		tmr.scheduleAtFixedRate(new TimerTask()  
		{  


			public void run()  
			{  
				
				
				contents = new StringBuffer(contents.substring(1)).append(contents.substring(0,1)).toString();  
				MainWindow.scrollLabel.setText(contents); 
				
			}  
		},0,500);  
	  

	/*for(;;){
		System.out.println("printing");
	}*/


}

	private String preparecontent() {
		// TODO Auto-generated method stub
		//System.out.println("in preparecontent");
		String displayString=" ";
		try{
			
			
			dir = new File(dirName);
			files = dir.list();
			if (dir.exists() == false){
				System.out.println("There is no such directory!");
			return displayString;
			}
			
			
			
			
			//System.out.println(files.length);
			
			
			for (int i = 0; i < files.length; i++){
			//System.out.println(dirName+"\\"+ files[i]);
			file = new File(dirName+"\\"+ files[i]);
			 FileInputStream fis = new FileInputStream(file);
			    text = new byte[(int)file.length()];
			    //System.out.println(dirName+"\\"+ files[1] + "file contents ="+ text);
			    fis.read(text);
			s = new String(text, "UTF-8");
			//StringBuffer sb=new StringBuffer(s);
			displayString=new StringBuffer(s).append(" "+displayString).toString();
			//System.out.println(displayString);
			   
			    fis.close();
			}//for loop close
			
			
			
			contentlength=contents.length();
			dir=null;
			
			
		}//try close
		catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		catch(Exception e){
			e.printStackTrace();
		}
		//System.out.println("before return");
		return displayString;

	}
	



}



