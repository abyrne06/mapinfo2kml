package net.mehrad.mapinfo2kml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.exception.ValidationException;
import net.mehrad.mapinfo2kml.mif.MifCoordinate;
import net.mehrad.mapinfo2kml.mif.MifData;
import net.mehrad.mapinfo2kml.mif.MifModel;
import net.mehrad.mapinfo2kml.mif.MifRegion;
import net.mehrad.mapinfo2kml.parser.MapinfoParser;
import net.mehrad.mapinfo2kml.parser.XlsParser;
import net.mehrad.mapinfo2kml.validator.MidValidator;
import net.mehrad.mapinfo2kml.validator.MifValidator;
import net.mehrad.mapinfo2kml.validator.XlsValidator;
import net.mehrad.mapinfo2kml.xls.XlsModel;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.LinearRing;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.Point;
import org.boehn.kmlframework.kml.Polygon;

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

	protected File midFile;
	protected File mifFile;
	protected File xlsFile;

	/**
	 * Translator constructor, for setting excel file use setXlsFile method.
	 * 
	 * @param midFile
	 * @param mifFile
	 */
	public Translator(File midFile, File mifFile) {
		this.midFile = midFile;
		this.mifFile = mifFile;
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

		validate(midFile, mifFile, xlsFile);
		MapinfoParser mapinfoParser = new MapinfoParser(midFile, mifFile);
		XlsParser xlsParser = new XlsParser(xlsFile);

		MifModel mifModel = (MifModel) mapinfoParser.parse();
		XlsModel xlsModel=null;
		if (xlsFile != null)
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
		
		for (MifData mifData : mifModel.getMifDatas()) {
			
			if (mifData instanceof MifRegion) {
				document.addFeature(getPlaceMarkForRegion((MifRegion) mifData));
			}

		}

		return kml;
	}

	/**
	 * creates a placemark from MifRegion model
	 * @param mifRegion
	 * @return
	 */
	private Placemark getPlaceMarkForRegion(MifRegion mifRegion)
	{
		Placemark ifi = new Placemark("Polygon# ");
		ifi.setDescription("Description#");

		for (List<MifCoordinate> region : mifRegion.getRegions()) {

			List<Point> points = new ArrayList<Point>();
			for (MifCoordinate mifCoordinate : region) {
				Point point = new Point();
				point.setLongitude(mifCoordinate.getX());
				point.setLatitude(mifCoordinate.getY());
				points.add(point);
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
	 * validation workflow:
	 * 
	 * 1) validating MIF file 2) validating MID file 3) validating XLS file
	 * 
	 * for every step if file is not given or validator isnt setted, it ignores
	 * that step.
	 * 
	 * @param midFile
	 * @param mifFile
	 * @param xlsFile
	 * @throws ValidationException
	 */
	private void validate(File midFile, File mifFile, File xlsFile)
			throws ValidationException {

		if (mifFile != null && mifValidator != null)
			mifValidator.validate(mifFile);
		if (midFile != null && midValidator != null)
			midValidator.validate(mifFile);
		if (xlsFile != null && xlsValidator != null)
			xlsValidator.validate(mifFile);
	}

	public MidValidator getMidValidator() {
		return midValidator;
	}

	public void setMidValidator(MidValidator midValidator) {
		this.midValidator = midValidator;
	}

	public MifValidator getMifValidator() {
		return mifValidator;
	}

	public void setMifValidator(MifValidator mifValidator) {
		this.mifValidator = mifValidator;
	}

	public XlsValidator getXlsValidator() {
		return xlsValidator;
	}

	public void setXlsValidator(XlsValidator xlsValidator) {
		this.xlsValidator = xlsValidator;
	}

	public File getMidFile() {
		return midFile;
	}

	public void setMidFile(File midFile) {
		this.midFile = midFile;
	}

	public File getMifFile() {
		return mifFile;
	}

	public void setMifFile(File mifFile) {
		this.mifFile = mifFile;
	}

	public File getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
	}

}
