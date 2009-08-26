package net.mehrad.mapinfo2kml.parser;

import java.io.File;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.XlsModel;

public class XlsParser extends Parser{

	protected XlsModel xlsModel;
	protected File xlsdFile;
	
	public XlsParser(File xlsFile) {
		this.xlsdFile=xlsFile;
	}

	@Override
	public DataModel parse() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	public XlsModel getXlsModel() {
		return xlsModel;
	}

}
