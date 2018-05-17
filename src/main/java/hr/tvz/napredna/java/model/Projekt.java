package hr.tvz.napredna.java.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//TODO mapiranje korisnika na projekt

@Data
@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String ime;
    private String opis;

    @ManyToMany
    private Set<Projekt> projekti = new HashSet<>();

}
