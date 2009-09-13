package net.mehrad.mapinfo2kml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.exception.ValidationException;
import net.mehrad.mapinfo2kml.mif.MifCoordinate;
import net.mehrad.mapinfo2kml.mif.MifData;
import net.mehrad.mapinfo2kml.mif.MifLine;
import net.mehrad.mapinfo2kml.mif.MifModel;
import net.mehrad.mapinfo2kml.mif.MifPLine;
import net.mehrad.mapinfo2kml.mif.MifPoint;
import net.mehrad.mapinfo2kml.mif.MifRectangle;
import net.mehrad.mapinfo2kml.mif.MifRegion;
import net.mehrad.mapinfo2kml.mif.MifText;
import net.mehrad.mapinfo2kml.parser.MapinfoParser;
import net.mehrad.mapinfo2kml.parser.XlsParser;
import net.mehrad.mapinfo2kml.validator.MidValidator;
import net.mehrad.mapinfo2kml.validator.MifValidator;
import net.mehrad.mapinfo2kml.validator.XlsValidator;
import net.mehrad.mapinfo2kml.xls.XlsModel;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Feature;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.LineString;
import org.boehn.kmlframework.kml.LinearRing;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.Point;
import org.boehn.kmlframework.kml.PolyStyle;
import org.boehn.kmlframework.kml.Polygon;
import org.boehn.kmlframework.kml.Style;
import org.boehn.kmlframework.kml.StyleSelector;

/**
 * main translation controller. its dependent to mid and mif files meanwhile its
 * completely independent to validators and excel file.
 * 
 * @author Mehrad
 * 
 */
public class Translator {

	protected MidValidator midValidator;
	protected MifValidator mifValidator;
	protected XlsValidator xlsValidator;

	protected List<String> midFileLines;
	protected List<String> mifFileLines;
	protected List<String> xlsFileLines;

	/**
	 * Translator constructor, for setting excel file use setXlsFile method.
	 * 
	 * @param midFile
	 * @param mifFile
	 */
	public Translator(List<String> midFileInputStream, List<String> mifFileInputStream) {
		this.midFileLines = midFileInputStream;
		this.mifFileLines = mifFileInputStream;
	}

	/**
	 * translate workflow with following steps: 1) Validating Inputs 2)
	 * initializing parsers 3) parsing. 4) filling KML model . precondition:
	 * midFile, mifFile, xlsFile must be initialized first . postcondition: The
	 * output file can be generated using the generated Kml model
	 * 
	 * @return Kml
	 * @throws ParserException
	 * @throws ValidationException
	 */
	public Kml translate() throws ParserException, ValidationException {

//		validate(midFileInputStream, mifFileInputStream, xlsFileInputStream);
		MapinfoParser mapinfoParser = new MapinfoParser(midFileLines, mifFileLines);
		XlsParser xlsParser = new XlsParser(xlsFileLines);

		MifModel mifModel = (MifModel) mapinfoParser.parse();

		XlsModel xlsModel=null;
		if (xlsFileLines != null)
			xlsModel = (XlsModel) xlsParser.parse();

		Kml kml = fillKmlFromMifAndXls(mifModel, xlsModel);
		return kml;
	}

