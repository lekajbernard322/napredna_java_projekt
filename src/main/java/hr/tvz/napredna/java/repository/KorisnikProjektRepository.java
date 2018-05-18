package hr.tvz.napredna.java.repository;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.KorisnikProjekt;
import org.springframework.data.jpa.repository.JpaRepository;


public interface KorisnikProjektRepository extends JpaRepository<KorisnikProjekt, Integer> {

}
