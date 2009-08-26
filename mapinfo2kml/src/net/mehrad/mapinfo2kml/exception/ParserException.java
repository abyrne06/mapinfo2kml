package net.mehrad.mapinfo2kml.exception;

/**
 * Exception class for Parsing step. all parent 
 * constructors are recreated for propagating exceptions
 * to higher levels while keeping the low level exceptions.
 * 
 * @author Mehrad
 *
 */public class ParserException extends Exception {

	private static final long serialVersionUID = 8755378835784648151L;

	public ParserException() {
		super();
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(Throwable cause) {
		super(cause);
	}

	
	
}
