package maintest;

import java.util.Scanner;

import conversion.ConversionTest;
import etudeexperimentale.ParallelTest;
import triehybride.TrieHybride;
import triehybride.TrieHybrideTest;
import utils.UtilsTest;
import briandais.BriandaisTest;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class MainTest {

	public static void main(String[] args) {
		System.out
				.println("Que voulez vous faire ?\n1 - Briandais test\n2 - Trie Hybride test\n3 - Test conversion\n4 - Shakespeare\n5 - Construction en parallele\n6 - Quitter");

		Scanner in = new Scanner(System.in);

		int i;

		while ((i = in.nextInt()) != 6) {
			switch (i) {
			case 1:
				BriandaisTest.main(new String[0]);
				break;
			case 2:
				TrieHybrideTest.main(new String[0]);
				break;
			case 3:
				ConversionTest.main(new String[0]);
				break;
			case 4:
				UtilsTest.main(new String[0]);
				break;
			case 5:
				ParallelTest.main(new String[0]);
				break;
			default:
				System.out
						.println("Erreur : veuillez entrer un nombre compris entre 1 et 6.");
				break;
			}

			System.out
					.println("Que voulez vous faire ?\n1 - Briandais test\n2 - Trie Hybride test\n3 - Test conversion\n4 - Shakespeare\n5 - Construction en parallele\n6 - Quitter");

		}
	}

}
