package hr.tvz.napredna.java.repository;

import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FilterRepository extends JpaRepository<Filter, Integer> {

	List<Filter> findAllByKorisnik(Korisnik korisnik);
    //List<Filter> findAllByprojekt(Projetk projekt);
}
