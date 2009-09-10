package net.mehrad.mapinfo2kml.parser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.mif.MifColumn;
import net.mehrad.mapinfo2kml.mif.MifCoordinate;
import net.mehrad.mapinfo2kml.mif.MifModel;
import net.mehrad.mapinfo2kml.mif.MifRegion;
import net.mehrad.mapinfo2kml.util.ParseStringUtils;

/**
 * MapInfo paser class reads Mif/Mid file line by line(in one iteration) 
 * and create a data structure (MIFModel/MIDModel) based on them.
 * @author Mehrad
 *
 */
public class MapinfoParser extends Parser {

	private static final String MIF_DATA_KEYWORD = "DATA";
	private static final String MIF_REGION_KEYWORD = "REGION";
	private static final String MIF_VERSION_KEYWORD = "VERSION";
	private static final String MIF_CHARSET_KEYWORD = "Charset";
	private static final String MIF_DELIMITER_KEYWORD = "DELIMITER";
	private static final String MIF_COLUMNS_KEYWORD = "COLUMNS";

	protected File midFile;
	protected File mifFile;

	public MapinfoParser(File midFile, File mifFile) {
		this.midFile = midFile;
		this.mifFile = mifFile;
	}

	/**
	 * . precondition midfile and miffile properties need be initialized .
	 * postcondition
	 * 
	 * @return DataModel
	 * 
	 */
	public DataModel parse() throws ParserException {

		MifModel mifModel = new MifModel();
		int linePointer = 0;

		try {

			List<String> mifFileLines = getReadedLines(mifFile);

			linePointer = handleMifHeader(mifFileLines, linePointer, mifModel);
			
			linePointer++;
			
			while (linePointer < mifFileLines.size()) {
				String line = (String) mifFileLines.get(linePointer);
				// TODO:fix this
				if (ParseStringUtils.startsWithIgnoreCase(line,MIF_REGION_KEYWORD)) {
					linePointer = handleRegion(mifFileLines, linePointer,
							mifModel);
				}
				linePointer++;
			}

		} catch (IOException e) {
			throw new ParserException(e);
		}

		return mifModel;
	}
	
	/**
	 * fills MifModel header section.
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @return
	 */
	private int handleMifHeader(List<String> mifFileLines, int linePointer,
			MifModel mifModel) {

		String line = (String) mifFileLines.get(linePointer);

		while (!ParseStringUtils.startsWithIgnoreCase(line,MIF_DATA_KEYWORD)) {

			if (ParseStringUtils.startsWithIgnoreCase(line,MIF_VERSION_KEYWORD)) {
				mifModel.setVersion(ParseStringUtils.getStringPart(line, 2));
			}
			if (ParseStringUtils.startsWithIgnoreCase(line,MIF_CHARSET_KEYWORD)) {
				mifModel.setCharset(ParseStringUtils.getStringPart(line, 2));
			}
			if (ParseStringUtils.startsWithIgnoreCase(line,MIF_DELIMITER_KEYWORD)) {
				mifModel.setDelimiter(ParseStringUtils.getStringPart(line, 2));
			}
			if (ParseStringUtils.startsWithIgnoreCase(line, MIF_COLUMNS_KEYWORD)) {
				linePointer = handleMifColumns(mifFileLines, linePointer,
						mifModel);
			}

			line = (String) mifFileLines.get(++linePointer);
		}
		return linePointer;
	}

	/**
	 * creates MifColumns and add them to MifModel while keeping line pointer
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @return
	 */
	private int handleMifColumns(List<String> mifFileLines, int linePointer,
			MifModel mifModel) {

		String line = (String) mifFileLines.get(linePointer);
		String numColumnsStr=ParseStringUtils.getStringPart(line, 2);
		int numColumns=Integer.parseInt(numColumnsStr);
		
		for(int i=0;i<numColumns;i++)
		{
			linePointer++;
			MifColumn mifColumn=new MifColumn();
			mifColumn.setName(ParseStringUtils.getStringPart(mifFileLines.get(linePointer),1));
			mifColumn.setType(ParseStringUtils.getStringPart(mifFileLines.get(linePointer),2));
			mifModel.addMifColumn(mifColumn);
		}
		
		return linePointer;
	}

	/**
	 * creates MifRegions and add them to MifModel while keeping line pointer 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @return
	 */
	private int handleRegion(List<String> mifFileLines, int linePointer, MifModel mifModel) {

		MifRegion mifRegion = new MifRegion();

		String firstLine = mifFileLines.get(linePointer);
		String numPolygonsStr = ParseStringUtils.getStringPart(firstLine, 2);
		int numPolygons = Integer.parseInt(numPolygonsStr);

		for (int j = 1; j <= numPolygons; j++) {
			linePointer++;
			String numPointStr = mifFileLines.get(linePointer);
			int numPoints = Integer.parseInt(numPointStr.trim());

			List<MifCoordinate> mifCoordinates = new ArrayList<MifCoordinate>();
			for (int k = 1; k <= numPoints; k++) {
				linePointer++;
				MifCoordinate mifCoordinate = new MifCoordinate();

				mifCoordinate.setX(Double.parseDouble(ParseStringUtils
						.getStringPart(mifFileLines.get(linePointer).trim(), 1)));
				mifCoordinate.setY(Double.parseDouble(ParseStringUtils
						.getStringPart(mifFileLines.get(linePointer).trim(), 2)));
				mifCoordinates.add(mifCoordinate);

			}
			mifRegion.addRegion(mifCoordinates);
		}
		mifModel.addMifData(mifRegion);

		return linePointer;
	}

	/**
	 * data structure that shows a file line by line
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private List<String> getReadedLines(File file) throws IOException {
		List<String> lines = new ArrayList<String>();
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;

		while ((strLine = br.readLine()) != null) {
			lines.add(strLine);
		}
		in.close();

		return lines;
	}

}
