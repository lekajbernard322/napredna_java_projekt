package hr.tvz.napredna.java.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String ime;
    private String opis;

}
