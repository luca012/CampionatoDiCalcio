package campionatoDiCalcio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	private static Scanner input = new Scanner(System.in);

	public static int menu() {
		int scelta;
		System.out.println("\n *** Gestione CSV Campionato di calcio *** ");
		System.out.println("1. Stampa classifica");
		System.out.println("2. Visualizza partite di una squadra");
		System.out.println("3. Visualizza partite di una giornata");
		System.out.println("4. Esporta classifica");
		System.out.println("0. Fine programma");
		System.out.print("Opzione scelta: ");
		scelta = input.nextInt();
		input.nextLine();
		return scelta;
	}

	public static void main(String[] args) {
		Campionato c = new Campionato("Premier League", "2019-2020");
		c.carica_dati("league2020.csv");
		int scelta;
		do {
			scelta = menu();
			switch (scelta) {
			case 1:
				c.stampaClassifica();
				break;
			case 2:
				System.out.print("\nInserire il nome di una squadra: ");
				String nomeSquadra = input.nextLine();
				c.stampaPartiteSquadra(nomeSquadra);
				break;
			case 3:
				System.out.print("\nInserire il numero di una giornata: ");
				int numGiornata = input.nextInt();
				c.stampaPartiteGiornata(numGiornata);
				break;
			case 4:
				c.esportaClassifica();
			}
		} while (scelta != 0);
	}
}
