package briandais;

import java.util.ArrayList;
import java.util.Collections;

import utils.Utils;

/**
 * Fonctions sur les arbres de la Briandais
 * 
 * @author KOBROSLI Hassan 3001993
 * 
 */
public class Briandais {

	/**
	 * @param mot
	 *            Mot a inserer.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut inserer le mot.
	 * @return Arbre de la Briandais avec le mot ajoute.
	 */
	public static ArbreBriandais inserer(String mot, ArbreBriandais arbre) {
		// On transforme le mot en tableau de chaînes de caractère
		char[] tabMot = mot.toCharArray();
		char[] tabMot2 = new char[tabMot.length + 1];

		for (int i = 0; i < tabMot.length; i++) {
			tabMot2[i] = tabMot[i];
		}
		// On ajoute comme dernier caractère \0 qui marque la fin d'un mot
		tabMot2[tabMot.length] = '\0';
		return inserer(tabMot2, arbre);
	}

	/**
	 * @param mot
	 *            Mot a inserer.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut inserer le mot.
	 * @return Arbre de la Briandais avec le mot ajoute.
	 */
	private static ArbreBriandais inserer(char[] mot, ArbreBriandais arbre) {
		// Si le mot est vide on ne fait rien
		if (mot.length == 0) {
			return arbre;
		}
		// Si l'arbre n'existe pas encore on en instancie un nouveau
		if (arbre == null) {
			ArbreBriandais arbreOut = new ArbreBriandais(mot[0], null, null);
			arbreOut.setSuivant(inserer(Utils.copierSaufPremLett(mot),
					arbreOut.getSuivant()));
			return arbreOut;

		}
		/*
		 * Si la première lettre du mot est egale a la cle du noeud, on insere
		 * le reste du mot dans le noeud suivant
		 */
		if (arbre.getLettre() == mot[0]) {
			return new ArbreBriandais(mot[0], arbre.getAlternatif(), inserer(
					Utils.copierSaufPremLett(mot), arbre.getSuivant()));
		}
		// Sinon on insère dans le noeud alternatif
		return new ArbreBriandais(arbre.getLettre(), inserer(mot,
				arbre.getAlternatif()), arbre.getSuivant());
	}

	/**
	 * @param mot
	 *            Mot a chercher.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut chercher le mot
	 * @return Vrai si le mot est trouve. Faux sinon.
	 */
	public static boolean chercherMot(String mot, ArbreBriandais arbre) {
		// On transforme le mot en tableau de chaînes de caractère
		char[] tabMot = mot.toCharArray();
		char[] tabMot2 = new char[tabMot.length + 1];

		for (int i = 0; i < tabMot.length; i++) {
			tabMot2[i] = tabMot[i];
		}
		// On ajoute comme dernier caractère \0 qui marque la fin d'un mot
		tabMot2[tabMot.length] = '\0';
		return chercherMot(tabMot2, arbre);
	}

	/**
	 * @param mot
	 *            Mot a chercher.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut chercher le mot
	 * @return Vrai si le mot est trouve. Faux sinon.
	 */
	private static boolean chercherMot(char[] mot, ArbreBriandais arbre) {
		// S l'arbre vaut null ou le mot est vide, on retourne faux
		if (arbre == null || mot.length == 0) {
			return false;
		}
		if (arbre.getLettre() == mot[0]) {
			// On est arrive a la fin du mot
			if (mot.length == 1) {
				return true;
			}
			// On cherche dans le noeud suivant le reste du mot
			return chercherMot(Utils.copierSaufPremLett(mot),
					arbre.getSuivant());
		}
		// On cherche le mot dans le noeud alternatif
		return chercherMot(mot, arbre.getAlternatif());
	}

	/**
	 * @param mot
	 *            Mot a supprimer.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut supprimer le mot
	 * @return L'arbre apres suppression du mot.
	 */
	public static ArbreBriandais supprimerMot(String mot, ArbreBriandais arbre) {
		if (!chercherMot(mot, arbre)) {
			return arbre;
		}
		// On transforme le mot en tableau de chaînes de caractère
		char[] tabMot = mot.toCharArray();
		char[] tabMot2 = new char[tabMot.length + 1];
		for (int i = 0; i < tabMot.length; i++) {
			tabMot2[i] = tabMot[i];
		}
		// On ajoute comme dernier caractère \0 qui marque la fin d'un mot
		tabMot2[tabMot.length] = '\0';
		return supprimerMot(tabMot2, arbre);
	}

