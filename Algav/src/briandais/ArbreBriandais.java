package briandais;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class ArbreBriandais extends Noeud {

	/**
	 * @param lettre Cle du noeud
	 * @param alternative Arbre alternatif
	 * @param suivante Arbre suivant
	 */
	public ArbreBriandais(char lettre, ArbreBriandais alternatif, ArbreBriandais suivant) {
		super(lettre, alternatif, suivant);
	}

}
