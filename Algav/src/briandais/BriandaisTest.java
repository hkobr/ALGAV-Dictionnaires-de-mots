package briandais;

import java.util.ArrayList;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class BriandaisTest {

	public static void main(String[] args) {
		ArbreBriandais arbre = Briandais.inserer("test", null);
		arbre = Briandais.inserer("tante", arbre);
		if (arbre != null) {
			System.out.println(arbre);
			System.out.println(Briandais.chercherMot("tante", arbre));
			System.out.println(Briandais.chercherMot("test", arbre));
		}
		arbre = Briandais.supprimerMot("tante", arbre);
		arbre = Briandais.supprimerMot("test", arbre);
		System.out.println(Briandais.chercherMot("tante", arbre));
		System.out.println(Briandais.chercherMot("test", arbre));

		String phrase = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";

		arbre = Briandais.insererPhrase(phrase, arbre);
		String[] tabMots = phrase.split(" ");
		for (String mot : tabMots) {
			System.out.println(mot+" est dans l'arbre : "+Briandais.chercherMot(mot, arbre));
		}

		System.out.println("Nombre de mots = "+Briandais.comptageMots(arbre));
		System.out.println("Hauteur = "+Briandais.hauteur(arbre));
		System.out.println("Profondeur moyenne = "+Briandais.profondeurMoyenne(arbre));
		
		System.out.println("Prefixe dactylo : "+Briandais.prefixe("dactylo",  arbre));
		
		ArrayList<String> listeMots = Briandais.listeMots(arbre);
		for(String s : listeMots){
			System.out.println(s);
		}
		
		ArbreBriandais arbre2 = Briandais.inserer("test", null);
		arbre2 = Briandais.inserer("tante", arbre2);
		arbre2 = Briandais.inserer("un", arbre2);
		ArbreBriandais arbre3 = Briandais.fusion(arbre, arbre2);
		
		listeMots = Briandais.listeMots(arbre3);
		for(String s : listeMots){
			System.out.println(s);
		}
		
		
	}

}
