package net.mehrad.mapinfo2kml.parser;

import java.io.File;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.xls.XlsModel;

public class XlsParser extends Parser{

	protected XlsModel xlsModel;
	protected File xlsFile;
	
	public XlsParser(File xlsFile) {
		this.xlsFile=xlsFile;
	}

	/**
	 * . precondition xlsFile needs to be initialized
	 * . postcondition xlsModel is created
	 * @return DataModel 
	 */
	@Override
	public DataModel parse() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public XlsModel getXlsModel() {
		return xlsModel;
	}

}
