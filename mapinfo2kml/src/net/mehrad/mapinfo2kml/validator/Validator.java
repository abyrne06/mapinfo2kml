package net.mehrad.mapinfo2kml.validator;

import java.io.File;

import net.mehrad.mapinfo2kml.exception.ValidationException;

/**
 * base validator class for common aspects of input validation
 * in mid/mif and xml files.
 * @author Mehrad
 *
 */
public abstract class Validator {

	/**
	 * validates input file. instead of returning
	 * a success/fail code, changes program flow 
	 * by throwing a ValidationException
	 *   
	 * @param mifFile
	 * @throws ValidationException
	 */
	public abstract void validate(File file) throws ValidationException;

}
