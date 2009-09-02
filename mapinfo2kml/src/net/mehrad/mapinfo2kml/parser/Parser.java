package net.mehrad.mapinfo2kml.parser;

import net.mehrad.mapinfo2kml.DataModel;
import net.mehrad.mapinfo2kml.exception.ParserException;

/**
 * common parser tasks for mid/mif and excel files
 * @author Mehrad
 *
 */
public abstract class Parser {

	/**
	 * parses the files and throws exception if any errors occur
	 * . precondition The input files need to be set
	 * . postcondition DataModel can be converted to KML file
	 * @return DataModel
	 * @throws ParserException
	 */
	public abstract DataModel parse() throws ParserException;
	
}
