package hr.tvz.napredna.java.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Zadatak {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column(nullable = false)
    private String ime;
    
    private String opis;
    
    @Column(nullable = false)
    private String tip;
    
    @Column(nullable = false)
    private String prioritet;
    
    @Column(nullable = false)
    private String stanje;
    
    private int procjenaVremena;
    
    private Timestamp datumStvoren;
    
    private Timestamp datumUredivan;
    
    private Timestamp datumRjesen;
    
    @Column(nullable = false)
    private Timestamp datumOcekivano;
        
    @ManyToOne
	@JoinColumn(name = "reporter")
	private Korisnik reporter;
    
    @ManyToOne
	@JoinColumn(name = "assignee")
	private Korisnik assignee;
    
    @ManyToOne
	@JoinColumn(name = "projekt")
	private Projekt projekt;       
}
