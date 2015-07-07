package conversion;

import java.io.File;
import java.util.ArrayList;

import triehybride.TrieHybride;
import triehybride.Hybride;
import utils.Utils;
import briandais.ArbreBriandais;
import briandais.Briandais;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class ConversionTest {

	public static void main(String[] args) {
		File dir = new File("data");

		long avant1 = System.currentTimeMillis();
		ArrayList<String> listeMots = Utils.readAllFiles(dir);
		long apres1 = System.currentTimeMillis();
		long temps1 = apres1 - avant1;

		System.out.println("Fin de l'exécution\nNombre de mots :"
				+ listeMots.size() + "\nTemps de lecture : " + temps1 + " ms");

		ArbreBriandais briandais = null;
		long avant2 = System.currentTimeMillis();
		for (String mot : listeMots) {
			briandais = Briandais.inserer(mot, briandais);
		}
		long apres2 = System.currentTimeMillis();
		long temps2 = apres2 - avant2;
		System.out.println("Temps d'insertion Briandais : " + temps2
				+ " ms\nNombre de mots briandais : "
				+ Briandais.comptageMots(briandais));

		TrieHybride trieHybride = null;
		long avant3 = System.currentTimeMillis();
		for (String mot : listeMots) {
			// System.out.println("Insertion : "+mot);
			trieHybride = Hybride.inserer(mot, trieHybride, new Integer(
					TrieHybride.marquerFinMot()));
		}
		long apres3 = System.currentTimeMillis();
		long temps3 = apres3 - avant3;
		System.out.println("Temps d'insertion TrieHybride : " + temps3
				+ " ms\nNombre de mots TrieHybride : "
				+ Hybride.comptageMots(trieHybride));

		avant1 = System.currentTimeMillis();
		ArbreBriandais briandais2 = Conversion
				.trieHybrideToBriandais(trieHybride);
		apres1 = System.currentTimeMillis();
		temps1 = apres1 - avant1;

		avant2 = System.currentTimeMillis();
		TrieHybride trieHybride2 = Conversion
				.briandaisToTrieHybride(briandais);
		apres2 = System.currentTimeMillis();
		temps2 = apres2 - avant2;

		System.out.println("Conversion reussie vers briandais : "
				+ " Temps : "
				+ temps1 + " ms"+"\nNombre de mots briandais : "
				+ Briandais.comptageMots(briandais2));
		System.out.println("Conversion reussie vers trieHybride : "
				+" Temps : "
				+ temps2 + " ms"+"\nNombre de mots TrieHybride : "
				+ Hybride.comptageMots(trieHybride2));

	}

}
