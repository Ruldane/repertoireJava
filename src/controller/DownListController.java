package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;

public class DownListController implements ActionListener {
		
	/**
	 * La {@link JList} de la classe
	 */
	private JList<String> listView;

	/**
	 * Construis un objet <code>DownListController</code>
	 * 
	 * @param listView JList pour sélectionner un élément de sa liste
	 */
	public DownListController(JList<String> listView) {
		this.listView = listView;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int lenghtList = listView.getModel().getSize();
		int indexSelected = listView.getSelectedIndex();
		
		if (lenghtList > 0) {
			if (indexSelected < 0) {
				listView.setSelectedIndex(0);
				listView.ensureIndexIsVisible(0);
			}
			else if (indexSelected == lenghtList-1) {
				listView.setSelectedIndex(indexSelected);
				listView.ensureIndexIsVisible(indexSelected);
			}
			else {
				indexSelected++;
				listView.setSelectedIndex(indexSelected++);
				listView.ensureIndexIsVisible(indexSelected++);
			}
		}
	}
	
	
	
	

}
