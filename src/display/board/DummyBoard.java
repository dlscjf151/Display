package display.board;

import display.TextWindow;
import display.message.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/*******************************************************************************
 * Contains list of messages and cycles through displaying each in turn
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class DummyBoard implements DisplayBoardControl {
	// INSTANCE VARIABLES ******************************************************
	private List<Message> messageList;
	private TextWindow textWindow;
	// CONSTRUCTORS ************************************************************

	/**
	 * Constructor
	 */
	public DummyBoard(TextWindow textWindow) {
		this.textWindow = textWindow;
		messageList = null;
	}

	// METHODS *****************************************************************

	/**
	 * Load messages for display from a specified Collection
	 *
	 * @param pMessages set of Messages to be displayed
	 */
	public void loadMessages(Collection<Message> pMessages) {
		messageList = new ArrayList<Message>(pMessages);
	}

	/**
	 * Cycle through messages displaying each in turn
	 */
	public void run() {
		// loop through all messages in list displaying each in turn
		textWindow.UpdateMessages(messageList);
	}
}