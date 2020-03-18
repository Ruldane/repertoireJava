package exercice1;

import java.util.Properties;

/**
 * Exercice 1 du projet
 * 
 * @author DJABIRI Abalkassim
 *
 */
public class Test {
	
	/**
	 * Récupère les propriétés de la jvm et afficher les avec leurs valeurs respectives
	 */
	public static void configJVM () {		
		Properties myProps = new Properties();		
		myProps = System.getProperties();
		myProps.list(System.out);
	}

}
