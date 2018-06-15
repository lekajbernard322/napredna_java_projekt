package hr.tvz.napredna.java.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Komentar {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Size(max = 500)
	@Column(length = 500)
    private String tekst;
	
	@ManyToOne
	@JoinColumn(name = "zadatak")
	private Zadatak zadatak;
	
	@ManyToOne
	@JoinColumn(name = "korisnik")
	private Korisnik autor;
	
	@NotNull
	@Column(nullable = false)
    private Timestamp vrijeme;
	
	public Komentar() { }
	
	public Komentar(String tekst, Zadatak zadatak, Korisnik autor, Timestamp vrijeme) {
		this.tekst = tekst;
		this.zadatak = zadatak;
		this.autor = autor;
		this.vrijeme = vrijeme;
	}
}
