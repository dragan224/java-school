package entities;

import java.io.Serializable;

public class Kupovine implements Serializable {

	private static final long serialVersionUID = -6301139363859113888L;
	
	public Kupovine(int korisnik_id, int proizvod_id, int kolicina) {
		super();
		this.korisnik_id = korisnik_id;
		this.proizvod_id = proizvod_id;
		this.kolicina = kolicina;
	}
	public int getKorisnik_id() {
		return korisnik_id;
	}
	public void setKorisnik_id(int korisnik_id) {
		this.korisnik_id = korisnik_id;
	}
	public int getProizvod_id() {
		return proizvod_id;
	}
	public void setProizvod_id(int proizvod_id) {
		this.proizvod_id = proizvod_id;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	private int korisnik_id;
	private int proizvod_id;
	private int kolicina;

}
