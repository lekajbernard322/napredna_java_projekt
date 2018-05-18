package hr.tvz.napredna.java.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String ime;
    private String opis;

    @ManyToMany(mappedBy="projekti")
    private Set<Korisnik> korisnici = new HashSet<>();

}
