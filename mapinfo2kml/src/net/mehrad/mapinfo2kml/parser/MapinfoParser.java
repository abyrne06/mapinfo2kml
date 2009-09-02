package net.mehrad.mapinfo2kml.parser;

import java.io.File;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.exception.ParserException;
import net.mehrad.mapinfo2kml.mid.MidModel;

public class MapinfoParser extends Parser{

	protected MidModel model;
	protected File midFile;
	protected File mifFile;
	
	public MapinfoParser(File midFile, File mifFile) {
		this.midFile=midFile;
		this.mifFile=mifFile;
	}

	/**
	 * . precondition midfile and miffile properties need be initialized
	 * . postcondition
	 * @return DataModel
	 * 
	 */
	public DataModel parse() throws ParserException{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Method not implemented");
	}

	public MidModel getModel() {
		return model;
	}

}
