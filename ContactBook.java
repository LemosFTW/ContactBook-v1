
public class ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private Contact[] contacts;

	public ContactBook() {
		counter = 0;
		contacts = new Contact[DEFAULT_SIZE];
	}

	/** Pre: name != null */
	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	/** Pre: name!= null && !hasContact(name) */
	public void addContact(String name, int phone, String email) {
		if (counter == contacts.length)
			resize();
		contacts[counter] = new Contact(name, phone, email);
		counter++;
	}

	/** Pre: name != null && hasContact(name) */
	public void deleteContact(String name) {
		int index = searchIndex(name);
		for (int i = index; i < counter; i++)
			contacts[i] = contacts[i + 1];
		counter--;
	}

	/** Pre: name != null && hasContact(name) */
	public int getPhone(String name) {
		return contacts[searchIndex(name)].getPhone();
	}

	/** Pre: name != null && hasContact(name) */
	public String getEmail(String name) {
		return contacts[searchIndex(name)].getEmail();
	}

	/** Pre: name != null && hasContact(name) */
	public void setPhone(String name, int phone) {
		contacts[searchIndex(name)].setPhone(phone);
	}

	/** Pre: name != null && hasContact(name) */
	public void setEmail(String name, String email) {
		contacts[searchIndex(name)].setEmail(email);
	}

	public ContactIterator iterator() {
		return new ContactIterator(contacts, counter);
	}

	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i < counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found)
			result = i;
		return result;
	}

	private void resize() {
		Contact tmp[] = new Contact[2 * contacts.length];
		for (int i = 0; i < counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}

	// metodos para o gn e ep.
	public String searchNumber(int number) {
		int count = 0;
		String contact = "";
		for (int i = 0; i < getNumberOfContacts(); i++) {
			if (contacts[i].getPhone() == number && count < 1) {
				count++;
				contact = contacts[i].getName();
			}
		}
		if (count == 0) {
			contact = "nenhum";
		}
		return contact;
	}

	private int getNumberOfIndex(int index) {
		int number;
		number = contacts[index].getPhone();
		return number;

	}

	//mudar para um while pra parar quando achar o mesmo numero
	public boolean sameNumber() {
		boolean tem = false;
		int number = 0;
		for (int i = 0; i < getNumberOfContacts(); i++) {
			number = getNumberOfIndex(i);
			int count = 0;
			for (int j = 1; j < getNumberOfContacts(); j++) {
				if (number == contacts[j].getPhone()) {
					count++;
				}
				if (count >= 2) {
					tem = true;
				}
			}
		}
		return tem;
	}
}
