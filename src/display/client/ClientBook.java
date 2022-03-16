package display.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/*******************************************************************************
 * Collection of clients of the message display service
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class ClientBook implements Serializable {
	// CONSTANTS ***************************************************************

	// INSTANCE VARIABLES ******************************************************
	private Map<String, Client> clientMap;

	// CONSTRUCTORS ************************************************************

	/**
	 * Constructor for ClientBook
	 */
	public ClientBook() {
		clientMap = new HashMap<String, Client>();
	}

	// METHODS *****************************************************************

	/**
	 * Add client to book
	 *
	 * @param pClientID  string uniquely identifying client
	 * @param pNewClient client object to be associated with ID
	 */
	public void addClient(String pClientID, Client pNewClient) {
		clientMap.put(pClientID, pNewClient);
	}

	/**
	 * Find client for specific ID
	 *
	 * @param pClientID string uniquely identifying client
	 * @return client object associated with ID (or null if none exist)
	 */
	public Client getClient(String pClientID) throws UnknownClientException {
		Client foundClient;

		foundClient = clientMap.get(pClientID);

		if (foundClient != null) {
			return foundClient;
		} else {
			throw new UnknownClientException("ClientBook.getClient(): unknown client ID:" + pClientID);
		}
	}

	/**
	 * 65. * Delete client with specific ID 66. * 67. * @param pClientID string
	 * uniquely identifying client 68.
	 */
	public void deleteClient(String pClientID) throws UnknownClientException {
		Client removedClient;

		// removedClient = (Client) clientMap.remove(pClientID);
		removedClient = clientMap.remove(pClientID);

		if (removedClient == null) {
			throw new UnknownClientException("ClientBook.deleteClient(): unknown client ID:" + pClientID);
		}
	}

	/**
	 * Write data for all clients to file
	 */
	public void saveToFile(ObjectOutputStream oos) {
		try {
			oos.writeObject(this);

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);

		}
	}

	/**
	 * Read data for all clients from file
	 */
	static public ClientBook readFromFile(ObjectInputStream ois) {
		ClientBook cb = null;

		try {
			cb = (ClientBook) ois.readObject();

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "" + ioe);
			System.exit(1);
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "" + cnfe);
			cnfe.printStackTrace();
			System.exit(1);
		}

		return cb;
	}

}