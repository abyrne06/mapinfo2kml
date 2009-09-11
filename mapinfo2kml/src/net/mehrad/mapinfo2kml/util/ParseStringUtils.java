package net.mehrad.mapinfo2kml.util;

import java.util.StringTokenizer;

public class ParseStringUtils {

	/**
	 * utility method for getting words in a string by their indices
	 * @param originalString
	 * @param partIndex
	 * @return
	 */
	public static String getStringPart(String originalString,int partIndex,boolean isText)
	{
		String delim=isText?" \t":" ,\t";
		StringTokenizer stringTokenizer=new StringTokenizer(originalString, delim);
		for(int i=1;i<partIndex;i++)
			stringTokenizer.nextToken();
		
		String stringPart = stringTokenizer.nextToken().trim();

		if(stringPart.startsWith("\"") && stringPart.endsWith("\""))
		{
			stringPart=stringPart.substring(1,stringPart.length()-1);
		}

		return stringPart;
	}

	/**
	 * for getting correct text while text can contain comma
	 * @param originalString
	 * @param partIndex
	 * @return
	 */
	public static String getStringPart(String originalString,int partIndex)
	{
		return getStringPart(originalString, partIndex, false);
	}

	
	/**
	 * will be used for finding keyword in MIF file
	 * @param originalStr
	 * @param strToCheckWith
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String originalStr,String strToCheckWith)
	{
		return originalStr.trim().toLowerCase().startsWith(strToCheckWith.trim().toLowerCase());
	}
	
}
