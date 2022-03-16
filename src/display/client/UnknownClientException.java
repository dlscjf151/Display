package display.client;

import java.lang.Exception;

/*******************************************************************************
 * Exception thrown when attempting to get an non-existent client ID
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class UnknownClientException extends Exception {

	/**
	 * Constructor
	 *
	 * @param pMessage description of exception
	 */
	UnknownClientException(String pMessage) {
		super(pMessage);
	}
}
