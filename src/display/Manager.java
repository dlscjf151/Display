package display;

import display.client.ClientBook;
import display.message.MessageSet;

import javax.swing.*;
import java.io.*;


/*******************************************************************************
 * This provides the high level central control of the system
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class Manager {
	private final String MMS_DATA_FILE = "mmsdata.dat";

	ClientBook cb;

	MessageSet ms;

	/**
	 * This method runs at startup and restores a clientbook and mmessage set or
	 * creates new empty collections.
	 *
	 */
	public void startUp() {
		try {
			FileInputStream fis = new FileInputStream(MMS_DATA_FILE);
			ObjectInputStream ois = new ObjectInputStream(fis);

			cb = ClientBook.readFromFile(ois);
			ms = MessageSet.readFromFile(ois);
			fis.close();

		} catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(null, "No existing client/message data found");
			cb = new ClientBook();
			ms = new MessageSet(cb);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);
			System.exit(1);
		}
	}

	/**
	 * This method save the client book and message set to file and shuts the system
	 * down.
	 *
	 */
	public void shutDown() {

		try {
			FileOutputStream fos = new FileOutputStream(MMS_DATA_FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			cb.saveToFile(oos);
			ms.saveToFile(oos);
			fos.close();

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);
		}
	}

	/**
	 * Get client book so other parts of system can have access to this.
	 *
	 * @return client book object
	 */
	public ClientBook getClientBook() {
		return cb;
	}

	/**
	 * Get message set so other parts of system can have access to this.
	 *
	 * @return message set object
	 */
	public MessageSet getMessageSet() {
		return ms;
	}
}