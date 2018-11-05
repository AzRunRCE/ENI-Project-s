package fr.eni.ecole.maximot1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Maximot1 {

	public static final int NB_MOTS = 22506;
	public static Random r = new Random();

	public static void main(String[] args) {
		char[] motATrouver = tirerMotAleatoirement();
		char[] motMelanger = melanger(motATrouver);
		System.out.print("Tirage de lettres : ");
		afficher(motMelanger);
		System.out.println("quel mot proposez-vous ?");
		Scanner s = new Scanner(System.in);
		char[] prop = s.nextLine().toUpperCase().toCharArray();
		s.close();
		if(bonnesLettres(motMelanger, prop)) {
			if(dansLeDico(prop)) {
				System.out.printf("Le mot est correct ! Il vous rapporte %d points.%n", prop.length);
			} else {
				System.out.println("Le mot n'est pas présent dans le dictionnaire");
			}
		} else {
			System.out.println("Lettre non autorisée !");
		}
		System.out.print("Le mot à trouver était ");
		afficher(motATrouver);
	}

	public static char[] tirerMotAleatoirement() {
		char[] mot = null;

		int numMot = r.nextInt(NB_MOTS);
		try (FileInputStream fichier = new FileInputStream("./dictionnaire.txt"); Scanner s = new Scanner(fichier)) {
			for (int i = 0; i < numMot; i++) {
				s.nextLine();
			}
			mot = s.nextLine().toUpperCase().toCharArray();
		} catch (IOException e) {
			System.out.println("Lecture impossible");
		}
		return mot;
	}

	public static char[] melanger(char[] motDansLOrdre) {
		char[] motMelange = new char[motDansLOrdre.length];
		int[] interdits = new int[motDansLOrdre.length];
		for (int i = 0; i < motDansLOrdre.length; i++) {
			int indiceAlea;
			boolean indiceDejaUtilise;
			do {
				indiceAlea = r.nextInt(motDansLOrdre.length);
				int j = 0;
				indiceDejaUtilise = false;
				while (j < i && !indiceDejaUtilise) {
					if (interdits[j] == indiceAlea)
						indiceDejaUtilise = true;
					j++;
				}
			} while (indiceDejaUtilise);
			motMelange[i] = motDansLOrdre[indiceAlea];
			interdits[i] = indiceAlea;
		}

		return motMelange;
	}

	public static void afficher(char[] mot) {
		for (int i = 0; i < mot.length; i++) {
			System.out.print(mot[i]);
		}
		System.out.println();
	}

	public static boolean bonnesLettres(char[] tirage, char[] prop) {
		boolean[] utilise = new boolean[tirage.length];
		for (int i = 0; i < tirage.length; i++)
			utilise[i] = false;
		boolean motOk = true;
		int i = 0;
		while (i < prop.length && motOk) {
			boolean trouve = false;
			int j = 0;
			while (j < tirage.length && !trouve) {
				if (tirage[j] == prop[i] && !utilise[j])
					trouve = true;
				else
					j++;
			}
			if (trouve) {
				utilise[j] = true;
			} else {
				motOk = false;
			}
			i++;
		}
		return motOk;
	}

	public static boolean dansLeDico(char[] prop) {
		boolean trouve = false;
		int i = 0;
		char[] mot;
		try (FileInputStream fichier = new FileInputStream("./dictionnaire.txt"); Scanner s = new Scanner(fichier)) {
			while (i < NB_MOTS && !trouve) {
				mot = s.nextLine().toUpperCase().toCharArray();
				if(mot.length == prop.length) {
					int j=0;
					while(j<mot.length && mot[j] == prop[j]) {
						j++;
					}
					if(j == mot.length)
						trouve = true;
				}
				i++;
			}
		} catch (IOException e) {
			System.out.println("Lecture impossible");
		}
		return trouve;
	}
}
