package triehybride;

import java.util.ArrayList;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class TrieHybrideTest {

	public static void main(String[] args) {
		TrieHybride trieHybride = null;
		trieHybride = Hybride.inserer("test", trieHybride, 1);
		System.out.println("\"test\" est dans l'arbre : "
				+ Hybride.chercherMot("test", trieHybride));
		System.out.println("\"tests\" est dans l'arbre : "
				+ Hybride.chercherMot("tests", trieHybride));
		System.out.println("\"tes\" est dans l'arbre : "
				+ Hybride.chercherMot("tes", trieHybride));
		System.out.println("\"abc\" est dans l'arbre : "
				+ Hybride.chercherMot("abc", trieHybride));
		System.out.println("\"\" est dans l'arbre : "
				+ Hybride.chercherMot("", trieHybride));
		trieHybride = Hybride.inserer("tests", trieHybride, 2);
		trieHybride = Hybride.inserer("ceci", trieHybride, 3);
		trieHybride = Hybride.inserer("un", trieHybride, 4);
		System.out.println("Nombre de mots : "
				+ Hybride.comptageMots(trieHybride));
		System.out.println("Nombre de nil : "
				+ Hybride.comptageNil(trieHybride));
		System.out.println("Hauteur : " + Hybride.hauteur(trieHybride));
		
		System.out.println("Profondeur moyenne : "
				+ Hybride.profondeurMoyenne(trieHybride));
		System.out.println("\"t\" Prefixe : "
				+ Hybride.prefixe("t", trieHybride));
		System.out.println("\"test\" Prefixe : "
				+ Hybride.prefixe("test", trieHybride));

		ArrayList<String> mots = Hybride.listeMots(trieHybride);
		for (String tmp : mots) {
			System.out.println(tmp);
		}

		trieHybride = Hybride.supprimerMot("ceci", trieHybride);
		System.out.println("\"ceci\" est dans l'arbre : "
				+ Hybride.chercherMot("tes", trieHybride));

		TrieHybride trieHybride2 = Hybride.inserer("Deuxieme", null,
				TrieHybride.marquerFinMot());
		trieHybride2 = Hybride.inserer("Deuxiemes", trieHybride2,
				TrieHybride.marquerFinMot());

		System.out.println("Nombre de mots trieHybride avant fusion : "
				+ Hybride.comptageMots(trieHybride));
		System.out.println("Nombre de mots trieHybride2 avant fusion : "
				+ Hybride.comptageMots(trieHybride2));
		

		// trieHybride = Hybride.equilibrer(trieHybride);
		mots = Hybride.listeMots(trieHybride);
		for (String tmp : mots) {
			System.out.println(tmp);
		}

		String phrase = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";

		TrieHybride hybride3 = null;
		hybride3 = Hybride.insererPhrase(phrase, hybride3);

		System.out.println("Nombre mots differents dans la phrase : "
				+ Hybride.comptageMots(hybride3));
		for (String mot : Hybride.listeMots(hybride3))
			System.out.println(mot);
	}

}
