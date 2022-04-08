package campionatoDiCalcio;

public class Partita {
	int giornata;
	String data;
	String squadra_casa;
	String punteggio;
	String squadra_ospite;
	
	public Partita(Partita p) {
		this.giornata = p.giornata;
		this.data = p.data;
		this.squadra_casa = p.squadra_casa;
		this.punteggio = p.punteggio;
		this.squadra_ospite = p.squadra_ospite;
	}
	
	public Partita(int giornata, String data, String squadra_casa, String punteggio, String squadra_ospite) {
		this.giornata = giornata;
		this.data = data;
		this.squadra_casa = squadra_casa;
		this.punteggio = punteggio;
		this.squadra_ospite = squadra_ospite;
	}
	
	@Override
	public String toString() {
		return giornata + " | " + data + " | " + squadra_casa
				+ " | " + punteggio + " | " + squadra_ospite;
	}
	
}