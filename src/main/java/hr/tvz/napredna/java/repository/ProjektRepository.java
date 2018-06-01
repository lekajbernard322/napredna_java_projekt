package hr.tvz.napredna.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;

public interface ProjektRepository extends JpaRepository<Projekt, Integer> {

    List<Projekt> findAllByKorisnici(Korisnik korisnik);
    List<Projekt> findAllByKorisnici_KorisnickoIme(String korisnickoIme);
    
}
