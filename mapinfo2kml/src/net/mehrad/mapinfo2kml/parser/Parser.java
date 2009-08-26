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
	 * @return
	 * @throws ParserException
	 */
	public abstract DataModel parse() throws ParserException;
	
}
