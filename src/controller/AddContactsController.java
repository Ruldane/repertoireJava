package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.ContactsModel;
import view.AddressBook;

public class AddContactsController implements ActionListener {
	
	/**
	 * Texte du titre des boites de dialogue
	 */
	public static String titreDialogue = "Entrée";
	/**
	 * Texte du message de la première boite de dialogue affichée
	 */
	public static String msgNomDialog = "Entrez le nom souhaité";
	/**
	 * Texte du message de la deuxième boite de dialogue affichée
	 */
	public static String msgInfosDialog = "Entrez les informations";
	

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
	 * Frame où vont être instanciée les boites de dialogue
	 */
	private JFrame frame;

	
	/**
	 * Construis un objet <code>AddContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations
	 * @param modelContacts Model contenant les éléments la JList <code>listViewContacts</code>
	 * @param listViewContacts Permets de visualiser la liste des contacts
	 * @param frame Frame où vont être instanciée les boites de dialogue
	 */
	public AddContactsController(Properties contacts, DefaultListModel<String> modelContacts, JList<String> listViewContacts, JFrame frame) {
		super();
		this.contacts = contacts;
		this.modelContacts = modelContacts;
		this.listViewContacts = listViewContacts;
		this.frame = frame;
	}


	/** (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String nomContactAjoute, infosContactsAjoute;
		
		nomContactAjoute = (String)JOptionPane.showInputDialog(frame, msgNomDialog, titreDialogue, JOptionPane.QUESTION_MESSAGE);
		
		if ((nomContactAjoute != null) && (nomContactAjoute.length() > 0)) {
			infosContactsAjoute = (String)JOptionPane.showInputDialog(frame, msgInfosDialog, titreDialogue,	JOptionPane.QUESTION_MESSAGE);
			
			if ((infosContactsAjoute != null)) {
				contacts.setProperty(nomContactAjoute, infosContactsAjoute);
				((ContactsModel) modelContacts).setProperties(contacts);
				int index = modelContacts.indexOf(nomContactAjoute);
				listViewContacts.setSelectedIndex(index);
				listViewContacts.ensureIndexIsVisible(index);
				
				AddressBook.activeBtnSave();
			}
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddContactsController\ncontacts=");
		builder.append(contacts);
		builder.append("\nmodelContacts=");
		builder.append(modelContacts);
		builder.append("\nlistViewContacts=");
		builder.append(listViewContacts);
		builder.append("\nframe=");
		builder.append(frame);
		
		return builder.toString();
	}
	
	
	
	

}
