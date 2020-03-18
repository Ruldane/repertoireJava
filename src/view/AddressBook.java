package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.LineBorder;

import controller.AddContactsController;
import controller.ChangeInfoContactsController;
import controller.CloseFrameContactsController;
import controller.DownListController;
import controller.ChangeLangController;
import controller.SaveInfoTxtPaneController;
import controller.MousePopupListener;
import controller.RemoveContactsController;
import controller.SaveContactsController;
import controller.UpListController;
import model.ContactsModel;	

@SuppressWarnings("serial")
public class AddressBook extends JFrame {
	
	/**
	 * Chemin du fichier où est enregistrée la liste des <a href="#contacts">contacts</a>
	 */
	private static final String pathFileProperties = "phones.properties";
	/**
	 * Chemin de l'icône du JButton <a href="#btnToolBarSave">btnToolBarSave</a> de la barre de dialogue
	 */
	private static final String pathImageSaveIcon = "/toolbarButtonGraphics/general/Save16.gif";
	/**
	 * Chemin de l'icône du JButton <a href="#btnToolBarAdd">btnToolBarAdd</a> de la barre de dialogue
	 */
	private static final String pathImageAddIcon = "/toolbarButtonGraphics/general/Add16.gif";
	/**
	 * Chemin de l'icône du JButton <a href="#btnToolBarRemove">btnToolBarRemove</a> de la barre de dialogue
	 */
	private static final String pathImageRemoveIcon = "/toolbarButtonGraphics/general/Delete16.gif";
	/**
	 * Chemin de l'icône du JButton <a href="#btnToolBarUp">btnToolBarUp</a> de la barre de dialogue
	 */
	private static final String pathImageUpIcon = "/toolbarButtonGraphics/navigation/Up16.gif";
	/**
	 * Chemin de l'icône du JButton <a href="#btnToolBarDown">btnToolBarDown</a> de la barre de dialogue
	 */
	private static final String pathImageDownIcon = "/toolbarButtonGraphics/navigation/Down16.gif";
	
	/**
	 * Texte du JMenu <a href="#menuFile">menuFile</a>
	 */
	private static final String fileString = "Fichier";
	/**
	 * Texte du JMenu <a href="#menuContacts">menuContacts</a>
	 */
	private static final String contactsString = "Contacts";
	/**
	 * Texte du JMenu <a href="#menuLanguage">menuLanguage</a>
	 */
	private static final String languageString = "Langage";
	/**
	 * Texte du JRadioButtonMenuItem <a href="#btnMenuRadioFrench">btnMenuRadioFrench</a>
	 */
	private static final String frenchString = "Français";
	/**
	 * Texte du JRadioButtonMenuItem <a href="#btnMenuRadioEnglish">btnMenuRadioEnglish</a>
	 */
	private static final String englishString = "Anglais";
	/**
	 * Texte des composants graphiques permettant de sauvegarder la liste des contacts
	 */
	private static final String saveString = "Enregistrer";
	/**
	 * Texte des composants graphiques permettant d'ajouter un contact
	 */
	private static final String addString = "Ajouter";
	/**
	 * Texte des composants graphiques permettant de supprimer un contact
	 */
	private static final String removeString = "Supprimer";
	
	
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
	 * La barre de menu de la fenêtre
	 */
	private JMenuBar menu;
	/**
	 * Le menu "Fichier"
	 */
	private JMenu menuFile;
	/**
	 * Le menu "Contacts"
	 */
	private JMenu menuContacts;
	/**
	 * Le menu "Langage"
	 */
	private JMenu menuLanguage;
	/**
	 * Item du menu qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private static JMenuItem itemSave;
	/**
	 * Item du menu qui permet d'ajouter un nouveau contact
	 */
	private JMenuItem itemAdd;
	/**
	 * Item du menu qui permet de supprimer un contact
	 */
	private JMenuItem itemRemove;
	/**
	 * Item du menu qui change la langue des éléments du frame en français
	 */
	private JRadioButtonMenuItem btnMenuRadioFrench;
	/**
	 * Item du menu qui change la langue des éléments du frame en anglais
	 */
	private JRadioButtonMenuItem btnMenuRadioEnglish;
	/**
	 * Bouton qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private static JButton btnSave;
	/**
	 * Bouton qui permet d'ajouter un nouveau contact
	 */
	private JButton btnAdd;
	/**
	 * Bouton qui permet de supprimer un contact
	 */
	private JButton btnRemove;
	/**
	 * La barre d'outils de la fenêtre
	 */
	private JToolBar toolBar;
	/**
	 * Bouton de la barre d'outils qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 */
	private static JButton btnToolBarSave;
	/**
	 * Bouton de la barre d'outils qui permet d'ajouter un nouveau contact
	 */
	private JButton btnToolBarAdd;
	/**
	 * Bouton de la barre d'outils qui permet de supprimer un contact
	 */
	private JButton btnToolBarRemove;
	/**
	 * Bouton de la barre d'outils qui permet de remonter vers le haut de la JList <a href="#listViewContacts">listViewContacts</a>
	 */
	private JButton btnToolBarUp;
	/**
	 * Bouton de la barre d'outils qui permet de redescendre vers le bas de la JList <a href="#listViewContacts">listViewContacts</a>
	 */
	private JButton btnToolBarDown;
	/**
	 * Le menu contextuel qui s'affiche lorsqu'on fait un click droit sur la JList <a href="#listViewContacts">listViewContacts</a>
	 */
	private JPopupMenu popupMenu;
	/**
	 * Item du menu contextuel qui permet de supprimer un contact
	 */
	private JMenuItem itemPopupRemove;	


