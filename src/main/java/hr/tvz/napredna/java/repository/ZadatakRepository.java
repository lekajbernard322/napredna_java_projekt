package hr.tvz.napredna.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.model.Zadatak;

public interface ZadatakRepository extends JpaRepository<Zadatak, Integer> {

    List<Zadatak> findAllByAssignee(Korisnik korisnik);
    
}