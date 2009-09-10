package net.mehrad.mapinfo2kml.util;

import java.util.StringTokenizer;

public class ParseStringUtils {

	public static String getStringPart(String originalString,int partIndex)
	{
		StringTokenizer stringTokenizer=new StringTokenizer(originalString, " ,\t");
		for(int i=1;i<partIndex;i++)
			stringTokenizer.nextToken();
		return stringTokenizer.nextToken().trim();
	}
	
	public static boolean startsWithIgnoreCase(String originalStr,String strToCheckWith)
	{
		return originalStr.trim().toLowerCase().startsWith(strToCheckWith.trim().toLowerCase());
	}
	
}
