package display.board;

import display.message.Message;

import java.util.Collection;


/*******************************************************************************
 * Interface for board to display messages
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/

public interface DisplayBoardControl {
	// METHOD SIGNATURES *******************************************************

	/**
	 * Load messages for display from a specified Collection
	 *
	 * @param pMessages set of Messages to be displayed
	 */
	public void loadMessages(Collection<Message> pMessages);

	/**
	 * Cycle through messages displaying each in turn
	 */
	public void run();

}