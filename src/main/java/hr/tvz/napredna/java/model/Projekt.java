package hr.tvz.napredna.java.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;


@Data
@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String ime;
    private String opis;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
	@JoinTable(
			name = "korisnik_projekt",
			joinColumns = { @JoinColumn(name = "korisnik_id") },
			inverseJoinColumns = { @JoinColumn(name = "projekt_id") })
    private Set<Korisnik> korisnici = new HashSet<>();



    public int getId() {
        return id;
    }

    public void setId(int id) {
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
