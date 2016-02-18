package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author KOBROSLI Hassan 3001993
 *
 */
public class Utils {

	/**
	 * @param mot
	 *            Un mot
	 * @return le mot sans sa première lettre
	 */
	public static char[] copierSaufPremLett(char[] mot) {
		char[] mot2 = new char[mot.length - 1];
		for (int i = 0; i < mot2.length; i++) {
			mot2[i] = mot[i + 1];
		}
		return mot2;
	}

	/**
	 * @param dir
	 *            le dossier qui contient les fichiers a lire
	 * @return la liste des mots de tous les fichiers du dossier
	 */
	public static ArrayList<String> readAllFiles(File dir) {
		ArrayList<String> liste = new ArrayList<>();
		if (dir.isDirectory()) {
			File[] tabFiles = dir.listFiles();
			if (tabFiles != null)
				for (File tmp : tabFiles) {
					if (tmp.isFile()) {
						try {
							liste.addAll(readFile(tmp));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		}

		return liste;
	}

	/**
	 * @param fin
	 *            fichier a lire
	 * @return la liste des mots du fichier
	 * @throws IOException
	 *             erreur de lecture
	 */
	public static ArrayList<String> readFile(File fin) throws IOException {
		ArrayList<String> liste = new ArrayList<>();

		FileInputStream fis = new FileInputStream(fin);

		// Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		String line = null;
		while ((line = br.readLine()) != null) {
			liste.add(line);
		}

		br.close();
		fis.close();

		return liste;
	}

}
