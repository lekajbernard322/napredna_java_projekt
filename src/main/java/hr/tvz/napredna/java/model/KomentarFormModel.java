package hr.tvz.napredna.java.model;

import javax.validation.constraints.NotNull;

public class KomentarFormModel {
	
	private Integer id;
	
	private Korisnik autor;
	
	private Zadatak zadatak;
	
	@NotNull
	private String tekst;
	
	private Integer zadatakId;
	
	public KomentarFormModel() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Korisnik getAutor() {
		return autor;
	}

	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}

	public Zadatak getZadatak() {
		return zadatak;
	}

	public void setZadatak(Zadatak zadatak) {
		this.zadatak = zadatak;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public Integer getZadatakId() {
		return zadatakId;
	}

	public void setZadatakId(Integer zadatakId) {
		this.zadatakId = zadatakId;
	}
}
