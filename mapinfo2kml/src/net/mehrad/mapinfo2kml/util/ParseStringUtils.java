package net.mehrad.mapinfo2kml.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
		String delim=isText?" \t":" ,\t";//TODO: text can have space and this fucks the text
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
	
	/**
	 * data structure that shows a file line by line
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String> getReadedLines(InputStream is) throws IOException {
		List<String> lines = new ArrayList<String>();
		
		DataInputStream in = new DataInputStream(is);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			lines.add(strLine);
		}
		br.close();
		in.close();
		is.close();

		return lines;
	}
	
}