	/**
	 * Construis un nouveau carnet d'adresses en initialisant tous les éléments qui la composent.
	 */
	public AddressBook() {
		super();
		contacts = new Properties();
		loadFileProperties(pathFileProperties, contacts);
		
		modelContacts = new ContactsModel(contacts);
		listViewContacts = new JList<String>(modelContacts);
		infosTxtPane = new JTextPane();

		JScrollPane scrollListView = new JScrollPane(listViewContacts);
		
		createMenuBar();
		createToolBar();
		
		btnSave = new JButton(saveString);
		btnAdd = new JButton(addString);
		btnRemove = new JButton(removeString);
		btnSave.setMnemonic(KeyEvent.VK_S);
		btnAdd.setMnemonic(KeyEvent.VK_J);
		btnRemove.setMnemonic(KeyEvent.VK_P);
		JPanel panelButton = new JPanel(new FlowLayout());
		panelButton.add(btnSave);
		panelButton.add(btnAdd);
		panelButton.add(btnRemove);
		
		popupMenu = new JPopupMenu();
		itemPopupRemove = new JMenuItem(removeString + " " + listViewContacts.getSelectedValue());
		itemPopupRemove.setMnemonic(KeyEvent.VK_P);
		popupMenu.add(itemPopupRemove);		

		addListenerComponents();		

		itemSave.setEnabled(false);
		btnSave.setEnabled(false);
		btnToolBarSave.setEnabled(false);
		
		
		listViewContacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listViewContacts.setVisibleRowCount(5);
		infosTxtPane.setBorder(new LineBorder(Color.GRAY));
		JPanel panelTop = new JPanel(new BorderLayout());
		panelTop.add(toolBar, BorderLayout.NORTH);
		panelTop.add(scrollListView, BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(panelTop, BorderLayout.NORTH);
		this.getContentPane().add(infosTxtPane, BorderLayout.CENTER);
		this.getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		this.setSize(400, 300);
		this.setLocation(200, 200);
		this.setVisible(true);
	}

	/**
	 * Ajoute des écouteurs aux composants de la fenêtre
	 */
	private void addListenerComponents() {
		ChangeLangController englighLang = new ChangeLangController(Locale.ENGLISH, this);
		ChangeLangController frenchLang = new ChangeLangController(Locale.FRENCH, this);
		ChangeInfoContactsController changeInfoListener = new ChangeInfoContactsController(contacts, modelContacts, listViewContacts, infosTxtPane, itemPopupRemove);
		SaveInfoTxtPaneController modifListener = new SaveInfoTxtPaneController(contacts, listViewContacts);
		AddContactsController ajoutListener = new AddContactsController(contacts, modelContacts, listViewContacts, this);
		RemoveContactsController removeListener = new RemoveContactsController(contacts, modelContacts, listViewContacts, this);
		SaveContactsController saveListener = new SaveContactsController(contacts, itemSave, btnSave, btnToolBarSave);
		UpListController upListener = new UpListController(listViewContacts);
		DownListController downListener = new DownListController(listViewContacts);
		MousePopupListener popupListener = new MousePopupListener(popupMenu, listViewContacts);
		CloseFrameContactsController closeFrame = new CloseFrameContactsController(contacts, this);

		btnMenuRadioEnglish.addActionListener(englighLang);
		btnMenuRadioFrench.addActionListener(frenchLang);
		listViewContacts.addListSelectionListener(changeInfoListener);
		infosTxtPane.getDocument().addDocumentListener(modifListener);
		itemAdd.addActionListener(ajoutListener);
		btnAdd.addActionListener(ajoutListener);
		btnToolBarAdd.addActionListener(ajoutListener);
		itemRemove.addActionListener(removeListener);
		btnRemove.addActionListener(removeListener);
		btnToolBarRemove.addActionListener(removeListener);
		itemPopupRemove.addActionListener(removeListener);
		itemSave.addActionListener(saveListener);
		btnSave.addActionListener(saveListener);
		btnToolBarSave.addActionListener(saveListener);
		btnToolBarUp.addActionListener(upListener);
		btnToolBarDown.addActionListener(downListener);
		listViewContacts.addMouseListener(popupListener);
		addWindowListener(closeFrame);
	}
	
	/**
	 * Crée la barre de menu de la fenêtre en instanciant les éléments qui la composent
	 */
	private void createMenuBar() {
		menu = new JMenuBar();
		menuFile = new JMenu(fileString);
		menuContacts = new JMenu(contactsString);
		menuLanguage = new JMenu(languageString);
		itemSave = new JMenuItem(saveString, KeyEvent.VK_S);
		itemAdd = new JMenuItem(addString, KeyEvent.VK_J);
		itemRemove = new JMenuItem(removeString, KeyEvent.VK_P);
		btnMenuRadioFrench = new JRadioButtonMenuItem(frenchString, true);
		btnMenuRadioEnglish = new JRadioButtonMenuItem(englishString);	
		ButtonGroup groupLangue = new ButtonGroup();
		
		
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuContacts.setMnemonic(KeyEvent.VK_C);
		menuContacts.setMnemonic(KeyEvent.VK_L);
		
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		itemAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		
		groupLangue.add(btnMenuRadioFrench);
		groupLangue.add(btnMenuRadioEnglish);
		menuFile.add(itemSave);
		menuContacts.add(itemAdd);
		menuContacts.add(itemRemove);
		menuLanguage.add(btnMenuRadioFrench);
		menuLanguage.add(btnMenuRadioEnglish);
		menu.add(menuFile);
		menu.add(menuContacts);
		menu.add(menuLanguage);
		setJMenuBar(menu);
		
	}
	
	/**
	 * Crée la barre d'outils de la fenêtre en instanciant les éléments qui la composent
	 */
	private void createToolBar() {
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		URL imageURL;
		
		imageURL = Object.class.getResource(pathImageSaveIcon);
		btnToolBarSave = new JButton( new ImageIcon(imageURL) );
		imageURL = Object.class.getResource(pathImageAddIcon);
		btnToolBarAdd = new JButton( new ImageIcon(imageURL) );
		imageURL = Object.class.getResource(pathImageRemoveIcon);
		btnToolBarRemove = new JButton( new ImageIcon(imageURL) );
		imageURL = Object.class.getResource(pathImageUpIcon);
		btnToolBarUp = new JButton( new ImageIcon(imageURL) );
		imageURL = Object.class.getResource(pathImageDownIcon);
		btnToolBarDown = new JButton( new ImageIcon(imageURL) );
		
		toolBar.add(btnToolBarSave);
		toolBar.addSeparator();
		toolBar.add(btnToolBarAdd);
		toolBar.add(btnToolBarRemove);
		toolBar.addSeparator();
		toolBar.add(btnToolBarUp);
		toolBar.add(btnToolBarDown);
	}
	

	/**
	 * Chargement d'un fichier de type {@link Properties}
	 * 
	 * <p>
	 * Cette fonction va rechercher un fichier properties qu'on lui donne comme paramètre.
	 * Ensuite, Elle va charger tous ces {@link Properties} dans une autre {@link Properties}
	 * qu'on donne aussi en paramètre de la fontion. Pour faire ça, elle utilise la méthode
	 * {@link Properties#load(InputStream)} de la classe <code>Properties</code>.
	 * </p>
	 * 
	 * @param file {@link String} représentant le chemin du fichier properties
	 * @param p {@link Properties} à laquelle on a mis les propriétés contenues dans <code>file</code>
	 */
	private static void loadFileProperties(String file, Properties p) {
		try {
			File fichierContacts = new File(file); 
			if (fichierContacts.exists()) {
				InputStream in = new FileInputStream(fichierContacts);
				p.load(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Vérifie s'il y a eu des modifications entre la <code>Properties
	 * contacts</code> et le fichier sur disque.
	 * 
	 * @param contacts Properties de la liste des contacts et leurs informations
	 * @return <code>true</code> s'il n'y a pas d'égalité entre la
	 * <code>Properties contacts</code> et les properties
	 * qui sont dans un fichier dont l'emplacement est la constante
	 * <a href="#pathFileProperties">pathFileProperties</a> de la classe.
	 */
	public static boolean checkChangesWithTheBackup(Properties contacts) {
		Properties p = new Properties();
		AddressBook.loadFileProperties(AddressBook.pathFileProperties, p);
		return !contacts.equals(p);
	}
	
	/**
	 * Active tous les {@link JButton} "Enregistrer"
	 * <p>
	 * Cette fonction active tous les {@link JButton} permettant de sauvegarder
	 * sur disque le Properties <a href="#contacts">contacts</a>.
	 * </p>
	 */
	public static void activeBtnSave() {
		itemSave.setEnabled(true);
		btnSave.setEnabled(true);
		btnToolBarSave.setEnabled(true);
	}

	
	/**
	 * Retourne la chaîne de caractère <a href="#pathFileProperties">pathFileProperties</a>
	 * 
	 * @return la chaîne de caractère <code>pathFileProperties</code>
	 */
	public static final String getPathfileproperties() {
		return pathFileProperties;
	}

	/**
	 * Retourne le texte des composants graphiques qui suppriment un contact
	 * 
	 * @return la chaîne de caractère <code>removeString</code>
	 */
	public static final String getRemovestring() {
		return removeString;
	}

	/**
	 * Retourne le menu "Fichier"
	 *
	 * @return le JMenu <code>menuFile</code>
	 */
	public JMenu getMenuFile() {
		return menuFile;
	}

	/**
	 * Retourne le menu "Contacts"
	 *
	 * @return le JMenu <code>menuContacts</code>
	 */
	public JMenu getMenuContacts() {
		return menuContacts;
	}

	/**
	 * Retourne le menu "Langage"
	 *
	 * @return le JMenu <code>menuLanguage</code>
	 */
	public JMenu getMenuLanguage() {
		return menuLanguage;
	}

	/**
	 * Retourne l'item du menu qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 *
	 * @return le JMenuItem <code>itemSave</code>
	 */
	public static JMenuItem getItemSave() {
		return itemSave;
	}

	/**
	 * Retourne l'item du menu qui permet d'ajouter un nouveau contact
	 *
	 * @return le JMenuItem <code>itemAdd</code>
	 */
	public JMenuItem getItemAdd() {
		return itemAdd;
	}

	/**
	 * Retourne l'item du menu qui permet de supprimer un contact
	 *
	 * @return le JMenuItem <code>itemRemove</code>
	 */
	public JMenuItem getItemRemove() {
		return itemRemove;
	}

	/**
	 * Retourne l'item du menu qui change la langue des éléments du frame en français
	 *
	 * @return le JRadioButtonMenuItem <code>btnMenuRadioFrench</code>
	 */
	public JRadioButtonMenuItem getBtnMenuRadioFrench() {
		return btnMenuRadioFrench;
	}

	/**
	 * Retourne l'item du menu qui change la langue des éléments du frame en anglais
	 *
	 * @return le JRadioButtonMenuItem <code>btnMenuRadioEnglish</code>
	 */
	public JRadioButtonMenuItem getBtnMenuRadioEnglish() {
		return btnMenuRadioEnglish;
	}

	/**
	 * Retourne le bouton qui permet de sauvegarder sur disque le Properties <a href="#contacts">contacts</a>
	 *
	 * @return le JButton <code>btnSave</code>
	 */
	public static JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * Retourne le bouton qui permet d'ajouter un nouveau contact
	 *
	 * @return le JButton <code>btnAdd</code>
	 */
	public JButton getBtnAdd() {
		return btnAdd;
	}

	/**
	 * Retourne le bouton qui permet de supprimer un contact
	 *
	 * @return le JButton <code>btnRemove</code>
	 */
	public JButton getBtnRemove() {
		return btnRemove;
	}

	/**
	 * Retourne l'item du menu contextuel qui permet de supprimer un contact
	 *
	 * @return le JMenuItem <code>itemPopupRemove</code>
	 */
	public JMenuItem getItemPopupRemove() {
		return itemPopupRemove;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carnet d'adresse : \n");
		
		if (contacts.isEmpty()) {
			builder.append("Aucun contacts dans le carnet d'adresses.");
		} else {
			for (Entry<Object, Object> entree : contacts.entrySet()) {
				builder.append((String) entree.getKey() + " - ");
				builder.append((String) entree.getValue() + "\n");
				
			}
		}
		return builder.toString();
	}
	

}