	/**
	 * @param mot
	 *            Mot a supprimer.
	 * @param arbre
	 *            Arbre de la Briandais dans lequel il faut supprimer le mot
	 * @return L'arbre apres suppression du mot.
	 */
	private static ArbreBriandais supprimerMot(char[] mot, ArbreBriandais arbre) {
		if (mot.length == 0) {
			return arbre;
		}
		if (arbre == null) {
			return null;
		}

		ArbreBriandais ArbreBriandais2;

		if (mot[0] == arbre.getLettre()) {
			// On supprime recursivement
			ArbreBriandais2 = supprimerMot(Utils.copierSaufPremLett(mot),
					arbre.getSuivant());

			// Si la suppression du sous arbre renvoie null
			if (ArbreBriandais2 == null) {
				return arbre.getAlternatif();
			}

			else {
				return new ArbreBriandais(arbre.getLettre(),
						arbre.getAlternatif(), ArbreBriandais2);
			}
		}
		// On recherche dans le noeud alternatif
		else {
			return new ArbreBriandais(arbre.getLettre(), supprimerMot(mot,
					arbre.getAlternatif()), arbre.getSuivant());
		}
	}

	/**
	 * @param phrase
	 *            Phrase contenant les mots a ajouter dans un arbre.
	 * @param arbre
	 *            Arbre dans lequel il faut ajouter la phrase.
	 * @return L'arbre apres ajout.
	 */
	public static ArbreBriandais insererPhrase(String phrase,
			ArbreBriandais arbre) {
		// On supprime les caracteres non ASCII
		String resultString = phrase.replaceAll("[^\\x00-\\x7F]", "");
		// On decoupe la phrase en mots
		String[] tabMots = resultString.split(" ");
		// On insere les mots un par un
		for (String mot : tabMots) {
			arbre = inserer(mot, arbre);
		}
		return arbre;
	}

	/**
	 * Fonction qui compte le nmobre de mots, c'est a dire le nombre de
	 * caracteres \0
	 * 
	 * @param arbre
	 *            L'arbre dans lequel il faut compter les mots
	 * @return Le nombre de mots dans l'arbre.
	 */
	public static int comptageMots(ArbreBriandais arbre) {
		if (arbre == null) {
			return 0;
		}
		int compteAlternative = comptageMots(arbre.getAlternatif());
		if (arbre.getLettre() == '\0') {
			return 1 + compteAlternative;
		}
		int compteSuivante = comptageMots(arbre.getSuivant());
		return compteAlternative + compteSuivante;
	}

	/**
	 * @param arbre
	 *            L'arbre dans lequel il faut compter les null.
	 * @return Le nombre de pointeurs vers null.
	 */
	public static int comptageNil(ArbreBriandais arbre) {
		if (arbre == null) {
			return 1;
		}
		return comptageNil(arbre.getAlternatif())
				+ comptageNil(arbre.getSuivant());
	}

	/**
	 * @param arbre
	 *            L'arbre dont il faut determiner la hauteur
	 * @return La hauteur de l'arbre.
	 */
	public static int hauteur(ArbreBriandais arbre) {
		if (arbre == null) {
			return 0;
		}
		return Math.max(1 + hauteur(arbre.getSuivant()),
				1 + hauteur(arbre.getAlternatif()));
	}

	/**
	 * Compte le nombre de feuilles, c'est a dire le nombre de noeuds qui n'ont
	 * ni noeud suivant, ni noeud alternatif
	 * 
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return le nombre de noeuds non vides de cet arbre
	 */
	private static int nombreFeuilles(ArbreBriandais arbre) {
		if (arbre == null) {
			return 0;
		}
		if (arbre.getAlternatif() == null && arbre.getSuivant() == null) {
			return 1;
		}
		return nombreFeuilles(arbre.getAlternatif())
				+ nombreFeuilles(arbre.getSuivant());
	}

	/**
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return La profondeur moyenne des noeuds de l'arbre
	 */
	public static int profondeurMoyenne(ArbreBriandais arbre) {
		return sommeProfondeurs(arbre) / nombreFeuilles(arbre);
	}

	/**
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return La somme des profondeurs de chaque feuille de l'arbre
	 */
	private static int sommeProfondeurs(ArbreBriandais arbre) {
		if (arbre == null) {
			return 0;
		}
		// Si le noeud est une feuille
		if (arbre.getAlternatif() == null && arbre.getSuivant() == null) {
			return 1;
		}
		int profAlt = sommeProfondeurs(arbre.getAlternatif());
		int profSuiv = sommeProfondeurs(arbre.getSuivant());
		if (profAlt > 0)
			profAlt++;
		if (profSuiv > 0)
			profSuiv++;
		return profAlt + profSuiv;
	}

