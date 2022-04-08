package campionatoDiCalcio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {
	
	public static void menu() {
		System.out.println("\n *** Gestione CSV Campionato di calcio *** ");
		System.out.println("1. Stampa classifica");
		System.out.println("2. Visualizza partite di una squadra");
		System.out.println("3. Visualizza partite di una giornata");
		System.out.println("4. Esporta classifica");
		System.out.println("0. Fine programma");
		System.out.print("Opzione scelta: ");
		//input.nextLine();
	}

	public static void main(String[] args) {
		Campionato c = new Campionato("Premier League", "2019-2020");
		c.carica_dati("league2020.csv");
		Input input = new Input();
		int scelta;
		do {
			menu();
			scelta = input.inputInt("");
			switch (scelta) {
			case 1:
				c.stampaClassifica();
				break;
			case 2:
				String nomeSquadra = input.inputString("\nInserire il nome di una squadra: ");
				c.stampaPartiteSquadra(nomeSquadra);
				break;
			case 3:
				int numGiornata = input.inputInt("\nInserire il numero della giornata (1-38): ");
				c.stampaPartiteGiornata(numGiornata);
				break;
			case 4:
				c.esportaClassifica();
			}
		} while (scelta != 0);
	}
}
