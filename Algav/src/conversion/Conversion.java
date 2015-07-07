package conversion;

import triehybride.TrieHybride;
import briandais.ArbreBriandais;

/**
 * Fonctions de conversion de trie hybride vers briandais et inversement
 * 
 * @author KOBROSLI Hassan 3001993
 * 
 */
public class Conversion {

	/**
	 * @param trieHybride
	 *            trie hybride a convertir
	 * @return resultat de la conversion
	 */
	public static ArbreBriandais trieHybrideToBriandais(TrieHybride trieHybride) {
		return trieHybrideToBriandais(trieHybride, null);
	}

	/**
	 * @param trieHybride
	 *            trie hybride a convertir
	 * @param briandais
	 *            briandais dans lequel il faut insérer les noeuds
	 * @return resultat de la conversion
	 */
	private static ArbreBriandais trieHybrideToBriandais(
			TrieHybride trieHybride, ArbreBriandais briandais) {
		if (trieHybride == null) {
			return briandais;
		}

		// On instancie un nouvel arbre de la briandais
		if (briandais == null) {
			briandais = new ArbreBriandais(trieHybride.getCle(), null,
					trieHybrideToBriandais(trieHybride.getEq(), null));
			// On insere dans le noeud alternatif les noeuds inferieurs et
			// superieurs de trie hybride
			briandais.setAlternatif(trieHybrideToBriandais(
					trieHybride.getInf(), null));

			briandais.setAlternatif(trieHybrideToBriandais(
					trieHybride.getSup(), briandais.getAlternatif()));
			// Si fin mot
			if (trieHybride.getValeur() != null) {
				briandais.setSuivant(new ArbreBriandais('\0', briandais
						.getSuivant(), null));
			}
			return briandais;
		}

		if (trieHybride.getCle() == briandais.getLettre()) {
			if (trieHybride.getValeur() != null) {
				briandais.setSuivant(new ArbreBriandais('\0', briandais
						.getSuivant(), null));
			}
			briandais.setSuivant(trieHybrideToBriandais(trieHybride.getEq(),
					briandais.getSuivant()));
			briandais.setAlternatif(trieHybrideToBriandais(
					trieHybride.getInf(), briandais.getAlternatif()));
			briandais.setAlternatif(trieHybrideToBriandais(
					trieHybride.getSup(), briandais.getAlternatif()));
			return briandais;
		}

		briandais.setAlternatif(trieHybrideToBriandais(trieHybride,
				briandais.getAlternatif()));
		return briandais;

	}

	/**
	 * @param briandais
	 *            arbre a convertir
	 * @return resultat de la conversion
	 */
	public static TrieHybride briandaisToTrieHybride(ArbreBriandais briandais) {
		return briandaisToTrieHybride(briandais, null);
	}

	/**
	 * @param briandais
	 *            arbre a convertir
	 * @param trieHybride
	 *            trie dans lequel il faut inserer les noeuds
	 * @return resultat de la conversion
	 */
	private static TrieHybride briandaisToTrieHybride(ArbreBriandais briandais,
			TrieHybride trieHybride) {
		if (briandais == null) {
			return trieHybride;
		}
		if (briandais.getLettre() == '\0') {
			return briandaisToTrieHybride(briandais.getAlternatif(),
					trieHybride);
		}
		if (trieHybride == null) {
			trieHybride = new TrieHybride(briandais.getLettre(), null, null,
					null, null);
		}

		if (briandais.getLettre() < trieHybride.getCle()) {
			trieHybride.setInf(briandaisToTrieHybride(briandais,
					trieHybride.getInf()));
			return trieHybride;
		} else if (briandais.getLettre() > trieHybride.getCle()) {
			trieHybride.setSup(briandaisToTrieHybride(briandais,
					trieHybride.getSup()));
			return trieHybride;
		}

		ArbreBriandais tmpBriandais = briandais.getSuivant();
		if (tmpBriandais != null) {
			if (tmpBriandais.getLettre() == '\0') {
				trieHybride.setValeur(TrieHybride.marquerFinMot());
			} else
				while ((tmpBriandais = tmpBriandais.getAlternatif()) != null) {
					if (tmpBriandais.getLettre() == '\0') {
						trieHybride.setValeur(TrieHybride.marquerFinMot());
						break;
					}
				}
		}
		trieHybride = briandaisToTrieHybride(briandais.getAlternatif(),
				trieHybride);
		trieHybride.setEq(briandaisToTrieHybride(briandais.getSuivant(),
				trieHybride.getEq()));
		return trieHybride;

	}

}