	/**
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return La liste de tous les mots contenus dans l'arbre.
	 */
	public static ArrayList<String> listeMots(ArbreBriandais arbre) {
		ArrayList<String> liste = listeMots("", arbre);
		// Trie la liste
		Collections.sort(liste);
		return liste;
	}

	/**
	 * @param act
	 *            prefixe du mot actuel.
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return La liste de tous les mots contenus dans l'arbre.
	 */
	private static ArrayList<String> listeMots(String act, ArbreBriandais arbre) {
		ArrayList<String> liste = new ArrayList<String>();
		if (arbre == null) {
			return liste;
		}
		// Si fin mot, on ajoute l'ajoute dans la liste
		if (arbre.getLettre() == '\0') {
			liste.add(act);
			liste.addAll(listeMots(act, arbre.getAlternatif()));
			return liste;
		}
		// on recherche les mots avec le meme prefixe dans le noeud alternatif
		liste.addAll(listeMots(act, arbre.getAlternatif()));

		act += arbre.getLettre();
		liste.addAll(listeMots(act, arbre.getSuivant()));

		return liste;
	}

	/**
	 * @param mot
	 *            Prefixe
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return Le nombre de mots dont la chaine de caractere passee en argument
	 *         est prefixe.
	 */
	public static int prefixe(String mot, ArbreBriandais arbre) {
		char[] tabMot = mot.toCharArray();
		if (tabMot.length == 0) {
			return 0;
		}
		char[] tabMot2 = new char[tabMot.length + 1];
		for (int i = 0; i < tabMot.length; i++) {
			tabMot2[i] = tabMot[i];
		}
		tabMot2[tabMot.length] = '\0';
		return prefixe(tabMot2, arbre);
	}

	/**
	 * @param mot
	 *            Prefixe
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return Le nombre de mots dont la chaine de caractere passee en argument
	 *         est prefixe.
	 */
	private static int prefixe(char[] mot, ArbreBriandais arbre) {
		if (arbre == null) {
			return 0;
		}
		if (mot.length == 0) {
			return 0;
		}
		if (mot.length == 1) {
			if (arbre.getLettre() == mot[0]) {
				return 1 + comptageMots(arbre.getAlternatif());
			}
			return comptageMots(arbre.getAlternatif())
					+ comptageMots(arbre.getSuivant());
		}
		if (arbre.getLettre() == mot[0]) {
			return prefixe(Utils.copierSaufPremLett(mot), arbre.getSuivant());
		}
		return prefixe(mot, arbre.getAlternatif());
	}

	/**
	 * Decoupe un arbre
	 * 
	 * @param arbre
	 *            Un arbre de la Briandais
	 * @return Une liste d'arbres de la Briandais
	 */
	private static ArrayList<ArbreBriandais> split(ArbreBriandais arbre) {
		ArrayList<ArbreBriandais> liste = new ArrayList<ArbreBriandais>();
		if (arbre != null) {
			liste.add(new ArbreBriandais(arbre.getLettre(), null, arbre
					.getSuivant()));
			liste.addAll(split(arbre.getAlternatif()));
		}
		return liste;
	}

	/**
	 * @param arbre1
	 *            Premier arbre dans lequel on va insérer
	 * @param arbre2
	 *            Deuxieme arbre qu'on insère dans le premier
	 * @return Une copie de l'arbre1 dans lequel on a insere arbre2
	 */
	public static ArbreBriandais fusion(ArbreBriandais arbre1,
			ArbreBriandais arbre2) {
		ArrayList<ArbreBriandais> liste = split(arbre2);
		for (ArbreBriandais tmp : liste) {
			arbre1 = fusion2(arbre1, tmp);
		}
		return arbre1;
	}

	/**
	 * @param arbre1
	 *            Premier arbre dans lequel on va insérer
	 * @param arbre2
	 *            Deuxieme arbre qu'on insère dans le premier
	 * @return Une copie de l'arbre1 dans lequel on a insere arbre2
	 */
	private static ArbreBriandais fusion2(ArbreBriandais arbre1,
			ArbreBriandais arbre2) {
		if (arbre1 == null) {
			return arbre2;
		}
		if (arbre2 == null) {
			return arbre1;
		}
		if (arbre1.getLettre() != arbre2.getLettre()) {
			return new ArbreBriandais(arbre1.getLettre(), fusion(
					arbre1.getAlternatif(), arbre2), arbre1.getSuivant());
		}
		return new ArbreBriandais(arbre1.getLettre(), arbre1.getAlternatif(),
				fusion(arbre1.getSuivant(), arbre2.getSuivant()));
	}
}
