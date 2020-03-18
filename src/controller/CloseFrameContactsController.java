package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.AddressBook;

public class CloseFrameContactsController extends WindowAdapter {
	
	/**
	 * Texte du titre des boites de dialogue
	 */
	public static String titreDialogClose = "Sélectionner une option";
	/**
	 * Texte du message de la boite de dialogue
	 */
	public static String msgDialogClose = "Sauvegarder les changements ?";
	
	/**
	 * Contiens la liste des contacts et leurs informations
	 */
	private Properties contacts;
	
	/**
	 * Frame où vont être instanciée la boite de dialogue
	 */
	private JFrame frame;

	/**
	 * Construis un objet <code>CloseFrameContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations
	 * @param frame Frame où vont place mes boite de dialogue
	 */
	public CloseFrameContactsController(Properties contacts, JFrame frame ) {
		super();
		this.contacts = contacts;
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		super.windowClosing(e);
		
		if (AddressBook.checkChangesWithTheBackup(contacts)) {
			int reponse = JOptionPane.showConfirmDialog(frame, msgDialogClose, titreDialogClose, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (reponse == JOptionPane.YES_OPTION) {
				SaveContactsController saveContacts = new SaveContactsController(contacts);
				saveContacts.actionPerformed(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			} else if (reponse == JOptionPane.CANCEL_OPTION) {
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			} else {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		} else {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CloseFrameContactsController \ncontacts=");
		builder.append(contacts);
		builder.append("\nframe=");
		builder.append(frame);
		return builder.toString();
	}

}
