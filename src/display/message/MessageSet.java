package display.message;

import display.board.DisplayBoardControl;
import display.client.Client;
import display.client.ClientBook;
import display.client.UnknownClientException;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*******************************************************************************
 * Current collection of messages to be displayed
 *
 * @author Simon Kendal
 * @version 1.1 (29 June 2009)
 ******************************************************************************/
public class MessageSet implements Serializable {
	// CONSTANTS ***************************************************************

	// INSTANCE VARIABLES ******************************************************
	private Set<Message> messageSet;

	private ClientBook clients;

	// CONSTRUCTORS ************************************************************

	/**
	 * Constructor
	 *
	 * @param pClients the ClientBook containing client details for service
	 */
	public MessageSet(ClientBook pClients) {
		clients = pClients;
		messageSet = new HashSet<Message>();
	}

	// METHODS *****************************************************************

	/**
	 * Add a message to the current collection
	 *
	 * @param pMsgToAdd message to be added
	 */
	public void addMessage(Message pMsgToAdd) {
		messageSet.add(pMsgToAdd);
	}

	/**
	 * Display the messages on a specified DisplayBoard
	 *
	 * @param db implementation of DisplayBoard interface
	 */
	public void display(DisplayBoardControl db) {
		db.loadMessages(messageSet);
		db.run();
	}

	/**
	 * Remove messages which have expired or where the client's credit has run out
	 *
	 */
	public void dailyPurge() {

		Client client;

		// loop thorugh all current messages

		for (Message msg : messageSet) {

			// deduct 1 from days remaining for message
			msg.decrementDays();

			try {
				// decrease client credit for this message
				client = clients.getClient(msg.getClientID());
				client.decreaseCredit(msg.getCost());
//if message expired or client out of credit, remove message
				if (msg.hasExpired() || client.getCredit() <= 0) {
					messageSet.remove(msg);
				}
			} catch (UnknownClientException uce) {
				JOptionPane.showMessageDialog(null, "INTERNAL ERROR IN MessageSet.Purge()\n" + "Exception details: "
						+ uce + "\nMesssage details:\n" + msg);
				if (msg.hasExpired()) {
					messageSet.remove(msg);
				}

			}
		}

	}

	/**
	 * Write data for all messages to file
	 */
	public void saveToFile(ObjectOutputStream oos) {
		try {
			oos.writeObject(this);

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);
		}
	}

	/**
	 * Read data for all messages from file
	 */
	static public MessageSet readFromFile(ObjectInputStream ois) {
		MessageSet cb = null;

		try {
			cb = (MessageSet) ois.readObject();

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);
			System.exit(1);
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "" + cnfe);
			System.exit(1);
		}
		return cb;
	}

	public int CountOfMessages(){
		return messageSet.size();
	}
}