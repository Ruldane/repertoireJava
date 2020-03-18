package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import view.AddressBook;

 class SaveContactsController implements ActionListener {
	
	/**
	 * Texte en commentaire au début du fichier properties
	 */
	private static final String comments = "mes contacts";
	
	/**
	 * Contiens la liste des contacts et leurs informations
	 */
	private Properties contacts;
	/**
	 * Item du menu qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private JMenuItem itemSave;
	/**
	 * Bouton qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private JButton btnSave;
	/**
	 * Bouton de la barre d'outils qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private JButton btnToolBarSave;

	/**
	 * Construis un objet <code>SaveContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations pour qui enregistrer
	 */
	public SaveContactsController(Properties contacts) {
		super();
		this.contacts = contacts;
		this.itemSave = new JMenuItem();
		this.btnSave = new JButton();
		this.btnToolBarSave = new JButton();
	}

	/**
	 * Construis un objet <code>SaveContactsController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations pour qui enregistrer
	 * @param saveItem  Un item qui permet de sauvegarder
	 * @param btnSave Un bouton qui permet de sauvegarder
	 * @param btnToolBarSave Un bouton qui permet de sauvegarder
	 */
	public SaveContactsController(Properties contacts, JMenuItem saveItem, JButton btnSave, JButton btnToolBarSave) {
		super();
		this.contacts = contacts;
		this.itemSave = saveItem;
		this.btnSave = btnSave;
		this.btnToolBarSave = btnToolBarSave;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try (OutputStream out = new FileOutputStream(AddressBook.getPathfileproperties())) {
			contacts.store(out, comments);
			itemSave.setEnabled(false);
			btnSave.setEnabled(false);
			btnToolBarSave.setEnabled(false);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveContactsController \ncontacts=");
		builder.append(contacts);
		builder.append("\nitemSave=");
		builder.append(itemSave);
		builder.append("\nbtnSave=");
		builder.append(btnSave);
		builder.append("\nbtnToolBarSave=");
		builder.append(btnToolBarSave);
		return builder.toString();
	}

}
