package hr.tvz.napredna.java.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @ManyToMany(mappedBy="projekti")
    private Set<Korisnik> korisnici = new HashSet<>();

}
