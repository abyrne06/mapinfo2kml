package net.mehrad.mapinfo2kml.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.mid.MidModel;
import net.mehrad.mapinfo2kml.mif.MifColumn;
import net.mehrad.mapinfo2kml.mif.MifCoordinate;
import net.mehrad.mapinfo2kml.mif.MifLine;
import net.mehrad.mapinfo2kml.mif.MifModel;
import net.mehrad.mapinfo2kml.mif.MifPLine;
import net.mehrad.mapinfo2kml.mif.MifPoint;
import net.mehrad.mapinfo2kml.mif.MifRectangle;
import net.mehrad.mapinfo2kml.mif.MifRegion;
import net.mehrad.mapinfo2kml.mif.MifText;
import net.mehrad.mapinfo2kml.util.ParseStringUtils;

/**
 * MapInfo paser class reads Mif/Mid file line by line(in one iteration) and
 * create a data structure (MIFModel/MIDModel) based on them.
 * 
 * @author Mehrad
 * 
 */
public class MapinfoParser extends Parser {

	private static final String MIF_DATA_KEYWORD = "DATA";
	private static final String MIF_REGION_KEYWORD = "REGION";
	private static final String MIF_POINT_KEYWORD = "POINT";
	private static final String MIF_LINE_KEYWORD = "LINE";
	private static final String MIF_PLINE_KEYWORD = "PLINE";
	private static final String MIF_POLYLINE_KEYWORD = "POLYLINE";
	private static final String MIF_RECTANGLE_KEYWORD = "RECTANGLE";
	private static final String MIF_ROUNDRECT_KEYWORD = "ROUNDRECT";
	private static final String MIF_ELLIPSE_KEYWORD = "ELLIPSE";
	private static final String MIF_ARC_KEYWORD = "ARC";
	private static final String MIF_TEXT_KEYWORD = "TEXT";
	private static final String MIF_VERSION_KEYWORD = "VERSION";
	private static final String MIF_CHARSET_KEYWORD = "Charset";
	private static final String MIF_DELIMITER_KEYWORD = "DELIMITER";
	private static final String MIF_COLUMNS_KEYWORD = "COLUMNS";

	protected List<String> midFileLines;
	protected List<String> mifFileLines;

	public MapinfoParser(List<String> midFileLines, List<String> mifFileLines) {
		this.midFileLines = midFileLines;
		this.mifFileLines = mifFileLines;
	}

	/**
	 * . precondition midfile and miffile properties need be initialized .
	 * postcondition
	 * 
	 * @return DataModel
	 * 
	 */
	public DataModel parse() throws ParserException {

		try {

			MidModel midModel=new MidModel();
			midModel.setMidFileLines(midFileLines);
			
			
			
			return parseMif(midModel);

		} catch (Throwable e) {
			throw new ParserException("Files syntax/syntaxes is incorrect",e);
		}
		
	}

	private DataModel parseMif(MidModel midModel) throws IOException {
		MifModel mifModel = new MifModel();
		int linePointer = 0;

		//parse header part
		linePointer = handleMifHeader(mifFileLines, linePointer, mifModel);
		//go to DATA line
		linePointer++;

		List<MifColumn> columns = mifModel.getColumns();
		String delimiter = mifModel.getDelimiter();
		
		int shapeCounter=0; //this will be used for mapping MID lines to proper Object
		while (linePointer < mifFileLines.size()) {
			String line = (String) mifFileLines.get(linePointer);
			//TODO: make it look good
			if (ParseStringUtils.startsWithIgnoreCase(line,MIF_REGION_KEYWORD)) {
				linePointer = handleRegion(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			} else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_POINT_KEYWORD)) {
				linePointer = handlePoint(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_LINE_KEYWORD)) {
				linePointer = handleLine(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (
						ParseStringUtils.startsWithIgnoreCase(line,MIF_PLINE_KEYWORD) ||
						ParseStringUtils.startsWithIgnoreCase(line,MIF_POLYLINE_KEYWORD)) 
			{
				linePointer = handlePLine(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_RECTANGLE_KEYWORD)) {
				linePointer = handleRectangle(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_ROUNDRECT_KEYWORD)) {
				//TODO: handle round rectangle seperately
				linePointer = handleRectangle(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_TEXT_KEYWORD)) {
				linePointer = handleText(mifFileLines, linePointer,mifModel,
						getMidRecord(++shapeCounter, midModel, delimiter, columns));
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_ELLIPSE_KEYWORD)) {
				shapeCounter++;
			}else if (ParseStringUtils.startsWithIgnoreCase(line,MIF_ARC_KEYWORD)) {
				shapeCounter++;
			}

			linePointer++;
		}


		return mifModel;
	}

