package hr.tvz.napredna.java.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class KorisnikFormModel {
	
	private Integer id;
	
	@NotNull(message = "{validacija.not_null}")
	@Size(min = 5, message = "{validacija.size_5}")
	private String korisnickoIme;
	
	@NotNull(message = "{validacija.not_null}")
	@Size(min = 2, message = "{validacija.size_2}")
	private String ime;
	
	@NotNull(message = "{validacija.not_null}")
	@Size(min = 2, message = "{validacija.size_2}")
	private String prezime;
	
	@NotNull(message = "{validacija.not_null}")
	@Size(min = 8, message = "{validacija.size_8}")
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
