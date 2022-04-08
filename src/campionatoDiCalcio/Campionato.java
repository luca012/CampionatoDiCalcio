package campionatoDiCalcio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe per la gestione di un Campionato di calcio dato il file CSV delle
 * partite giocate
 * 
 * @author Andrea Bucelli, Luca Lorenzi, Kevin Hu, Sohana Ahsan
 * @version versione 1.0 del 01/04/2022
 *
 */

public class Campionato {

	private String serie;
	private String stagione;
	private BufferedReader br;
	private Map<Integer, List<Partita>> partite_giornate;
	private Map<String, List<Partita>> partite_squadre;
	private List<Squadra> classifica;

	/**
	 * Costruttore
	 * 
	 * @param serie    Il nome della serie del campionato
	 * @param stagione Periodo (anno-anno+1) in cui si tiene il campionato
	 */

	public Campionato(String serie, String stagione) {
		this.serie = serie;
		this.stagione = stagione;
	}

	/**
	 * Costruttore di copia
	 * 
	 * @param c oggetto campionato
	 */

	public Campionato(Campionato c) {
		this.serie = c.serie;
		this.stagione = c.stagione;
	}

	/**
	 * Funzione che calcola il punteggio della squadre e popola l'ArrayList
	 * <b>classifica</b>
	 */

	private void calcola_punteggio() {
		classifica = new ArrayList<>();
		for (Map.Entry<String, List<Partita>> elemento : partite_squadre.entrySet()) {
			Squadra s = new Squadra(elemento.getKey());
			classifica.add(s);
			for (Partita p : elemento.getValue()) {
				if ((p.squadra_casa.equals(s.getNome())) && (p.punteggio.charAt(0) > p.punteggio.charAt(2))
						|| (p.squadra_ospite.equals(s.getNome())) && (p.punteggio.charAt(2) > p.punteggio.charAt(0))) {
					s.setPunti((s.getPunti() + 3));
					s.setVittorie((s.getVittorie() + 1));
				} else if (p.punteggio.charAt(2) == p.punteggio.charAt(0)) {
					s.setPunti((s.getPunti() + 1));
					s.setPareggi((s.getPareggi() + 1));
				} else {
					s.setSconfitte((s.getSconfitte() + 1));
				}
			}
		}
	}

	/**
	 * Questo metodo inizializza la lista delle partite <b>partite</b>, la HashMap
	 * <b>partite_giornate</b> e la TreeMap <b>partite_squadre</b> e le popola.
	 * 
	 * @param filename nome del file CSV contenente le informazioni sulle partite
	 *                 giocate
	 */

	public void carica_dati(String filename) {
		List<Partita> partite = new ArrayList<>();
		partite_giornate = new HashMap<>();
		partite_squadre = new TreeMap<>();
		String data[] = new String[5];
		String line;
		try {
			br = new BufferedReader(new FileReader(filename));
			String header = br.readLine();
			while ((line = br.readLine()) != null) {
				data = line.split(",");
				// popola hash map
				if (partite_giornate.containsKey(Integer.parseInt(data[0]))) {
					partite = partite_giornate.get(Integer.parseInt(data[0]));
					partite.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
				} else {
					List<Partita> lista = new ArrayList<>();
					lista.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
					partite_giornate.put(Integer.parseInt(data[0]), lista);
				}
				// popola tree map
				if (partite_squadre.containsKey(data[2])) {
					partite = partite_squadre.get(data[2]);
					partite.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
				} else {
					List<Partita> lista = new ArrayList<>();
					lista.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
					partite_squadre.put(data[2], lista);
				}
				if (partite_squadre.containsKey(data[4])) {
					partite = partite_squadre.get(data[4]);
					partite.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
				} else {
					List<Partita> lista = new ArrayList<>();
					lista.add(new Partita(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]));
					partite_squadre.put(data[4], lista);
				}
			}
			br.close();
			aggiornaClassifica();
		} catch (FileNotFoundException e) {
			System.out.println("File non presente");
		} catch (IOException e) {
			System.out.println("Errore");
		}

	}

	/**
	 * Stampa tutte le partite che la squadra <b>nomeSquadra</b> ha giocato
	 * 
	 * @param nomeSquadra Nome della squadra di cui stampare le partite
	 */

	public void cercaSquadra(String nomeSquadra) {
		if (partite_squadre.containsKey(nomeSquadra)) {
			System.out.println("\n*** Partite giocate dalla squadra " + nomeSquadra + " ***\n");
			List<Partita> lista = partite_squadre.get(nomeSquadra);
			System.out.println("Giornata --- Data --- Squadra casa --- Risultato --- Squadra ospite");
			for (Partita p : lista) {
				System.out.println(p.toString());
			}
		}
	}

	/**
	 * Stampa tutte le partite giocate nella giornata <b>numGiornata</b>
	 * 
	 * @param numGiornata Numero della giornata di cui stampare le partite
	 */

	public void cercaGiornata(int numGiornata) {
		if (partite_giornate.containsKey(numGiornata)) {
			System.out.println("\n*** Partite giocate nella giornata " + numGiornata + " ***\n");
			List<Partita> lista = partite_giornate.get(numGiornata);
			System.out.println("\tData --- Squadra casa --- Risultato --- Squadra ospite");
			for (Partita p : lista) {
				System.out.println(p.toString());
			}
		}
	}

	/**
	 * Calcola il punteggio e ordina la classifica
	 */

	private void aggiornaClassifica() {
		calcola_punteggio();
		Collections.sort(this.classifica);
	}

	/**
	 * Crea un margine dopo la stringa <b>str</b> che aiuta a formattare il testo
	 * 
	 * @param str       Stringa da stampare
	 * @param larghezza Larghezza del margine
	 * @param ch        Carattere che forma il margine
	 */

	public static void formatta(String str, int larghezza, char ch) {
		System.out.print(str);
		for (int x = str.length(); x < larghezza; ++x) {
			System.out.print(ch);
		}
	}

	/**
	 * Stampa la classifica
	 */

	public void stampaClassifica() {
		System.out.println("\n *** Classifica " + this.serie + " stagione " + this.stagione + " ***\n");
		System.out.println("Squadra" + "\t\t\t\tPunti" + "\tV" + "\tP" + "\tS");
		for (Squadra s : classifica) {
			formatta(s.getNome(), 32, ' ');
			System.out.println(Integer.toString(s.getPunti()) + "\t" + Integer.toString(s.getVittorie()) + "\t"
					+ Integer.toString(s.getPareggi()) + "\t" + Integer.toString(s.getSconfitte()));
		}
	}

	/**
	 * @return la classifica convertita in una stringa
	 * 
	 *         Converte la classifica in una stringa
	 */
	private String classificaToString() {
		String out = "Squadra,Punti,V,P,S\n";
		for (Squadra s : classifica) {
			out += s.getNome() + "," + s.getPunti() + "," + Integer.toString(s.getVittorie()) + ","
					+ Integer.toString(s.getPareggi()) + "," + Integer.toString(s.getSconfitte()) + "\n";
		}
		return out;
	}

	/**
	 * Esporta la classifica in un file CSV
	 */

	public void esportaClassifica() {
		File f = new File("classificaPremier20192020.csv");
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(classificaToString());
			fw.close();
			System.out.print("\nClassifica esportata nel file classificaPremier20192020.csv");
		} catch (IOException e) {
			System.out.print("\nErrore nella scrittura del file");
		}
	}

}
