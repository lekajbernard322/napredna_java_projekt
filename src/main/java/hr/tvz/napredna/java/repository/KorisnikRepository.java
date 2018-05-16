package hr.tvz.napredna.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.napredna.java.model.Korisnik;


public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	Korisnik findByKorisnickoIme(String korisnickoIme);
}
