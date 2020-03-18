package controller;

import java.awt.event.MouseAdapter;


import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JList;
import javax.swing.JPopupMenu;

public class MousePopupListener extends MouseAdapter {

	/**
	 * La <code>JPopupMenu</code> qui va s'afficher
	 */
	private JPopupMenu popupMenu;
	/**
	 * La {@link JList} qui affiche <a href="#popupMenu">popupMenu</a> dés qu'on fait un click droit sur lui
	 */
	private JList<String> listView;

	/**
	 * Construis un objet <code>MousePopupListener</code>
	 * 
	 * @param popupMenu La <code>JPopupMenu</code> qui va s'afficher
	 * @param listView La JList qui affiche <a href="#popupMenu">popupMenu</a> dés qu'on fait un click droit sur lui
	 */
	public MousePopupListener(JPopupMenu popupMenu, JList<String> listView) {
		super();
		this.popupMenu = popupMenu;
		this.listView = listView;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		showPopup(e);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		showPopup(e);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		showPopup(e);
	}
	
	/**
	 * Affiche le menu contextuel dès qu'il reçoit un MouseEvent
	 * 
	 * @param e MouseEvent qui détecte où a lieu le click 
	 */
	private void showPopup(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popupMenu.show(listView, e.getX(), e.getY());
			int index = listView.locationToIndex(e.getPoint());
			if (index >= 0) {
				listView.setSelectedIndex(index);
				listView.ensureIndexIsVisible(index);
			}
		}
	}

}
