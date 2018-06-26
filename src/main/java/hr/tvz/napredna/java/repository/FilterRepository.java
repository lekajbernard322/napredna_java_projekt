package hr.tvz.napredna.java.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.Korisnik;

@RepositoryRestResource(collectionResourceRel = "filteri", path = "filteri")
public interface FilterRepository extends JpaRepository<Filter, Integer> {

	List<Filter> findAllByKorisnik(Korisnik korisnik);
	
	Optional<Filter> findByIdAndKorisnik(Integer id, Korisnik korisnik);
}
