package hr.tvz.napredna.java.repository;

import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "filteri", path = "filteri")
public interface FilterRepository extends JpaRepository<Filter, Integer> {

	List<Filter> findAllByKorisnik(Korisnik korisnik);
}
