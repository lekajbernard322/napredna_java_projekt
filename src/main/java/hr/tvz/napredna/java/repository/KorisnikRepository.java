package hr.tvz.napredna.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hr.tvz.napredna.java.model.Korisnik;

@RepositoryRestResource(collectionResourceRel = "korisnici", path = "korisnici")
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	Korisnik findByKorisnickoIme(@Param("korisnickoIme") String korisnickoIme);
}