	/**
	 * creates MifText and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */

	private int handleText(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {

		MifText mifText=new MifText();
		
		String firstLine = mifFileLines.get(linePointer);
		mifText.setText(ParseStringUtils.getStringPart(firstLine.trim(), 2,true));//TODO: needs support for  -> ""
		
		String secondLine = mifFileLines.get(++linePointer);
		
		MifCoordinate coordinate1=new MifCoordinate();
		MifCoordinate coordinate2=new MifCoordinate();

		coordinate1.setX(Double.parseDouble(ParseStringUtils.getStringPart(secondLine.trim(), 1)));
		coordinate1.setY(Double.parseDouble(ParseStringUtils.getStringPart(secondLine.trim(), 2)));

		coordinate2.setX(Double.parseDouble(ParseStringUtils.getStringPart(secondLine.trim(), 3)));
		coordinate2.setY(Double.parseDouble(ParseStringUtils.getStringPart(secondLine.trim(), 4)));

		mifText.setCoordinate1(coordinate1);
		mifText.setCoordinate2(coordinate2);
		
		mifText.setObjectData(map);
		mifModel.addMifData(mifText);

		return linePointer;

		
	}

	/**
	 * creates MifRectangle and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */
	private int handleRectangle(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {

		MifRectangle mifRectangle=new MifRectangle();
		String firstLine = mifFileLines.get(linePointer);
		MifCoordinate coordinate1=new MifCoordinate();
		MifCoordinate coordinate2=new MifCoordinate();

		coordinate1.setX(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 2)));
		coordinate1.setY(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 3)));

		coordinate2.setX(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 4)));
		coordinate2.setY(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 5)));

		mifRectangle.setCoordinate1(coordinate1);
		mifRectangle.setCoordinate2(coordinate2);
		mifRectangle.setObjectData(map);
		mifModel.addMifData(mifRectangle);

		return linePointer;

	}

	/**
	 * creates MifPLine and add them to MifModel while keeping line pointer
	 * implementation is same as MifRegion handler
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */
	private int handlePLine(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {
		
		MifPLine mifPLine = new MifPLine();

		int numSections;
		String firstLine = mifFileLines.get(linePointer);
		try{ // this should be here, MULTIPLE section is not mandatory and handling it is easier this way
			String numSectionsStr = ParseStringUtils.getStringPart(firstLine, 3);
			numSections=Integer.parseInt(numSectionsStr);
		}catch(Throwable t )
		{
			numSections = 1;
		}

		for (int j = 1; j <= numSections; j++) {
			
			String numPointStr;
			try{// if its in front of PLine
				if(numSections>1)
					numPointStr = mifFileLines.get(++linePointer);
				else
					numPointStr=ParseStringUtils.getStringPart(firstLine, 2);
			}catch(Throwable t )
			{
				numPointStr = mifFileLines.get(++linePointer);
			}

			int numPoints = Integer.parseInt(numPointStr.trim());

			List<MifCoordinate> mifCoordinates = new ArrayList<MifCoordinate>();
			for (int k = 1; k <= numPoints; k++) {
				linePointer++;
				MifCoordinate mifCoordinate = new MifCoordinate();

				mifCoordinate.setX(Double
						.parseDouble(ParseStringUtils.getStringPart(
								mifFileLines.get(linePointer).trim(), 1)));
				mifCoordinate.setY(Double
						.parseDouble(ParseStringUtils.getStringPart(
								mifFileLines.get(linePointer).trim(), 2)));
				mifCoordinates.add(mifCoordinate);

			}
			mifPLine.addSection(mifCoordinates);
		}
		mifPLine.setObjectData(map);
		mifModel.addMifData(mifPLine);

		return linePointer;
	}

	/**
	 * creates MifLine and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */

	private int handleLine(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {
		MifLine mifLine=new MifLine();
		String firstLine = mifFileLines.get(linePointer);
		MifCoordinate coordinate1=new MifCoordinate();
		MifCoordinate coordinate2=new MifCoordinate();

		coordinate1.setX(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 2)));
		coordinate1.setY(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 3)));

		coordinate2.setX(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 4)));
		coordinate2.setY(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 5)));

		mifLine.setCoordinate1(coordinate1);
		mifLine.setCoordinate2(coordinate2);
		mifLine.setObjectData(map);
		mifModel.addMifData(mifLine);

		return linePointer;
	}

	/**
	 * creates MifPoint and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */
	private int handlePoint(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {

		MifPoint mifPoint=new MifPoint();
		String firstLine = mifFileLines.get(linePointer);
		MifCoordinate coordinate=new MifCoordinate();
		coordinate.setX(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 2)));
		coordinate.setY(Double.parseDouble(ParseStringUtils.getStringPart(firstLine.trim(), 3)));
		
		mifPoint.setCoordinate(coordinate);
		mifPoint.setObjectData(map);
		mifModel.addMifData(mifPoint);
		
		return linePointer;
	}

	/**
	 * fills MifModel header section.
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @return
	 */
	private int handleMifHeader(List<String> mifFileLines, int linePointer,
			MifModel mifModel) {

		String line = (String) mifFileLines.get(linePointer);

		while (!ParseStringUtils.startsWithIgnoreCase(line, MIF_DATA_KEYWORD)) {

			if (ParseStringUtils
					.startsWithIgnoreCase(line, MIF_VERSION_KEYWORD)) {
				mifModel.setVersion(ParseStringUtils.getStringPart(line, 2));
			}
			if (ParseStringUtils
					.startsWithIgnoreCase(line, MIF_CHARSET_KEYWORD)) {
				mifModel.setCharset(ParseStringUtils.getStringPart(line, 2,true));
			}
			if (ParseStringUtils.startsWithIgnoreCase(line,
					MIF_DELIMITER_KEYWORD)) {
				mifModel.setDelimiter(ParseStringUtils.getStringPart(line, 2,true));
			}
			if (ParseStringUtils
					.startsWithIgnoreCase(line, MIF_COLUMNS_KEYWORD)) {
				linePointer = handleMifColumns(mifFileLines, linePointer,
						mifModel);
			}

			line = (String) mifFileLines.get(++linePointer);
		}
		return linePointer;
	}

	/**
	 * creates MifColumns and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @return
	 */
	private int handleMifColumns(List<String> mifFileLines, int linePointer,
			MifModel mifModel) {

		String line = (String) mifFileLines.get(linePointer);
		String numColumnsStr = ParseStringUtils.getStringPart(line, 2);
		int numColumns = Integer.parseInt(numColumnsStr);

		for (int i = 0; i < numColumns; i++) {
			linePointer++;
			MifColumn mifColumn = new MifColumn();
			mifColumn.setName(ParseStringUtils.getStringPart(mifFileLines
					.get(linePointer), 1));
			mifColumn.setType(ParseStringUtils.getStringPart(mifFileLines
					.get(linePointer), 2));
			mifModel.addMifColumn(mifColumn);
		}

		return linePointer;
	}

	/**
	 * creates MifRegions and add them to MifModel while keeping line pointer
	 * 
	 * @param mifFileLines
	 * @param linePointer
	 * @param mifModel
	 * @param map 
	 * @return
	 */
	private int handleRegion(List<String> mifFileLines, int linePointer,
			MifModel mifModel, Map<String, Object> map) {

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

				mifCoordinate.setX(Double
						.parseDouble(ParseStringUtils.getStringPart(
								mifFileLines.get(linePointer).trim(), 1)));
				mifCoordinate.setY(Double
						.parseDouble(ParseStringUtils.getStringPart(
								mifFileLines.get(linePointer).trim(), 2)));
				mifCoordinates.add(mifCoordinate);

			}
			mifRegion.addRegion(mifCoordinates);
		}
		mifRegion.setObjectData(map);
		mifModel.addMifData(mifRegion);

		return linePointer;
	}

	private Map<String,Object> getMidRecord(int shapeIndex,MidModel midModel,String delimiter,List<MifColumn> mifColumns)
	{
		Map<String,Object> objectData=new HashMap<String, Object>();
		if(midModel==null || midModel.getMidFileLines()==null || midModel.getMidFileLines().size()==0)
			return objectData;
		String recordStr=midModel.getMidFileLines().get(shapeIndex-1);
		
		StringTokenizer tokenizer=new StringTokenizer(recordStr, delimiter);
		
		for(int i=0;tokenizer.hasMoreTokens();i++)
		{
			objectData.put(mifColumns.get(i).getName(), tokenizer.nextToken());
		}
		return objectData;
		
	}
}
