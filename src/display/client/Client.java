package display.client;

import java.io.Serializable;

/*******************************************************************************
 * A customer of the message display service
 *
 * @author Simon Kendal
 * @version 1.0 (26 June 2009)
 ******************************************************************************/
public class Client implements Serializable {

	// INSTANCE VARIABLES ******************************************************

	private String name;

	private String address;

	private String phone;

	private int credit;

	// CONSTRUCTORS ************************************************************

	/**
	 * Constructor
	 *
	 * @param pName    name of client
	 * @param pAddress client's address
	 * @param pPhone   client's phone number
	 * @param pCredit  initial credit for client
	 */
	public Client(String pName, String pAddress, String pPhone, int pCredit) {
		name = pName;
		address = pAddress;
		phone = pPhone;
		credit = pCredit;
	}

	// METHODS *****************************************************************

	/**
	 * return the client's current credit
	 *
	 * @return credit units remaining
	 */
	public int getCredit() {
		return credit;
	}

	/**
	 * reduce client's remaining credit
	 *
	 * @param pDrecrease number of units of credit to be deducted
	 */
	public void decreaseCredit(int pDecrease) {
		credit = credit - pDecrease;
	}

	/**
	 * increase client's remaining credit by specified amount
	 *
	 * @param pExtraCredit units of credit to be added
	 */
	public void increaseCredit(int pExtraCredit) {
		credit = credit + pExtraCredit;
	}

	/**
	 * format client data as a String
	 *
	 * @return string with client attributes one per line
	 */
	public String toString() {
		return ("이름 : " + name + "\n주소 : " + address + "\n전화번호 : " + phone + "\n잔액 : " + credit);
	}
}