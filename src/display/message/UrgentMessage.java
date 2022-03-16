package display.message;

import display.message.Message;

/*******************************************************************************
 * An urgent message to be displayed for a client
 *
 * @author Simon Kendal
 * @version 1.0 (29 June 2009)
 ******************************************************************************/
public class UrgentMessage extends Message {

	final int COST = 2;

	/**
	 * Constructor
	 *
	 * @param pClientID      string uniquely identifying client
	 * @param pText          text of message to be displayed
	 * @param pDaysRemaining number of days before message expires
	 */
	public UrgentMessage(String pClientID, String pText, int pDaysRemaining) {
		super(pClientID, pText, pDaysRemaining);
	}

	/**
	 * Get text of message
	 *
	 * @return text of message awith stars added before and after
	 */
	public String getText() {
		return "*** " + super.getText() + " ***";
	}

	/**
	 * Get cost of message
	 *
	 * @return cost of message
	 */
	public int getCost() {
		return COST;
	}

}