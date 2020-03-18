package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JComponent;

import view.AddressBook;


public class ChangeLangController implements ActionListener {
	
	/**
	 *  Le nom de base du groupe de ressources de <code>AddressBook</code>
	 */
	private static final String baseName = "lang/AddressBook";
	/**
	 * Possède des éléments qui doivent être traduits
	 */
	private AddressBook annuaire;
	/**
	 * Définis une locale à laquelle les éléments de la fenêtre vont être traduits
	 */
	private Locale locale;
	/**
	 * Permets de retrouver les ressources de traduction
	 */
	private ResourceBundle resource;
	

	
	/**
	 * Construis un objet <code>ChangeLangController</code>
	 * 
	 * @param locale Définis une locale à laquelle les éléments de la fenêtre vont être traduit
	 * @param annuaire Possède des éléments qui doivent être traduit
	 */
	public ChangeLangController(Locale locale, AddressBook annuaire) {
		this.annuaire = annuaire;
		this.locale = locale;
		this.resource = ResourceBundle.getBundle(baseName, locale);
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent.setDefaultLocale(locale);
		Locale.setDefault(locale);
		annuaire.getMenuFile().setText( resource.getString("fichierString") );
		annuaire.getMenuContacts().setText( resource.getString("contactsString") );
		annuaire.getMenuLanguage().setText( resource.getString("langueString") );
		annuaire.getBtnMenuRadioFrench().setText( resource.getString("frenchString") );
		annuaire.getBtnMenuRadioEnglish().setText( resource.getString("englishString") );
		AddressBook.getItemSave().setText( resource.getString("saveString") );
		AddressBook.getBtnSave().setText( resource.getString("saveString") );
		annuaire.getItemAdd().setText( resource.getString("addString") );
		annuaire.getBtnAdd().setText( resource.getString("addString") );
		annuaire.getItemRemove().setText( resource.getString("removeString") );
		annuaire.getItemPopupRemove().setText( resource.getString("removeString") );
		annuaire.getBtnRemove().setText( resource.getString("removeString") );

		AddContactsController.titreDialogue = resource.getString("titreDialogue");
		AddContactsController.msgNomDialog = resource.getString("msgNomDialog");
		AddContactsController.msgInfosDialog = resource.getString("msgInfosDialog");

		RemoveContactsController.titreDialogRemove = resource.getString("titreDialogRemove");
		RemoveContactsController.msgDialogRemove = resource.getString("msgDialogRemove");

		CloseFrameContactsController.titreDialogClose = resource.getString("titreDialogClose");
		CloseFrameContactsController.msgDialogClose = resource.getString("msgDialogClose");
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangeLangController \nannuaire=");
		builder.append(annuaire);
		builder.append("\nlocale=");
		builder.append(locale);
		builder.append("\nresource=");
		builder.append(resource);
		return builder.toString();
	}


}
