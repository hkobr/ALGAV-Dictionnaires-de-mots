package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import triehybride.TrieHybride;
import triehybride.Hybride;
import briandais.ArbreBriandais;
import briandais.Briandais;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class UtilsTest {

	public static void main(String[] args) {
		File dir = new File("data");

		long avant1 = System.currentTimeMillis();
		ArrayList<String> listeMots = Utils.readAllFiles(dir);
		long apres1 = System.currentTimeMillis();
		long temps1 = apres1 - avant1;
		/*
		 * HashSet<String> hs = new HashSet<String>(); hs.addAll(listeMots);
		 * listeMots.clear(); listeMots.addAll(hs);
		 */
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

		System.out.println("Mots inseres : " + TrieHybride.getMotInseres());

		boolean tousPresents = true;
		avant2 = System.currentTimeMillis();
		for (String mot : listeMots) {
			tousPresents &= Briandais.chercherMot(mot, briandais);
		}
		apres2 = System.currentTimeMillis();
		temps2 = apres2 - avant2;
		System.out.println("Temps de recherche Briandais : " + temps2
				+ " ms\nNombre de mots briandais : "
				+ Briandais.comptageMots(briandais)
				+ "\nTous les mots sont presents Briandais: " + tousPresents);

		tousPresents = true;
		avant3 = System.currentTimeMillis();
		for (String mot : listeMots) {
			// System.out.println("Insertion : "+mot);
			tousPresents &= Hybride.chercherMot(mot, trieHybride);
		}
		apres3 = System.currentTimeMillis();
		temps3 = apres3 - avant3;
		System.out
				.println("Temps de recherche TrieHybride : " + temps3
						+ " ms\nNombre de mots TrieHybride : "
						+ Hybride.comptageMots(trieHybride)
						+ "\nTous les mots sont presents TrieHybride : "
						+ tousPresents);

		System.out.println("Hauteur Briandais : "
				+ Briandais.hauteur(briandais));
		System.out.println("Hauteur TrieHybride : "
				+ Hybride.hauteur(trieHybride));
		
		System.out.println("Profondeur moyenne Briandais : "
				+ Briandais.profondeurMoyenne(briandais));
		System.out.println("Profondeur moyenne TrieHybride : "
				+ Hybride.profondeurMoyenne(trieHybride));
		

		avant2 = System.currentTimeMillis();
		for (String mot : listeMots) {
			briandais = Briandais.supprimerMot(mot, briandais);
		}
		apres2 = System.currentTimeMillis();
		temps2 = apres2 - avant2;
		System.out.println("Temps de suppression Briandais : " + temps2
				+ " ms\nNombre de mots briandais : "
				+ Briandais.comptageMots(briandais));
		if (briandais == null) {
			System.out.println("Briandais  null");
		}

		tousPresents = true;
		avant3 = System.currentTimeMillis();
		for (String mot : listeMots) {
			// System.out.println("Insertion : "+mot);
			trieHybride = Hybride.supprimerMot(mot, trieHybride);
		}
		apres3 = System.currentTimeMillis();
		temps3 = apres3 - avant3;
		System.out.println("Temps de suppression TrieHybride : " + temps3
				+ " ms\nNombre de mots TrieHybride : "
				+ Hybride.comptageMots(trieHybride));
		if (trieHybride == null) {
			System.out.println("TrieHybride  null");
		}

	}

}