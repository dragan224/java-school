package entities;

import java.io.Serializable;

public class Proizvod implements Serializable {
	
	private static final long serialVersionUID = -1684994277395778952L;
	
	public Proizvod(String id, String ime, String kolicina) {
		super();
		this.id = id;
		this.ime = ime;
		this.kolicina = kolicina;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getKolicina() {
		return kolicina;
	}
	public void setKolicina(String kolicina) {
		this.kolicina = kolicina;
	}


	String id;
	private String ime;
	private String kolicina;

}
