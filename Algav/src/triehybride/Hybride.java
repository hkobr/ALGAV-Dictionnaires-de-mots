package triehybride;

import java.util.ArrayList;
import java.util.Collections;

import briandais.ArbreBriandais;
import utils.Utils;

/**
 * Fonctions sur les tries hybrides
 * 
 * @author KOBROSLI Hassan 3001993
 * 
 */
public class Hybride {

	/**
	 * @param mot
	 *            Mot a inserer
	 * @param trieHybride
	 *            trie dans lequel il faut inserer le mot
	 * @param val
	 *            valeur de fin de mot
	 * @return le trie dans lequel on a ajouté le mot
	 */
	public static TrieHybride inserer(String mot, TrieHybride trieHybride,
			Integer val) {
		if (mot.length() == 0) {
			return trieHybride;
		}
		char[] tabMot = mot.toCharArray();
		return inserer(tabMot, trieHybride, val);
	}

	/**
	 * @param mot
	 *            Mot a inserer
	 * @param trieHybride
	 *            trie dans lequel il faut inserer le mot
	 * @param val
	 *            valeur de fin de mot
	 * @return le trie dans lequel on a ajouté le mot
	 */
	private static TrieHybride inserer(char[] mot, TrieHybride trieHybride,
			Integer val) {
		if (trieHybride == null) {
			if (mot.length == 1) {
				return new TrieHybride(mot[0], null, null, null, val);
			} else {
				return new TrieHybride(mot[0], null, inserer(
						Utils.copierSaufPremLett(mot), null, val), null, null);
			}
		}
		if (mot.length == 1) {
			if (mot[0] == trieHybride.getCle()) {
				trieHybride.setValeur(val);
				return trieHybride;
			}
		}

		if (mot[0] < trieHybride.getCle()) {
			trieHybride.setInf(equilibrer(inserer(mot, trieHybride.getInf(),
					val)));
			return equilibrer(trieHybride);
			// return trieHybride;
		} else if (mot[0] > trieHybride.getCle()) {
			trieHybride.setSup(equilibrer(inserer(mot, trieHybride.getSup(),
					val)));
			return equilibrer(trieHybride);
			// return trieHybride;
		}

		trieHybride.setEq(inserer(Utils.copierSaufPremLett(mot),
				trieHybride.getEq(), val));

		return trieHybride;
	}

	/**
	 * @param mot
	 *            Mot a rechercher
	 * @param arbre
	 *            Trie hybride dans lequel on recherche
	 * @return true si le mot a ete trouve, false sinon.
	 */
	public static boolean chercherMot(String mot, TrieHybride arbre) {
		char[] tabMot = mot.toCharArray();
		return chercherMot(tabMot, arbre);
	}

	/**
	 * @param mot
	 *            Mot a rechercher
	 * @param arbre
	 *            Trie hybride dans lequel on recherche
	 * @return true si le mot a ete trouve, false sinon.
	 */
	private static boolean chercherMot(char[] mot, TrieHybride arbre) {
		if (arbre == null || mot.length == 0) {
			return false;
		}
		if (mot.length == 1 && arbre.getValeur() != null) {
			return true;
		}
		if (mot[0] == arbre.getCle()) {
			return chercherMot(Utils.copierSaufPremLett(mot), arbre.getEq());
		} else if (mot[0] < arbre.getCle()) {
			return chercherMot(mot, arbre.getInf());
		} else if (mot[0] > arbre.getCle()) {
			return chercherMot(mot, arbre.getSup());
		}
		return false;
	}

