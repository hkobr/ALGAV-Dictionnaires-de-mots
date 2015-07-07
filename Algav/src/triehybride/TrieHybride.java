package triehybride;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class TrieHybride extends Noeud {

	public TrieHybride(char cle, TrieHybride inf, TrieHybride eq,
			TrieHybride sup, Integer valeur) {
		super(cle, inf, eq, sup, valeur);
	}
	
	public TrieHybride(TrieHybride th){
		super(th.getCle(), th.getInf(), th.getEq(), th.getSup(), th.getValeur());
	}
	
	

}
