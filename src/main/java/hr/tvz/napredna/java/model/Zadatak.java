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
public class Zadatak {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@NotNull
	@Size(min = 5, max = 50)
	@Column(length = 50, nullable = false)
    private String ime;
    
	@Size(max = 500)
	@Column(length = 500)
    private String opis;
    
    private String tip;
    
    private String prioritet;
    
    private String stanje;
    
    private int procjenaVremena;
    
    @NotNull
	@Column(nullable = false)
    private Timestamp datumStvoren;
    
    private Timestamp datumUredivan;
    
    private Timestamp datumRjesen;
    
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
