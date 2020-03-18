package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import view.AddressBook;

public class RemoveContactsController implements ActionListener {
	
	/**
	 * Texte du titre de la boites de dialogue
	 */
	public static String titreDialogRemove = "Sélectionné une option";
	/**
	 * Texte du message de la boite de dialogue
	 */
	public static String msgDialogRemove = "Effacez";
	

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
	 * Frame où vont être instanciée la boite de dialogue
	 */
	private JFrame frame;



	/**
	 * Construit un objet <code>RemoveContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations
	 * @param modelContacts Model contenant les éléments la JList <code>listViewContacts</code>
	 * @param listViewContacts Permets de visualiser la liste des contacts
	 * @param frame Frame où vont être instanciée la boite de dialogue
	 */
	public RemoveContactsController(Properties contacts, DefaultListModel<String> modelContacts, JList<String> listViewContacts, JFrame frame) {
		super();
		this.contacts = contacts;
		this.modelContacts = modelContacts;
		this.listViewContacts = listViewContacts;
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int indexContactSelected = listViewContacts.getSelectedIndex();
		String contactSelected = listViewContacts.getSelectedValue();

		if (contactSelected != null) {
			int reponse = JOptionPane.showConfirmDialog(frame, msgDialogRemove + " " + contactSelected, titreDialogRemove,
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (reponse == JOptionPane.OK_OPTION) {
				this.contacts.remove(contactSelected);
				this.modelContacts.remove(indexContactSelected);

				if (indexContactSelected > 0) {
					indexContactSelected--;
				}
				
				listViewContacts.setSelectedIndex(indexContactSelected);
				listViewContacts.ensureIndexIsVisible(indexContactSelected);
				AddressBook.activeBtnSave();
			}
		}
	}

}
