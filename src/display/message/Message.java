package display.message;

import java.io.Serializable;

/*******************************************************************************
 * An individual message to be displayed for a client
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class Message implements Serializable {
	final int COST = 1;

	// INSTANCE VARIABLES ******************************************************
	private String clientID;

	private String messageText;

	private int daysRemaining;

	// CONSTRUCTORS ************************************************************

	/**
	 * Constructor
	 *
	 * @param pClientID      string uniquely identifying client
	 * @param pText          text of message to be displayed
	 * @param pDaysRemaining number of days before message expires
	 */
	public Message(String pClientID, String pText, int pDaysRemaining) {
		clientID = pClientID;
		messageText = pText;
		daysRemaining = pDaysRemaining;
	}

	// METHODS *****************************************************************

	/**
	 * Reduce days remaining before expiry by one
	 */
	public void decrementDays() {
		daysRemaining--;
	}

	/**
	 * Get expiry status of message
	 *
	 * @return true if expired, false otherwise
	 */
	public boolean hasExpired() {
		return (daysRemaining == 0);
	}

	/**
	 * Get client ID string
	 *
	 * @return string uniquely identifying client
	 */
	public String getClientID() {
		return clientID;
	}

	/**
	 * Get text of message
	 *
	 * @return text of message
	 */
	public String getText() {
		return messageText;
	}

	/**
	 * Get cost of message
	 *
	 * @return cost of message
	 */
	public int getCost() {
		return COST;
	}

	/**
	 * Override hashCode() from Object class
	 *
	 * @return hash code value for this Message
	 */
	public int hashCode() {
		return (clientID + messageText).hashCode();
	}

	/**
	 * Override equals() from Object class Messages are considered equal if the
	 * clientID and Text are both identical in the two Messages
	 *
	 * @param pOther Message with which to compare
	 * @return true if Messages equal, false otherwise
	 */
	public boolean equals(Object pOther) {
		Message otherMsg = (Message) pOther;

		return (clientID.equals(otherMsg.clientID) && messageText.equals(otherMsg.messageText));
	}

	/**
	 * 105. * format message data as a String 106. * 107. * @return string with
	 * message attributes one per line 108.
	 */

	public String toString() {
		return ("Message text: " + messageText + "\nClient: " + clientID + "\nDays left: " + daysRemaining);
	}
}