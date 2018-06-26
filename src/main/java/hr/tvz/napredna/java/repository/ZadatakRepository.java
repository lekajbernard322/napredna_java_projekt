package hr.tvz.napredna.java.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Projekt;
import hr.tvz.napredna.java.model.Zadatak;

@RepositoryRestResource(collectionResourceRel = "zadaci", path = "zadaci")
public interface ZadatakRepository extends JpaRepository<Zadatak, Integer> {

    List<Zadatak> findAllByAssignee(Korisnik korisnik);
    
    List<Zadatak> findAllByAssigneeInAndProjektIn(Collection<Korisnik> korisnici, Collection<Projekt> projekti);
    
    List<Zadatak> findAllByProjekt(Projekt projekt);
}