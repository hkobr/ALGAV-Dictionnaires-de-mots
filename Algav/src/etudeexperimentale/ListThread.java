package etudeexperimentale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import utils.Utils;
import briandais.ArbreBriandais;
import briandais.Briandais;

/**
 * Thread qui permet de remplir un arbre de la briandais en parallèle avec
 * d'autres
 * 
 * @author KOBROSLI Hassan 3001993
 * 
 */
public class ListThread implements Callable<ArbreBriandais> {
	ArrayList<String> listeMots;
	int threadNumber;

	/**
	 * @param fic
	 *            fichier a lire
	 * @param threadNumber
	 *            numero du thread
	 */
	public ListThread(File fic, int threadNumber) {
		try {
			listeMots = Utils.readFile(fic);

			this.threadNumber = threadNumber;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArbreBriandais call() throws Exception {

		ArbreBriandais arbre = null;
		for (String mot : listeMots) {
			arbre = Briandais.inserer(mot, arbre);
		}
		return arbre;
	}

}
