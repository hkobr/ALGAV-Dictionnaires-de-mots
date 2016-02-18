package etudeexperimentale;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import utils.Utils;
import briandais.ArbreBriandais;
import briandais.Briandais;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class ParallelTest {

	public static void main(String[] args) {

		File dir = new File("data");
		File[] filesList = dir.listFiles();

		ArbreBriandais arbreFusion = null;

		long avant1 = System.currentTimeMillis();

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<ArbreBriandais>> list = new ArrayList<Future<ArbreBriandais>>();
		Callable<ArbreBriandais> callable;
		if (filesList != null)
			for (int i = 0; i < filesList.length; i++) {
				callable = new ListThread(filesList[i], i);
				Future<ArbreBriandais> future = executor.submit(callable);
				list.add(future);
			}
		for (Future<ArbreBriandais> fut : list) {
			try {
				arbreFusion = Briandais.fusion(arbreFusion, fut.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		long apres1 = System.currentTimeMillis();
		executor.shutdown();

		long temps1 = apres1 - avant1;

		System.out
				.println("Nombre de mots arbreFusion :"
						+ Briandais.comptageMots(arbreFusion)
						+ "\nTemps de lecture + insertion + fusion : " + temps1
						+ " ms");

		ArbreBriandais arbre = null;
		avant1 = System.currentTimeMillis();
		ArrayList<String> listeMots = Utils.readAllFiles(dir);
		apres1 = System.currentTimeMillis();
		temps1 = apres1 - avant1;
		System.out.println("Temps de lecture : " + temps1 + " ms");

		long avant2 = System.currentTimeMillis();
		int cpt = 0;
		for (String mot : listeMots) {
			arbre = Briandais.inserer(mot, arbre);
			cpt++;
		}

		apres1 = System.currentTimeMillis();
		temps1 = apres1 - avant1;
		long temps2 = apres1 - avant2;

		System.out.println("Nombre de mots insérés : " + cpt);

		System.out.println("Nombre de mots arbre :"
				+ Briandais.comptageMots(arbre)
				+ "\nTemps de lecture + insertion : " + temps1 + " ms"
				+ "\nTemps d'ajout :" + temps2 + " ms\nFin de l'execution");

	}
}
