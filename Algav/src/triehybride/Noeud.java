package triehybride;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public abstract class Noeud {
	private static int finMot = 0;
	private static int motInseres = 0;

	private char cle;
	private TrieHybride inf;
	private TrieHybride eq;
	private TrieHybride sup;
	private Integer valeur;


	public Noeud(char cle, TrieHybride inf, TrieHybride eq, TrieHybride sup,
			Integer valeur) {
		super();
		this.cle = cle;
		this.inf = inf;
		this.eq = eq;
		this.sup = sup;
		this.valeur = valeur;
	}

	public void setEq(TrieHybride eq) {
		this.eq = eq;
	}

	public void setCle(char cle) {
		this.cle = cle;
	}

	public void setInf(TrieHybride inf) {
		this.inf = inf;
	}

	public void setSup(TrieHybride sup) {
		this.sup = sup;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}

	public char getCle() {
		return cle;
	}

	public TrieHybride getInf() {
		return inf;
	}

	public TrieHybride getEq() {
		return eq;
	}

	public TrieHybride getSup() {
		return sup;
	}

	public Integer getValeur() {
		return valeur;
	}

	public static int marquerFinMot() {
		motInseres++;
		return finMot++;

	}

	public static int getMotInseres() {
		return motInseres;
	}

}
