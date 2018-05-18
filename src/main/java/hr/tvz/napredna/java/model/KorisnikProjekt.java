package hr.tvz.napredna.java.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "korisnik_projekt")
public class KorisnikProjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(nullable = false)
    private Integer korisnik_id;
    @Column(nullable = false)
    private Integer projekt_id;

    public Integer getKorisnik_id() {
        return korisnik_id;
    }

    public Integer getProjekt_id() {
        return projekt_id;
    }


}
