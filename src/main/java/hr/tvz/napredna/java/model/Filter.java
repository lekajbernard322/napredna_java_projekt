package hr.tvz.napredna.java.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Filter  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Column(nullable = false)
    private String ime;
    private String opis;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="korisnik")
    private Korisnik korisnik;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "filter_projekt",
            joinColumns = { @JoinColumn(name = "projekt") },
            inverseJoinColumns = { @JoinColumn(name = "filter_id") })
    private Set<Projekt> projekti = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "filter_korisnik",
            joinColumns = { @JoinColumn(name = "filter_id") },
            inverseJoinColumns = { @JoinColumn(name = "korisnik_id") })
    private Set<Korisnik> korisnici = new HashSet<>();

}
