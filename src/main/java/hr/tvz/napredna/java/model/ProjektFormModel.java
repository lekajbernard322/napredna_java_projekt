package hr.tvz.napredna.java.model;

import java.util.HashSet;
import java.util.Set;

public class ProjektFormModel {
	
	private Integer id;
	
	private String ime;
	
	private String opis;
	
	private Set<Korisnik> korisnici = new HashSet<>();
	
	public ProjektFormModel() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Set<Korisnik> getKorisnici() {
		return korisnici;
	}

	public void setKorisnici(Set<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
	
	
}
