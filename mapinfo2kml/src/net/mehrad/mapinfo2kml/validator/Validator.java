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
	 * . precondition the translate method of the Tranlate class should be called, and also the input file
	 * needs to be initialized.
	 * . postcondition The file is either valid so translation process can go on, or its invalid and then an
	 * input validation exception will be thrown. 
	 * @param mifFile
	 * @throws ValidationException
	 */
	public abstract void validate(File file) throws ValidationException;

}