	/**
	 * @param trieHybride
	 *            Trie dans lequel on compte les mots
	 * @return le nombre de mots de l'arbre
	 */
	public static int comptageMots(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		}
		if (trieHybride.getValeur() != null) {
			return 1 + comptageMots(trieHybride.getInf())
					+ comptageMots(trieHybride.getEq())
					+ comptageMots(trieHybride.getSup());
		}
		return comptageMots(trieHybride.getInf())
				+ comptageMots(trieHybride.getEq())
				+ comptageMots(trieHybride.getSup());
	}

	/**
	 * @param trieHybride
	 *            Trie dans lequel on compte
	 * @return le nombre de pointeurs vers null
	 */
	public static int comptageNil(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 1;
		} else {
			return comptageNil(trieHybride.getInf())
					+ comptageNil(trieHybride.getEq())
					+ comptageNil(trieHybride.getSup());
		}
	}

	/**
	 * @param trieHybride
	 *            Trie dont on veut determiner la hauteur
	 * @return la hauteur de l'arbre
	 */
	public static int hauteur(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		} else {
			return 1 + Math.max(
					hauteur(trieHybride.getEq()),
					Math.max(hauteur(trieHybride.getInf()),
							hauteur(trieHybride.getSup())));
		}
	}

	/**
	 * @param trieHybride
	 *            Trie dans lequel on compte le nombre de feuilles
	 * @return le nombre de feuilles de l'arbre
	 */
	public static int compteFeuilles(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		}
		if (trieHybride.getInf() == null && trieHybride.getEq() == null
				&& trieHybride.getSup() == null) {
			return 1;
		} else {
			return compteFeuilles(trieHybride.getInf())
					+ compteFeuilles(trieHybride.getEq())
					+ compteFeuilles(trieHybride.getSup());
		}
	}

	/**
	 * @param trieHybride
	 *            Trie hybride
	 * @return La somme des profondeurs des feuilles
	 */
	private static int sommeProfondeurs(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		}
		if (trieHybride.getInf() == null && trieHybride.getEq() == null
				&& trieHybride.getSup() == null) {
			return 1;
		}
		int profInf = sommeProfondeurs(trieHybride.getInf());
		int profEq = sommeProfondeurs(trieHybride.getEq());
		int profSup = sommeProfondeurs(trieHybride.getSup());
		if (profInf > 0)
			profInf++;
		if (profEq > 0)
			profEq++;
		if (profSup > 0)
			profSup++;
		return profInf + profEq + profSup;
	}

	/**
	 * @param trieHybride
	 *            Un trie hybride
	 * @return la profondeur moyenne des feuilles du trie
	 */
	public static int profondeurMoyenne(TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		}
		return sommeProfondeurs(trieHybride) / compteFeuilles(trieHybride);
	}

	/**
	 * @param pref
	 *            Prefixe
	 * @param trieHybride
	 *            Un trie hybride
	 * @return Le nombre de mots dont pref est le prefixe
	 */
	public static int prefixe(String pref, TrieHybride trieHybride) {
		if (pref.length() == 0) {
			return 0;
		}
		char[] tabMot = pref.toCharArray();
		return prefixe(tabMot, trieHybride);
	}

	/**
	 * @param pref
	 *            Prefixe
	 * @param trieHybride
	 *            Un trie hybride
	 * @return Le nombre de mots dont pref est le prefixe
	 */
	private static int prefixe(char[] pref, TrieHybride trieHybride) {
		if (trieHybride == null) {
			return 0;
		}
		if (pref[0] < trieHybride.getCle()) {
			return prefixe(pref, trieHybride.getInf());
		}
		if (pref[0] > trieHybride.getCle()) {
			return prefixe(pref, trieHybride.getSup());
		}
		if (pref.length == 1) {
			if (trieHybride.getValeur() != null) {
				return 1 + comptageMots(trieHybride.getEq());
			}
			return comptageMots(trieHybride.getEq());
		}
		return prefixe(Utils.copierSaufPremLett(pref), trieHybride.getEq());
	}

	/**
	 * @param mot
	 *            Mot a supprimer
	 * @param trieHybride
	 *            Trie dans lequel on veut supprimer
	 * @return le trie dans lequel on a supprime le mot
	 */
	public static TrieHybride supprimerMot(String mot, TrieHybride trieHybride) {
		if (!chercherMot(mot, trieHybride)) {
			return trieHybride;
		}
		char[] tabMot = mot.toCharArray();
		return supprimerMot(tabMot, trieHybride);
	}

	/**
	 * @param mot
	 *            Mot a supprimer
	 * @param trieHybride
	 *            Trie dans lequel on veut supprimer
	 * @return le trie dans lequel on a supprime le mot
	 */
	private static TrieHybride supprimerMot(char[] mot, TrieHybride trieHybride) {
		if (trieHybride == null) {
			return null;
		}
		if (mot.length == 0) {
			return trieHybride;
		}
		char lettre = trieHybride.getCle();

		if (mot[0] < lettre) {
			return new TrieHybride(lettre, supprimerMot(mot,
					trieHybride.getInf()), trieHybride.getEq(),
					trieHybride.getSup(), trieHybride.getValeur());
		}
		if (mot[0] > lettre) {
			return new TrieHybride(lettre, trieHybride.getInf(),
					trieHybride.getEq(),
					supprimerMot(mot, trieHybride.getSup()),
					trieHybride.getValeur());
		}

		if (mot.length == 1) {
			if (trieHybride.getEq() == null) {
				return fusion(trieHybride.getInf(), trieHybride.getSup());
			}
			trieHybride.setValeur(null);
			return trieHybride;
		}
		TrieHybride trie2 = supprimerMot(Utils.copierSaufPremLett(mot),
				trieHybride.getEq());
		if (trie2 == null) {
			return fusion(trieHybride.getInf(), trieHybride.getSup());
		}
		trieHybride.setEq(trie2);
		return trieHybride;

	}

	/**
	 * @param trieHybride
	 *            Un trie hybride
	 * @return la liste des mots contenus dans l'arbre
	 */
	public static ArrayList<String> listeMots(TrieHybride trieHybride) {
		ArrayList<String> liste = listeMots("", trieHybride);
		Collections.sort(liste);
		return liste;
	}

	/**
	 * @param act
	 *            pointeur
	 * @param trieHybride
	 *            Un trie hybride
	 * @return la liste des mots contenus dans l'arbre
	 */
	private static ArrayList<String> listeMots(String act,
			TrieHybride trieHybride) {
		ArrayList<String> liste = new ArrayList<String>();
		if (trieHybride == null) {
			return liste;
		}
		if (trieHybride.getValeur() != null) {
			liste.add(act + trieHybride.getCle());
		}
		liste.addAll(listeMots(act, trieHybride.getInf()));
		liste.addAll(listeMots(act + trieHybride.getCle(), trieHybride.getEq()));
		liste.addAll(listeMots(act, trieHybride.getSup()));
		return liste;
	}

	/**
	 * Fonction utilisée lors de la suppression d'un mot
	 * 
	 * @param th1
	 * @param th2
	 * @return la fusion de th1 et th2
	 */
	private static TrieHybride fusion(TrieHybride th1, TrieHybride th2) {
		if (th1 == null) {
			return th2;
		}
		if (th2 == null) {
			return th1;
		}

		if (th2.getCle() < th1.getCle()) {
			return new TrieHybride(th1.getCle(), fusion(th1.getInf(), th2),
					th1.getEq(), th1.getSup(), th1.getValeur());
		}

		if (th2.getCle() > th1.getCle()) {
			return new TrieHybride(th1.getCle(), th1.getInf(), th1.getEq(),
					fusion(th1.getSup(), th2), th1.getValeur());
		}

		Integer val = null;
		if (th1.getValeur() != null) {
			val = th1.getValeur();
		} else if (th2.getValeur() != null) {
			val = th2.getValeur();
		}
		return new TrieHybride(th1.getCle(),
				fusion(th1.getInf(), th2.getInf()), fusion(th1.getEq(),
						th2.getEq()), fusion(th1.getInf(), th2.getInf()), val);
	}

	/**
	 * @param trie
	 * @return
	 */
	private static int nbInf(TrieHybride trie) {
		if (trie == null) {
			return 0;
		}
		int n = 1 + nbInf(trie.getInf());
		return n;
	}

	/**
	 * @param trie
	 * @return
	 */
	private static int nbSup(TrieHybride trie) {
		if (trie == null) {
			return 0;
		}
		int n = 1 + nbSup(trie.getSup());
		return n;
	}

	/**
	 * @param trie
	 *            Trie hybride a equilibrer
	 * @return trie equilibre
	 */
	private static TrieHybride equilibrer(TrieHybride trie) {
		if (trie == null) {
			return null;
		}
		int nbInf = nbInf(trie.getInf());
		int nbSup = nbSup(trie.getSup());
		if (Math.abs(nbInf - nbSup) <= 1) {
			return trie;
		}
		TrieHybride trie2 = null;
		if (nbInf > nbSup) {
			trie2 = trie.getInf();
			trie.setInf(trie2.getSup());
			trie2.setSup(trie);

		} else if (nbSup > nbInf) {
			trie2 = trie.getSup();
			trie.setSup(trie2.getInf());
			trie2.setInf(trie);
		}
		return trie2;

	}

	/**
	 * @param phrase
	 *            Phrase a inserer
	 * @param trieHybride
	 *            Trie dans lequel on insere
	 * @return L'arbre apres insertion de tous les mots de la phrase
	 */
	public static TrieHybride insererPhrase(String phrase,
			TrieHybride trieHybride) {
		String resultString = phrase.replaceAll("[^\\x00-\\x7F]", "");
		String[] tabMots = resultString.split(" ");
		for (String mot : tabMots) {
			trieHybride = inserer(mot, trieHybride, TrieHybride.marquerFinMot());
		}
		return trieHybride;
	}

}
