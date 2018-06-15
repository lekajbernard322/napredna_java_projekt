package hr.tvz.napredna.java.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KorisnikFormModel {
	
	private Integer id;
	
	@NotNull(message = "{forma.prazno}")
	@Size(min = 5, message = "{forma.korisnicko_ime.najmanje}")
	private String korisnickoIme;
	
	@NotNull(message = "{forma.prazno}")
	@Size(min = 2, message = "{forma.ime.najmanje}")
	private String ime;
	
	@NotNull(message = "{forma.prazno}")
	@Size(min = 2, message = "{forma.prezime.najmanje}")
	private String prezime;
	
	@NotNull(message = "{forma.prazno}")
	@Size(min = 8, message = "{forma.lozinka.najmanje}")
	private String lozinka;
	
	private String lozinkaPotvrda;
	
	public KorisnikFormModel() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getLozinkaPotvrda() {
		return lozinkaPotvrda;
	}

	public void setLozinkaPotvrda(String lozinkaPotvrda) {
		this.lozinkaPotvrda = lozinkaPotvrda;
	}
}
