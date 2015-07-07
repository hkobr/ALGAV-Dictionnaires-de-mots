package briandais;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public abstract class Noeud {
	/**
	 * Cle de noeud
	 */
	private char lettre;
	/**
	 * Noeud alternatif
	 */
	private ArbreBriandais alternatif;
	/**
	 * Noeud suivant
	 */
	private ArbreBriandais suivant;

	/**
	 * @param lettre
	 * @param alternative
	 * @param suivante
	 */
	public Noeud(char lettre, ArbreBriandais alternative,
			ArbreBriandais suivante) {
		super();
		this.lettre = lettre;
		this.alternatif = alternative;
		this.suivant = suivante;
	}

	/**
	 * @return la cle du noeud
	 */
	public char getLettre() {
		return lettre;
	}

	/**
	 * @param lettre
	 *            cle a attribuer au noeud
	 */
	public void setLettre(char lettre) {
		this.lettre = lettre;
	}

	/**
	 * @return le noeud alternatif
	 */
	public ArbreBriandais getAlternatif() {
		return alternatif;
	}

	/**
	 * @param alternative
	 *            le noeud alternatif a attribuer a l'arbre actuel
	 */
	public void setAlternatif(ArbreBriandais alternative) {
		this.alternatif = alternative;
	}

	/**
	 * @return le noeud suivant
	 */
	public ArbreBriandais getSuivant() {
		return suivant;
	}

	/**
	 * @param suivante
	 *            le noeud suivant a attribuer a l'arbre actuel
	 */
	public void setSuivant(ArbreBriandais suivante) {
		this.suivant = suivante;
	}
	


}