	/**
	 * fill KML model from Xls Model (Election data) and Mid model by knowing
	 * the mapping between fields.
	 * 
	 * @precondition midModel and xlsModel must be prepared by their responsible
	 *               parsers.
	 * @postcondition
	 * @param mifModel
	 * @param xlsModel
	 * @return
	 */
	private Kml fillKmlFromMifAndXls(MifModel mifModel, XlsModel xlsModel) {
		//TODO: handle XLS and MID
		Kml kml = new Kml();
		
		Document document = new Document();
		kml.setFeature(document);
		
		//TODO: clean this shit
		Style style=new Style();
		PolyStyle polyStyle=new PolyStyle();
		polyStyle.setFill(true);
		polyStyle.setColor("ff7fff55");
		style.setPolyStyle(polyStyle);
		style.setId("mehrad");
		List<StyleSelector> styles=new ArrayList<StyleSelector>();
		styles.add(style);
		document.setStyleSelectors(styles);
		
		for (MifData mifData : mifModel.getMifDatas()) {
			
			if (mifData instanceof MifRegion) {
				Placemark placeMarkForRegion = getPlaceMarkForMifRegion((MifRegion) mifData);
				placeMarkForRegion.setStyleUrl("#mehrad");
				document.addFeature(placeMarkForRegion);
			}
			else if (mifData instanceof MifPoint) {
				Feature placeMarkForPoint = getPlaceMarkForMifPoint((MifPoint) mifData);
				placeMarkForPoint.setStyleUrl("#mehrad");
				document.addFeature(placeMarkForPoint);
			}
			else if (mifData instanceof MifLine) {
				Feature placeMarkForLine = getPlaceMarkForMifLine((MifLine) mifData);
				placeMarkForLine.setStyleUrl("#mehrad");
				document.addFeature(placeMarkForLine);
			}
			else if (mifData instanceof MifPLine) { // it should handle multi-section Pline by creating more than one LineStrings
				for(Feature feature:getPlaceMarkForMifPLine((MifPLine) mifData))
				{
					feature.setStyleUrl("#mehrad");
					document.addFeature(feature);
				}
			}
			else if (mifData instanceof MifRectangle) { // it handles both MifRectangle and MifRoundRectangle 
				Feature placeMarkForRectangle = getPlaceMarkForMifRectangle((MifRectangle) mifData);
				placeMarkForRectangle.setStyleUrl("#mehrad");
				document.addFeature(placeMarkForRectangle);
			}
			else if (mifData instanceof MifText) {  
				Feature placeMarkForText = getPlaceMarkForMifRText((MifText) mifData);
				placeMarkForText.setStyleUrl("#mehrad");
				document.addFeature(placeMarkForText);
			}



		}

		return kml;
	}

	/**
	 * creates a PlaceMark with text which come from MifText.getText. this placemark
	 * will be positioned in the middle of the rectangle that presents MifText boundaries.
	 * @param mifData
	 * @return
	 */
	private Feature getPlaceMarkForMifRText(MifText mifData) {
		Point center=new Point();
		Double centerX=(mifData.getCoordinate1().getX()+mifData.getCoordinate2().getX())/2;
		Double centerY=(mifData.getCoordinate1().getY()+mifData.getCoordinate2().getY())/2;
		center.setLongitude(centerX);
		center.setLatitude(centerY);
		
		Placemark ifi = new Placemark(mifData.getText());
		ifi.setDescription(getPresentableDescription(mifData));
		ifi.setGeometry(center);
		return ifi;

	}

	/**
	 * cerates a Rectangle with LineString base on MifRectangle Model
	 * @param mifData
	 * @return
	 */
	private Feature getPlaceMarkForMifRectangle(MifRectangle mifData) {

		Placemark ifi = new Placemark(getPresentableTitle(mifData));
		ifi.setDescription(getPresentableDescription(mifData));

		List<Point> points=new ArrayList<Point>();
		Point UL=convertToPoint(mifData.getCoordinate1());
		Point DR=convertToPoint(mifData.getCoordinate2());
		
		Point UR=new Point();
		UR.setLongitude(DR.getLongitude());
		UR.setLatitude(UL.getLatitude());
		Point DL=new Point();
		DL.setLongitude(UL.getLongitude());
		DL.setLatitude(DR.getLatitude());
		
		points.add(UL);
		points.add(UR);
		points.add(DR);
		points.add(DL);
		points.add(UL);
		
		LineString lineString=new LineString();
		lineString.setCoordinates(points);
		ifi.setGeometry(lineString);
		return ifi;

	}

	/**
	 * creates a List of LineStrings based on a multi/single-section MifPline Model
	 * @param mifPLine
	 * @return
	 */
	private List<? extends Feature> getPlaceMarkForMifPLine(MifPLine mifPLine) {

		List<Placemark> ifis=new ArrayList<Placemark>();
		for (List<MifCoordinate> section : mifPLine.getSections()) {

			Placemark ifi = new Placemark(getPresentableTitle(mifPLine));
			ifi.setDescription(getPresentableDescription(mifPLine));

			List<Point> points = new ArrayList<Point>();
			for (MifCoordinate mifCoordinate : section) {
				points.add(convertToPoint(mifCoordinate));
			}

			LineString lineString=new LineString();
			lineString.setCoordinates(points);
			ifi.setGeometry(lineString);
			ifis.add(ifi);
			
		}

		return ifis;
	}

