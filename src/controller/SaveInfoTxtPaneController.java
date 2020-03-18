package controller;

import java.util.Properties;

import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import view.AddressBook;

public class SaveInfoTxtPaneController implements DocumentListener {
	
	/**
	 * Contiens la liste des contacts et leurs informations
	 */
	private Properties contacts;
	/**
	 * Permets de visualiser des attributs du Properties <a href="#contacts">contacts</a>
	 */
	private JList<String> listView;
	
	/**
	 * Construis un objet <code>SaveInfoTxtPaneController</code>
	 * 
	 * @param contacts Contiens la liste des contacts et leurs informations
	 * @param listView Permets de visualiser la liste des contacts
	 */
	public SaveInfoTxtPaneController(Properties contacts, JList<String> listView) {
		super();
		this.contacts = contacts;
		this.listView = listView;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		if (this.listView.getModel().getSize() > 0)
			modifInfo(e);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		if (this.listView.getModel().getSize()> 0)
			modifInfo(e);
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
//		if (this.listView.getModel().getSize() > 0)
//			modifInfo(e);
	}

    /**
     * Modifie une valeur d'un {@link Properties} par celui d'un {@link DocumentEvent}
     * 
     * @param docEvent DocumentEvent qui possède la nouvelle valeur de la <code>Properties</code>
     */
    private void modifInfo(DocumentEvent docEvent) {
		Document doc  = docEvent.getDocument();
		String contactSelected = listView.getSelectedValue();
		try {
			String infoChanged = doc.getText(0, doc.getLength());
			String infoBeforeChanged = contacts.getProperty(contactSelected);
			
			if (!infoBeforeChanged.equals(infoChanged)) {
				contacts.setProperty(contactSelected, infoChanged);
				AddressBook.activeBtnSave();
			}
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaveInfoTxtPaneController \ncontacts=");
		builder.append(contacts);
		builder.append("\nlistView=");
		builder.append(listView);
		return builder.toString();
	}

}
