/**
 * 
 */
package controller;

import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.AddressBook;

public class ChangeInfoContactsController implements ListSelectionListener {

	/**
	 * Contiens la liste des contacts et leurs informations
	 */
	private Properties contacts;
	/**
	 * Model contenant les éléments la JList <a href="#listViewContacts">listViewContacts</a>
	 */
	private DefaultListModel<String> modelContacts;
	/**
	 * Permets de visualiser la liste des contacts
	 */
	private JList<String> listViewContacts;
	/**
	 * Permets de visualiser les informations d'un contact
	 */
	private JTextPane infosTxtPane;
	/**
	 * Item du menu contextuel qui permet de supprimer un contact
	 */
	private JMenuItem itemPopupRemove;
	

	/**
	 * Construis un objet <code>ChangeInfoContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations
	 * @param modelContacts Model contenant les éléments la JList <code>listViewContacts</code>
	 * @param listViewContacts Permets de visualiser la liste des contacts
	 * @param infosTxtPane Permets de visualiser les informations d'un contact
	 * @param itemPopupRemove Item du menu contextuel qui s'affiche lors d'un click droit 
	 * dans la <code>listViewContacts</code> et qui change de son texte en fonction du contact 
	 * sélectionnées la <code>listViewContacts</code>
	 */
	public ChangeInfoContactsController(Properties contacts, DefaultListModel<String> modelContacts,
			JList<String> listViewContacts, JTextPane infosTxtPane, JMenuItem itemPopupRemove) {
		super();
		this.contacts = contacts;
		this.modelContacts = modelContacts;
		this.listViewContacts = listViewContacts;
		this.infosTxtPane = infosTxtPane;
		this.itemPopupRemove = itemPopupRemove;
	}


	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
        	if (modelContacts.size() > 0) {
				String contactSelected = listViewContacts.getSelectedValue();
				if (contactSelected != null) {
					String infosContactSelected = contacts.getProperty(contactSelected);
					infosTxtPane.setText(infosContactSelected);

					itemPopupRemove.setText(AddressBook.getRemovestring() + " " + contactSelected);
					
				}
        	} else {
        		infosTxtPane.setText("");
        	}
			
        }
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeInfoContactsController \ncontacts=");
		builder.append(contacts);
		builder.append("\nmodelContacts=");
		builder.append(modelContacts);
		builder.append("\nlistViewContacts=");
		builder.append(listViewContacts);
		builder.append("\ninfosTxtPane=");
		builder.append(infosTxtPane);
		builder.append("\nitemPopupRemove=");
		builder.append(itemPopupRemove);
		return builder.toString();
	}

}
