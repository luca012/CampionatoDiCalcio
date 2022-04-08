package campionatoDiCalcio;

public class Squadra implements Comparable<Squadra> {
	private String nome;
	private int punti = 0;
	private int vittorie = 0;
	private int pareggi = 0;
	private int sconfitte = 0;

	public Squadra(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public int getVittorie() {
		return vittorie;
	}

	public void setVittorie(int vittorie) {
		this.vittorie = vittorie;
	}

	public int getPareggi() {
		return pareggi;
	}

	public void setPareggi(int pareggi) {
		this.pareggi = pareggi;
	}

	public int getSconfitte() {
		return sconfitte;
	}

	public void setSconfitte(int sconfitte) {
		this.sconfitte = sconfitte;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	@Override
	public String toString() {
		return nome + "," + punti + "," + vittorie + "," + pareggi + "," + sconfitte ;
	}

	@Override
	public int compareTo(Squadra o) {
		// ORDINAMENTO DECRESCENTE PER LA CLASSIFICA
		return o.getPunti() - this.getPunti();
	}

}
