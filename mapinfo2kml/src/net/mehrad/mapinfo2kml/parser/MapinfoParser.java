package net.mehrad.mapinfo2kml.parser;

import java.io.File;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.MidModel;
import net.mehrad.mapinfo2kml.exception.ParserException;

public class MapinfoParser extends Parser{

	protected MidModel model;
	protected File midfFile;
	protected File miffiFile;
	
	public MapinfoParser(File midFile, File mifFile) {
		this.midfFile=midFile;
		this.miffiFile=mifFile;
	}

	public DataModel parse() throws ParserException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented");
	}

	public MidModel getModel() {
		return model;
	}

}
