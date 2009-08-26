package net.mehrad.mapinfo2kml.exception;

/**
 * Exception class for Validation step. all parent 
 * constructors are recreated for propagating exceptions
 * to higher levels while keeping the low level exceptions.
 * 
 * @author Mehrad
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 6376557054017070686L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	
}
