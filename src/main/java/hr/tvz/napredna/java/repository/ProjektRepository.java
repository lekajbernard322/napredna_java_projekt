package hr.tvz.napredna.java.repository;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjektRepository extends JpaRepository<Projekt, Integer> {

    List<Projekt> findAllByKorisnici(Korisnik korisnik);

}
