package hr.tvz.napredna.java.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "korisnik")
public class Korisnik implements Serializable{
	
	private static final long serialVersionUID = 8514167339765934175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(min = 5, max = 50)
	@Column(length = 50, nullable = false)
	private String korisnickoIme;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(length = 50, nullable = false)
	private String ime;
	
	@NotNull
	@Size(min = 2, max = 50)
	@Column(length = 50, nullable = false)
	private String prezime;
	
	@NotNull
	@Size(min = 8, max = 120)
	@Column(length = 120, nullable = false)
	private String lozinka;
	
	@NotNull
	private Boolean aktivan = Boolean.TRUE;
	
	@ElementCollection
	@CollectionTable(
			name = "korisnik_uloga", 
			joinColumns = @JoinColumn(
					name="korisnik", 
					referencedColumnName="korisnickoIme"))
	@Column(name = "uloga")
	private Set<String> uloge = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "korisnik_projekt",
			joinColumns = { @JoinColumn(name = "korisnik_id") },
			inverseJoinColumns = { @JoinColumn(name = "projekt") })
	private Set<Projekt> projekti = new HashSet<>();
	
	public Korisnik() {
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

	public Boolean getAktivan() {
		return aktivan;
	}

	public void setAktivan(Boolean aktivan) {
		this.aktivan = aktivan;
	}

	public Set<String> getUloge() {
		return uloge;
	}

	public void setUloge(Set<String> uloge) {
		this.uloge = uloge;
	}

	public Set<Projekt> getProjekti() {
		return projekti;
	}

	public void setProjekti(Set<Projekt> projekti) {
		this.projekti = projekti;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Korisnik))
			return false;
		
		Korisnik k = (Korisnik) obj;
		return k.id == id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, korisnickoIme);
	}
	
}