	/**
	 * creates a LineString(for KML) based on MifLine Model
	 * @param mifLine
	 * @return
	 */
	private Feature getPlaceMarkForMifLine(MifLine mifLine) {
		Placemark ifi = new Placemark(getPresentableTitle(mifLine));
		ifi.setDescription(getPresentableDescription(mifLine));

		List<Point> points=new ArrayList<Point>();
		Point start=convertToPoint(mifLine.getCoordinate1());
		Point end=convertToPoint(mifLine.getCoordinate2());
		points.add(start);
		points.add(end);
		
		LineString lineString=new LineString();
		lineString.setCoordinates(points);
		ifi.setGeometry(lineString);
		return ifi;
	}

	/**
	 * creates a placemark from MifPoint model
	 * @param mifPoint
	 * @return
	 */
	private Feature getPlaceMarkForMifPoint(MifPoint mifPoint) {
		Placemark ifi = new Placemark(getPresentableTitle(mifPoint));
		ifi.setDescription(getPresentableDescription(mifPoint));
		ifi.setGeometry(convertToPoint(mifPoint.getCoordinate()));
		return ifi;
	}

	/**
	 * creates a placemark from MifRegion model
	 * @param mifRegion
	 * @return
	 */
	private Placemark getPlaceMarkForMifRegion(MifRegion mifRegion)
	{
		Placemark ifi = new Placemark(getPresentableTitle(mifRegion));
		ifi.setDescription(getPresentableDescription(mifRegion));
		for (List<MifCoordinate> region : mifRegion.getRegions()) {

			List<Point> points = new ArrayList<Point>();
			for (MifCoordinate mifCoordinate : region) {
				points.add(convertToPoint(mifCoordinate));
			}

			LinearRing linearRing = new LinearRing();
			linearRing.setCoordinates(points);
			Polygon polygon = new Polygon();
			polygon.setOuterBoundary(linearRing);
			ifi.setGeometry(polygon);
			
		}
		return ifi;
		
	}

	/**
	 * creates a Point(for KML) based on MifCoordinateModel
	 * @param mifCoordinate
	 * @return
	 */
	private Point convertToPoint(MifCoordinate mifCoordinate)
	{
		Point point=new Point();
		point.setLongitude(mifCoordinate.getX());
		point.setLatitude(mifCoordinate.getY());
		return point;
		
	}


	//TODO: implements this
	private String getPresentableTitle(MifData mifData) {
		return null;
	}

	/**
	 * validation workflow:
	 * 
	 * 1) validating MIF file 2) validating MID file 3) validating XLS file
	 * 
	 * for every step if file is not given or validator isnt setted, it ignores
	 * that step.
	 * 
	 * @deprecated
	 * @param midFile
	 * @param mifFile
	 * @param xlsFileLines
	 * @throws ValidationException
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void validate(InputStream midFileInputStream, InputStream mifFileInputStream, InputStream xlsFileInputStream)
			throws ValidationException {

		if (mifFileInputStream != null && mifValidator != null)
			mifValidator.validate(mifFileInputStream);
		if (midFileInputStream != null && midValidator != null)
			midValidator.validate(mifFileInputStream);
		if (xlsFileInputStream != null && xlsValidator != null)
			xlsValidator.validate(mifFileInputStream);
	
		throw new UnsupportedOperationException("Method not implemented");
	}

	private String getPresentableDescription(MifData mifData)
	{
		StringBuffer buffer=new StringBuffer();
		Map<String, Object> objectData = mifData.getObjectData();
		for(String key:objectData.keySet())
		{
			buffer.append(key).append(": ").append(objectData.get(key)).append("<br/>");
		}
		return buffer.toString();
	}

	public void setMidValidator(MidValidator midValidator) {
		this.midValidator = midValidator;
	}

	public void setMifValidator(MifValidator mifValidator) {
		this.mifValidator = mifValidator;
	}

	public void setXlsValidator(XlsValidator xlsValidator) {
		this.xlsValidator = xlsValidator;
	}

	public void setMidFileLines(List<String> midFileLines) {
		this.midFileLines = midFileLines;
	}

	public void setMifFileLines(List<String> mifFileLines) {
		this.mifFileLines = mifFileLines;
	}

	public void setXlsFileLines(List<String> xlsFileLines) {
		this.xlsFileLines = xlsFileLines;
	}


}
