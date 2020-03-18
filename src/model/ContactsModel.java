package model;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JList;

@SuppressWarnings("serial")
public class ContactsModel extends DefaultListModel<String> {

	/**
	 * Crée un <code>ContactsModel</code> avec les attributs d'une {@link Properties}
	 * 
	 * @param myProps On récupère ses attributs pour qu'ils soient 
	 * des éléments du model.
	 */
	public ContactsModel(Properties myProps) {
		
		setProperties(myProps);
	}

	/**
	 * Réinitialise le model, puis ajoute les attributs de <code>myProps</code> 
	 * au model par ordre alphabétique
	 * 
	 * @param myProps On récupère ses attributs pour qu'ils soient 
	 * des éléments du model.
	 */
	public void setProperties(Properties myProps) {
		this.removeAllElements();
		Map<Object, Object> myPropsSorted = new TreeMap<Object, Object>();
		myPropsSorted.putAll(myProps);
		
		for (Entry<Object, Object> entree : myPropsSorted.entrySet()) {
			addElement((String) entree.getKey());
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContactsModel [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
