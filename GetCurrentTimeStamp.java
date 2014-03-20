package rvce.Display.com;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.*;
 
public class GetCurrentTimeStamp 
{
	public Date date;
	String f;
	public SimpleDateFormat sdf;
	
	public String getDate()
	{
		Date date = new Date();
		sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedDate = sdf.format(date);
		
		return (formattedDate);/*
		String dateStr = "Jul 27, 2011 8:35:29 AM";
		DateFormat readFormat = new SimpleDateFormat( "MMM dd, yyyy hh:mm:ss aa");
		DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try
		{
		    date = readFormat.parse( dateStr );
		}
		catch ( ParseException e )
		{
		        e.printStackTrace();
		}
		if( date != null )
		{
		    f = writeFormat.format( date );
		}
		return f;*/
		
	}
	public boolean isGreater(String a,String b){
	
	double ad = Double.parseDouble(a);
	double bd = Double.parseDouble(b);
	return(ad>bd?true:false);

	}
   
}